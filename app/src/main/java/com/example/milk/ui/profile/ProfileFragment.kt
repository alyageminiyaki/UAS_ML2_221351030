package com.example.milk.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.milk.R
import com.example.milk.databinding.FragmentProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupBottomNavigationBar() {
        val bottomNavView = (activity as? AppCompatActivity)?.findViewById<BottomNavigationView>(R.id.mobile_navigation)

        bottomNavView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    Toast.makeText(requireContext(), "Home Clicked from Nav Bar", Toast.LENGTH_SHORT).show()
                    // Logika navigasi untuk item Home (misalnya menggunakan NavController dari MainActivity)
                    true
                }
                R.id.navigation_profile -> {
                    Toast.makeText(requireContext(), "Profile Clicked from Nav Bar", Toast.LENGTH_SHORT).show()
                    // Logika navigasi untuk item Profile
                    true
                }
                else -> false
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title = "Profil Mahasiswa"

        binding.tvName.text = " : De Putri Rahma Aliyah"
        binding.tvNIM.text = " : 221351030"
        binding.tvClass.text = " : IF/P/C"
        binding.tvCourse.text = " : Machine Learning II"
        binding.tvAppName.text = "Aplikasi Prediksi Kualitas Susu"

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
