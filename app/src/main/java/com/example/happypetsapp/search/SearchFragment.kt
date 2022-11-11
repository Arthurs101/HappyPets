package com.example.happypetsapp.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.happypetsapp.Services.Api.information.AnimalsAPI
import com.example.happypetsapp.Services.Api.photos.PhotosApi
import com.example.happypetsapp.adapters.SearchAdapter
import com.example.happypetsapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private var _binding : FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    private val viewmodel : SearchViewModel by activityViewModels {
        SearchViewModelFacotry(AnimalsAPI.service, PhotosApi.service)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        val root: View = binding.root
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.searchImage.setOnClickListener{
            recyclerView.adapter = SearchAdapter(viewmodel.searchAnimals(binding.searchText.text.toString()),viewmodel)
            // This was just for testing -> viewmodel.searchImage(binding.searchText.text.toString())
        }
    }
    override fun onDestroyView() { //quitar el binding cuando no se está usando el fragmento
        super.onDestroyView()
        _binding = null
    }

}
