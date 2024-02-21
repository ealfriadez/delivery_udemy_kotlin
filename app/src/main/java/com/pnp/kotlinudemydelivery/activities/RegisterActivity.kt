package com.pnp.kotlinudemydelivery.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.pnp.kotlinudemydelivery.R

class RegisterActivity : AppCompatActivity() {

    val TAG = "RegisterActivity"

    var imageViewGoToLogin: ImageView? = null
    var editTextFirstName: EditText? = null
    var editTextLastName: EditText? = null
    var editTextEmail: EditText? = null
    var editTextPhone: EditText? = null
    var editTextPassword: EditText? = null
    var editTextConfirmPassword: EditText? = null
    var buttonRegister: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        imageViewGoToLogin = findViewById(R.id.imageview_go_to_login)
        editTextFirstName = findViewById(R.id.edittext_first_name)
        editTextLastName = findViewById(R.id.edittext_last_name)
        editTextEmail = findViewById(R.id.edittext_email)
        editTextPhone = findViewById(R.id.edittext_phone)
        editTextPassword = findViewById(R.id.edittext_password)
        editTextConfirmPassword = findViewById(R.id.edittext_confirm_password)
        buttonRegister = findViewById(R.id.btn_register)

        imageViewGoToLogin?.setOnClickListener{goToLogin()}
        buttonRegister?.setOnClickListener{register()}
    }

    private fun register(){
        var firstName = editTextFirstName?.text.toString()
        var lastName = editTextLastName?.text.toString()
        var email = editTextEmail?.text.toString()
        var phone = editTextPhone?.text.toString()
        var password = editTextPassword?.text.toString()
        var confirmPassword = editTextConfirmPassword?.text.toString()

        if (isValidForm(firstName = firstName, lastName = lastName, email = email, phone = phone, password = password, confirmPassword = confirmPassword)){
            Toast.makeText(this, "El formulario es valido", Toast.LENGTH_LONG).show()
        }

        Log.d(TAG, "Nombres: $firstName")
        Log.d(TAG, "Apellidos: $lastName")
        Log.d(TAG, "Correo electronico: $email")
        Log.d(TAG, "Numero telfonico: $phone")
        Log.d(TAG, "Contraseña: $password")
        Log.d(TAG, "Confirme  contraseña: $confirmPassword")
    }

    fun String.isEmailValid():Boolean{
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun isValidForm(
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String
    ): Boolean{

        if (firstName.isBlank()){
            Toast.makeText(this, "Debes ingresar los nombres", Toast.LENGTH_LONG).show()
            return false
        }

        if (lastName.isBlank()){
            Toast.makeText(this, "Debes ingresar los apellidos", Toast.LENGTH_LONG).show()
            return false
        }

        if (email.isBlank()){
            Toast.makeText(this, "Debes ingresar el email", Toast.LENGTH_LONG).show()
            return false
        }

        if (phone.isBlank()){
            Toast.makeText(this, "Debes el numero telefonico", Toast.LENGTH_LONG).show()
            return false
        }

        if (password.isBlank()){
            Toast.makeText(this, "Debes ingresar el password", Toast.LENGTH_LONG).show()
            return false
        }

        if (confirmPassword.isBlank()){
            Toast.makeText(this, "Debes ingresar la confirmacion del password", Toast.LENGTH_LONG).show()
            return false
        }

        if (!email.isEmailValid()){
            Toast.makeText(this, "El correo electronico no es valido", Toast.LENGTH_LONG).show()
            return false
        }

        if (password != confirmPassword){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }

    private fun goToLogin(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}