package poputka.neobis.poputkakg.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import poputka.neobis.poputkakg.data.RetrofitRepository
import poputka.neobis.poputkakg.utils.Preference
import poputka.neobis.poputkakg.utils.Preference.REQUEST_TIME_MINUTE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
@Module
@Singleton
class NetworkModule{

    @Singleton
    @Provides
    internal fun provideRetrofit(client: OkHttpClient, gsonFactory: GsonConverterFactory): RetrofitRepository {
        return Retrofit.Builder()
            .baseUrl(Preference.BASE_URL)
            .addConverterFactory(gsonFactory)
            .client(client).build()
            .create(RetrofitRepository::class.java!!)
    }

    @Singleton
    @Provides
    internal fun provideOkHttpClient(interceptor: HttpLoggingInterceptor, preference: Preference): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                      .method(original.method(), original.body())
                    .build()
                chain.proceed(request)
            }.addInterceptor(interceptor)
            .writeTimeout(REQUEST_TIME_MINUTE, TimeUnit.MINUTES)
            .readTimeout(REQUEST_TIME_MINUTE, TimeUnit.MINUTES)
            .connectTimeout(REQUEST_TIME_MINUTE, TimeUnit.MINUTES)
        return client.build()
    }

    @Singleton
    @Provides
    internal fun provideLoginInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    internal fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

}