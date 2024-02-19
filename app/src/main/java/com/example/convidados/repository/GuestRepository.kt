package com.example.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.constants.Constants
import com.example.convidados.constants.Constants.GUEST.COLUMNS.ID
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

            db.insert(Constants.GUEST, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0
            val values = ContentValues()
            val selection = "id = ?"
            val args = arrayOf(guest.id.toString())

            values.put(PRESENCE, presence)
            values.put(NAME, guest.name)

            db.update(Constants.GUEST, values, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val selection = "id = ?"
            val args = arrayOf(guest.id.toString())

            db.delete(Constants.GUEST, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun  getAll(): List<GuestModel> {

        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val selection = arrayOf(
                ID,
                NAME,
                PRESENCE
            )

            val cursor = db.query(Constants.GUEST, selection, null,
                null, null, null, null)

            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(ID))
                    val name = cursor.getString(cursor.getColumnIndex(NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(PRESENCE))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }

            cursor.close()
        } catch (e: Exception){
            return list
        }
        return list
    }
}