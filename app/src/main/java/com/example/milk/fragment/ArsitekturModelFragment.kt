package com.example.milk.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.milk.R
import com.example.milk.databinding.FragmentArsitekturModelBinding

class ArsitekturModelFragment : Fragment() {

    private var _binding: FragmentArsitekturModelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArsitekturModelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.title_arsitektur_model)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
