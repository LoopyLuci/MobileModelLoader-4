package dev.luci.mobilemodelloader.datasource

import androidx.room.*
import dev.luci.mobilemodelloader.domain.model.BenchmarkResult
import dev.luci.mobilemodelloader.domain.model.ChatMessage
import dev.luci.mobilemodelloader.domain.model.McpServerConfig
import dev.luci.mobilemodelloader.domain.model.ModelInfo
import dev.luci.mobilemodelloader.domain.model.SkillManifest
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "models")
data class ModelEntity(
    @PrimaryKey val id: String,
    val name: String,
    val modelType: String,
    val downloadUrl: String?,
    val filePath: String?,
    val sizeBytes: Long,
    val isDownloaded: Boolean,
    val tags: List<String>,
    val description: String?,
    val createdAt: Long,
    val updatedAt: Long
) {
    fun toDomain() = ModelInfo(
        id = id,
        name = name,
        modelType = modelType,
        downloadUrl = downloadUrl,
        filePath = filePath,
        sizeBytes = sizeBytes,
        isDownloaded = isDownloaded,
        tags = tags,
        description = description,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
    companion object {
        fun fromDomain(model: ModelInfo) = ModelEntity(
            id = model.id,
            name = model.name,
            modelType = model.modelType,
            downloadUrl = model.downloadUrl,
            filePath = model.filePath,
            sizeBytes = model.sizeBytes,
            isDownloaded = model.isDownloaded,
            tags = model.tags,
            description = model.description,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )
    }
}

@Dao
interface ModelDao {
    @Query("SELECT * FROM models")
    fun observeModels(): Flow<List<ModelEntity>>

    @Query("SELECT * FROM models")
    suspend fun listModels(): List<ModelEntity>

    @Query("SELECT * FROM models WHERE id = :id")
    suspend fun getModel(id: String): ModelEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertModel(model: ModelEntity)

    @Query("DELETE FROM models WHERE id = :id")
    suspend fun deleteModel(id: String)
}

@Entity(tableName = "chats")
data class ChatEntity(
    @PrimaryKey val conversationId: String,
    val lastMessageAt: Long = System.currentTimeMillis()
)

@Dao
interface ChatDao {
    @Query("SELECT conversationId FROM chats ORDER BY lastMessageAt DESC")
    suspend fun listConversations(): List<String>

    @Query("SELECT * FROM chats WHERE conversationId = :id")
    suspend fun getConversation(id: String): ChatEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertConversation(chat: ChatEntity)

    @Query("DELETE FROM chats WHERE conversationId = :id")
    suspend fun deleteConversation(id: String)
}

@Entity(
    tableName = "messages",
    foreignKeys = [ForeignKey(entity = ChatEntity::class, parentColumns = ["conversationId"], childColumns = ["conversationId"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("conversationId")]
)
data class ChatMessageEntity(
    @PrimaryKey val id: String,
    val conversationId: String,
    val role: String,
    val content: String,
    val timestamp: Long,
    val attachmentsJson: String?,
    val toolCallsJson: String?
) {
    fun toDomain() = ChatMessage(
        id = id,
        role = MessageRole.valueOf(role),
        content = content,
        timestamp = timestamp
    )
}

@Dao
interface ChatMessageDao {
    @Query("SELECT * FROM messages WHERE conversationId = :conversationId ORDER BY timestamp ASC")
    fun observeMessages(conversationId: String): Flow<List<ChatMessageEntity>>

    @Query("SELECT * FROM messages WHERE conversationId = :conversationId ORDER BY timestamp ASC")
    suspend fun getMessages(conversationId: String): List<ChatMessageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatMessageEntity)

    @Query("DELETE FROM messages WHERE conversationId = :conversationId")
    suspend fun clearMessages(conversationId: String)
}

@Entity(tableName = "benchmark_results")
data class BenchmarkEntity(
    @PrimaryKey val id: String,
    val modelId: String,
    val latencyMs: Long,
    val tokensPerSecond: Double,
    val memoryBytes: Long,
    val createdAt: Long
) {
    fun toDomain() = BenchmarkResult(
        id = id,
        modelId = modelId,
        latencyMs = latencyMs,
        tokensPerSecond = tokensPerSecond,
        memoryBytes = memoryBytes,
        createdAt = createdAt
    )
    companion object {
        fun fromDomain(result: BenchmarkResult) = BenchmarkEntity(
            id = result.id,
            modelId = result.modelId,
            latencyMs = result.latencyMs,
            tokensPerSecond = result.tokensPerSecond,
            memoryBytes = result.memoryBytes,
            createdAt = result.createdAt
        )
    }
}

@Dao
interface BenchmarkDao {
    @Query("SELECT * FROM benchmark_results ORDER BY createdAt DESC")
    suspend fun listResults(): List<BenchmarkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: BenchmarkEntity)

    @Query("DELETE FROM benchmark_results WHERE id = :id")
    suspend fun deleteResult(id: String)
}

@Entity(tableName = "skills")
data class SkillEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val manifestJson: String
) {
    fun toDomain() = SkillManifest(
        id = id,
        name = name,
        description = description,
        parameters = emptyMap()
    )
    companion object {
        fun fromDomain(skill: SkillManifest) = SkillEntity(
            id = skill.id,
            name = skill.name,
            description = skill.description,
            manifestJson = "{}"
        )
    }
}

