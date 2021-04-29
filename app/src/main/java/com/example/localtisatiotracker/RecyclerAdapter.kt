package com.example.localtisatiotracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private var locationId: List<String>, private var location: List<Double>, private var location2: List<Double>):
        RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
            inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
                val itemId: TextView = itemView.findViewById(R.id.locationId)
                val itemLocation: TextView = itemView.findViewById(R.id.locationId)
                val itemLocation2: TextView = itemView.findViewById(R.id.locationId)
                init{
                    itemView.setOnClickListener { v: View ->
                        Toast.makeText(itemView.context,"You clicked on item # ${position + 1}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_localisation,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemId.text = locationId[position]
        holder.itemLocation.text = location[position].toString()
        holder.itemLocation2.text = location2[position].toString()
    }

    override fun getItemCount(): Int {
        return locationId.size
    }
}