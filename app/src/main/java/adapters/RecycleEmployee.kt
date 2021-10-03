package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite_tabllar.R
import kotlinx.android.synthetic.main.item_rv.view.*
import models.Employee

class RecycleEmployee(var list: List<Employee>) : RecyclerView.Adapter<RecycleEmployee.Vh>() {
    inner class Vh(var itemRv: View) : RecyclerView.ViewHolder(itemRv.rootView) {
        fun onBind(names: Employee) {
            itemRv.item_name.text = names.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}