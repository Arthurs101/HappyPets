package com.example.happypetsapp.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.happypetsapp.R
import com.example.happypetsapp.Services.Firebase.FirebaseSingleton
import com.example.happypetsapp.databinding.FragmentDoggydataBinding
import com.example.happypetsapp.models.PetModel
import com.example.happypetsapp.ui.home.HomeViewModel
import com.squareup.picasso.Picasso

class PetDisplay : Fragment() {
    private var _binding: FragmentDoggydataBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!
    private val viewModel = PetDisplayDatabase()
    val arg: PetDisplayArgs by navArgs()

    private val GalleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent() , ActivityResultCallback {
        binding.MascotImage.setImageURI(it)
        it?.let {
            FirebaseSingleton.uploadImage(it,viewModel) }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentDoggydataBinding.inflate(inflater,container,false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (arg.edit) {
            super.onViewCreated(view, savedInstanceState)
            binding.MascotImage.setOnClickListener {
                GalleryLauncher.launch("image/*")
            }
            binding.SubmitMascot.setOnClickListener {
                val name = binding.DoggoName.text.toString()
                val age = binding.Age.text.toString()
                val medical = binding.Medical.text.toString()
                val allergy = binding.Alergy.text.toString()
                val breed = binding.breed.text.toString()
                val res = viewModel.publish(PetModel(name, breed, allergy, medical, age))
                if (res != "succes") {
                    alert(res)
                } else {
                    findNavController().popBackStack()
                }

            }
        }else{
            binding.DoggoName.setText(arg.information.name)
            binding.Age.setText(arg.information.age)
            binding.Medical.setText(arg.information.medical)
            binding.Alergy.setText(arg.information.alergy)
            binding.breed.setText(arg.information.breed)
            binding.DoggoName.keyListener = null
            binding.Age.keyListener = null
            binding.Medical.keyListener = null
            binding.Alergy.keyListener = null
            binding.breed.keyListener = null
            binding.SubmitMascot.visibility = View.INVISIBLE
            if (arg.information.imgref.isNotEmpty()){
                Picasso.get().load(arg.information.imgref).error(R.drawable.notfound).placeholder(R.drawable.kisspng_dog_grooming_puppy_cat_pet_white_dog_5a70ac435552b0_5161753115173335713495)
                    .into(binding.MascotImage)
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