package com.pnp.kotlinudemydelivery.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.github.dhaval2404.imagepicker.ImagePicker
import com.pnp.kotlinudemydelivery.R
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File

class SaveImageActivity : AppCompatActivity() {

    var circleImageUser: CircleImageView? = null
    var buttonNext: Button? = null
    var buttonConfirm: Button? = null

    private var imageFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_image)

        circleImageUser = findViewById(R.id.circleimage_user)
        buttonNext = findViewById(R.id.btn_next)
        buttonConfirm = findViewById(R.id.btn_confirm)

        circleImageUser?.setOnClickListener { selectImage() }
    }

    private val startImageForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->

            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK){
                val fileUri = data?.data
                imageFile = File(fileUri?.path) //EL ARCHIVO QUE VAMOS A GUARDAR COMO IMAGEN
                circleImageUser?.setImageURI(fileUri)
            }else if (resultCode == ImagePicker.RESULT_ERROR){
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Tarea cancelada", Toast.LENGTH_LONG).show()
            }
    }

    private fun selectImage(){
        ImagePicker.with(this)
            .crop()
            .compress(maxSize = 1024)
            .maxResultSize(width = 1080, height = 1080)
            .createIntent { intent ->
                startImageForResult.launch(intent)
            }
    }
}