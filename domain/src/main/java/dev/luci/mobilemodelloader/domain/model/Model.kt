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
data class ModelInfo(
    val id: String,
    val name: String,
    val modelType: String,
    val downloadUrl: String? = null,
    val filePath: String? = null,
    val sizeBytes: Long = 0,
    val isDownloaded: Boolean = false,
    val tags: List<String> = emptyList(),
    val description: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
)

@Serializable
data class ModelHandlerConfig(
    val id: String,
    val modelType: String,
    val displayName: String,
    val parameters: Map<String, ConfigParam> = emptyMap(),
)

@Serializable
data class ConfigParam(
    val key: String,
    val value: String,
    val type: ParamType = ParamType.STRING,
)

@Serializable
enum class ParamType {
    STRING,
    INT,
    FLOAT,
    BOOLEAN,
}
