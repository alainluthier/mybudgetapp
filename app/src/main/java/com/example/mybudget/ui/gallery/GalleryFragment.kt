package com.example.mybudget.ui.gallery


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import android.widget.Button

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mybudget.DBHandler
import com.example.mybudget.Expense
import com.example.mybudget.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val categories = arrayOf("Basic Services","Food","Clothes","Transport","Education","Tithing","Entertainment","Internet","Fix Home")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val category = binding.autoCategory
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(),
            android.R.layout.select_dialog_item, categories)
        category.threshold = 1
        category.setAdapter(adapter)

        /*val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        val db by lazy { DBHandler(requireContext(), null, null, 1) }
        val inputAmount = binding.inputAmount
        val description = binding.inputDescription
        val button: Button = binding.buttonSave
        button.setOnClickListener{ view ->
            val expense = Expense(inputAmount.text.toString().toFloat(),description.text.toString(),category.text.toString())
            db.addExpense(expense)
            Toast.makeText(requireContext(), "Your expense was saved" , Toast.LENGTH_SHORT ).show();
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}