@Dao
interface SkillDao {
    @Query("SELECT * FROM skills")
    suspend fun listSkills(): List<SkillEntity>

    @Query("SELECT * FROM skills WHERE id = :id")
    suspend fun getSkill(id: String): SkillEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertSkill(skill: SkillEntity)

    @Query("DELETE FROM skills WHERE id = :id")
    suspend fun deleteSkill(id: String)
}

@Entity(tableName = "mcp_servers")
data class McpServerEntity(
    @PrimaryKey val id: String,
    val name: String,
    val endpoint: String,
    val enabled: Boolean
) {
    fun toDomain() = McpServerConfig(
        id = id,
        name = name,
        endpoint = endpoint,
        enabled = enabled
    )
    companion object {
        fun fromDomain(config: McpServerConfig) = McpServerEntity(
            id = config.id,
            name = config.name,
            endpoint = config.endpoint,
            enabled = config.enabled
        )
    }
}

@Dao
interface McpServerDao {
    @Query("SELECT * FROM mcp_servers")
    suspend fun listServers(): List<McpServerEntity>

    @Query("SELECT * FROM mcp_servers WHERE id = :id")
    suspend fun getServer(id: String): McpServerEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertServer(server: McpServerEntity)

    @Query("DELETE FROM mcp_servers WHERE id = :id")
    suspend fun deleteServer(id: String)
}

@Database(
    entities = [ModelEntity::class, ChatEntity::class, ChatMessageEntity::class, BenchmarkEntity::class, SkillEntity::class, McpServerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MobileModelLoaderDatabase : RoomDatabase() {
    abstract fun modelDao(): ModelDao
    abstract fun chatDao(): ChatDao
    abstract fun chatMessageDao(): ChatMessageDao
    abstract fun benchmarkDao(): BenchmarkDao
    abstract fun skillDao(): SkillDao
    abstract fun mcpServerDao(): McpServerDao
}

class RealModelDataSource(private val database: MobileModelLoaderDatabase) : ModelDataSource {
    private val dao = database.modelDao()
    override fun observeModels(): Flow<List<ModelInfo>> = dao.observeModels().map { it.map(ModelEntity::toDomain) }
    override suspend fun listModels(): List<ModelInfo> = dao.listModels().map(ModelEntity::toDomain)
    override suspend fun getModel(id: String): ModelInfo? = dao.getModel(id)?.toDomain()
    override suspend fun upsertModel(model: ModelInfo) = dao.upsertModel(ModelEntity.fromDomain(model))
    override suspend fun deleteModel(id: String) = dao.deleteModel(id)
}

class RealChatDataSource(private val database: MobileModelLoaderDatabase) : ChatDataSource {
    private val chatDao = database.chatDao()
    private val messageDao = database.chatMessageDao()
    override suspend fun listConversations(): List<String> = chatDao.listConversations()
    override suspend fun getMessages(conversationId: String): List<ChatMessage> =
        messageDao.getMessages(conversationId).map(ChatMessageEntity::toDomain)
    override suspend fun appendMessage(conversationId: String, message: ChatMessage) {
        chatDao.upsertConversation(ChatEntity(conversationId))
        messageDao.insertMessage(
            ChatMessageEntity(
                id = message.id,
                conversationId = conversationId,
                role = message.role.toString(),
                content = message.content,
                timestamp = message.timestamp,
                attachmentsJson = null,
                toolCallsJson = null
            )
        )
    }
    override suspend fun clearConversation(conversationId: String) {
        messageDao.clearMessages(conversationId)
        chatDao.deleteConversation(conversationId)
    }
}

class RealBenchmarkDataSource(private val database: MobileModelLoaderDatabase) : BenchmarkDataSource {
    private val dao = database.benchmarkDao()
    override suspend fun listResults(): List<BenchmarkResult> = dao.listResults().map(BenchmarkEntity::toDomain)
    override suspend fun saveResult(result: BenchmarkResult) = dao.insertResult(BenchmarkEntity.fromDomain(result))
    override suspend fun deleteResult(id: String) = dao.deleteResult(id)
}

class RealSkillDataSource(private val database: MobileModelLoaderDatabase) : SkillDataSource {
    private val dao = database.skillDao()
    override suspend fun listSkills(): List<SkillManifest> = dao.listSkills().map(SkillEntity::toDomain)
    override suspend fun getSkill(id: String): SkillManifest? = dao.getSkill(id)?.toDomain()
    override suspend fun upsertSkill(skill: SkillManifest) = dao.upsertSkill(SkillEntity.fromDomain(skill))
    override suspend fun deleteSkill(id: String) = dao.deleteSkill(id)
}

class RealMcpDataSource(private val database: MobileModelLoaderDatabase) : McpDataSource {
    private val dao = database.mcpServerDao()
    override suspend fun listServers(): List<McpServerConfig> = dao.listServers().map(McpServerEntity::toDomain)
    override suspend fun getServer(id: String): McpServerConfig? = dao.getServer(id)?.toDomain()
    override suspend fun upsertServer(config: McpServerConfig) = dao.upsertServer(McpServerEntity.fromDomain(config))
    override suspend fun deleteServer(id: String) = dao.deleteServer(id)
}
