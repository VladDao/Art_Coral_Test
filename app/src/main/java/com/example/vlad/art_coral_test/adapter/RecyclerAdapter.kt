package com.example.vlad.art_coral_test.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.vlad.art_coral_test.R
import com.example.vlad.art_coral_test.model.Dino
import com.squareup.picasso.Picasso

class RecyclerAdapter (private val mContext: Context,
                       private val posts:List<Dino>)
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, position: Int):ViewHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.item_dino,
            parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        if (posts != null){
            return posts.size
        }
        return Log.i("Error Recycler Adapter"," Error get Item size")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(posts[position])
    }




    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItem(dino:Dino){
            val dinoImage: ImageView = itemView.findViewById(R.id.dino_ImageView)
            val dinoTitle: TextView = itemView.findViewById(R.id.dino_title_TextView)
            val dinoColorTitle:TextView =itemView.findViewById (R.id.dino_colot_textView)
            val dinoLinear: LinearLayout = itemView.findViewById(R.id.dinoLinear)
            val dinobirthdate: TextView = itemView.findViewById(R.id.dino_birthdate_TextView)
            val dinoAbout: TextView = itemView.findViewById(R.id.dino_about_TextView)

            Picasso.get().load(dino.dino!!.dinoImage!!.src)
                .resize(100,100)
                .into(dinoImage)

            dinoTitle.text = dino.dino!!.dinoTitle
            dinobirthdate.text = dino.dino!!.dinoBirthdate
            dinoColorTitle.text = dino.dino!!.dinoColor
            dinoAbout.text = dino.dino!!.dinoAbout



        }

    }

}