package dev.luci.mobilemodelloader.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.luci.mobilemodelloader.data.datasource.DataSources
import dev.luci.mobilemodelloader.data.datasource.FakeDataSources
import dev.luci.mobilemodelloader.data.repository.FakeRepositories
import dev.luci.mobilemodelloader.domain.repository.Repositories
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val Context.dataStore by preferencesDataStore(name = "mobile_model_loader")

    @Provides
    @Singleton
    fun provideDataSources(@ApplicationContext context: Context): DataSources {
        return FakeDataSources(context.dataStore)
    }

    @Provides
    @Singleton
    fun provideRepositories(dataSources: DataSources): Repositories {
        return FakeRepositories(dataSources)
    }
}
