package com.example.milk.fragment

import android.content.ContentValues.TAG
import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.milk.R
import com.example.milk.databinding.FragmentSimulasiBinding
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class SimulasiFragment : Fragment() {

    private var _binding: FragmentSimulasiBinding? = null
    private val binding get() = _binding!!

    private lateinit var interpreter: Interpreter
    private val mModelPath = "milk.tflite"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Logika inisialisasi ViewModel atau data non-UI lainnya bisa di sini jika diperlukan.
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout untuk Fragment ini menggunakan View Binding
        _binding = FragmentSimulasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi interpreter TFLite
        initInterpreter()

        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.title_simulasi)

        // Menangani klik pada tombol prediksi
        binding.btnCheck.setOnClickListener {
            try {
                // Panggil doInference dengan input dari EditTexts
                val result = doInference(
                    binding.etpH.text.toString(),
                    binding.etTemperature.text.toString(),
                    binding.etTaste.text.toString(),
                    binding.etOdor.text.toString(),
                    binding.etFat.text.toString()
                    // Pastikan Anda juga mengaktifkan edTurbidity dan edColour jika diperlukan
                    // binding.etTurbidity.text.toString(),
                    // binding.etColour.text.toString()
                )

                // Update UI di main thread
                // Di dalam setOnClickListener, kode sudah berjalan di main thread,
                // jadi runOnUiThread {} tidak selalu mutlak diperlukan kecuali
                // doInference() berjalan di background thread.
                // Namun, kita akan memastikan outputnya di main thread.
                // Ini akan langsung diupdate di main thread karena event listener ada di main thread
                when (result) {
                    0 -> binding.tvResult.text = "low"
                    1 -> binding.tvResult.text = "high"
                    else -> binding.tvResult.text = "medium"
                }

            } catch (e: IllegalArgumentException) {
                // Menangani error jika input tidak valid
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                binding.tvResult.text = "Error Input" // Atau bersihkan teks
                Log.e(TAG, "Input validation error: ${e.message}")
            } catch (e: Exception) {
                // Menangani error umum selama inferensi model
                Toast.makeText(requireContext(), "Terjadi kesalahan saat simulasi: ${e.message}", Toast.LENGTH_LONG).show()
                binding.tvResult.text = "Error Simulasi"
                Log.e(TAG, "Error during inference: ${e.message}", e)
            }
        }
    }

    private fun initInterpreter() {
        val options = Interpreter.Options()
        options.setNumThreads(5)
        options.setUseNNAPI(true)
        // Menggunakan requireContext().assets untuk mengakses AssetManager dari Fragment
        interpreter = Interpreter(loadModelFile(requireContext().assets, mModelPath), options)
    }

    // Mengubah jumlah input menjadi 5 sesuai dengan parameter doInference
    private fun doInference(
        input1: String, input2: String, input3: String,
        input4: String, input5: String
    ): Int {
        val inputs = listOf(input1, input2, input3, input4, input5)
        // Memeriksa apakah ada input yang kosong atau tidak bisa diubah ke float
        if (inputs.any { it.isBlank() || it.toFloatOrNull() == null }) {
            throw IllegalArgumentException("Semua input harus valid dan terisi angka.")
        }

        // Membuat FloatArray dengan ukuran yang sesuai (5 input)
        val inputVal = FloatArray(5)
        inputVal[0] = input1.toFloat()
        inputVal[1] = input2.toFloat()
        inputVal[2] = input3.toFloat()
        inputVal[3] = input4.toFloat()
        inputVal[4] = input5.toFloat()

        // Output model: Array(batch_size) { FloatArray(jumlah_kelas) }
        // Asumsi output model adalah 3 kelas (low, high, medium)
        val output = Array(1) { FloatArray(3) }

        // Menjalankan inferensi model
        // InputVal akan otomatis dikonversi ke ByteBuffer oleh interpreter.run jika tipenya FloatArray
        interpreter.run(inputVal, output)

        // Logging hasil output model (untuk debugging)
        Log.e(TAG, "Result: ${output[0].toList()}")

        // Mengembalikan indeks dari nilai tertinggi (probabilitas tertinggi)
        // Jika model mengembalikan probabilitas, ini akan mendapatkan kelas dengan probabilitas tertinggi.
        return output[0].indexOfFirst { it == output[0].maxOrNull() }
    }

    // loadModelFile sudah benar, menggunakan AssetManager
    private fun loadModelFile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        // Tutup interpreter jika tidak lagi dibutuhkan (opsional, tergantung siklus hidup aplikasi)
        // interpreter.close()
    }
}
