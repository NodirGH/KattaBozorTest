package di.remote

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.kattabozortest.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import remote.BaseUrl
import remote.interceptors.JsonParseInterceptor
import remote.services.HomeService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIME_OUT = 20L

    @Provides
    fun provideHomeService(retrofit: Retrofit): HomeService{
        return retrofit.create(HomeService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(
        @ApplicationContext context: Context,
        interceptor: JsonParseInterceptor,
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT * 3, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(logging)
            clientBuilder.addInterceptor(
                ChuckerInterceptor(context)
            )
        }
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseUrl.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideMoshiConverter(): MoshiConverterFactory {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return MoshiConverterFactory.create(moshi).asLenient()
    }


    @Singleton
    @Provides
    fun provideJsonMainInterceptor(
        listener: JsonParseInterceptor.Listener
    ): JsonParseInterceptor {
        return JsonParseInterceptor(listener)
    }
}