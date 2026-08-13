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

import dev.luci.mobilemodelloader.datasource.FakeChatDataSource
import dev.luci.mobilemodelloader.domain.model.ChatMessage
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FakeChatDataSourceTest {

    private lateinit var dataSource: FakeChatDataSource

    @Before
    fun setup() {
        dataSource = FakeChatDataSource()
    }

    @Test
    fun `empty data source returns empty conversations`() = runTest {
        assertEquals(emptyList<String>(), dataSource.listConversations())
    }

    @Test
    fun `append message creates conversation and message`() = runTest {
        val message = ChatMessage(conversationId = "conv1", text = "Hello", sender = "user")
        dataSource.appendMessage("conv1", message)

        val conversations = dataSource.listConversations()
        assertEquals(1, conversations.size)
        assertEquals("conv1", conversations[0])

        val messages = dataSource.getMessages("conv1")
        assertEquals(1, messages.size)
        assertEquals("Hello", messages[0].text)
    }

    @Test
    fun `append multiple messages to same conversation`() = runTest {
        dataSource.appendMessage("conv1", ChatMessage("conv1", "Hello", "user"))
        dataSource.appendMessage("conv1", ChatMessage("conv1", "World", "agent"))

        val messages = dataSource.getMessages("conv1")
        assertEquals(2, messages.size)
        assertEquals("Hello", messages[0].text)
        assertEquals("World", messages[1].text)
    }

    @Test
    fun `clear conversation removes all messages`() = runTest {
        dataSource.appendMessage("conv1", ChatMessage("conv1", "Hello", "user"))
        dataSource.clearConversation("conv1")

        assertEquals(emptyList<String>(), dataSource.listConversations())
        assertEquals(emptyList(), dataSource.getMessages("conv1"))
    }

    @Test
    fun `get messages for non-existent conversation returns empty list`() = runTest {
        assertEquals(emptyList(), dataSource.getMessages("nonexistent"))
    }
}
