package com.example.mybudget.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mybudget.databinding.FragmentSlideshowBinding


class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val table=binding.table

        val param = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.MATCH_PARENT,
            1.0f
        )
        val tr = TableRow(requireContext())

        val tv1= TextView(requireContext())
        tv1.text="A"
        val tv2= TextView(requireContext())
        tv2.text="B"
        val tv3= TextView(requireContext())
        tv3.text="C"
        val tv4= TextView(requireContext())
        tv4.text="D"
        tv1.layoutParams=param
        tv2.layoutParams=param
        tv3.layoutParams=param
        tv4.layoutParams=param
        tr.addView(tv1)
        tr.addView(tv2)
        tr.addView(tv3)
        tr.addView(tv4)
        table.addView(tr)
        /*val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}