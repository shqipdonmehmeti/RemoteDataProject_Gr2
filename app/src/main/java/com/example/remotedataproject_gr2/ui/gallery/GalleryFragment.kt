package com.example.remotedataproject_gr2.ui.gallery

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.remotedataproject_gr2.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSaveData.setOnClickListener {
            val usernameValue = binding.etUsername.text.toString()
            val sharedPreferences = requireContext().getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().apply {
                putString("username",usernameValue)
                apply() // we use instead of commit() to work async (background)
            }
        }
    }


}