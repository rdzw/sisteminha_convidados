package com.example.convidados02.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados02.service.model.GuestModel
import com.example.convidados02.service.repository.GuestRepository


//classe com o contrutor ViewModel
class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    //peguei o contexto passado da view
    private val mContext = application.applicationContext

    //criar a variavel pra pegar meu usuario
    private val mGuestRepository: GuestRepository = GuestRepository.getInstance(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest


    private var mGuest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = mGuest


    // aqui eu quero um nome e uma presenca
    fun save(id: Int, name: String, presence: Boolean) {
        val guest = GuestModel(id, name, presence)


        if (id == 0) {

            mSaveGuest.value = mGuestRepository.save(guest)

        } else {

            mSaveGuest.value = mGuestRepository.update(guest)
        }


    }

    fun load(id: Int) {
        mGuest.value = mGuestRepository.get(id)
    }
}