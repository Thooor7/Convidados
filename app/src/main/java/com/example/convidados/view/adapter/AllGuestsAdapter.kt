package com.example.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.RowGuestsBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.view.OnGuestListener
import com.example.convidados.view.adapter.viewholder.AllGuestsViewHolder

class AllGuestsAdapter: RecyclerView.Adapter<AllGuestsViewHolder>() {

    private var guestList: List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllGuestsViewHolder {
        val item = RowGuestsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllGuestsViewHolder(item, listener)

        }

    override fun onBindViewHolder(holder: AllGuestsViewHolder, position: Int) {
       holder.bind(guestList[position])
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    fun updateGuests(list: List<GuestModel>){
        guestList = list
        notifyDataSetChanged()
    }

    fun attachListener(guestListener: OnGuestListener){
        listener = guestListener
    }
}