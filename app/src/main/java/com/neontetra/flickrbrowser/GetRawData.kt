package com.neontetra.flickrbrowser

import android.os.AsyncTask
import android.util.Log
import androidx.navigation.fragment.DialogFragmentNavigatorDestinationBuilder
import java.io.IOException
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL
import javax.security.auth.login.LoginException

enum class DownloadStatus {
    OK, IDLE, NOT_INITIALIZED, FAILED_OR_EMPTY, PERMISSIONS_ERROR, ERROR
}
class GetRawData(private val listener: OnDownloadComplete) : AsyncTask<String, Void, String>() {
    private val TAG = "GetRawData"
    private var downloadStatus = DownloadStatus.IDLE

    interface OnDownloadComplete{
        fun onDownloadComplete(data: String, status: DownloadStatus)
    }

    override fun onPostExecute(result: String) {
        Log.d(TAG, "onPostExecute called")
        listener.onDownloadComplete(result, downloadStatus)
    }

    override fun doInBackground(vararg params: String?): String {
        if (params[0] == null) {
            downloadStatus = DownloadStatus.NOT_INITIALIZED
            return "No URL entered"
        }
        try {
            downloadStatus = DownloadStatus.OK
            return URL(params[0]).readText()
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is MalformedURLException -> {
                    downloadStatus = DownloadStatus.NOT_INITIALIZED
                    "doInBackground: Invalid URL ${e.message}"
                }
                is IOException -> {
                    downloadStatus = DownloadStatus.FAILED_OR_EMPTY
                    "doInBackground: IO Exception reading data ${e.message}"
                }
                is SecurityException -> {
                    downloadStatus = DownloadStatus.PERMISSIONS_ERROR
                    "doInBackground: Security exception: Needs permission? ${e.message}"
                }
                else -> {
                    downloadStatus = DownloadStatus.ERROR
                    "Unknown error: ${e.message}"
                }

            }
            Log.e(TAG, errorMessage)

            return errorMessage
        }
    }
}