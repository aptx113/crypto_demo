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
package com.danteyu.studio.crypto.ui.common

import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.danteyu.studio.crypto.R
import com.danteyu.studio.crypto.ext.setSafeOnClickListener
import com.danteyu.studio.crypto.model.CurrencyInfo
import com.danteyu.studio.crypto.ui.currency.CurrencyListAdapter
import timber.log.Timber

/**
 * Created by George Yu in 12月. 2021.
 */
object CommonBindings {

    private const val DELAY_FOR_REVERSE_LIST_TIME: Long = 200

    @JvmStatic
    @BindingAdapter("onSafeClick")
    fun setOnSafeClick(view: View, onClick: (View) -> Unit) =
        view.setSafeOnClickListener {
            onClick(it)
        }

    @JvmStatic
    @BindingAdapter("listData")
    fun bindListData(recyclerView: RecyclerView, items: List<CurrencyInfo>?) {
        Timber.d("ORZ bindListData")
        items?.let {
            if (recyclerView.adapter == null) return
            (recyclerView.adapter as CurrencyListAdapter).submitList(it)
            recyclerView.postDelayed(
                {
                    recyclerView.scrollToPosition(0)
                },
                DELAY_FOR_REVERSE_LIST_TIME
            )
        }
    }

    @JvmStatic
    @BindingAdapter("enableBButton")
    fun bindButtonEnable(btn: Button, item: List<CurrencyInfo>?) {
        btn.apply {
            isEnabled = !item.isNullOrEmpty()

            if (item.isNullOrEmpty()) {
                R.color.crypto_symbol
            } else {
                R.color.purple_500
            }.let { backgroundTintList = ContextCompat.getColorStateList(this.context, it) }
        }
    }
}
