package com.example.happypetsapp.ui.alerts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.happypetsapp.databinding.FragmentViewUserpageBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentViewUserpageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentViewUserpageBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}