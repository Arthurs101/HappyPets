package com.example.happypetsapp.Publish

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.happypetsapp.R
import com.example.happypetsapp.Services.Firebase.FirebaseSingleton
import com.example.happypetsapp.databinding.FragmentCreatePublicationBinding


class PublishFragment :Fragment() {
    private var _binding : FragmentCreatePublicationBinding ?= null
    private val binding get() = _binding!!
    private val viewModel = PublishViewModel()


    private val GalleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent() , ActivityResultCallback {
        binding.ImageImporter.setImageURI(it)
        it?.let {
             FirebaseSingleton.uploadImage(it,viewModel) }
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
            viewModel.setContent( binding.description.text.toString() )
            if(binding.ToAlerts.isChecked or binding.ToPublications.isChecked){
                viewModel.pulish()
                binding.ImageImporter.setImageResource(android.R.drawable.ic_menu_gallery)
                binding.description.text.clear()
                Toast.makeText(context,"Done",Toast.LENGTH_SHORT)
            }else{
                Toast.makeText(context, "Elija una opción", Toast.LENGTH_SHORT)
            }

            findNavController().popBackStack()
        }
        binding.radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId){
                binding.ToAlerts.id ->{
                    viewModel.publication = false
                }
                binding.ToPublications.id ->{
                    viewModel.publication = true
                }
            }
        }
    }
}