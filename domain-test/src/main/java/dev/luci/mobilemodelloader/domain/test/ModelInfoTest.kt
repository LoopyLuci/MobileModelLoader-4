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

package dev.luci.mobilemodelloader.domain.test

import dev.luci.mobilemodelloader.domain.model.ModelInfo
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ModelInfoTest {

    @Test
    fun `model info serialization fields are present`() = runTest {
        val model = ModelInfo(
            id = "model-1",
            name = "Test Model",
            modelType = "llm",
            isDownloaded = true,
            tags = listOf("chat", "gemma"),
        )

        assertEquals("model-1", model.id)
        assertEquals("Test Model", model.name)
        assertEquals("llm", model.modelType)
        assertEquals(true, model.isDownloaded)
        assertEquals(listOf("chat", "gemma"), model.tags)
    }

    @Test
    fun `model info copy preserves original and updates field`() {
        val original = ModelInfo(
            id = "model-1",
            name = "Original",
            modelType = "llm",
            isDownloaded = false,
            tags = emptyList(),
        )
        val updated = original.copy(name = "Updated")

        assertEquals("model-1", updated.id)
        assertEquals("Updated", updated.name)
        assertEquals(false, updated.isDownloaded)
        assertEquals(original, original)
    }

    @Test
    fun `model info equality works correctly`() {
        val model1 = ModelInfo(id = "m1", name = "Test", modelType = "llm", isDownloaded = true, tags = listOf("a"))
        val model2 = ModelInfo(id = "m1", name = "Test", modelType = "llm", isDownloaded = true, tags = listOf("a"))
        val model3 = ModelInfo(id = "m2", name = "Test", modelType = "llm", isDownloaded = true, tags = listOf("a"))

        assertEquals(model1, model2)
        assertTrue(model1 != model3)
    }

    @Test
    fun `model info hash code is consistent`() {
        val model1 = ModelInfo(id = "m1", name = "Test", modelType = "llm", isDownloaded = true, tags = listOf("a"))
        val model2 = ModelInfo(id = "m1", name = "Test", modelType = "llm", isDownloaded = true, tags = listOf("a"))

        assertEquals(model1.hashCode(), model2.hashCode())
    }
}
