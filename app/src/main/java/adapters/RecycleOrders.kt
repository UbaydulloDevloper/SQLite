package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite_tabllar.R
import kotlinx.android.synthetic.main.item_rv_orders.view.*
import models.Orders

class RecycleOrders(var list: List<Orders>) : RecyclerView.Adapter<RecycleOrders.Vh>() {
    inner class Vh(var itemRv: View) : RecyclerView.ViewHolder(itemRv.rootView) {
        fun onBind(names: Orders) {
            itemRv.customer_text.text = names.custumor?.name
            itemRv.employee_text.text = names.employee?.name
            itemRv.data_text.text = names.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_orders, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}