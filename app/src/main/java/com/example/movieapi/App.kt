package com.example.movieapi

import android.app.Application
import com.example.movieapi.api.Api
import com.example.movieapi.data.DescriptionModel
import com.example.movieapi.data.MovieModel
import com.example.movieapi.data.MovieRepo
import com.example.movieapi.data.MovieRepoImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class App : Application() {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org"
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        val module: Module = module {
            single {
                OkHttpClient.Builder()
                        .addInterceptor(get<HttpLoggingInterceptor>())
                        .build()
            }
            single {
                HttpLoggingInterceptor(
                        HttpLoggingInterceptor.Logger { message ->
                            Timber.tag("OkHttp").d(message)
                        }
                ).apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            }
            single<Retrofit> {
                Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl(BASE_URL)
                        .client(get())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            single<Api> {
                get<Retrofit>()
                        .create(Api::class.java)
            }

            single<MovieRepo> {
                MovieRepoImpl(get())
            }

            viewModel { MovieModel(get()) }
            viewModel { DescriptionModel(get()) }
        }
        startKoin {
            androidContext(this@App)
            modules(listOf(
                    module
            ))
        }
    }

}