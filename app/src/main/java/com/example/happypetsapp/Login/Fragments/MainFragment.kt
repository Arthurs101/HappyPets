package com.example.happypetsapp.Login.Fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.happypetsapp.databinding.FragmentStartOfSectionBinding
import com.example.happypetsapp.ui.home.HomeViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.happypetsapp.MainActivity
import com.example.happypetsapp.R
import com.example.happypetsapp.Services.Firebase.FirebaseSingleton
import com.example.happypetsapp.databinding.FragmentBeginningBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import kotlin.math.sign

class MainFragment : Fragment() {
    private var _binding: FragmentBeginningBinding? = null
    private val GOOGLE_SIGN = 1001
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest
    private lateinit var goglesign: GoogleSignInClient
    private var auth = FirebaseSingleton.Firebaseauth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        binding.Google.setOnClickListener{
            val gsin = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.ClientID))
                .requestEmail()
                .build()
            val googleSignIn = GoogleSignIn.getClient(findNavController().context,gsin)
            googleSignIn.signOut() //cerrar sesión de cualquier Cuenta de Google que haya quedado abierta
            startActivityForResult(googleSignIn.signInIntent,GOOGLE_SIGN)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_SIGN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)

            account?.let {
                try {
                    val credential = GoogleAuthProvider.getCredential(it.idToken, null)
                    FirebaseSingleton.Firebaseauth.signInWithCredential(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val action =
                                    MainFragmentDirections.actionMainFragmentToNavigationHome()
                                binding.root.findNavController().navigate(action)
                            } else {
                                alert("No se pudo Completar el Registro")
                            }
                        }
                }catch (e : ApiException){
                    alert(e.toString())
                }

            }



        }
    }

    private fun alert(s: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage(s)
        builder.setPositiveButton("Ok", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    companion object{
        const val RC_Signing = 1001
        const val EXTRA_NAME = "EXTRANAME"
    }
}