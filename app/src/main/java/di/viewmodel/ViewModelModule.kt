package di.viewmodel

import com.example.kattabozortest.home.HomeUseCase
import com.example.kattabozortest.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideHomeViewModel(useCase: HomeUseCase) = HomeViewModel(useCase)
}