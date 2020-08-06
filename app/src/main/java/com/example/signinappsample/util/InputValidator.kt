package com.example.signinappsample.util

import androidx.core.util.PatternsCompat
import java.util.regex.Pattern

class InputValidator {
    companion object Validate {
        private const val PROFILE_NAME_EXPRESSION = "^[a-zA-Z0-9]*$"
        private const val PROFILE_NAME_MAX_COUNT = 30

        fun isValidEmail(data: String): Boolean = PatternsCompat.EMAIL_ADDRESS.matcher(data).matches()

        fun isValidPassword(str: String): Boolean {
            var valid = true

            // Password policy check
            // Password should be minimum minimum 8 characters long
            if (str.length < 8) {
                valid = false
            }
            // Password should contain at least one number
            var exp = ".*[0-9].*"
            var pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
            var matcher = pattern.matcher(str)
            if (!matcher.matches()) {
                valid = false
            }

            // Password should contain at least one capital letter
            exp = ".*[A-Z].*"
            pattern = Pattern.compile(exp)
            matcher = pattern.matcher(str)
            if (!matcher.matches()) {
                valid = false
            }

            // Password should contain at least one small letter
            exp = ".*[a-z].*"
            pattern = Pattern.compile(exp)
            matcher = pattern.matcher(str)
            if (!matcher.matches()) {
                valid = false
            }

            return valid
        }

        fun isValidName(profileName: String): Boolean = profileName.length <= PROFILE_NAME_MAX_COUNT
                && Pattern.compile(PROFILE_NAME_EXPRESSION).matcher(profileName).matches()
                && profileName.isNotEmpty()

    }
}