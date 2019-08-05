package com.example.gsontest

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gsontest.databinding.ItemContentBinding

class ItemAdapter(val context: Context,val itemlist:ArrayList<Img>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var VIEW_TYPE_ITEM = 0
    var VIEW_TYPE_LOADING = 1

    override fun getItemViewType(position: Int): Int {
        return if (itemlist[position].image_url != "progress")
            VIEW_TYPE_ITEM
        else
            VIEW_TYPE_LOADING
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==VIEW_TYPE_ITEM) {
            val binding = ItemContentBinding.inflate(LayoutInflater.from(context), parent, false)
            return ViewHolder(binding)
        }else{
            //로딩중이면 얘반환
            val view = LayoutInflater.from(context).inflate(R.layout.item_progress,parent, false)
            return progressHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemlist[position]
        if(item.image_url!="progress")
            (holder as ViewHolder).bind(item)

    }


    override fun getItemCount(): Int {
        return itemlist.size
    }
    fun addprogress(position: Int=itemlist.size){
        if(position==0)//topscroll
            itemlist.add(position,getProgressItem())
        else//bottom
            itemlist.add(getProgressItem())
    }
    fun removeprogress(position: Int=itemlist.size-1){
        itemlist.removeAt(position)
        notifyItemRemoved(position)
    }

    fun replaceAll(items:ArrayList<Img>){
        itemlist.apply {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }
    fun addAll(items:ArrayList<Img>){
        itemlist.apply {
            addAll(items)
            notifyDataSetChanged()
        }
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var binding: ItemContentBinding
        constructor(binding: ItemContentBinding) : this(binding.root){
            Log.d("ViewHolder", " create")
            this.binding=binding
        }
        fun bind(img: Img){
            binding.setVariable(BR.listContent, img)
        }
    }
    class progressHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
//  리스트뷰 시절
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val view: View=LayoutInflater.from(context).inflate(R.layout.item_content,null)
//        val txt=view.findViewById<TextView>(R.id.item_content)
//        val it=itemlist[position]
//
//        txt.text = it.image_url
//
//        return view
//    }
//
//    override fun getItem(position: Int): Any {
//        return itemlist[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return 0
//    }
//
//    override fun getCount(): Int {
//        return itemlist.size
//    }
}
@BindingAdapter("image_url")
fun getImg(view:ImageView, url:String){
    Glide.with(view.context).load(url).thumbnail(0.5f).into(view)
}