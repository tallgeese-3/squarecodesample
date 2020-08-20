package lightcycleconsulting.com.squarecodesample

import kotlinx.coroutines.runBlocking
import lightcycleconsulting.com.squarecodesample.service.EmployeeApiManager
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
     fun testValidEmployeeList() {
        runBlocking {
            assert (EmployeeApiManager().getEmployeesCoroutines("https://s3.amazonaws.com/", "/sq-mobile-interview/employees.json").employees.isNotEmpty())
        }
    }

    @Test
    fun testEmptyEmployeeList() {
        runBlocking {
            assert (EmployeeApiManager().getEmployeesCoroutines("https://s3.amazonaws.com/", "/sq-mobile-interview/employees_empty.json").employees.isEmpty())

        }
    }

    @Test
    fun testInvalidEmployeeList() {
        runBlocking {
            try {
                EmployeeApiManager().getEmployeesCoroutines("https://s3.amazonaws.com/", "/sq-mobile-interview/employees_malformed.json").employees.isEmpty()
            } catch(e: Exception) {
                System.out.println(e)
                assert(e.javaClass.simpleName == "MissingFieldException")
            }
        }
    }
}