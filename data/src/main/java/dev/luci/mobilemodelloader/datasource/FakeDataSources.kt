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

class FakeModelDataSource : ModelDataSource {
    private val items = mutableListOf<ModelInfo>()
    override fun observeModels(): Flow<List<ModelInfo>> = kotlinx.coroutines.flow.flowOf(items.toList())
    override suspend fun listModels(): List<ModelInfo> = items.toList()
    override suspend fun getModel(id: String): ModelInfo? = items.firstOrNull { it.id == id }
    override suspend fun upsertModel(model: ModelInfo) {
        val idx = items.indexOfFirst { it.id == model.id }
        if (idx >= 0) items[idx] = model else items.add(model)
    }
    override suspend fun deleteModel(id: String) {
        items.removeAll { it.id == id }
    }
}

class FakeChatDataSource : ChatDataSource {
    private val store = mutableMapOf<String, MutableList<ChatMessage>>()
    override suspend fun listConversations(): List<String> = store.keys.toList()
    override suspend fun getMessages(conversationId: String): List<ChatMessage> = store[conversationId]?.toList() ?: emptyList()
    override suspend fun appendMessage(conversationId: String, message: ChatMessage) {
        store.getOrPut(conversationId) { mutableListOf() }.add(message)
    }
    override suspend fun clearConversation(conversationId: String) {
        store.remove(conversationId)
    }
}

class FakeMcpDataSource : McpDataSource {
    private val items = mutableListOf<McpServerConfig>()
    override suspend fun listServers(): List<McpServerConfig> = items.toList()
    override suspend fun getServer(id: String): McpServerConfig? = items.firstOrNull { it.id == id }
    override suspend fun upsertServer(config: McpServerConfig) {
        val idx = items.indexOfFirst { it.id == config.id }
        if (idx >= 0) items[idx] = config else items.add(config)
    }
    override suspend fun deleteServer(id: String) {
        items.removeAll { it.id == id }
    }
}

class FakeBenchmarkDataSource : BenchmarkDataSource {
    private val items = mutableListOf<BenchmarkResult>()
    override suspend fun listResults(): List<BenchmarkResult> = items.toList()
    override suspend fun saveResult(result: BenchmarkResult) {
        items.add(result)
    }
    override suspend fun deleteResult(id: String) {
        items.removeAll { it.id == id }
    }
}

class FakeSkillDataSource : SkillDataSource {
    private val items = mutableListOf<SkillManifest>()
    override suspend fun listSkills(): List<SkillManifest> = items.toList()
    override suspend fun getSkill(id: String): SkillManifest? = items.firstOrNull { it.id == id }
    override suspend fun upsertSkill(skill: SkillManifest) {
        val idx = items.indexOfFirst { it.id == skill.id }
        if (idx >= 0) items[idx] = skill else items.add(skill)
    }
    override suspend fun deleteSkill(id: String) {
        items.removeAll { it.id == id }
    }
}
