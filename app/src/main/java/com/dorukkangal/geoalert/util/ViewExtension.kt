package com.dorukkangal.geoalert.util

import android.content.res.Resources
import android.os.Build
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

/**
 * View
 */
fun View.setBackgroundColorRes(@ColorRes color: Int) {
    setBackgroundColor(ContextCompat.getColor(context, color))
}

fun View.inflater(): LayoutInflater = LayoutInflater.from(context)

fun ViewGroup.inflate(
    @LayoutRes resourceId: Int,
    root: ViewGroup? = this,
    attachToRoot: Boolean = false
): View {
    return inflater().inflate(resourceId, root, attachToRoot)
}

/**
 * TextView
 */
fun TextView.setTextColorRes(@ColorRes color: Int) {
    setTextColor(ContextCompat.getColor(context, color))
}

fun TextView.setTextAppearanceRes(@StyleRes textAppearance: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        setTextAppearance(textAppearance)
    } else {
        setTextAppearance(context, textAppearance)
    }
}

fun TextView.setFont(@FontRes font: Int) {
    typeface = ResourcesCompat.getFont(context, font)
}

fun TextView.text(): String {
    return text.toString().trim()
}

fun TextView.clearText() {
    setText("")
}

/**
 * ImageView
 */
fun ImageView.setTint(@ColorRes colorRes: Int) {
    setColorFilter(ContextCompat.getColor(context, colorRes))
}

/**
 * Dimension
 */
val Number.px: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()
