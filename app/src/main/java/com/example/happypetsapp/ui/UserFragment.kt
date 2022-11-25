package com.example.happypetsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.happypetsapp.databinding.FragmentBreedinformationBinding
import com.example.happypetsapp.databinding.FragmentViewUserpageBinding
import com.example.happypetsapp.search.BreedInfoFragmentArgs

class UserFragment : Fragment() {
    private var _binding: FragmentViewUserpageBinding? = null
    private val binding get() = _binding!!
    val arg: BreedInfoFragmentArgs by navArgs()
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewUserpageBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}