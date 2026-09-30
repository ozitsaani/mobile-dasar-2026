package com.latihan.belajarstorage

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.latihan.belajarstorage.model.Kontak

/**
 * Adapter untuk menampilkan daftar kontak pada RecyclerView.
 * Mengimplementasikan ViewHolder dan event klik sesuai ketentuan PRD (Praktik 2 & 3).
 */
class KontakAdapter(
    private val originalList: List<Kontak>,
    private val onItemClick: (Kontak) -> Unit
) : RecyclerView.Adapter<KontakAdapter.KontakViewHolder>() {

    // List yang ditampilkan di UI (dapat berubah ketika ada pencarian/filter)
    private var displayedList: MutableList<Kontak> = originalList.toMutableList()

    /**
     * ViewHolder memegang referensi view dari item_contact.xml
     */
    inner class KontakViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNama: TextView = itemView.findViewById(R.id.tvNamaKontak)
        val tvPesan: TextView = itemView.findViewById(R.id.tvPesanKontak)
        val tvWaktu: TextView = itemView.findViewById(R.id.tvWaktu)
        val tvInisial: TextView = itemView.findViewById(R.id.tvInisial)
        val avatarContainer: FrameLayout = itemView.findViewById(R.id.avatarContainer)

        fun bind(kontak: Kontak) {
            tvNama.text = kontak.nama
            tvPesan.text = kontak.pesan
            tvWaktu.text = kontak.waktu

            // Menampilkan huruf inisial dari nama
            val inisial = if (kontak.nama.isNotEmpty()) kontak.nama.first().uppercase() else "?"
            tvInisial.text = inisial

            // Mewarnai lingkaran avatar sesuai warna pada model
            try {
                val colorInt = Color.parseColor(kontak.avatarColor)
                val drawable = GradientDrawable().apply {
                    shape = GradientDrawable.OVAL
                    setColor(colorInt)
                }
                avatarContainer.background = drawable
            } catch (e: Exception) {
                // Gunakan background default jika parsing gagal
            }

            // Event klik item sesuai PRD (FR-03, AC-08)
            itemView.setOnClickListener {
                onItemClick(kontak)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KontakViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return KontakViewHolder(view)
    }

    override fun onBindViewHolder(holder: KontakViewHolder, position: Int) {
        val kontak = displayedList[position]
        holder.bind(kontak)
    }

    override fun getItemCount(): Int = displayedList.size

    /**
     * Fitur Pengembangan Lanjutan (Praktik 3): Pencarian Kontak
     */
    fun filter(query: String) {
        displayedList.clear()
        if (query.trim().isEmpty()) {
            displayedList.addAll(originalList)
        } else {
            val lowercaseQuery = query.lowercase().trim()
            val filtered = originalList.filter {
                it.nama.lowercase().contains(lowercaseQuery) ||
                        it.pesan.lowercase().contains(lowercaseQuery)
            }
            displayedList.addAll(filtered)
        }
        notifyDataSetChanged()
    }
}
