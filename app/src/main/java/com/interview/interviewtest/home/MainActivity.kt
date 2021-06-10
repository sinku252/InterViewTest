package com.interview.interviewtest.home


import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Room
import com.example.restapiidemo.home.viewmodel.HomeViewModel
import com.interview.interviewtest.R
import com.interview.interviewtest.data.PostEntity
import com.interview.interviewtest.data.PostModel
import com.interview.interviewtest.db.AppDatabase

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), HomeAdapter.HomeListener {

    private lateinit var vm:HomeViewModel
    private lateinit var adapter: HomeAdapter


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "Posts"
        ).build()

        vm = ViewModelProvider(this)[HomeViewModel::class.java]

        initAdapter()

        if(isOnline(this))
        {
            vm.fetchAllPosts()
        }
        else
        {
            lateinit var postList: List<PostEntity>
            GlobalScope.launch {
                 postList=db.taskDao().getAllPosts()
            }

            if(postList.size >0)
            {
                lateinit var listPost: ArrayList<PostModel>
                for (post in postList) {
                    listPost.add(PostModel(post.id.toString(),
                        post.brand,post.name,
                        post.price,post.product_link,
                        post.image_link,
                        0,
                        "",
                        "",
                        "",
                        "",
                        "",
                        arrayListOf(),
                        "",
                        "",
                        1.5,
                        arrayListOf(),
                        "",
                        "",
                    ))
                }
                rv_posts.visibility = View.VISIBLE
                adapter.setData(listPost)
            }
           /* vm.postModelListLiveData?.observe(this, Observer {
                if (it != null) {
                    rv_posts.visibility = View.VISIBLE
                    adapter.setData(it as ArrayList<PostModel>)
                } else {
                    showToast("Something went wrong")
                }
                progress_home.visibility = View.GONE
            })*/
        }


        vm.postModelListLiveData?.observe(this, Observer {
            if (it != null) {
                rv_posts.visibility = View.VISIBLE
                adapter.setData(it as ArrayList<PostModel>)

                for (i in it) {
                    var postModel:PostModel=i
                    GlobalScope.launch {
                        db.taskDao()?.savePosts(PostEntity(postModel.id, postModel.brand,postModel.name, postModel.price,postModel.productLink, postModel.imageLink))
                    }

                }

            } else {
                showToast("Something went wrong")
            }
            progress_home.visibility = View.GONE
        })

    }





    private fun initAdapter() {
        adapter = HomeAdapter(this,this)
        rv_posts.layoutManager = LinearLayoutManager(this)
        rv_posts.adapter = adapter
    }



    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(postModel: PostModel, position: Int)
    {
        val url = postModel.productApiUrl
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                } else {
                    TODO("VERSION.SDK_INT < M")
                }
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

}
