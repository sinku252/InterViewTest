//package com.example.restapiidemo.home.ui
package com.interview.interviewtest.home
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.interview.interviewtest.R
import com.interview.interviewtest.data.PostModel
import kotlinx.android.synthetic.main.post_item.view.*

//import kotlinx.android.synthetic.main.home_rv_item_view.view.*

class HomeAdapter(var listener:HomeListener,var context: Context) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){

    private var data : ArrayList<PostModel>?=null

    interface HomeListener{
        fun onItemClick(postModel: PostModel, position: Int)
    }

    fun setData(list: ArrayList<PostModel>){
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item,context)
        holder.itemView.ll_click.setOnClickListener {
            item?.let { it1 ->
                listener.onItemClick(it1, position)
            }
        }
    }

    fun addData(postModel: PostModel) {
        data?.add(0,postModel)
        notifyItemInserted(0)
    }

    fun removeData(position: Int) {
        data?.removeAt(position)
        notifyDataSetChanged()
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(item: PostModel?, context: Context) {
            itemView.post_title.text = item?.name
            itemView.post_brand.text = item?.brand
            itemView.post_price.text = item?.price
            itemView.post_description.text = item?.description
            Glide.with(context).load(item?.imageLink).into(itemView.post_image);
        }

    }

}