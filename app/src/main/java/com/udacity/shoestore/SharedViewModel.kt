package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedViewModel : ViewModel() {

    // List of Shoe objects displayed in the ShoeListFragment
    private val _shoeList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoeList: LiveData<MutableList<Shoe>> get() = _shoeList

    // Shoe object to save data displayed in the ShoeDetailFragment in case of Configuration Changes
    private val _newShoe = MutableLiveData<Shoe>(Shoe())
    val newShoe: LiveData<Shoe> get() = _newShoe

    // Boolean that triggers event (navigate from ShoeDetailFragment to ShoeListFragment)
    private val _eventNavigate = MutableLiveData<Boolean>(false)
    val eventNavigate: LiveData<Boolean> get() = _eventNavigate
    fun onNavigation() { _eventNavigate.value = false }

    //// METHODS ///////////////////////////////////////////////////////////////////////////

    fun setShoeSize(size: Int) {
        _newShoe.value = _newShoe.value?.also { it.size = size }
    }

    fun setShoeCompany(company: String) {
        when(company) {
            "Nike" -> _newShoe.value?.image = R.drawable.nike
            "ADIDAS" -> _newShoe.value?.image = R.drawable.adidas
            "Puma" -> _newShoe.value?.image = R.drawable.puma
            "Vans" -> _newShoe.value?.image = R.drawable.vans
            "Bata" -> _newShoe.value?.image = R.drawable.bata
            else -> _newShoe.value?.image = R.drawable.nike
        }
        _newShoe.value = _newShoe.value?.also { it.company = company }
    }

    fun onSave() {
        _shoeList.value?.add(_newShoe.value!!)
        _newShoe.value = Shoe()
        _eventNavigate.value = true
    }

    fun onCancel() {
        _newShoe.value = Shoe()
        _eventNavigate.value = true
    }
}
