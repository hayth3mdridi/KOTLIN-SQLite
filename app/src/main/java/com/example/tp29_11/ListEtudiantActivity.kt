package com.example.tp29_11

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup as ViewGroup1

class ListEtudiantActivity : AppCompatActivity() {

    private var adapter: SimpleCursorAdapter? = null
    private var dbHelper: EtudiantDBHelper? = null

   // @SuppressLint("MissingInflatedId")

    private lateinit var ListEtudiant : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list_etudiant);

        ListEtudiant = findViewById(R.id.idlistetu)
        ListEtudiant.setAdapter(getAdapter())


    }

    fun getDbHelper(): EtudiantDBHelper? {
        if (dbHelper == null) {
            dbHelper = EtudiantDBHelper(this)
        }
        return dbHelper
    }

    private fun getAdapter(): SimpleCursorAdapter? {

        if (adapter == null) {
            val db = getDbHelper()?.readableDatabase
            val c = db?.rawQuery("SELECT * FROM etudiant", null)
            print(c)
            adapter = CustomAdapter(
                this,
                R.layout.ligne_etudiant,
                c,
                arrayOf(
                    EtudiantBC.EtudiantEntry.COLUMN_NAME_NOM,
                    EtudiantBC.EtudiantEntry.COLUMN_NAME_PRENOM
                ),
                intArrayOf(R.id.nometud, R.id.preetud),
                0
            )


        }
        return adapter!!
    }


    inner class CustomAdapter(
        context: Context?,
        layout: Int,
        c: Cursor?,
        from: Array<out String>?,
        to: IntArray?,
        i: Int,

    ) : SimpleCursorAdapter(context, layout, c, from, to) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup1?): View {
            return super.getView(position, convertView, parent).apply {
                if (convertView == null) {
                    findViewById<View>(R.id.editButton).setOnClickListener {

                        val cursor = getItem(position) as Cursor
                        val id = cursor.getLong(cursor.getColumnIndexOrThrow("_id"))
                        val nom = cursor.getString(cursor.getColumnIndexOrThrow("nom"))
                        val prenom = cursor.getString(cursor.getColumnIndexOrThrow("prenom"))
                        val tel = cursor.getString(cursor.getColumnIndexOrThrow("phone"))
                        val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
                        val login = cursor.getString(cursor.getColumnIndexOrThrow("login"))
                        val mdp = cursor.getString(cursor.getColumnIndexOrThrow("mdp"))


                        val intent = Intent(this@ListEtudiantActivity, EditActivity::class.java)
                        intent.putExtra("id",id.toString())
                        intent.putExtra("nom",nom)
                        intent.putExtra("prenom",prenom)
                        intent.putExtra("tel",tel)
                        intent.putExtra("email",email)
                        intent.putExtra("login",login)
                        intent.putExtra("mdp",mdp)
                        startActivity(intent)
                    }

                    findViewById<View>(R.id.deleteButton).setOnClickListener {


                        val alertDialog: AlertDialog = AlertDialog.Builder(this@ListEtudiantActivity).create()

                        alertDialog.setTitle("Attention")
                        alertDialog.setMessage("Voulez vous vraiment le supprimer ?")
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO") { dialog, _ ->
                            dialog.dismiss()
                        }
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OUI") { dialog, _ ->


                            val mDbHelper = EtudiantDBHelper(context)
                            val db = mDbHelper.writableDatabase
                            val itemId = getItemId(position)
                            val result = db.delete("etudiant", "_id=?", arrayOf(itemId.toString()))
                            db.close()
                            notifyDataSetChanged()


                            val intent = Intent(this@ListEtudiantActivity, ListEtudiantActivity::class.java)
                            startActivity(intent)
                            finish()

                        }
                        alertDialog.show()
                    }
                }
            }
        }
    }


}
