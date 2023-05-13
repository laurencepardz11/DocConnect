package com.nethealth.drconnect.di

import com.nethealth.drconnect.BuildConfig
import com.nethealth.drconnect.data.remote.AppService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(20, TimeUnit.SECONDS)
        builder.connectTimeout(20, TimeUnit.SECONDS)
        builder.writeTimeout(20, TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)

        builder.addNetworkInterceptor(Interceptor { chain: Interceptor.Chain ->
            val original: Request = chain.request()
            val request: Request.Builder = original.newBuilder()
                .header("Connection", "close")

            if (original.header("Authorization") == null) {
                request.addHeader("Header", "Accept: application/json")
                    .addHeader("Header", "Content-Type: application/json")
            }
            chain.proceed(request.build())
        })

        return builder
            .retryOnConnectionFailure(false)
            .cache(null)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun  providesDrApi(retrofit: Retrofit): AppService = retrofit.create(AppService::class.java)

}