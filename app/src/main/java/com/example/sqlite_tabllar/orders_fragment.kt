package com.example.sqlite_tabllar

import DB.MyDBHelper
import adapters.RecycleOrders
import android.os.Bundle
import android.service.media.MediaBrowserService
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_orders_fragment.*
import kotlinx.android.synthetic.main.fragment_orders_fragment.view.*
import models.Customer
import models.Employee
import models.Orders

class orders_fragment : Fragment() {
    lateinit var root: View
    lateinit var recycleOrders: RecycleOrders
    lateinit var listCustomer: ArrayList<Customer>
    lateinit var listEmployee: ArrayList<Employee>
    lateinit var myDBHelper: MyDBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_orders_fragment, container, false)
        myDBHelper = MyDBHelper(root.context)
        root.save_btn.setOnClickListener {
            val positionCus = root.spinner_costumer.selectedItemPosition
            val positionEmp = root.spinner_employee.selectedItemPosition
            myDBHelper.addOrder(Orders(listCustomer[positionCus], listEmployee[positionEmp]))
            Toast.makeText(root.context, "SAVE", Toast.LENGTH_SHORT).show()
            onResume()
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        myDBHelper = MyDBHelper(root.context)
        listCustomer = ArrayList()
        listEmployee = ArrayList()

        listEmployee.addAll(myDBHelper.getAllEmploy())
        listCustomer.addAll(myDBHelper.getAllCustomer())

        val lisrCName = ArrayList<String>()
        val lisrEName = ArrayList<String>()

        for (i in listEmployee) {
            lisrEName.add(i.name!!)
        }
        for (i in listCustomer) {
            lisrCName.add(i.name!!)
        }

        val spinnerAdapterC =
            ArrayAdapter(root.context, android.R.layout.simple_list_item_1, lisrCName)
        val spinnerAdapterE =
            ArrayAdapter(root.context, android.R.layout.simple_list_item_1, lisrEName)

        root.spinner_costumer.adapter = spinnerAdapterC
        root.spinner_employee.adapter = spinnerAdapterE

        val list = ArrayList<Orders>()
        list.addAll(myDBHelper.getAllOrder())
        recycleOrders = RecycleOrders(list)
        root.recycle_view_orders.adapter = recycleOrders
    }
}