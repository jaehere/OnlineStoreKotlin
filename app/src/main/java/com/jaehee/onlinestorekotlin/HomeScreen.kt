package com.jaehee.onlinestorekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.jaehee.onlinestorekotlin.databinding.ActivityHomeScreenBinding
import java.net.URL

class HomeScreen : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val brandsURL = "http://61.109.169.174/OnlineStoreAPP/fetch_brands.php?"

        var brandsList = ArrayList<String>()

        var requestQ = Volley.newRequestQueue(this@HomeScreen)

        var jsonAR = JsonArrayRequest(Request.Method.GET, brandsURL, null, Response.Listener{
            response ->

            for(jsonObject in 0.until(response.length())) {
                brandsList.add(response.getJSONObject(jsonObject).getString("brand"))
            }

            var brandsListAdapter = ArrayAdapter(this@HomeScreen, R.layout.brand_item_text_view, brandsList)
            binding.brandsListView.adapter = brandsListAdapter

        }, Response.ErrorListener {
            error ->

            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()

        })

        requestQ.add(jsonAR)


        binding.brandsListView.setOnItemClickListener { adapterView, view, i, l ->

            val tappedBrand = brandsList.get(i)
            val intent = Intent(this@HomeScreen, FetchEProductsActivity::class.java)

            intent.putExtra("BRAND", tappedBrand)
            startActivity(intent)
        }



    }
}