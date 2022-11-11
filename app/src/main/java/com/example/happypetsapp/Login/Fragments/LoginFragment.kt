package com.example.happypetsapp.Login.Fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.happypetsapp.Services.Firebase.FirebaseSingleton

import com.example.happypetsapp.databinding.FragmentBeginningBinding
import com.example.happypetsapp.databinding.FragmentStartOfSectionBinding
import com.example.happypetsapp.ui.home.HomeViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    private var _binding: FragmentStartOfSectionBinding? = null
    private var firebase = FirebaseSingleton.Firebaseauth
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
        _binding = FragmentStartOfSectionBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.LoginButton
        .setOnClickListener {
            val email = binding.mail.text.toString()
            val password = binding.password.text.toString()
            if(email.isNotBlank() && password.isNotBlank()){
                firebase.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                    if(it.isSuccessful){
                        val action = LoginFragmentDirections.actionLoginFragmentToNavigationHome()
                        binding.root.findNavController().navigate(action)
                    }else{
                        alert("No se pudo completar el Ingreso")
                    }
                }
            }else{
                alert("Llenar todos los campos")
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun alert(error: String){ //crea un dialogo de error, con el mensaje que se indique
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage(error)
        builder.setPositiveButton("Ok", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}