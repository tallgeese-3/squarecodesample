package lightcycleconsulting.com.squarecodesample.service

import io.reactivex.rxjava3.core.Observable
import lightcycleconsulting.com.squarecodesample.model.Employees
import retrofit2.http.*

interface EmployeeApi {
        @GET("/sq-mobile-interview/employees.json")
        fun getEmployeesRxJava(): Observable<Employees>

        @GET("{endpoint}")
        suspend fun getEmployeesCoroutines(@Path("endpoint") endpoint: String): Employees
}