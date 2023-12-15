package com.example.tp29_11

import android.provider.BaseColumns

object EtudiantBC {
    private const val TEXT_TYPE = " TEXT"
    private const val INT_TYPE = " INTEGER"
    private const val COMMA_SEP = ","
    const val SQL_CREATE_ENTRIES = "CREATE TABLE ${EtudiantEntry.TABLE_NAME} (_id  INTEGER PRIMARY KEY,nom STRING ,prenom STRING ,phone STRING,email STRING,login STRING,mdp STRING)"
    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + EtudiantEntry.TABLE_NAME

    object EtudiantEntry : BaseColumns {
        const val TABLE_NAME = "etudiant"
        const val COLUMN_NAME_NOM = "nom"
        const val COLUMN_NAME_PRENOM = "prenom"
        const val COLUMN_NAME_PHONE = "phone"
        const val COLUMN_NAME_EMAIL = "email"
        const val COLUMN_NAME_LOGIN = "login"
        const val COLUMN_NAME_MDP = "mdp"
    }
}
