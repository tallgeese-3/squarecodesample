package lightcycleconsulting.com.squarecodesample.model

import kotlinx.serialization.Serializable

@Serializable
data class Employees(val employees: List<Employee>)