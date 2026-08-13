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
data class BenchmarkResult(
    val id: String,
    val modelId: String,
    val modelName: String,
    val accelerator: String,
    val warmUpIterations: Int,
    val benchmarkIterations: Int,
    val prefillMs: Double,
    val decodeMsPerToken: Double,
    val totalTokens: Int,
    val createdAt: Long = System.currentTimeMillis(),
)

@Serializable
data class BenchmarkComparison(
    val baseline: BenchmarkResult,
    val candidate: BenchmarkResult,
    val prefillSpeedup: Double,
    val decodeSpeedup: Double,
)
