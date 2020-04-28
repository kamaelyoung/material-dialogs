package com.afollestad.materialdialogs.picker

import android.widget.NumberPicker
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.utils.MDUtil.isLandscape
import kotlin.math.min

typealias TimeLengthCallback = ((dialog: MaterialDialog, minute: Int) -> Unit)?

internal val mDisplayedValues = arrayOf("00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55")

internal fun MaterialDialog.getHourPicker() = findViewById<NumberPicker>(R.id.dateTimeHour)

internal fun MaterialDialog.getMinutePicker() = findViewById<NumberPicker>(R.id.dateTimeMinute)

/**
 * 选择时常，5分钟一个间隔 返回分钟数
 */
fun MaterialDialog.timeLength(minute: Int = 0, callback: TimeLengthCallback = null): MaterialDialog {

  customView(R.layout.md_time_length_layout,
    noVerticalPadding = false,
    horizontalPadding = true,
    dialogWrapContent = windowContext.isLandscape())
  val h = minute / 60
  val m = minute - h * 60

  with(getHourPicker()) {
    descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
    wrapSelectorWheel = false
    minValue = 0
    maxValue = 23
    value = h
  }


  with((getMinutePicker())) {
    descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
    wrapSelectorWheel = false
    displayedValues = mDisplayedValues
    minValue = 0
    maxValue = 11
    value = m / 5
  }

  negativeButton(android.R.string.cancel)
  positiveButton(android.R.string.ok) {
    callback?.invoke(it, getHourPicker().value * 60 + getMinutePicker().value * 5)
  }
  return this
}

