package com.example.tp29_11

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditActivity : AppCompatActivity() {

    lateinit var id : String
    lateinit var nom : EditText
    lateinit var prenom : EditText
    lateinit var tel : EditText
    lateinit var email : EditText
    lateinit var login : EditText
    lateinit var mdp : EditText
    lateinit var submitBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        nom = findViewById(R.id.EditNomEditText);
        prenom = findViewById(R.id.PrenomEditText);
        tel = findViewById(R.id.TelEditText);
        email = findViewById(R.id.EmailEditText);
        login = findViewById(R.id.LoginEditText);
        mdp = findViewById(R.id.MdpEditText);
        submitBtn = findViewById(R.id.ValiderUpdatebutton)

        val intent = intent

        id = intent.getStringExtra("id")!!
        nom.setText(intent.getStringExtra("nom"))
        prenom.setText(intent.getStringExtra("prenom") )
        tel.setText(intent.getStringExtra("tel") )
        email.setText(intent.getStringExtra("email") )
        login.setText(intent.getStringExtra("login") )
        mdp.setText(intent.getStringExtra("mdp") )

        Log.d("item",intent.getStringExtra("nom")!!)

        submitBtn.setOnClickListener{


            var dbHelper = EtudiantDBHelper(this@EditActivity)
            val db = dbHelper.writableDatabase


           val values = ContentValues();
            values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_NOM,nom.text.toString())
            values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_PRENOM,prenom.text.toString())
            values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_PHONE,tel.text.toString())
            values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_EMAIL,email.text.toString())
            values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_LOGIN,login.text.toString())
            values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_MDP,mdp.text.toString())

            val rowsAffected =  db.update("etudiant", values, "_id=?", arrayOf(id.toString()));

            //Log.d("EditActivity", "Rows affected: $rowsAffected")

            db.close();

            val intent = Intent(this@EditActivity, ListEtudiantActivity::class.java)
            startActivity(intent)
        }

        }
}