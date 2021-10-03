package com.example.sqlite_tabllar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home_navigation.view.*

class Home_navigation : Fragment() {
    lateinit var root: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_home_navigation, container, false)

        root.employee_list.setOnClickListener {
            findNavController().navigate(R.id.action_home_navigation_to_employee_fragment)
        }

        root.costumer_list.setOnClickListener {
            findNavController().navigate(R.id.action_home_navigation_to_custumor_fragment)
        }

        root.orders_list.setOnClickListener {
            findNavController().navigate(R.id.action_home_navigation_to_orders_fragment)
        }

        return root

    }
}