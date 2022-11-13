package com.example.happypetsapp.Publish

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

import androidx.fragment.app.Fragment
import com.example.happypetsapp.Services.Firebase.FirebaseSingleton
import com.example.happypetsapp.databinding.FragmentCreatePublicationBinding


class PublishFragment :Fragment() {
    private var _binding : FragmentCreatePublicationBinding ?= null
    private val binding get() = _binding!!

    private val GalleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent() , ActivityResultCallback {
        binding.ImageImporter.setImageURI(it)
        it?.let {FirebaseSingleton.uploadImage(it)}
    })
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreatePublicationBinding.inflate(inflater,container,false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ImageImporter.setOnClickListener{
            GalleryLauncher.launch("image/*")
        }
        binding.Publish.setOnClickListener{
            val content = binding.description.text.toString()

        }
    }
}