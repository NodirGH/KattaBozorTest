package di

import android.app.Application
import base.SecurityService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import remote.interceptors.JsonParseInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

//    @Singleton
//    @Provides
//    fun provideVibratorService(appPreferences: AppPreferences): VibratorService {
//        return VibratorService(appPreferences)
//    }

    @Singleton
    @Provides
    fun provideSecurityService(
        application: Application,
    ): SecurityService {
        return SecurityService(application)
    }

    @Singleton
    @Provides
    fun provideAuthSecurityListener(securityService: SecurityService): JsonParseInterceptor.Listener =
        object : JsonParseInterceptor.Listener {
            override fun openHomeWithClearStack() {
                securityService.openMainActivityWithClearStack()
            }
        }
}