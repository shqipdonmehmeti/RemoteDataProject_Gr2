package com.example.remotedataproject_gr2.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.remotedataproject_gr2.R
import com.example.remotedataproject_gr2.adapter.PostAdapter
import com.example.remotedataproject_gr2.databinding.FragmentHomeBinding
import com.example.remotedataproject_gr2.helpers.Helpers.provideRetrofitInstance
import com.example.remotedataproject_gr2.model.Post
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var listOfPosts: List<Post>

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
        onClick()
    }

    private fun onClick() {
        binding.btnMakeApiCall.setOnClickListener {
            getPosts()
        }

        binding.lvPost.setOnItemClickListener { parent, view, position, id ->
            getOnePost(position)
        }
    }

    private fun getOnePost(position: Int) {
        val clickedItem = listOfPosts[position]
        provideRetrofitInstance().getOnePost(clickedItem.id).enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                if (response.isSuccessful && response.body() != null) {
                    val post = response.body()!!
                    val bundle = Bundle().apply {
                        putInt("id",post.id)
                        putString("title",post.title)
                        putString("body",post.body)
                    }
                    findNavController().navigate(R.id.action_nav_home_to_homeDetailsFragment,bundle)
                }
                else {
                    Toast.makeText(requireContext(),"Api call not successful!",Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<Post?>, t: Throwable) {
                Toast.makeText(requireContext(),"Something went wrong due to internet connection / server timeout / etc",Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun getPosts() {
        binding.progressBar.visibility = View.VISIBLE
            provideRetrofitInstance().getAllPosts().enqueue(object : Callback<List<Post>?> {
                override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>) {
                    binding.progressBar.visibility = View.GONE
                    if (response.isSuccessful && response.body() != null) {
                        listOfPosts = response.body()!!
                        if (!listOfPosts.isNullOrEmpty()) {
                            val postAdapter = PostAdapter(requireContext(), listOfPosts)
                            binding.lvPost.adapter = postAdapter
                        }
                    }
                }

                override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
                    binding.progressBar.visibility = View.GONE
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            })

    }

}