package com.example.happypetsapp.Login.Fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

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
            //obtener textos ingresados en las inputs
            val emal = binding.Email.text.toString()
            val password = binding.password.text.toString()
            val usernname = binding.Username.text.toString()
            if(password.contentEquals(binding.passwordRedundancy.text.toString())){ //ver que las contraseñas coincidan en las dos inputs de contraseña
                if(emal.isNotBlank() && password.isNotBlank() && usernname.isNotBlank()){
                    firebase.createUserWithEmailAndPassword(emal,password).addOnCompleteListener{
                        if(it.isSuccessful){ //si se pudo guardar el usuario configurar su nombre
                            val action = SignUpFragmetDirections.actionSignUpFragmetToNavigationHome()
                            //configurar su username

                            val user = Firebase.auth.currentUser
                            val temp = FirebaseSingleton.RealTimeData.child("users")
                            temp.orderByChild("username").equalTo(usernname).addValueEventListener(object : ValueEventListener{
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    if(snapshot.exists()){
                                        alert("Usuario tomado")
                                    }else{
                                        val profileUpdates = userProfileChangeRequest {
                                            displayName = usernname

                                        }
                                        user!!.updateProfile(profileUpdates)
                                            .addOnCompleteListener { task ->
                                                if (task.isSuccessful) {
                                                    binding.root.findNavController().navigate(action)
                                                }else{
                                                    alert("No se pudo Completar el Registro: No se pudo configurar Nombre de Usuario")
                                                }
                                            }
                                    }
                                }

                                override fun onCancelled(error: DatabaseError) {
                                    Log.d("ImmaCRy", "Failed")
                                }

                            })


                        }else{
                            alert("No se pudo Completar el Registro, Error Registrando en Base de datos")
                        }
                    }


                }else{
                    alert("No se pudo Completar el Registro: No deje campos en blanco")
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