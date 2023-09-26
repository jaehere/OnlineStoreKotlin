package com.jaehee.onlinestorekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.jaehee.onlinestorekotlin.databinding.SignUpLayoutBinding


class SignUpLayout : AppCompatActivity() {
    private lateinit var binding: SignUpLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.signUpLayoutBtnSignUp.setOnClickListener {
            if(binding.signUpLayoutEdtPassword.text.toString().equals(
                    binding.singUpLayoutEdtConfirmPassword.text.toString()
            )){
                // Registration Process

                val signUpURL = "http://61.109.169.174//OnlineStoreAPP/join_new_user.php?" +
                        "email=${binding.signUpLayoutEdtEmail.text.toString()}" +
                        "&username=${binding.signUpLayoutEdtUsername.text.toString()}" +
                        "&pass=${binding.signUpLayoutEdtPassword.text.toString()}"

                val requestQ = Volley.newRequestQueue(this@SignUpLayout)
                val stringRequest = StringRequest(Request.Method.GET, signUpURL, Response.Listener {
                    response ->

                    if(response.equals("A user with this Email Address already exists")){

                        val dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setTitle("Message")
                        dialogBuilder.setMessage(response)
                        dialogBuilder.create().show()
                    }

                }, Response.ErrorListener {
                    error ->

                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Message")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.create().show()
                })

                requestQ.add(stringRequest)


            }else{
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Message")
                dialogBuilder.setMessage("Password Mismatch")
                dialogBuilder.create().show()

            }
        }



    }
}