package com.example.core_03_themedallists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDFrag : BottomSheetDialogFragment() {
    //variables:
    private lateinit var medalrecords: MedalRecords
    private lateinit var countryfrag: TextView
    private lateinit var ioccodefrag: TextView
    private lateinit var goldmedals :TextView
    private lateinit var silvermedals:TextView
    private lateinit var bronzemedals:TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var fragview = inflater.inflate(R.layout.bot_fragment,container,false)
        countryfrag = fragview.findViewById(R.id.countryfrag)
        ioccodefrag = fragview.findViewById(R.id.ioccodefrag)
        goldmedals = fragview.findViewById(R.id.goldmedals)
        silvermedals = fragview.findViewById(R.id.silvermedals)
        bronzemedals = fragview.findViewById(R.id.bronzemedals)
        //set variables
        countryfrag.text = medalrecords.country
        ioccodefrag.text = medalrecords.ioccode
        goldmedals.text = medalrecords.gold.toString()
        silvermedals.text=medalrecords.silver.toString()
        bronzemedals.text=medalrecords.bronze.toString()
        return fragview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if(bundle!=null)
        {
            medalrecords = bundle.get("data") as MedalRecords
        }
    }

}