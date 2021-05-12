package com.example.convidados02.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados02.R
import com.example.convidados02.service.model.GuestModel
import com.example.convidados02.view.listener.GuestListener
import com.example.convidados02.view.viewholder.GuestViewHolder

class GuestAdapter : RecyclerView.Adapter<GuestViewHolder>() {
    private var mGuesList:List<GuestModel> = arrayListOf()

    private lateinit var mListener: GuestListener

    //cria
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest,parent,false)
        return GuestViewHolder(item,mListener)
    }

    //atribui valor
    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
       holder.bind(mGuesList[position])
    }

    override fun getItemCount(): Int {
        return mGuesList.count()
    }

    fun  updateGuests(list: List<GuestModel>){
        mGuesList =  list
        notifyDataSetChanged()
    }

    fun attachListener(listener:GuestListener){

        mListener  = listener
    }

}