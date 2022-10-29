package com.example.happypetsapp.Login.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.happypetsapp.databinding.FragmentStartOfSectionBinding
import com.example.happypetsapp.ui.home.HomeViewModel
import androidx.navigation.findNavController
import com.example.happypetsapp.databinding.FragmentBeginningBinding

class MainFragment : Fragment() {
    private var _binding: FragmentBeginningBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val NotificationsViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentBeginningBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonlog.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToLoginFragment()
            binding.root.findNavController().navigate(action)
        }
        binding.button4.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToSignUpFragmet()
            binding.root.findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}