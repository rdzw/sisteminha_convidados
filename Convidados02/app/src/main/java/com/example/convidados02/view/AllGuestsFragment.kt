package com.example.convidados02.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados02.R
import com.example.convidados02.constants.GuestConstants
import com.example.convidados02.view.adapter.GuestAdapter
import com.example.convidados02.view.listener.GuestListener
import com.example.convidados02.viewmodel.GuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var mViewModel: GuestsViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)

        //essa varialvel root ja criar a tela
        val root = inflater.inflate(R.layout.fragment_all, container, false)


        // RecyclerView
        // 1 - obter a recycler
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)
        // 2 - definir um layout
        recycler.layoutManager = LinearLayoutManager(context)
        // 3 - Definir um adapter
        recycler.adapter = mAdapter

        ////////////////
        mListener = object : GuestListener {
            override fun onClick(id: Int) {

                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.EMPTY)
            }

        }

        mAdapter.attachListener(mListener)
        observer()

        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(GuestConstants.FILTER.EMPTY)
    }

    private fun observer() {
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
}