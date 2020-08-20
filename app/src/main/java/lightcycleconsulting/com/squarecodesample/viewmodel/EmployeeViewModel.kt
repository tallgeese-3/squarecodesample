package lightcycleconsulting.com.squarecodesample.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lightcycleconsulting.com.squarecodesample.R
import lightcycleconsulting.com.squarecodesample.model.Employees
import lightcycleconsulting.com.squarecodesample.service.EmployeeApiManager
import lightcycleconsulting.com.squarecodesample.service.Resource


class EmployeeViewModel(application: Application) : AndroidViewModel(application) {
    private val employees :MutableLiveData<Resource<Employees>> by lazy {
        MutableLiveData<Resource<Employees>>().also {
            loadEmployeesCoroutines()
        }
    }

    fun getEmployees(): LiveData<Resource<Employees>> {
        return employees
    }

    /*We're trying something new here, using this blog as an example:
        https://blog.mindorks.com/using-retrofit-with-kotlin-coroutines-in-android
    */

     private fun loadEmployeesCoroutines() {
         var resource: Resource<Employees>
         val endpoint = (getApplication() as Context).getString(R.string.get_employees_valid_data)
         viewModelScope.launch(Dispatchers.IO) {
            resource = try {
                Resource.success(data = EmployeeApiManager().getEmployeesCoroutines(getApplication(), endpoint))
            } catch (exception: Exception) {
                Resource.error(data = Employees(ArrayList()), message = exception.message ?: "Error Occurred!"
                )
            }
            withContext(Dispatchers.Main) {
                employees.value = resource
            }
        }
    }


    private fun loadEmployeesRxJava() {
        EmployeeApiManager().getEmployeesRxJava(getApplication())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ employees.value = Resource.success(data = it) }, {Resource.error(data = Employees(ArrayList()), message = it.message ?: "Error Occurred!") }, {  })
    }
}
