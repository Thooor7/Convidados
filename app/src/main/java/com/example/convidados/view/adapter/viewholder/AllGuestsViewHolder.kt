package com.example.convidados.view.adapter.viewholder

import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.RowGuestsBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.view.OnGuestListener

class AllGuestsViewHolder(private val bind: RowGuestsBinding, private val listener: OnGuestListener) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel){
        bind.textName.text = guest.name

       bind.textName.setOnClickListener {
           listener.onClick(guest.id)
       }
        bind.textName.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Você tem certeza que quer remover ${guest
                    .name}?")
                .setPositiveButton("Sim") { dialogInterface, wich ->
                    listener.delete(guest.id)
                }
                .setNegativeButton("Não", null)
                .create()
                .show()
            true
        }
    }

}