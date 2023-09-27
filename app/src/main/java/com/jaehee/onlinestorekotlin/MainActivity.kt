package com.jaehee.onlinestorekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.jaehee.onlinestorekotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.activityMainBtnLogin.setOnClickListener {
            val loginURL = "http://61.109.169.174/OnlineStoreAPP/login_app_user.php?" +
                    "email=${binding.activityMainEdtEmail.text.toString()}" +
                    "&pass=${binding.activityMainEdtLoginPassword.text.toString()}"

            val requestQ = Volley.newRequestQueue(this@MainActivity)
            val stringRequest = StringRequest(Request.Method.GET, loginURL, Response.Listener {
                response ->

                if(response.equals("The user does exist")){
                    Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()

                    val homeIntent = Intent(this@MainActivity, HomeScreen::class.java)
                    startActivity(homeIntent)
                }else{
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


        }

        binding.activityMainBtnSignUp.setOnClickListener {

            var signUpIntent = Intent(this@MainActivity, SignUpLayout::class.java)
            startActivity(signUpIntent)

        }

    }
}