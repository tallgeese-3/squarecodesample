package lightcycleconsulting.com.squarecodesample.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Employee(val uuid: String, @SerialName("full_name") val fullName: String,
                    @SerialName("phone_number") val phoneNumber: String? = null,
                    @SerialName("email_address")val emailAddress: String,
                    @SerialName("biography")  val biography: String? = null,
                    @SerialName("photo_url_small")  val photoUrlSmall: String? = null,
                    @SerialName("photo_url_large")  val photoUrlLarge: String? = null,
                    @SerialName("team") val team: String,
                    @SerialName("employee_type")val employeeType : EmployeeType)