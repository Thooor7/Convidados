package com.example.convidados.constants

class Constants private constructor(){

    companion object GUEST{
        const val GUEST = "Guest"
        const val GUEST_ID = "guestid"

        object COLUMNS{
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }
    }
}