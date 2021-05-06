package com.example.convidados02.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.convidados02.viewmodel.GuestFormViewModel
import com.example.convidados02.R
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(),View.OnClickListener {

    //referencia da viewModel
    private lateinit var mVieewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mVieewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()



    }

    private fun setListeners() {
            button_save.setOnClickListener(this)



    }


    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_save){

        }
    }
}
