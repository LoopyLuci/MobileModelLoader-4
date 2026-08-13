package dev.luci.mobilemodelloader.repository

import dev.luci.mobilemodelloader.domain.model.BenchmarkResult
import dev.luci.mobilemodelloader.domain.model.ChatMessage
import dev.luci.mobilemodelloader.domain.model.McpServerConfig
import dev.luci.mobilemodelloader.domain.model.ModelInfo
import dev.luci.mobilemodelloader.domain.model.SkillManifest
import dev.luci.mobilemodelloader.domain.repository.BenchmarkRepository
import dev.luci.mobilemodelloader.domain.repository.ChatRepository
import dev.luci.mobilemodelloader.domain.repository.McpRepository
import dev.luci.mobilemodelloader.domain.repository.ModelRepository
import dev.luci.mobilemodelloader.domain.repository.SkillRepository
import kotlinx.coroutines.flow.Flow

class RealModelRepository(private val dataSource: ModelDataSource) : ModelRepository by dataSource
class RealChatRepository(private val dataSource: ChatDataSource) : ChatRepository by dataSource
class RealMcpRepository(private val dataSource: McpDataSource) : McpRepository by dataSource
class RealBenchmarkRepository(private val dataSource: BenchmarkDataSource) : BenchmarkRepository by dataSource
class RealSkillRepository(private val dataSource: SkillDataSource) : SkillRepository by dataSource
