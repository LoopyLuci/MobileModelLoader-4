/*
 * Copyright 2026 Google LLC
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

package dev.luci.mobilemodelloader.customtasks.agentchat

import androidx.datastore.core.DataStore
import dev.luci.mobilemodelloader.agent.AgentChatExecutor
import dev.luci.mobilemodelloader.agent.AgentRuntimeExecutor
import dev.luci.mobilemodelloader.data.SystemPromptRepository
import dev.luci.mobilemodelloader.proto.UserData
import dev.luci.mobilemodelloader.ui.llmchat.LlmChatViewModel
// Hilt removed
// Hilt removed

// Hilt removed: manual ViewModel
class AgentChatViewModel
@Inject
constructor(
  systemPromptRepository: SystemPromptRepository,
  userDataDataStore: DataStore<UserData>,
  @AgentChatExecutor runtimeExecutor: AgentRuntimeExecutor,
) :
LlmChatViewModel(systemPromptRepository, userDataDataStore, runtimeExecutor)
