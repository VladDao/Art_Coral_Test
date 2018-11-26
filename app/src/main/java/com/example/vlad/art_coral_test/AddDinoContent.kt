package com.example.vlad.art_coral_test

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

import kotlinx.android.synthetic.main.activity_add_dino_content.*
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.provider.MediaStore


import android.view.View
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.Toast
import com.example.vlad.art_coral_test.adapter.AppConstants
import com.example.vlad.art_coral_test.adapter.DatePickerFragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.text.DateFormat


class AddDinoContent : AppCompatActivity() {

    var fileUri: Uri? = null
    fun mUserName(name:String){
    var uName = name
}

    companion object {
        const val Green:Int = 98
        const val Red:Int = 99
        const val Purple:Int = 100

        fun mDay(dinoDayI: Int) {
            var dinoDay = dinoDayI
        }
        fun mYear(dinoYearI:Int) {
            var dinoYear = dinoYearI
        }
        fun mMonth(dinoMonthI:Int) {
            var dinoMonth = dinoMonthI
        }

        fun mUserName(name:String){
            var uName = name
        }


    }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_add_dino_content)

            dinoImageView.setOnClickListener {
                val builder = AlertDialog.Builder(this@AddDinoContent)

                builder.setTitle("Data source")

                builder.setMessage("Where is your dino?")

                builder.setPositiveButton("SD Card"){
                    dialog, which ->
                    Toast.makeText(applicationContext,"CD Card good choose"
                        ,Toast.LENGTH_SHORT).show()
                    pickPhotoFromGallerySD()
                }

                builder.setNegativeButton("Internal Memory"){
                    dialog, which ->
                    Toast.makeText(applicationContext,"Internal memory good choose"
                        ,Toast.LENGTH_SHORT).show()
                    pickPhotoFromGalleryInternal()
                }

                builder.setNeutralButton("Camera"){
                    dialog, which ->
                    Toast.makeText(applicationContext,"if you see Dino, it's awesome, get photo faster"
                        ,Toast.LENGTH_SHORT).show()
                    askCameraPermission()
                }
                val dialog: AlertDialog = builder.create()

                dialog.show()

            }
            DinoTitleEditText

            DinoAboutEditText
            DinoButtonOk
            val day =  dinoDateEditText
            fun mDay(int: Int) {}

            dinoDateEditText.setOnClickListener {
                val newFrame = DatePickerFragment()

                newFrame.show(fragmentManager,"Date Picker")
            }

            var selectedItem: Int? = null


            if (dinoColorSpinner != null) {
                dinoColorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                         //parent.getItemIdAtPosition(position)

                          when(position){
                           0,  Green -> {selectedItem = 98
                               Toast.makeText(applicationContext,"$selectedItem"
                               ,Toast.LENGTH_SHORT).show()}
                           1,  Red -> {selectedItem = 99
                               Toast.makeText(applicationContext,"$selectedItem"
                                   ,Toast.LENGTH_SHORT).show()}
                           2,  Purple ->{selectedItem = 100
                               Toast.makeText(applicationContext,"$selectedItem"
                                   ,Toast.LENGTH_SHORT).show()}
                        }


                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {

                    }

                }
            }


            /*  dinoImageView.setOnClickListener {

                val getIntent = Intent(Intent.ACTION_GET_CONTENT)
                getIntent.type = "image/*"

                val pickIntent =
                    Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                pickIntent.type = "image/*"

                val chooserIntent = Intent.createChooser(getIntent, "Select Image")
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))

                startActivityForResult(chooserIntent, PICK_IMAGE)
                //Picasso.with(context).load(new File(YOUR_FILE_PATH)).into(imageView);
                // Picasso.with(MainActivity.this).load(data.getData()).noPlaceholder().centerCrop().fit().into((ImageView) findViewById(R.id.imageView1));

            }
    }
*/  */  */

        }


    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        if (resultCode == Activity.RESULT_OK
            && requestCode == AppConstants.TAKE_PHOTO_REQUEST) {
            //photo from camera
            //display the photo on the imageview
            dinoImageView.setImageURI(fileUri)
        }else if(resultCode == Activity.RESULT_OK
            && requestCode == AppConstants.PICK_PHOTO_REQUEST){
            //photo from gallery
            fileUri = data?.data
            dinoImageView.setImageURI(fileUri)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
    private fun pickPhotoFromGallerySD() {
        val pickImageIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(pickImageIntent, AppConstants.PICK_PHOTO_REQUEST)
    }
    private fun pickPhotoFromGalleryInternal() {
        val pickImageIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.INTERNAL_CONTENT_URI)

        startActivityForResult(pickImageIntent, AppConstants.PICK_PHOTO_REQUEST)
    }
    private fun launchCamera() {
        val values = ContentValues(1)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        fileUri = contentResolver
            .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(packageManager) != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            startActivityForResult(intent, AppConstants.TAKE_PHOTO_REQUEST)
        }
    }

    //ask for permission to take photo
    fun askCameraPermission(){
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {/* ... */
                    if(report.areAllPermissionsGranted()){
                        //once permissions are granted, launch the camera
                        launchCamera()
                    }else{
                        Toast.makeText(this@AddDinoContent, "All permissions need to be granted to take photo", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {/* ... */
                    //show alert dialog with permission options
                    android.support.v7.app.AlertDialog.Builder(this@AddDinoContent)
                        .setTitle(
                            "Permissions Error!")
                        .setMessage(
                            "Please allow permissions to take photo with camera")
                        .setNegativeButton(
                            android.R.string.cancel
                        ) { dialog, _ ->
                            dialog.dismiss()
                            token.cancelPermissionRequest()
                        }
                        .setPositiveButton(android.R.string.ok
                        ) { dialog, _ ->
                            dialog.dismiss()
                            token.continuePermissionRequest()
                        }
                        .setOnDismissListener {
                            token.cancelPermissionRequest()
                        }
                        .show()
                }

            }).check()

    }


}
