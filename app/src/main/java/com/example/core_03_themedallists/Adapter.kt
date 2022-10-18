package com.example.core_03_themedallists

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val data: List<MedalRecords>, private val listener: (MedalRecords)-> Unit): RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.layout_row, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        val data_import = data[position] //Grab data
        holder.displayRow(data_import)
    }

    inner class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        private val total: TextView = v.findViewById(R.id.totalmedals) //the total medals
        private val ioccode: TextView = v.findViewById(R.id.ioc)
        private val countryname: TextView = v.findViewById(R.id.country)


        fun displayRow(item: MedalRecords) {
            val totalmedal = item.bronze + item.silver + item.gold
            total.text = totalmedal.toString()
            ioccode.text = item.ioccode
            countryname.text = item.country
            Log.i("countrycode", totalmedal.toString())
            if(totalmedal==0)
            {
                countryname.setTextColor(Color.parseColor("#7a7a7a"));

                total.setTextColor(Color.parseColor("#7a7a7a"));

                ioccode.setTextColor(Color.parseColor("#7a7a7a"));

            }
            else
            {
                countryname.setTextColor(Color.parseColor("#328580"));

                total.setTextColor(Color.parseColor("#328580"));

                ioccode.setTextColor(Color.parseColor("#328580"));
            }
            Log.i("countrycode", item.ioccode)
            if (item.silver<item.gold)
            {
                countryname.setTextColor(Color.parseColor("#9d7b0c"));

                total.setTextColor(Color.parseColor("#9d7b0c"));

                ioccode.setTextColor(Color.parseColor("#9d7b0c"));
            }
            else if (item.bronze<item.silver)
            {
                countryname.setTextColor(Color.parseColor("#723756"));

                total.setTextColor(Color.parseColor("#723756"));

                ioccode.setTextColor(Color.parseColor("#723756"));
            }
            v.setOnClickListener()
            {
                listener(item)
            }
        }

    }
}