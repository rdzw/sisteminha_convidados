package com.example.convidados02.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados02.constants.GuestConstants
import com.example.convidados02.service.model.GuestModel
import com.example.convidados02.service.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {


    //instaciamos o repositorio
    private val mGuestRepository = GuestRepository.getInstance(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList




    //buscamos na lista
    fun load(filter:Int) {

        if(filter == GuestConstants.FILTER.EMPTY){
              mGuestList.value = mGuestRepository.getAll()
        }else if (filter == GuestConstants.FILTER.PRESENT  ){
            mGuestList.value = mGuestRepository.getPresent()
        }else{
            mGuestList.value = mGuestRepository.getAbsent()
        }

    }

    fun delete(id: Int) {
        mGuestRepository.delete(id)
    }
}