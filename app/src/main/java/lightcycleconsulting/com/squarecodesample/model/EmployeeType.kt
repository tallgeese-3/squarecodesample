package lightcycleconsulting.com.squarecodesample.model

import kotlinx.serialization.Serializable

@Serializable
enum class EmployeeType(val friendlyName: String) {
    FULL_TIME("Full-Time"), PART_TIME("Part-Time"), CONTRACTOR ("Contractor")
}