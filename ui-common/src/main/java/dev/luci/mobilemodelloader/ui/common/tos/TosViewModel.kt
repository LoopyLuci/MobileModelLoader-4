/*
 * Copyright 2025 Google LLC
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

package dev.luci.mobilemodelloader.ui.common.tos

import androidx.lifecycle.ViewModel
import dev.luci.mobilemodelloader.data.DataStoreRepository
// Hilt removed
// Hilt removed

/** ViewModel responsible for managing terms of services related tasks. */
// Hilt removed: manual ViewModel
open class TosViewModel @Inject constructor(private val repository: private val dataStoreRepository: DataStoreRepository) :
  ViewModel() {
  open fun getIsTosAccepted(): Boolean {
    return dataStoreRepository.isTosAccepted()
  }

  open fun acceptTos() {
    dataStoreRepository.acceptTos()
  }

  open fun getIsGemmaTermsOfUseAccepted(): Boolean {
    return dataStoreRepository.isGemmaTermsOfUseAccepted()
  }

  open fun acceptGemmaTermsOfUse() {
    dataStoreRepository.acceptGemmaTermsOfUse()
  }
}
