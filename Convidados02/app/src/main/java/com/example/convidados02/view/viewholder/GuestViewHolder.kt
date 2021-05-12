package com.example.convidados02.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados02.R
import com.example.convidados02.service.model.GuestModel
import com.example.convidados02.view.listener.GuestListener
import kotlinx.android.synthetic.main.row_guest.view.*

class GuestViewHolder(itemView: View, private val listener: GuestListener) :
    RecyclerView.ViewHolder(itemView) {


    //vai tratar dos valores do layout
    fun bind(guest: GuestModel) {
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guest.name

        textName.setOnClickListener {
            listener.onClick(guest.id)
        }

        textName.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)

                .setTitle(R.string.remocao_convidado)
                .setMessage(R.string.deseja_remover)
                .setPositiveButton(R.string.remover) { dialog, which ->
                    listener.onDelete(guest.id)
                }
                .setNeutralButton(R.string.cancelar, null)
                .show()

            true
        }
    }
}