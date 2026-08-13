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

package dev.luci.mobilemodelloader.repository

import dev.luci.mobilemodelloader.datasource.FakeBenchmarkDataSource
import dev.luci.mobilemodelloader.datasource.FakeChatDataSource
import dev.luci.mobilemodelloader.datasource.FakeMcpDataSource
import dev.luci.mobilemodelloader.datasource.FakeModelDataSource
import dev.luci.mobilemodelloader.datasource.FakeSkillDataSource
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

class FakeModelRepository(
    private val dataSource: FakeModelDataSource = FakeModelDataSource()
) : ModelRepository by dataSource

class FakeChatRepository(
    private val dataSource: FakeChatDataSource = FakeChatDataSource()
) : ChatRepository by dataSource

class FakeMcpRepository(
    private val dataSource: FakeMcpDataSource = FakeMcpDataSource()
) : McpRepository by dataSource

class FakeBenchmarkRepository(
    private val dataSource: FakeBenchmarkDataSource = FakeBenchmarkDataSource()
) : BenchmarkRepository by dataSource

class FakeSkillRepository(
    private val dataSource: FakeSkillDataSource = FakeSkillDataSource()
) : SkillRepository by dataSource
