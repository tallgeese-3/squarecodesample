package lightcycleconsulting.com.squarecodesample.service

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.reactivex.rxjava3.core.Observable
import kotlinx.serialization.json.Json
import lightcycleconsulting.com.squarecodesample.R
import lightcycleconsulting.com.squarecodesample.model.Employees
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import java.util.concurrent.TimeUnit

class EmployeeApiManager {
    private val logging = HttpLoggingInterceptor()
    private val httpClient = OkHttpClient.Builder()
    private val contentType = "application/json".toMediaType()

    fun getEmployeesRxJava (context: Context): Observable<Employees> {
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(5, TimeUnit.MINUTES)
        httpClient.readTimeout(5, TimeUnit.MINUTES)
        httpClient.writeTimeout(5, TimeUnit.MINUTES)
        val retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.get_employees_endpoint))
            .addConverterFactory(Json.asConverterFactory(contentType))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
        return retrofit.create(EmployeeApi::class.java).getEmployeesRxJava()
    }

    suspend fun getEmployeesCoroutines (urlSrc: Any, endpoint: String): Employees {
        logging.level = HttpLoggingInterceptor.Level.BODY
        val baseUrl: String = if (urlSrc is Context) urlSrc.getString(R.string.get_employees_endpoint) else urlSrc as String
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(5, TimeUnit.MINUTES)
        httpClient.readTimeout(5, TimeUnit.MINUTES)
        httpClient.writeTimeout(5, TimeUnit.MINUTES)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .client(httpClient.build())
            .build()
        return retrofit.create(EmployeeApi::class.java).getEmployeesCoroutines(endpoint)
    }
}