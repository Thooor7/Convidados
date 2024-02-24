package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private  val repository = GuestRepository.getInstance(application.applicationContext)

    private val listaAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listaAllGuests

    fun getAll(){
        listaAllGuests.value = repository.getAll()
    }

    fun getAbsent(){
        listaAllGuests.value = repository.getAbsent()
    }

    fun getPresent(){
        listaAllGuests.value = repository.getPresent()
    }

    fun delete(id: Int){
        repository.delete(id)
    }
}