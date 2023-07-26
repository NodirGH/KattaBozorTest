package di.domain

import com.example.kattabozortest.home.HomeUseCase
import com.example.kattabozortest.home.HomeUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repository.HomeRepository

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideHomeUseCase(
        repository: HomeRepository
    ): HomeUseCase = HomeUseCaseImpl(repository)
}