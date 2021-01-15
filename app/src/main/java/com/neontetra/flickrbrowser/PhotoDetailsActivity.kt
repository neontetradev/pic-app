package com.neontetra.flickrbrowser

import android.os.Bundle
import kotlinx.android.synthetic.main.content_photo_details.*

class PhotoDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)
        activateToolbar(true)

        val photo = intent.getSerializableExtra(PHOTO_TRANSFER)
        photo_author.text = photo.author
        photo_tags.text = photo.tags
        photo_title.text =


    }
}