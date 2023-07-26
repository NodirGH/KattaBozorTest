package di.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import remote.services.HomeService
import repository.HomeRepository
import repository.HomeRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(
        service: HomeService
    ): HomeRepository{
        return HomeRepositoryImpl(service)
    }
}