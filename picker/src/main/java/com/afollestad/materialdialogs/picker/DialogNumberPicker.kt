package com.afollestad.materialdialogs.picker

import android.widget.NumberPicker
import androidx.annotation.CheckResult
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.list.ItemListener
import com.afollestad.materialdialogs.utils.MDUtil.isLandscape


fun MaterialDialog.numberPicker(items: Array<String>, initialSelection: Int = - 1, selection: ItemListener = null): MaterialDialog {
  require(initialSelection >= - 1 || initialSelection < items.size) {
    "Initial selection $initialSelection must be between -1 and " +
        "the size of your items array ${items.size}"
  }

  customView(R.layout.md_number_picker_layout,
    noVerticalPadding = true,
    horizontalPadding = true,
    dialogWrapContent = windowContext.isLandscape())

  with(getNumberPicker()) {
    displayedValues = items
    descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
    wrapSelectorWheel = false
    minValue = 0
    maxValue = items.size - 1

    value = initialSelection

    setOnValueChangedListener {picker, oldVal, newVal ->

    }
  }

  positiveButton(android.R.string.ok) {
    selection?.invoke(it, getNumberPicker().value, items[getNumberPicker().value])
  }

  negativeButton(android.R.string.cancel)
  return this
}

@CheckResult
fun MaterialDialog.selectedValue(): Int {
  return getNumberPicker().value
}

internal fun MaterialDialog.getNumberPicker() = findViewById<NumberPicker>(R.id.md_number_picker)