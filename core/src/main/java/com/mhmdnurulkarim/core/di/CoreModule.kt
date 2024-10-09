package com.mhmdnurulkarim.core.di

import androidx.room.Room
import com.mhmdnurulkarim.core.BuildConfig
import com.mhmdnurulkarim.core.data.UserRepository
import com.mhmdnurulkarim.core.data.source.local.LocalDataSource
import com.mhmdnurulkarim.core.data.source.local.datastore.UserDataStore
import com.mhmdnurulkarim.core.data.source.local.datastore.dataStore
import com.mhmdnurulkarim.core.data.source.local.room.UserDatabase
import com.mhmdnurulkarim.core.data.source.remote.RemoteDataSource
import com.mhmdnurulkarim.core.data.source.remote.network.ApiService
import com.mhmdnurulkarim.core.domain.repository.IUserRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<UserDatabase>().userDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("mhmdnurulkarim".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            UserDatabase::class.java, "User.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.github.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/GyhWVHsOXNZc6tGTNd15kXF9YD0kEZaGxYn6MUva5jY=")
            .add(hostname, "sha256/lmo8/KPXoMsxI+J9hY+ibNm2r0IYChmOsF9BxD74PVc=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val req = chain.request()
                val requestHeaders = req.newBuilder()
                    .addHeader("Authorization", BuildConfig.API_KEY)
                    .build()
                chain.proceed(requestHeaders)
            }
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single { UserDataStore(androidContext().dataStore) }
    single<IUserRepository> {
        UserRepository(
            get(),
            get(),
            get()
        )
    }
}