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

package dev.luci.mobilemodelloader.data.test

import dev.luci.mobilemodelloader.datasource.FakeModelDataSource
import dev.luci.mobilemodelloader.domain.model.ModelInfo
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class FakeModelDataSourceTest {

    private lateinit var dataSource: FakeModelDataSource

    @Before
    fun setup() {
        dataSource = FakeModelDataSource()
    }

    @Test
    fun `empty data source returns empty list`() = runTest {
        assertEquals(emptyList<ModelInfo>(), dataSource.listModels())
    }

    @Test
    fun `upsert and retrieve model`() = runTest {
        val model = ModelInfo(id = "m1", name = "Test", modelType = "llm", isDownloaded = false, tags = emptyList())
        dataSource.upsertModel(model)

        val retrieved = dataSource.getModel("m1")
        assertEquals(model, retrieved)
    }

    @Test
    fun `upsert updates existing model`() = runTest {
        dataSource.upsertModel(ModelInfo(id = "m1", name = "Original", modelType = "llm", isDownloaded = false, tags = emptyList()))
        dataSource.upsertModel(ModelInfo(id = "m1", name = "Updated", modelType = "llm", isDownloaded = true, tags = emptyList()))

        val models = dataSource.listModels()
        assertEquals(1, models.size)
        assertEquals("Updated", models[0].name)
        assertEquals(true, models[0].isDownloaded)
    }

    @Test
    fun `delete model removes it`() = runTest {
        dataSource.upsertModel(ModelInfo(id = "m1", name = "Test", modelType = "llm", isDownloaded = false, tags = emptyList()))
        dataSource.deleteModel("m1")

        assertEquals(emptyList(), dataSource.listModels())
        assertNull(dataSource.getModel("m1"))
    }

    @Test
    fun `delete non-existent model does not throw`() = runTest {
        dataSource.deleteModel("nonexistent")
        assertEquals(emptyList(), dataSource.listModels())
    }
}
