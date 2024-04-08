package com.example.mybudget.ui.expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mybudget.DBHandler
import com.example.mybudget.databinding.FragmentExpensesBinding
import java.text.SimpleDateFormat


class ExpensesFragment : Fragment() {

    private var _binding: FragmentExpensesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val expensesViewModel =
            ViewModelProvider(this).get(ExpensesViewModel::class.java)

        _binding = FragmentExpensesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val table=binding.table

        val db by lazy { DBHandler(requireContext(), null, null, 1) }
        val expenses = db.getAllExpenses()
        val param = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.MATCH_PARENT,
            1.0f
        )
        var nro = 0
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        for (e in expenses){
            val tr = TableRow(requireContext())
            nro += 1
            val tv1= TextView(requireContext())
            tv1.text=nro.toString()
            val tv2= TextView(requireContext())
            tv2.text=format.format(e.created)
            val tv3= TextView(requireContext())
            tv3.text=e.amount.toString()
            val tv4= TextView(requireContext())
            tv4.text=e.category
            tv1.layoutParams=param
            tv2.layoutParams=param
            tv3.layoutParams=param
            tv4.layoutParams=param
            tr.addView(tv1)
            tr.addView(tv2)
            tr.addView(tv3)
            tr.addView(tv4)
            table.addView(tr)
        }
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