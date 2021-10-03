package com.example.sqlite_tabllar

import DB.MyDBHelper
import adapters.RecycleCustomer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_custumor_fragment.view.*
import models.Customer

class custumor_fragment : Fragment() {
    lateinit var root: View
    lateinit var myDBHelper: MyDBHelper
    lateinit var recycleCustomer: RecycleCustomer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_custumor_fragment, container, false)

        root.save_btn_custom.setOnClickListener {
            val trim = root.text_name.text.toString().trim()
            root.text_name.text = null
            myDBHelper.addCustomer(Customer(trim))
            Toast.makeText(root.context, "SAVE", Toast.LENGTH_SHORT).show()
            onResume()
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        myDBHelper = MyDBHelper(root.context)
        val list = ArrayList<Customer>()
        list.addAll(myDBHelper.getAllCustomer())
        recycleCustomer = RecycleCustomer(list)
        root.recycleView.adapter = recycleCustomer

    }
}