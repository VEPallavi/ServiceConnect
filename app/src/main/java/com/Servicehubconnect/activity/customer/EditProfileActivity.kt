package com.Servicehubconnect.activity.customer

import `in`.mayanknagwanshi.countrypicker.CountrySelectActivity
import `in`.mayanknagwanshi.countrypicker.bean.CountryData
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.Servicehubconnect.R
import com.Servicehubconnect.helper.AppPreference
import com.Servicehubconnect.helper.RealPathUtil
import com.Servicehubconnect.helper.Utils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.rilixtech.widget.countrycodepicker.CountryCodePicker
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.quality
import id.zelory.compressor.constraint.resolution
import id.zelory.compressor.constraint.size
import kotlinx.android.synthetic.main.customer_activity_edit_profile.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class EditProfileActivity : AppCompatActivity(), View.OnClickListener, PopupMenu.OnMenuItemClickListener{
    var appPreference: AppPreference?= null
    var outputFileUri: Uri? = null
    val CAMERA_REQUEST = 101
    val GALLERY_REQUEST = 102
    var imageFilePath: String = ""
    var countryCodePicker: CountryCodePicker?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_edit_profile)
        appPreference = AppPreference.getInstance(this)

        tv_title.text = "Update Profile"

        initViews()

        setOnClickListener()

    }

    private fun initViews() {
        ed_name.setText(appPreference!!.getCustomerName())
        ed_phoneNumber.setText(appPreference!!.getCustomerMobileNo())
        tv_countryCodePicker.setText(appPreference!!.getCustomerCountryCode())
        tv_emailId.setText(appPreference!!.getCustomerEmailID())
        if(!appPreference!!.getCustomerProfilePic().equals("") && appPreference!!.getCustomerProfilePic()!= null){
            Glide.with(this)
                    .load(appPreference!!.getCustomerProfilePic())
                    .apply(RequestOptions().placeholder(R.drawable.dummy).error(R.drawable.dummy))
                    .into(iv_profile_image)
        }

    }

    private fun setOnClickListener() {
        ivBack.setOnClickListener(this)
        iv_image_picker.setOnClickListener(this)
        tv_countryCodePicker.setOnClickListener {

            val intent = Intent(this, CountrySelectActivity::class.java)
            startActivityForResult(intent, 1213)

        }

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack ->{
                finish()
            }
            R.id.iv_image_picker ->{
                if (checkPermission()){
                    openImagePickerMenu()
                }
                else {
                    requestPermission()
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) run {

            try {
                //val selectedFilePath = RealPathUtil.getPath(this@ProfileActivity, uri)

                var file: File
                file = File(imageFilePath)
                compressFile(file)
        //        val compressedImage = compressImage(imageFilePath)

                  Glide.with(this)
                   .load(file.absolutePath)
                   .apply(RequestOptions().placeholder(R.drawable.dummy).error(R.drawable.dummy))
                   .into(iv_profile_image)
              //  uploadImage(compressedImage)

            } catch (e: IOException) {
                e.printStackTrace()
            }


        }

        else if (requestCode == GALLERY_REQUEST && resultCode == Activity.RESULT_OK) run {
            val uri = data!!.getData()
            try {
                val selectedFilePath = RealPathUtil.getPath(this@EditProfileActivity, uri)

                var file: File
                file = File(selectedFilePath)
                compressFile(file)

            //    val compressedImage = compressImage(selectedFilePath)
                  Glide.with(this)
                   .load(file.absolutePath)
                   .apply(RequestOptions().placeholder(R.drawable.dummy).error(R.drawable.dummy))
                   .into(iv_profile_image)
              //  uploadImage(compressedImage)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }


        if (requestCode === 1213 && resultCode === Activity.RESULT_OK) {
            val countryData = data!!.getSerializableExtra(CountrySelectActivity.RESULT_COUNTRY_DATA) as CountryData
            Log.e("@@@@@@@@@@", "" + Gson().toJson(countryData))
            tv_countryCodePicker.setText(countryData.countryISD)
        }

    }

    private fun compressFile(file: File) {
        lifecycleScope.launch {
            val compressedImageFile = Compressor.compress(this@EditProfileActivity, file){
                resolution(1024, 720)
                quality(65)
                size(2_097_152)
            }
        }
    }

    private fun uploadImage(imageUri: String) {
       //  hitApi to upload image to server
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.size > 0) {

            var CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            var readExternalStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;
            var writeExternalStorage = grantResults[2] == PackageManager.PERMISSION_GRANTED;

            if (CameraPermission && readExternalStorage && writeExternalStorage) {
                openImagePickerMenu()

            } else {
                Utils.showToast(this, getString(R.string.msg_incomplete_permission))
            }
        }
    }



    fun checkPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if ((ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                    && (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                    && (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED))
            {
                return true

            } else {
                return false
            }

        } else {
            return true
        }
    }

    fun openImagePickerMenu() {
        var popUpMenu = PopupMenu(this, iv_image_picker)
        try {
            val fields = popUpMenu.javaClass.getDeclaredFields()
            for (field in fields) {
                if ("mPopup" == field.getName()) {
                    field.setAccessible(true)
                    val menuPopupHelper = field.get(popUpMenu)
                    val classPopupHelper = Class.forName(menuPopupHelper.javaClass.getName())
                    val setForceIcons = classPopupHelper.getMethod("setForceShowIcon", Boolean::class.javaPrimitiveType)
                    setForceIcons.invoke(menuPopupHelper, true)
                    break
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        popUpMenu.setOnMenuItemClickListener(this)
        popUpMenu.inflate(R.menu.image_picker_menu)
        popUpMenu.gravity = Gravity.START
        popUpMenu.show()
    }

    fun requestPermission() {
        var permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this, permissions, 123);
    }

    fun compressImage(imageUri: String): String {

        var filePath = getRealPathFromURI(imageUri)
        var scaledBitmap: Bitmap? = null

        var options = BitmapFactory.Options()

        options.inJustDecodeBounds = true
        var bmp = BitmapFactory.decodeFile(filePath, options)

        var actualHeight = options.outHeight
        var actualWidth = options.outWidth

        val maxHeight = 816.0f
        val maxWidth = 612.0f

        var imgRatio = (actualWidth / actualHeight).toFloat()


        var maxRatio = maxWidth / maxHeight

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight
                actualWidth = (imgRatio * actualWidth).toInt()
                actualHeight = maxHeight.toInt()
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth
                actualHeight = (imgRatio * actualHeight).toInt()
                actualWidth = maxWidth.toInt()
            } else {
                actualHeight = maxHeight.toInt()
                actualWidth = maxWidth.toInt()
            }
        }

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight)

        options.inJustDecodeBounds = false

        options.inPurgeable = true
        options.inInputShareable = true
        options.inTempStorage = ByteArray(16 * 1024)

        try {
            bmp = BitmapFactory.decodeFile(filePath, options)
        } catch (exception: OutOfMemoryError) {
            exception.printStackTrace()
        }

        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888)
        } catch (exception: OutOfMemoryError) {
            exception.printStackTrace()
        }

        val ratioX = actualWidth / options.outWidth.toFloat()
        val ratioY = actualHeight / options.outHeight.toFloat()
        val middleX = actualWidth / 2.0f
        val middleY = actualHeight / 2.0f

        val scaleMatrix = Matrix()
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY)

        val canvas = Canvas(scaledBitmap!!)
        canvas.setMatrix(scaleMatrix)
        canvas.drawBitmap(bmp, middleX - bmp.width / 2, middleY - bmp.height / 2, Paint(Paint.FILTER_BITMAP_FLAG))

        val exif: ExifInterface
        try {
            exif = ExifInterface(filePath)

            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0)
            Log.d("EXIF", "Exif: $orientation")
            val matrix = Matrix()
            if (orientation == 6) {
                matrix.postRotate(90F)
                Log.d("EXIF", "Exif: $orientation")
            } else if (orientation == 3) {
                matrix.postRotate(180F)
                Log.d("EXIF", "Exif: $orientation")
            } else if (orientation == 8) {
                matrix.postRotate(270F)
                Log.d("EXIF", "Exif: $orientation")
            }
            scaledBitmap = Bitmap.createBitmap(
                    scaledBitmap!!, 0, 0,
                    scaledBitmap!!.width, scaledBitmap.height, matrix,
                    true
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }

        var out: FileOutputStream? = null
        val filename = getFilename()
        try {
            out = FileOutputStream(filename)
            scaledBitmap!!.compress(Bitmap.CompressFormat.JPEG, 80, out)

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        return filename
    }



    fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        var height = options.outHeight;
        var width = options.outWidth;
        var inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            var heightRatio = Math.round((height.toFloat() / reqHeight.toFloat()))
            var widthRatio = Math.round((width.toFloat() / reqWidth.toFloat()))

            if (heightRatio < widthRatio)
                inSampleSize = heightRatio
            else
                inSampleSize = widthRatio


        }
        var totalPixels = width * height;
        var totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }

    fun getRealPathFromURI(contentURI: String): String {
        var contentUri = Uri.parse(contentURI);
        var filePathColumn = arrayOf(MediaStore.Images.Media.DATA)


        var cursor = getContentResolver().query(contentUri, filePathColumn, null, null, null);
        if (cursor == null) {
            return contentUri.getPath()!!
        } else {
            cursor.moveToFirst();
            var index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    fun getFilename(): String {
        var file = File(Environment.getExternalStorageDirectory().getPath(), resources.getString(R.string.app_name) + "/Images")
        if (!file.exists()) {
            file.mkdirs()
        }
        var uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg")
        return uriSting
    }


    private fun createImageFile(): File {
        var timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date());
        var imageFileName = "IMG_" + timeStamp + "_";
        var storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //  var storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        var image = File.createTempFile(imageFileName,  /* prefix */".jpg",         /* suffix */storageDir      /* directory */);
        imageFilePath = image.getAbsolutePath();
        return image;
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {

        when (item!!.itemId) {

            R.id.item_camera -> {
                val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)

                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    //Create a file to store the image
                    var photoFile: File? = null;
                    try {
                        photoFile = createImageFile();
                    } catch (ex: IOException) {
                        // Error occurred while creating the File
                    }
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(this,getPackageName() + ".provider", photoFile)
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST)
                    }
                }

                return true
            }
            R.id.item_gallery -> {

                try {
                    val galleryIntent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    galleryIntent.type = "image/*"
                    startActivityForResult(galleryIntent, GALLERY_REQUEST)
                } catch (e: Exception) {
                    Utils.showToast(this, "No Gallery Found..")
                }

                return true
            }
        }
        return false
    }


}


