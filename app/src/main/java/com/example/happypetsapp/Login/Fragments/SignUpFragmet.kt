package com.example.happypetsapp.Login.Fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.happypetsapp.Services.Firebase.FirebaseSingleton
import com.example.happypetsapp.databinding.FragmentBeginningBinding
import com.example.happypetsapp.databinding.FragmentCheckinBinding
import com.example.happypetsapp.ui.home.HomeViewModel
import com.google.firebase.auth.FirebaseAuth

class SignUpFragmet : Fragment()  {
    private var _binding: FragmentCheckinBinding? = null
    val firebase = FirebaseSingleton.Firebaseauth
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
        _binding = FragmentCheckinBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonregisters.setOnClickListener {
            val emal = binding.Email.text.toString()
            val password = binding.password.text.toString()
            if(password.contentEquals(binding.passwordRedundancy.text.toString())){
                if(emal.isNotBlank() && password.isNotBlank()){
                    firebase.createUserWithEmailAndPassword(emal,password).addOnCompleteListener{
                        if(it.isSuccessful){
                            val action = SignUpFragmetDirections.actionSignUpFragmetToNavigationHome()
                            binding.root.findNavController().navigate(action)
                        }else{
                            alert("No se pudo Completar el Registro")
                        }
                    }


                }
            }else{
                Toast.makeText(context,"Las contraseñas no coinciden",Toast.LENGTH_LONG)
            }


        }
    }
    fun alert(error: String){ //crea un dialogo de error, con el mensaje que se indique
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage(error)
        builder.setPositiveButton("Ok", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}