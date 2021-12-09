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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.danteyu.studio.crypto.databinding.ItemCurrencyBinding
import com.danteyu.studio.crypto.model.CurrencyInfo

/**
 * Created by George Yu in Dec. 2021.
 */
class CurrencyListAdapter :
    ListAdapter<CurrencyInfo, CurrencyListViewHolder>(CurrencyListDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCurrencyBinding.inflate(layoutInflater, parent, false)

        return CurrencyListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object CurrencyListDiffUtil : DiffUtil.ItemCallback<CurrencyInfo>() {
        override fun areItemsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean =
            oldItem == newItem
    }
}
