package di

import com.example.kattabozortest.home.adapter.HomeAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {

    @Provides
    fun provideHomeAdapter() = HomeAdapter()
}