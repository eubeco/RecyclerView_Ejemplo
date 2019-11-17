package com.example.recyclerview_ejemplo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val items = ArrayList<Tarjeta>()
        items.add(Tarjeta(R.string.note1))
        items.add(Tarjeta(R.string.note2))
        items.add(Tarjeta(R.string.note3))
        items.add(Tarjeta(R.string.note4))
        items.add(Tarjeta(R.string.note5))
        items.add(Tarjeta(R.string.note6))

        val recView = findViewById<RecyclerView>(R.id.recyclerView)

        recView.setHasFixedSize(true)

        val adaptador = CardsAdapter(items)
        recView.adapter = adaptador
        /*recView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)*/
        recView.setLayoutManager(StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL))

        adaptador.onClick = {
            Toast.makeText(this@MainActivity, ""+ recView.getChildAdapterPosition(it), Toast.LENGTH_LONG).show()
        }

        recView.itemAnimator = DefaultItemAnimator()

        val anyadir = findViewById(R.id.button1) as Button
        anyadir.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                items.add(1, Tarjeta(R.string.note7))
                adaptador.notifyItemInserted(1)
            }
        })
        val eliminar = findViewById(R.id.button2) as Button
        eliminar.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                items.removeAt(1)
                adaptador.notifyItemRemoved(1)
            }
        })
        val cambiar = findViewById(R.id.button3) as Button
        cambiar.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val auxiliar = items[0]
                items[0] = items[1]
                items[1] = auxiliar
                adaptador.notifyItemChanged(0, 1)
            }
        })
    }
}
