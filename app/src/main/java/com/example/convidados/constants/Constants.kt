package com.example.convidados.constants

class Constants private constructor(){

    companion object GUEST{
        const val TABLE_NAME = "Guest"

        object COLUMNS{
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }
    }
}