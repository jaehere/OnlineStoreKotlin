package com.jaehee.onlinestorekotlin

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jaehee.onlinestorekotlin.R.id.imgProduct
import com.jaehee.onlinestorekotlin.R.id.txtId
import com.jaehee.onlinestorekotlin.R.id.txtName
import com.jaehee.onlinestorekotlin.R.id.txtPrice
import com.squareup.picasso.Picasso


class EProductAdapter(var context: Context, var arrayList: ArrayList<EProduct>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val productView = LayoutInflater.from(context).inflate(R.layout.e_product_row, parent, false)

        return ProductViewHolder(productView)
    }

    override fun getItemCount(): Int {

        return arrayList.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as ProductViewHolder).initializeRowUIComponents(

                                                        arrayList[position].id,
                                                        arrayList[position].name,
                                                        arrayList[position].price,
                                                        arrayList[position].pictureName )

    }

    inner class ProductViewHolder(pView: View) : RecyclerView.ViewHolder(pView){

        fun initializeRowUIComponents(id: Int, name: String, price: Int, picName: String) {
            itemView.findViewById<TextView>(txtId).text = id.toString()
            itemView.findViewById<TextView>(txtPrice).text = price.toString()
            itemView.findViewById<TextView>(txtName).text = name

            var picUrl = "http://61.109.169.174/OnlineStoreAPP/osimages/"
            picUrl = picUrl.replace(" ", "%20")

            Picasso.get().load(picUrl + picName).into(itemView.findViewById<ImageView>(imgProduct))

            itemView.findViewById<ImageView>(R.id.imgAdd).setOnClickListener{

                Person.addToCartProductID = id
                var amountFragment = AmountFragment()
                var fragmentManager = (itemView.context as Activity).fragmentManager
                amountFragment.show(fragmentManager, "TAG")
            }
        }
    }


}