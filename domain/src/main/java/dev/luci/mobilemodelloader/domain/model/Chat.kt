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

package dev.luci.mobilemodelloader.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val id: String,
    val role: MessageRole,
    val content: String,
    val timestamp: Long = System.currentTimeMillis(),
    val attachments: List<MessageAttachment> = emptyList(),
    val toolCalls: List<ToolCall> = emptyList(),
)

@Serializable
enum class MessageRole {
    USER,
    ASSISTANT,
    SYSTEM,
    TOOL,
}

@Serializable
data class MessageAttachment(
    val id: String,
    val type: AttachmentType,
    val uri: String? = null,
    val mimeType: String? = null,
)

@Serializable
enum class AttachmentType {
    IMAGE,
    AUDIO,
    VIDEO,
    DOCUMENT,
}

@Serializable
data class ToolCall(
    val id: String,
    val name: String,
    val arguments: Map<String, String>,
    val result: String? = null,
    val isError: Boolean = false,
)
