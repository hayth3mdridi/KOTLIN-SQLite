package com.example.tp29_11

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var nom : EditText
    lateinit var prenom : EditText
    lateinit var tel : EditText
    lateinit var email : EditText
    lateinit var login : EditText
    lateinit var mdp : EditText
    lateinit var submitBtn : Button
    lateinit var cancelbutton : Button
    lateinit var  listBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nom = findViewById(R.id.NomEditText);
        prenom = findViewById(R.id.PrenomEditText);
        tel = findViewById(R.id.TelEditText);
        email = findViewById(R.id.EmailEditText);
        login = findViewById(R.id.LoginEditText);
        mdp = findViewById(R.id.MdpEditText);
        submitBtn = findViewById(R.id.Validerbutton);
        cancelbutton = findViewById(R.id.Cancelbutton)
        listBtn = findViewById(R.id.listBtn)

        listBtn.setOnClickListener{
            val intent = Intent(this@MainActivity, ListEtudiantActivity::class.java)

            startActivity(intent)
        }

        cancelbutton.setOnClickListener{
            val alertDialog: AlertDialog = AlertDialog.Builder(this).create()

            alertDialog.setTitle("Attention")
            alertDialog.setMessage("Voulez vous vraiment remettre a zero les champs")
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO") { dialog, _ ->
                dialog.dismiss()
            }
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OUI") { dialog, _ ->
                nom.setText("");
                prenom.setText("");
                tel.setText("");
                login.setText("");
                mdp.setText("");
                email.setText("")
                dialog.dismiss()
            }

            alertDialog.show()
        }

        submitBtn.setOnClickListener{

            if(nom.text.toString() == ""
                || prenom.text.toString() == "" ||
                tel.text.toString() == ""||
                login.text.toString() == "" ||
                mdp.text.toString() == "" ||
                email.text.toString() == ""
                ) {
                        val alertDialog: AlertDialog = AlertDialog.Builder(this).create()

                        alertDialog.setTitle("Attention")
                        alertDialog.setMessage("Tous les champs doivent etre remplis.")
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") { dialog, _ ->
                            dialog.dismiss()
                        }

                        alertDialog.show()
            }else{
                val values = ContentValues();
                values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_NOM,nom.text.toString())
                values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_PRENOM,prenom.text.toString())
                values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_PHONE,tel.text.toString())
                values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_EMAIL,email.text.toString())
                values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_LOGIN,login.text.toString())
                values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_MDP,mdp.text.toString())
                val mDbHelper = EtudiantDBHelper(applicationContext)
                val db = mDbHelper.writableDatabase
                var newRowId: Long
                newRowId = db.insert(EtudiantBC.EtudiantEntry.TABLE_NAME,null,values);
                db.close();
                mDbHelper.close();

                val intent = Intent(this@MainActivity, ListEtudiantActivity::class.java)

                startActivity(intent)
            }
        }
    }
}