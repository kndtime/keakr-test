package com.example.keakr.di

import com.example.keakr.BuildConfig
import com.example.keakr.data.remote.KeakrService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
object KeakrModule {


    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): KeakrService {
        return retrofit.create(KeakrService::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient().newBuilder()
        val interceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
            request.header("X-Keakr-Client-Id", "26e672c1-0c6b-40d8-93cb-3aaf9e69cfeb")
                .header("X-Keakr-Access-Token", "9f1d8880748d48d68e5a10f3be1e60d5")
            chain.proceed(request.build())
        }
        httpClient.interceptors().add(interceptor)
        httpClient.readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }
        return httpClient.build()
    }

    @Provides
    @Reusable
    @JvmStatic
    fun provideMoshi(): Moshi {
        val moshi =  Moshi.Builder()
            .build()
        return moshi
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .client(provideHttpClient())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    operator fun invoke(): KeakrModule {
        return this
    }
}