package com.example.remotedataproject_gr2.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.remotedataproject_gr2.adapter.PostAdapter
import com.example.remotedataproject_gr2.api.ServiceApi
import com.example.remotedataproject_gr2.databinding.FragmentHomeBinding
import com.example.remotedataproject_gr2.helpers.Helpers
import com.example.remotedataproject_gr2.helpers.Helpers.provideRetrofitInstance
import com.example.remotedataproject_gr2.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnMakeApiCall.setOnClickListener {

            provideRetrofitInstance().getAllPosts().enqueue(object : Callback<List<Post>?> {
                override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>) {
                   if (response.isSuccessful) {
                       val listOfPosts = response.body()
                       if (!listOfPosts.isNullOrEmpty()) {
                           val postAdapter = PostAdapter(requireContext(),listOfPosts)
                           binding.lvPost.adapter = postAdapter
                       }
                   }
                }

                override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            })
        }
    }






}