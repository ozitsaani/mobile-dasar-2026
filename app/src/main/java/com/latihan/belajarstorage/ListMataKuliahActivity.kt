package com.latihan.belajarstorage

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Activity untuk menampilkan daftar mata kuliah menggunakan ListView dan ArrayAdapter.
 * Memenuhi kriteria FR-01, FR-02, FR-03, FR-04, AC-01, AC-02, AC-03, serta Praktik 1 pada PRD.
 */
class ListMataKuliahActivity : AppCompatActivity() {

    // Data mata kuliah sesuai contoh pada PRD Bagian 5.1 & 13.1
    private val daftarMataKuliah = arrayOf(
        "Pemrograman Web",
        "Sistem Operasi",
        "Pemrograman Mobile",
        "Basis Data",
        "Algoritma",
        "Rekayasa Perangkat Lunak",
        "Jaringan Komputer",
        "Kecerdasan Buatan",
        "Keamanan Informasi",
        "Interaksi Manusia dan Komputer"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_mata_kuliah)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainListView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Hubungkan widget antarmuka
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        val lvMataKuliah = findViewById<ListView>(R.id.lvMataKuliah)

        // Tombol kembali
        btnBack.setOnClickListener {
            finish()
        }

        // FR-02: Sistem menggunakan adapter (ArrayAdapter) untuk menghubungkan data dan UI
        val adapter = ArrayAdapter(
            this,
            R.layout.item_mata_kuliah,
            R.id.tvNamaMataKuliah,
            daftarMataKuliah
        )

        // Set adapter ke ListView
        lvMataKuliah.adapter = adapter

        // FR-03 & FR-04 & AC-03: Event klik item menampilkan nama item melalui Toast
        lvMataKuliah.setOnItemClickListener { _, _, position, _ ->
            val selectedMataKuliah = daftarMataKuliah[position]
            Toast.makeText(
                this,
                "Mata Kuliah dipilih: $selectedMataKuliah",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
