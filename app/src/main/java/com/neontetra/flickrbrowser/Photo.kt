package com.neontetra.flickrbrowser

import android.util.Log
import java.io.IOException
import java.io.ObjectStreamException
import java.io.Serializable



class Photo(
    var title: String,
    var author: String,
    var author_id: String,
    var link: String,
    var tags: String,
    var image: String) : Serializable {

    companion object {
         const val serialVersionUID = 1L
    }

    override fun toString(): String {
        return "Photo(title='$title', author='$author', author_id='$author_id', link='$link', tags='$tags', image='$image')"
    }

    @Throws(IOException::class)
    private fun writeObject(out: java.io.ObjectOutputStream) {
        Log.d("Photo", "writeObject called")
        out.writeUTF(title)
        out.writeUTF(author)
        out.writeUTF(author_id)
        out.writeUTF(link)
        out.writeUTF(tags)
        out.writeUTF(image)
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(inStream: java.io.ObjectInputStream) {
        Log.d("Photo", "readObjectCalled")
        title = inStream.readUTF()
        author = inStream.readUTF()
        author_id = inStream.readUTF()
        link = inStream.readUTF()
        tags = inStream.readUTF()
        image = inStream.readUTF()
    }

    @Throws(ObjectStreamException::class)
    private fun readObjectNoData() {
        Log.d("Photo", "readObjectNoData called")
    }
}