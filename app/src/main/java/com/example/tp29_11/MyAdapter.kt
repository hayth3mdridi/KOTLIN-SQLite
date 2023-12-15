package com.example.tp29_11

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val myDataSet: Array<Etudiant>):
    RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    class ViewHolder(val itemview: View):
        RecyclerView.ViewHolder(itemview){
        val vText = itemView.findViewById(R.id.nometud) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ViewHolder {
        val vh = LayoutInflater.from(parent.context).inflate(R.layout.activity_list_etudiant,
            parent,false)
        return ViewHolder(vh)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var current = myDataSet[position]
        holder.vText.text = current.prenom.toString()
    }

    override fun getItemCount(): Int {
        return myDataSet.size
    }

}