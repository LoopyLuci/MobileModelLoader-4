package dev.luci.mobilemodelloader.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.luci.mobilemodelloader.data.MobileModelLoaderDatabase
import dev.luci.mobilemodelloader.data.datasource.RealChatDataSource
import dev.luci.mobilemodelloader.data.datasource.RealBenchmarkDataSource
import dev.luci.mobilemodelloader.data.datasource.RealMcpDataSource
import dev.luci.mobilemodelloader.data.datasource.RealModelDataSource
import dev.luci.mobilemodelloader.data.datasource.RealSkillDataSource
import dev.luci.mobilemodelloader.data.repository.RealChatRepository
import dev.luci.mobilemodelloader.data.repository.RealBenchmarkRepository
import dev.luci.mobilemodelloader.data.repository.RealMcpRepository
import dev.luci.mobilemodelloader.data.repository.RealModelRepository
import dev.luci.mobilemodelloader.data.repository.RealSkillRepository
import dev.luci.mobilemodelloader.domain.repository.BenchmarkRepository
import dev.luci.mobilemodelloader.domain.repository.ChatRepository
import dev.luci.mobilemodelloader.domain.repository.McpRepository
import dev.luci.mobilemodelloader.domain.repository.ModelRepository
import dev.luci.mobilemodelloader.domain.repository.SkillRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MobileModelLoaderDatabase =
        Room.databaseBuilder(
            context,
            MobileModelLoaderDatabase::class.java,
            "mobile_model_loader.db"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideModelRepository(
        dataSource: RealModelDataSource
    ): ModelRepository = RealModelRepository(dataSource)

    @Provides
    @Singleton
    fun provideChatRepository(
        dataSource: RealChatDataSource
    ): ChatRepository = RealChatRepository(dataSource)

    @Provides
    @Singleton
    fun provideBenchmarkRepository(
        dataSource: RealBenchmarkDataSource
    ): BenchmarkRepository = RealBenchmarkRepository(dataSource)

    @Provides
    @Singleton
    fun provideSkillRepository(
        dataSource: RealSkillDataSource
    ): SkillRepository = RealSkillRepository(dataSource)

    @Provides
    @Singleton
    fun provideMcpRepository(
        dataSource: RealMcpDataSource
    ): McpRepository = RealMcpRepository(dataSource)
}
