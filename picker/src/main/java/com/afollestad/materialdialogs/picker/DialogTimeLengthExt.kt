package com.afollestad.materialdialogs.picker

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView

fun MaterialDialog.timeLength(): MaterialDialog {

  customView(R.layout.md_time_length_layout)

  return this
}