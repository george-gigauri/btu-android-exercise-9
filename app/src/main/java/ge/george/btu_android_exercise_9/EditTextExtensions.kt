package ge.george.btu_android_exercise_9

import android.widget.EditText

fun EditText.isValidEmail(): Boolean =
    text.toString().contains("@") && text.toString().contains(".")

fun EditText.isPasswordValid(): Boolean =
    text.toString().length >= 9 && text.toString().matches(Regex(".*\\d.*")) &&
            containsSymbols(text.toString())

fun EditText.isPasswordMatch(repeatPassword: EditText) =
    text.toString() == repeatPassword.text.toString()

fun EditText.makeError(message: String? = null) {
    requestFocus()
    error = message ?: "This field is required"
}

fun containsSymbols(text: String): Boolean {
    val symbols = "!#$%^&*()~?><.,+_-{}[]':;|/"
    for (i in text)
        for (j in symbols)
            if (i == j)
                return true
    return false
}