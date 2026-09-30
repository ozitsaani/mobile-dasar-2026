package com.latihan.belajarstorage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Menu Utama Aplikasi Mobile List App.
 * Menyediakan navigasi ke Praktik 1 (ListView) dan Praktik 2 & 3 (RecyclerView),
 * serta akses ke latihan penyimpanan data sebelumnya.
 */
class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnMenuListView = findViewById<Button>(R.id.btnMenuListView)
        val btnMenuRecyclerView = findViewById<Button>(R.id.btnMenuRecyclerView)
        val btnMenuOld1 = findViewById<Button>(R.id.btnMenuOld1)
        val btnMenuOld2 = findViewById<Button>(R.id.btnMenuOld2)

        // Navigasi ke Praktik 1: ListView (Daftar Mata Kuliah)
        btnMenuListView.setOnClickListener {
            val intent = Intent(this, ListMataKuliahActivity::class.java)
            startActivity(intent)
        }

        // Navigasi ke Praktik 2 & 3: RecyclerView (Daftar Kontak)
        btnMenuRecyclerView.setOnClickListener {
            val intent = Intent(this, RecyclerKontakActivity::class.java)
            startActivity(intent)
        }

        // Navigasi ke Latihan Sebelumnya: Simpan 1 Data
        btnMenuOld1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Navigasi ke Latihan Sebelumnya: Simpan Banyak Data
        btnMenuOld2.setOnClickListener {
            val intent = Intent(this, MultiDataActivity::class.java)
            startActivity(intent)
        }
    }
}