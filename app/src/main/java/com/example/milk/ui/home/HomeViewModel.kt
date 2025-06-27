package com.example.milk.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _aboutPageDescription = MutableLiveData<String>()
    val aboutPageDescription: LiveData<String> = _aboutPageDescription

    init {
        // Blok init dipanggil ketika instance ViewModel pertama kali dibuat
        _aboutPageDescription.value = "Milk App adalah aplikasi inovatif untuk manajemen produk susu, menyediakan informasi lengkap dan simulasi terkait produk susu."
    }

    // ViewModel ini akan menyimpan data yang relevan dengan HomeFragment
    // seperti daftar fitur, data arsitektur model, atau status simulasi.
    // Metode-metode di sini akan berisi logika bisnis dan fetching data.
}
