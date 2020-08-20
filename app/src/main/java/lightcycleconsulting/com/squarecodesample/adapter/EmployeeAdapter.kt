package lightcycleconsulting.com.squarecodesample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lightcycleconsulting.com.squarecodesample.adapter.viewholder.EmployeeViewHolder
import lightcycleconsulting.com.squarecodesample.databinding.EmployeeItemBinding
import lightcycleconsulting.com.squarecodesample.model.Employee


class EmployeeAdapter(private var employees: List<Employee>?): RecyclerView.Adapter<EmployeeViewHolder>() {
    val expandedList =  ArrayList<Boolean>()
    init {
        initExpandedList()
    }

    fun add ( employees: List<Employee>?) {
        this.employees = employees
        initExpandedList()
        notifyDataSetChanged()
    }

    private fun initExpandedList() {
        expandedList.clear()
        if (employees != null) {
            for (employee in employees!!) {
                expandedList.add(false)
            }
        }
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employees?.get(position)
        holder.bind(employee, expandedList, position)
        holder.binding.moreInfoButton.setOnClickListener {
            expandedList[position] = !expandedList[position]
            notifyItemChanged(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: EmployeeItemBinding  = EmployeeItemBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return EmployeeViewHolder(itemBinding)
    }

    override fun getItemCount(): Int { return employees?.size!!
    }
}