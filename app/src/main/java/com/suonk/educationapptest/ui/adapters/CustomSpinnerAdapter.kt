package com.suonk.educationapptest.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.suonk.educationapptest.databinding.CustomSpinnerRowBinding
import com.suonk.educationapptest.model.Module

class CustomSpinnerAdapter(
    val context: Context?,
    private val listOfModule: MutableList<Module>
) :
    BaseAdapter() {

    lateinit var binding: CustomSpinnerRowBinding
    private val inflater: LayoutInflater =
        context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        binding = CustomSpinnerRowBinding.inflate(inflater, parent, false)

        val itemHolder = ItemHolder(binding)

        val category = listOfModule[position]

        itemHolder.bind(category)

        return binding.root
    }

    override fun getCount(): Int {
        return listOfModule.size
    }

    override fun getItem(position: Int): Any {
        return listOfModule[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private class ItemHolder(private var binding: CustomSpinnerRowBinding) {
        fun bind(module: Module) {
            binding.spinnerRowModule.text = module.name
            binding.spinnerRowCoefficient.text = "Coefficient : ${module.coefficient}"
            binding.spinnerRowGradeValue.text = module.note

            if (module.note.toInt() < 10) {
                binding.spinnerRowGradeIconGood.visibility = View.INVISIBLE
                binding.spinnerRowGradeIconBad.visibility = View.VISIBLE
            }
        }
    }
}