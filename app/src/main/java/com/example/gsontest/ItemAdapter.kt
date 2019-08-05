package com.example.gsontest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ItemAdapter(val context: Context,val itemlist:ArrayList<Img>):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View=LayoutInflater.from(context).inflate(R.layout.item,null)
        val txt=view.findViewById<TextView>(R.id.item)
        val it=itemlist[position]

        txt.text = it.image_url

        return view
    }

    override fun getItem(position: Int): Any {
        return itemlist[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return itemlist.size
    }

}