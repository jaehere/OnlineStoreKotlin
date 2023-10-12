package com.jaehee.onlinestorekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.jaehee.onlinestorekotlin.databinding.ActivityCartProdcutsBinding

class CartProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartProdcutsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartProdcutsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        var cartProductUrl = "http://61.109.169.174/OnlineStoreAPP/fetch_temporary_order.php?email=${Person.email}"
        var cartProductsList = ArrayList<String>()
        var requestQ = Volley.newRequestQueue(this@CartProductsActivity)
        var jsonAR = JsonArrayRequest(Request.Method.GET, cartProductUrl,null, Response.Listener { response ->

            for (joIndex in 0.until(response.length())){// id , name, price, email, amount
                cartProductsList.add("${response.getJSONObject(joIndex).getInt("id")} \n" +
                        " ${response.getJSONObject(joIndex).getString("name")} \n" +
                        " ${response.getJSONObject(joIndex).getString("price")} \n" +
                        " ${response.getJSONObject(joIndex).getString("email")} \n" +
                        " ${response.getJSONObject(joIndex).getString("amount")}")
            }

            var cartProductsAdapter = ArrayAdapter(this@CartProductsActivity, android.R.layout.simple_list_item_1, cartProductsList)
            binding.cartProductsListView.adapter = cartProductsAdapter
        }, Response.ErrorListener { error ->


        } )


        requestQ.add(jsonAR)

    }
}