package hu.dmorvai.myseries.ui.utils

import android.view.View
import androidx.core.content.ContextCompat

fun View.setBackgroundTint(colorId: Int) {
    this.backgroundTintList = ContextCompat.getColorStateList(context, colorId)
}
