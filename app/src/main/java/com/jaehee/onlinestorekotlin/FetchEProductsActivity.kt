package com.jaehee.onlinestorekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.jaehee.onlinestorekotlin.databinding.ActivityFetchEproductsBinding

class FetchEProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFetchEproductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFetchEproductsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val selectedBrand:String = intent.getStringExtra("BRAND").toString()

        binding.txtBrandName.text = "Products of $selectedBrand"

        var productsList = ArrayList<EProduct>()

        val productsURL = "http://61.109.169.174/OnlineStoreAPP/fetch_eproducts.php?brand=$selectedBrand"
        val reqeustQ = Volley.newRequestQueue(this@FetchEProductsActivity)
        val jsonAR = JsonArrayRequest(Request.Method.GET, productsURL, null, Response.Listener { response ->

                for(productJOIndex in 0.until(response.length())){
                    productsList.add(EProduct(response.getJSONObject(productJOIndex).getInt("id"),
                        response.getJSONObject(productJOIndex).getString("name"),
                        response.getJSONObject(productJOIndex).getInt("price"),
                        response.getJSONObject(productJOIndex).getString("picture")))
                }

                val pAdapter = EProductAdapter(this@FetchEProductsActivity, productsList)
                binding.productsRV.layoutManager = LinearLayoutManager(this@FetchEProductsActivity)
                binding.productsRV.adapter = pAdapter

            }, Response.ErrorListener {error ->

                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Message")
                dialogBuilder.setMessage(error.message)
                dialogBuilder.create().show()
            
        })

        reqeustQ.add(jsonAR)

    }
}