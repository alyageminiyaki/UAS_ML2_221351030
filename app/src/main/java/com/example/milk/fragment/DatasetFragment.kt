package com.example.milk.fragment
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.milk.R // Import R class untuk resource
import com.example.milk.databinding.FragmentDatasetBinding // Sesuaikan dengan nama binding Fragment Dataset Anda

class DatasetFragment : Fragment() {

    private var _binding: FragmentDatasetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDatasetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengatur judul AppBar/Toolbar di Activity host
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.title_dataset)

        // Menangani klik pada tautan sumber dataset
        //binding.tvDatasetSourceLink.setOnClickListener {
            //val url = getString(R.string.dataset_source_url)
            //val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            //startActivity(intent)
        //}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
