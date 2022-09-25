package com.udacity.shoestore.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : Fragment() {
    lateinit var binding: FragmentShoeDetailBinding
    lateinit var viewModel: SharedViewModel

    //// onCreateView //////////////////////////////////////////////////////////////////////

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // two-wat data binding
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // one-way data binding
        handleSpinners()

        // viewModel events
        viewModel.eventNavigate.observe(viewLifecycleOwner, Observer { value ->
            if (value) {
                this.findNavController().navigate(
                    ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
                viewModel.onNavigation()
            }
        })

        return binding.root
    }

    //// FUNCTIONS /////////////////////////////////////////////////////////////////////////

    private fun handleSpinners() {
        // Set ArrayAdapter with array of strings from resources for the spnCompany Spinner
        binding.spnCompany.adapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.array_companies, android.R.layout.simple_spinner_item
        ).also { ad ->
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        // Set onItemSelectedListener for the spnCompany Spinner
        binding.spnCompany.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            @SuppressLint("DiscouragedApi")
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                viewModel.setShoeCompany(parent.getItemAtPosition(pos).toString())
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        // Set ArrayAdapter with list of integers for the spnSize Spinner
        binding.spnSize.adapter = ArrayAdapter<Any?>(
            requireContext(), android.R.layout.simple_spinner_item, (16..52).toList()
        ).also { ad ->
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        // Set onItemSelectedListener for the spnSize Spinner
        binding.spnSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                viewModel.setShoeSize(parent.getItemAtPosition(pos).toString().toInt())
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

}