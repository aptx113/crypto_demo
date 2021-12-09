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

import android.os.SystemClock
import android.view.View
import com.danteyu.studio.crypto.CLICK_INTERVAL

/**
 * Created by George Yu in Dec. 2021.
 */
class SafeClickListener(
    private var defaultInterval: Int = CLICK_INTERVAL,
    private val onSafeClick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked = 0L
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeClick(v)
    }
}
