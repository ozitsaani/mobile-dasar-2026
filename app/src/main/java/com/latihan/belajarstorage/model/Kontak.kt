package com.latihan.belajarstorage.model

/**
 * Model data class Kontak sesuai spesifikasi PRD Mobile List App.
 * Menyimpan data nama, pesan, waktu, serta warna representasi avatar profil.
 */
data class Kontak(
    val nama: String,
    val pesan: String,
    val waktu: String = "10:00",
    val avatarColor: String = "#2563EB"
)
