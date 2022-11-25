package com.example.happypetsapp.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.happypetsapp.R
import com.example.happypetsapp.adapters.BreedInformationAdapter
import com.example.happypetsapp.databinding.FragmentBreedinformationBinding
import com.squareup.picasso.Picasso


class BreedInfoFragment : Fragment() {
    private var _binding : FragmentBreedinformationBinding?= null
    private val binding get() = _binding!!
    val arg: BreedInfoFragmentArgs by navArgs()
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedinformationBinding.inflate(inflater,container,false)
        val root: View = binding.root
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animal = arg.information
        animal?.let {
            binding.DisplayName.text = animal.name
            animal.tax?.let {  binding.DisplayCientific.text =  it.sname}
            animal.details?.let {
                binding.diet.text= it.diet
                binding.distinct.text = it.specialf
                binding.group.text = it.group
                binding.lifetime.text = it.lspan
                binding.predators.text = it.predators
                binding.mating.text = it.age_of_sexual_maturity
                binding.presas.text = it.prey
                binding.hait.text = it.habitat
                recyclerView = binding.Ubications
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.adapter = BreedInformationAdapter(it.counties)
                if(animal.ref.isNotBlank()){
                    Picasso.get().load(animal.ref).placeholder(R.drawable.notfound).into(binding.DisplayPhoto)
                }else{
                    binding.DisplayPhoto.setImageResource(R.drawable.notfound)
                }
            }

        }
    }
}