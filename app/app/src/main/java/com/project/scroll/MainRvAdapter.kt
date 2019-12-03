package com.project.scroll

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainRvAdapter(val context: Context, val itemList: ArrayList<Item>):
        RecyclerView.Adapter<MainRvAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.cardview_item_book, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
      holder.bind(itemList[position], context)
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val img = itemView.findViewById<ImageView>(R.id.image)
        val title = itemView.findViewById<TextView>(R.id.title)
        fun bind(item : Item, context: Context){
            if(item.img != ""){
                val resourceId = context.resources.getIdentifier(item.img, "drawable", context.packageName)
                img?.setImageResource(resourceId)
            }
            else{
                img?.setImageResource(R.mipmap.ic_launcher)
            }
            title?.text = item.title

        }
    }
}