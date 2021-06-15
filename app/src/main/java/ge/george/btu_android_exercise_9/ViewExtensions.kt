package ge.george.btu_android_exercise_9

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snackBar(message: String, duration: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, duration).show()
}