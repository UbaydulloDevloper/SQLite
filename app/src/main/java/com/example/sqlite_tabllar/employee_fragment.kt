package com.example.sqlite_tabllar

import DB.MyDBHelper
import adapters.RecycleEmployee
import android.os.Bundle
import android.service.media.MediaBrowserService
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_employee_fragment.*
import kotlinx.android.synthetic.main.fragment_employee_fragment.view.*
import models.Employee

class employee_fragment : Fragment() {
    lateinit var root: View
    lateinit var myDBHelper: MyDBHelper
    lateinit var recycleEmployee: RecycleEmployee
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_employee_fragment, container, false)

        root.save_btn.setOnClickListener {
            val trim = root.text_name.text.toString().trim()
            root.text_name.text = null
            myDBHelper.addEmploy(Employee(trim))
            Toast.makeText(root.context, "SAVE", Toast.LENGTH_SHORT).show()
            onResume()
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        myDBHelper = MyDBHelper(root.context)
        val list = ArrayList<Employee>()
        list.addAll(myDBHelper.getAllEmploy())
        recycleEmployee = RecycleEmployee(list)
        recycleView_Employee.adapter = recycleEmployee

    }

}