package com.pnp.kotlinudemydelivery.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import com.pnp.kotlinudemydelivery.R
import com.pnp.kotlinudemydelivery.models.ResponseHttp
import com.pnp.kotlinudemydelivery.models.User
import com.pnp.kotlinudemydelivery.providers.UsersProviders
import com.pnp.kotlinudemydelivery.utils.SharedPref
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class SaveImageActivity : AppCompatActivity() {

    val TAG = "SaveImageActivity"

    var circleImageUser: CircleImageView? = null
    var buttonNext: Button? = null
    var buttonConfirm: Button? = null

    private var imageFile: File? = null

    var usersProviders = UsersProviders()
    var user: User? = null
    var sharedPref: SharedPref? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_image)

        sharedPref = SharedPref(this)

        getUserFromSession()

        circleImageUser = findViewById(R.id.circleimage_user)
        buttonNext = findViewById(R.id.btn_next)
        buttonConfirm = findViewById(R.id.btn_confirm)

        circleImageUser?.setOnClickListener { selectImage() }

        buttonConfirm?.setOnClickListener{ saveImage() }
    }

    private fun saveImage(){

        if(imageFile != null && user != null){
            usersProviders.update(imageFile!!, user!!)?.enqueue(object: Callback<ResponseHttp> {
                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {
                    Log.d(TAG, "RESPONSE: $response")
                    Log.d(TAG, "BODY: ${response.body()}")
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "Error: ${t.message}")
                    Toast.makeText(this@SaveImageActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }else{
            Toast.makeText(this, "La imagen no puede ser nula ni tampoco los datos de sesion del usuario", Toast.LENGTH_LONG).show()
        }
    }

    private fun getUserFromSession(){

        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            //SI EL USUARIO EXISTE EN SESION
            user = gson.fromJson(sharedPref?.getData("user"), User:: class.java)
        }
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