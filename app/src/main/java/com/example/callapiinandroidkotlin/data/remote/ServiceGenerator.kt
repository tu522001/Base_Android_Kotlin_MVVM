package com.example.callapiinandroidkotlin.data.remote


import android.util.Log
import androidx.databinding.ktx.BuildConfig
import com.example.callapiinandroidkotlin.BASE_URLS
import com.example.callapiinandroidkotlin.contentType
import com.example.callapiinandroidkotlin.contentTypeValue
import com.example.callapiinandroidkotlin.timeoutConnect
import com.example.callapiinandroidkotlin.timeoutRead
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceGenerator @Inject constructor() {
    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val retrofit: Retrofit

    private var headerInterceptor = Interceptor { chain ->
        val original = chain.request()

        val request = original.newBuilder()
            .header(contentType, contentTypeValue)
            .method(original.method, original.body)
            .build()

        chain.proceed(request)
    }

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            return loggingInterceptor
        }

    init {
        okHttpBuilder.addInterceptor(headerInterceptor)
        okHttpBuilder.addInterceptor(logger)
        okHttpBuilder.connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
        val client = okHttpBuilder.build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URLS).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <S> createService(serviceClass: Class<S>): S {

        val service = retrofit.create(serviceClass)
        // Gọi enqueue() để gửi yêu cầu và nhận kết quả
        (service as? Call<*>)?.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                // Xử lý kết quả thành công
                if (response.isSuccessful) {
                    // TODO: Xử lý kết quả ở đây
                    Log.d("PPP","Xử lý kết quả thành công")
                } else {
                    // API trả về lỗi
                    val errorBody = response.errorBody()?.string()
                    logError(errorBody)
                    Log.d("PPP","API trả về lỗi")
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                // Xử lý lỗi khi gửi yêu cầu
                logError(t.message)
                Log.d("PPP","Xử lý lỗi khi gửi yêu cầu")
            }
        } as Nothing)

        return service
    }

    private fun logError(message: String?) {
        Log.d("ServiceGenerator", "API error: $message")
    }

//    private fun getMoshi(): Moshi {
//        return Moshi.Builder()
//            .add(MyKotlinJsonAdapterFactory())
//            .add(MyStandardJsonAdapters.FACTORY)
//            .build()
//    }
}
