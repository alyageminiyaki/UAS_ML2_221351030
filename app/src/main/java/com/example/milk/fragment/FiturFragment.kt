package com.example.milk.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.milk.R
import com.example.milk.databinding.FragmentFiturBinding

class FiturFragment : Fragment() {
    private var _binding: FragmentFiturBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFiturBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.title_fitur)

        // Mengisi detail untuk Fitur 1: pH Susu
        Glide.with(this)
            .load(R.drawable.ic_feature_ph)
            .circleCrop()
            .into(binding.featureIconpH)
        binding.featureTitlepH.text = getString(R.string.feature_title_ph)
        binding.featureDescriptionpH.text = getString(R.string.feature_desc_ph)

        // Mengisi detail untuk Fitur 2: Suhu Susu
        Glide.with(this)
            .load(R.drawable.ic_feature_temperature)
            .circleCrop()
            .into(binding.featureIconTemperature)
        binding.featureTitleTemperature.text = getString(R.string.feature_title_temperature)
        binding.featureDescriptionTemperature.text = getString(R.string.feature_desc_temperature)

        // Mengisi detail untuk Fitur 3: Rasa Susu
        Glide.with(this)
            .load(R.drawable.ic_feature_taste)
            .circleCrop()
            .into(binding.featureIconTaste)
        binding.featureTitleTaste.text = getString(R.string.feature_title_taste)
        binding.featureDescriptionTaste.text = getString(R.string.feature_desc_taste)

        // Mengisi detail untuk Fitur 4: Bau/Aroma Susu
        Glide.with(this)
            .load(R.drawable.ic_feature_odor)
            .circleCrop()
            .into(binding.featureIconOdor)
        binding.featureTitleOdor.text = getString(R.string.feature_title_odor)
        binding.featureDescriptionOdor.text = getString(R.string.feature_desc_odor)

        // Mengisi detail untuk Fitur 5: Lemak Susu
        Glide.with(this)
            .load(R.drawable.ic_feature_fat)
            .circleCrop()
            .into(binding.featureIconFat)
        binding.featureTitleFat.text = getString(R.string.feature_title_fat)
        binding.featureDescriptionFat.text = getString(R.string.feature_desc_fat)

        // Mengisi detail untuk Fitur 6: Kekeruhan Susu
        Glide.with(this)
            .load(R.drawable.ic_feature_turbidity)
            .circleCrop()
            .into(binding.featureIconTurbidity)
        binding.featureTitleTurbidity.text = getString(R.string.feature_title_turbidity)
        binding.featureDescriptionTurbidity.text = getString(R.string.feature_desc_turbidity)

        // Mengisi detail untuk Fitur 7: Warna Susu
        Glide.with(this)
            .load(R.drawable.ic_feature_colour)
            .circleCrop()
            .into(binding.featureIconColour)
        binding.featureTitleColour.text = getString(R.string.feature_title_colour)
        binding.featureDescriptionColour.text = getString(R.string.feature_desc_colour)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
