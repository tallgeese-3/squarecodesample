package lightcycleconsulting.com.squarecodesample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import lightcycleconsulting.com.squarecodesample.adapter.EmployeeAdapter
import lightcycleconsulting.com.squarecodesample.databinding.EmployeeFragmentBinding
import lightcycleconsulting.com.squarecodesample.model.Employee
import lightcycleconsulting.com.squarecodesample.service.Status
import lightcycleconsulting.com.squarecodesample.viewmodel.EmployeeViewModel

class EmployeeFragment: Fragment() {
    private val employeeViewModel: EmployeeViewModel by activityViewModels()
    private var _binding: EmployeeFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var adapter = EmployeeAdapter(ArrayList<Employee>())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EmployeeFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.setHasFixedSize(true)
        (binding.list.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        binding.list.adapter = adapter
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        employeeViewModel.getEmployees().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressIndicator.visibility = View.GONE
                        if (it.data?.employees?.size == 0) {
                            binding.noData.errorView.visibility = View.GONE
                            binding.noData.emptyView.visibility = View.VISIBLE
                        } else {
                            binding.noData.errorView.visibility = View.GONE
                            binding.noData.emptyView.visibility = View.GONE
                            adapter.add(it.data?.employees)
                        }
                    }
                    Status.ERROR -> {
                        binding.progressIndicator.visibility = View.GONE
                        binding.noData.errorMeesage.text = resource.message
                        binding.noData.errorView.visibility = View.VISIBLE
                        binding.noData.emptyView.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.progressIndicator.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}