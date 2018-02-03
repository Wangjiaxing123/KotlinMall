package newtrekwang.com.baselibrary.data.net

import newtrekwang.com.baselibrary.common.BaseConstant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by WJX on 2018/2/3.
 *
 */
class RetrofitFactory private constructor(){

    companion object {
        val instance: RetrofitFactory by lazy {
            RetrofitFactory()
        }
    }

    private val retrofit: Retrofit
    private val interceptor: Interceptor


    init {
        interceptor = Interceptor {
            chain ->
           val request =  chain.request()
                    .newBuilder()
                    .addHeader("Content-Type","application/json")
                    .addHeader("charset","utf-8")
                   .build()
            chain.proceed( request)

        }

        retrofit  = Retrofit.Builder()
                .baseUrl(BaseConstant.SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initCient())
                .build()
    }

    private fun initCient(): OkHttpClient? {
        return OkHttpClient.Builder()
                .addInterceptor(initLogInterceptor())
                .addInterceptor(interceptor)
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .build()
    }

    private fun initLogInterceptor(): Interceptor? {
       val interceptor =  HttpLoggingInterceptor ()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return  interceptor
    }


  fun <T>  create(service: Class<T>): T{
      return retrofit.create(service)
  }

}