package com.example.vlad.art_coral_test.adapter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v7.app.AppCompatActivity

class TakePickDino: AppCompatActivity() {
    var fileUri: Uri? = null


    private fun pickPhotoFromGallery() {
        val pickImageIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(pickImageIntent, AppConstants.PICK_PHOTO_REQUEST)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        if (resultCode == Activity.RESULT_OK
            && requestCode == AppConstants.TAKE_PHOTO_REQUEST) {
            //photo from camera
            //display the photo on the imageview
          //  imageView.setImageURI(fileUri)
        }else if(resultCode == Activity.RESULT_OK
            && requestCode == AppConstants.PICK_PHOTO_REQUEST){
            //photo from gallery
            fileUri = data?.data
            //imageView.setImageURI(fileUri)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}

object AppConstants{
    val TAKE_PHOTO_REQUEST: Int = 2
    val PICK_PHOTO_REQUEST: Int = 1
}