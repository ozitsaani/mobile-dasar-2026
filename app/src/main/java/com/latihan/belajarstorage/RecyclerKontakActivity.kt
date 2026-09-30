package com.latihan.belajarstorage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.latihan.belajarstorage.model.Kontak

/**
 * Activity untuk menampilkan daftar kontak menggunakan RecyclerView.
 * Memenuhi kriteria FR-05, FR-06, FR-07, AC-04 sampai AC-08, serta Praktik 2 & 3 pada PRD.
 */
class RecyclerKontakActivity : AppCompatActivity() {

    private lateinit var rvKontak: RecyclerView
    private lateinit var kontakAdapter: KontakAdapter
    private lateinit var layoutEmpty: LinearLayout
    private lateinit var tvCountInfo: TextView

    // Data kontak sesuai spesifikasi PRD Bagian 5.2
    private val dataKontak = listOf(
        Kontak("Budi", "Halo, apa kabar?", "09:41", "#3B82F6"),
        Kontak("Siti", "Selamat pagi", "08:15", "#10B981"),
        Kontak("Andi", "Sampai jumpa", "Kemarin", "#8B5CF6"),
        Kontak("Rina", "Terima kasih", "Kemarin", "#F43F5E"),
        Kontak("Dimas", "Oke, siap", "28 Sep", "#F59E0B"),
        Kontak("Dewi", "Bisa kirim filenya sekarang?", "27 Sep", "#06B6D4"),
        Kontak("Fajar", "Pertemuan besok jam 10 pagi ya.", "25 Sep", "#6366F1"),
        Kontak("Alya", "Catatan kuliah sudah saya upload.", "24 Sep", "#EC4899")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_kontak)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainRecycler)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Hubungkan widget antarmuka
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        val etSearch = findViewById<EditText>(R.id.etSearch)
        rvKontak = findViewById(R.id.rvKontak)
        layoutEmpty = findViewById(R.id.layoutEmpty)
        tvCountInfo = findViewById(R.id.tvCountInfo)

        // Tombol kembali
        btnBack.setOnClickListener {
            finish()
        }

        // Setup LayoutManager (FR-07: Sistem menggunakan LinearLayoutManager)
        rvKontak.layoutManager = LinearLayoutManager(this)

        // Setup Adapter dan Event Klik Item (FR-02, FR-03, AC-08)
        kontakAdapter = KontakAdapter(dataKontak) { kontak ->
            // AC-08: Ketika item diklik, aplikasi memberikan respons berupa Toast
            Toast.makeText(
                this,
                "Kontak dipilih: ${kontak.nama} (${kontak.pesan})",
                Toast.LENGTH_SHORT
            ).show()
        }

        rvKontak.adapter = kontakAdapter
        updateCountInfo(kontakAdapter.itemCount)

        // Praktik 3 (Pengembangan Lanjutan): Pencarian Kontak
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s?.toString() ?: ""
                kontakAdapter.filter(query)

                val count = kontakAdapter.itemCount
                updateCountInfo(count)

                // Tampilkan empty state jika hasil pencarian kosong
                if (count == 0) {
                    rvKontak.visibility = View.GONE
                    layoutEmpty.visibility = View.VISIBLE
                } else {
                    rvKontak.visibility = View.VISIBLE
                    layoutEmpty.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun updateCountInfo(count: Int) {
        tvCountInfo.text = "$count Kontak Tersedia"
    }
}
