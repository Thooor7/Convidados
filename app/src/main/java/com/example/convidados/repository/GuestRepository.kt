package com.example.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.constants.Constants
import com.example.convidados.constants.Constants.GUEST.COLUMNS.NAME
import com.example.convidados.constants.Constants.GUEST.COLUMNS.PRESENCE
import com.example.convidados.model.GuestModel

class GuestRepository private constructor(context: Context) {

    private var guestDataBase = GuestDataBase(context)

    companion object{
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository{
            if(!::repository.isInitialized){
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest: GuestModel){
        try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(PRESENCE, presence)
            values.put(NAME, guest.name)

            db.insert(Constants.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }
}