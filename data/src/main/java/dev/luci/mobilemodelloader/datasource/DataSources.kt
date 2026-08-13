/*
 * Copyright 2025 Luci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.luci.mobilemodelloader.datasource

import dev.luci.mobilemodelloader.domain.model.BenchmarkResult
import dev.luci.mobilemodelloader.domain.model.ChatMessage
import dev.luci.mobilemodelloader.domain.model.McpServerConfig
import dev.luci.mobilemodelloader.domain.model.ModelInfo
import dev.luci.mobilemodelloader.domain.model.SkillManifest
import kotlinx.coroutines.flow.Flow

interface ModelDataSource {
    suspend fun listModels(): List<ModelInfo>
    suspend fun getModel(id: String): ModelInfo?
    suspend fun upsertModel(model: ModelInfo)
    suspend fun deleteModel(id: String)
    fun observeModels(): Flow<List<ModelInfo>>
}

interface ChatDataSource {
    suspend fun listConversations(): List<String>
    suspend fun getMessages(conversationId: String): List<ChatMessage>
    suspend fun appendMessage(conversationId: String, message: ChatMessage)
    suspend fun clearConversation(conversationId: String)
}

interface McpDataSource {
    suspend fun listServers(): List<McpServerConfig>
    suspend fun getServer(id: String): McpServerConfig?
    suspend fun upsertServer(config: McpServerConfig)
    suspend fun deleteServer(id: String)
}

interface BenchmarkDataSource {
    suspend fun listResults(): List<BenchmarkResult>
    suspend fun saveResult(result: BenchmarkResult)
    suspend fun deleteResult(id: String)
}

interface SkillDataSource {
    suspend fun listSkills(): List<SkillManifest>
    suspend fun getSkill(id: String): SkillManifest?
    suspend fun upsertSkill(skill: SkillManifest)
    suspend fun deleteSkill(id: String)
}
