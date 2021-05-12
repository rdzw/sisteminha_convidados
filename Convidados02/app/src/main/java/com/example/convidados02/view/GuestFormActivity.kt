package com.example.convidados02.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados02.viewmodel.GuestFormViewModel
import com.example.convidados02.R
import com.example.convidados02.constants.GuestConstants
import kotlinx.android.synthetic.main.activity_guest_form.*
import kotlinx.android.synthetic.main.row_guest.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    //referencia da viewModel
    private lateinit var mVieewModel: GuestFormViewModel
    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mVieewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
        LoadData()


        radio_presence.isChecked = true

    }

    //assim que ele clicar em salvar vou invocar a minha view model
    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_save) {

            //aqui vou pegar o nome
            val name = edit_name.text.toString()

            //se ele nao tiver checado a presença entao presença igual a false
            val presence = radio_presence.isChecked

            // -> viewModel salva ai nosso usuario
            mVieewModel.save(mGuestId,name, presence)

        }
    }

    private fun LoadData(){
        val bundle = intent.extras
        if (bundle!=null){
            mGuestId = bundle.getInt(GuestConstants.GUESTID)
            //carregar

            mVieewModel.load(mGuestId)

        }

    }

    private fun observe() {
        mVieewModel.saveGuest.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        mVieewModel.guest.observe(this, Observer {
            edit_name.setText(it.name)
            if (it.presence){
                    radio_presence.isChecked = true
            }else{
                radio_absent.isChecked = true
            }
        })
    }

    private fun setListeners() {
        button_save.setOnClickListener(this)


    }


}
