/*
 * Copyright 2021 Dante Yu
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
package com.danteyu.studio.crypto.ui.currency

import androidx.recyclerview.widget.RecyclerView
import com.danteyu.studio.crypto.databinding.ItemCurrencyBinding
import com.danteyu.studio.crypto.model.CurrencyInfo

/**
 * Created by George Yu in Dec. 2021.
 */
class CurrencyListViewHolder(private val viewDataBinding: ItemCurrencyBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {

    fun bind(currencyInfo: CurrencyInfo) {
        viewDataBinding.currencyInfo =
            currencyInfo.let { CurrencyInfo(it.id.first().toString(), it.name, it.symbol) }
        viewDataBinding.executePendingBindings()
    }
}
