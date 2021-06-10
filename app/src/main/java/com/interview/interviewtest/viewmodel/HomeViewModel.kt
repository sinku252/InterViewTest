package com.example.restapiidemo.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.interview.interviewtest.data.HomeRepository
import com.interview.interviewtest.data.PostEntity

import com.interview.interviewtest.data.PostModel

class HomeViewModel(application: Application): AndroidViewModel(application){

    private var homeRepository:HomeRepository?=null
    var postModelListLiveData : LiveData<List<PostModel>>?=null
    var postEntityListLiveData : LiveData<List<PostEntity>>?=null

    init {
        homeRepository = HomeRepository()
        postModelListLiveData = MutableLiveData()
    }

    fun fetchAllPosts(){
        postModelListLiveData = homeRepository?.fetchAllPosts()
    }

}