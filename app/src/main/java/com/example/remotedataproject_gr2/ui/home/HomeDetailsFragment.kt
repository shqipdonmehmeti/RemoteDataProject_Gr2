package com.example.remotedataproject_gr2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.remotedataproject_gr2.databinding.FragmentHomeDetailsBinding


class HomeDetailsFragment : Fragment() {
    private lateinit var binding: FragmentHomeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        binding.tvPostId.text = bundle?.getInt("id").toString()
        binding.tvPostTitle.text = bundle?.getString("title")
        binding.tvPostBody.text = bundle?.getString("body")
    }

}