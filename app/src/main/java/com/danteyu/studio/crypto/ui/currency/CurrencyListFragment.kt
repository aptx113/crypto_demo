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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.danteyu.studio.crypto.DemoViewModel
import com.danteyu.studio.crypto.R
import com.danteyu.studio.crypto.databinding.FragListCurrencyBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by George Yu in Dec. 2021.
 */
@AndroidEntryPoint
class CurrencyListFragment : Fragment() {

    val viewModel by activityViewModels<DemoViewModel>()
    private val adapter by lazy { CurrencyListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragListCurrencyBinding.inflate(layoutInflater, container, false).run {
        lifecycleOwner = viewLifecycleOwner
        this.viewModel = this@CurrencyListFragment.viewModel
        currencyRecycler.apply {
            adapter = this@CurrencyListFragment.adapter

            val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            ResourcesCompat.getDrawable(resources, R.drawable.recycler_divider, null)?.let {
                itemDecoration.setDrawable(it)
            }
            addItemDecoration(itemDecoration)
        }
        root
    }
}
