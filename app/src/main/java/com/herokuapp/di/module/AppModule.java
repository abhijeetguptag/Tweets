package com.herokuapp.di.module;

import android.app.Application;

import androidx.room.Room;

import com.herokuapp.BuildConfig;
import com.herokuapp.data.local.EntityDao;
import com.herokuapp.data.local.HeroKuDataBase;
import com.herokuapp.data.remote.network.ApiConstants;
import com.herokuapp.data.remote.network.ApiService;
import com.herokuapp.data.remote.network.RequestInterceptor;
import com.herokuapp.data.remote.network.RetryCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.retryOnConnectionFailure(true);
        okHttpClient.addInterceptor(new RequestInterceptor());
        if(BuildConfig.DEBUG)
            okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        else
            okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC));
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    ApiService provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RetryCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    HeroKuDataBase provideHerokuDatabase(Application application) {
        return Room.databaseBuilder(application, HeroKuDataBase.class, "tweets.db")
                // Wipes and rebuilds instead of migrating
                // if no Migration object.
                // Migration is not part of this practical.
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    EntityDao provideDao(HeroKuDataBase articleDatabase) {
        return articleDatabase.entityDao();
    }

}
