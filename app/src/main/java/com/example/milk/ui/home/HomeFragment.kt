package com.example.milk.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.milk.databinding.FragmentHomeBinding
import com.example.milk.R

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi ViewModel
        // homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java) // Aktifkan jika akan dipakai

        setupCardViewClicks()
    }

    private fun setupCardViewClicks() {
        // Menangani klik pada LinearLayout "Tentang"
        binding.llTentang.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_tentangFragment)
        }

        // Menangani klik pada LinearLayout "Fitur"
        binding.llFitur.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_fiturFragment)
        }

        // Menangani klik pada LinearLayout "Modeling"
        binding.llModeling.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_arsitekturModelFragment)
        }

        // Menangani klik pada LinearLayout "Dataset"
        binding.llDataset.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_datasetFragment)
        }

        // Menangani klik pada LinearLayout "Simulasi"
        binding.llSimulasi.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_simulasiFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
