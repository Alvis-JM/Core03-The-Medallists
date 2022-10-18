package com.example.core_03_themedallists


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val olympicsLists = findViewById<RecyclerView>(R.id.olympicslists)

        val data = mutableListOf<MedalRecords>()

        val readfile = resources.openRawResource(R.raw.medallists).bufferedReader()

        readfile.readLine() //ignore headers
        readfile.forEachLine {
            val temp = it.split(",")
            val countryrecord = MedalRecords(temp[0],
                temp[1],
                temp[2].toInt(),
                temp[3].toInt(),
                temp[4].toInt(),
                temp[5].toInt())

                data.add(countryrecord)
        }
        Log.i("SIZE", data.size.toString())

        olympicsLists.adapter = Adapter(data) { saveData(it) }
        olympicsLists.layoutManager = LinearLayoutManager(this)
    }

    fun saveData(item: MedalRecords) {
        val sharepref = this.getSharedPreferences("data", Context.MODE_PRIVATE) ?: return
        sharepref.edit().apply {
            putString("name", item.country)
            putString("code", item.ioccode)

        }.apply()
        btmfragpass(item)
    }

    private fun btmfragpass(item: MedalRecords): BottomSheetDFrag
    {

        val btmfrag = BottomSheetDFrag()
        val bundle = Bundle()
        bundle.putSerializable("data", item)
        btmfrag.arguments = bundle
        btmfrag.show(supportFragmentManager, "BottomSheetDialog")
        return btmfrag
    }

    //create the menu button on the top menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.lastclicked_menu, menu)
        return true

    }

    //get saved data from shared preferences
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.lastclicked -> {
                val transfer = Intent(this, DetailActivity::class.java)
                startActivity(transfer)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}