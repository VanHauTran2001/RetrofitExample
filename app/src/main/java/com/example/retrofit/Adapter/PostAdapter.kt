package com.example.retrofit.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.Adapter.PostAdapter.PostViewHolder
import com.example.retrofit.R
import androidx.databinding.DataBindingUtil
import com.example.retrofit.databinding.PostLayoutBinding
import com.example.retrofit.model.Post

class PostAdapter (var postList: List<Post>) : RecyclerView.Adapter<PostViewHolder>(){
    var biding: PostLayoutBinding? = null
    class PostViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view:View =LayoutInflater.from(parent.context).inflate(R.layout.post_layout,parent,false)
        biding = DataBindingUtil.bind(view)
        return PostViewHolder(biding!!.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        biding!!.txtTitle.text = postList[position].title
        biding!!.txtAthor.text = postList[position].userID.toString()
        biding!!.txtContent.text = StringBuilder(postList[position].body.substring(0,20)).append("...").toString()
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}