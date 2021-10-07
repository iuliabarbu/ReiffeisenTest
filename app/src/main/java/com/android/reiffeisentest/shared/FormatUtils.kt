package com.android.reiffeisentest.shared

import android.content.res.Resources
import androidx.core.content.ContextCompat
import com.android.reiffeisentest.R
import java.text.SimpleDateFormat
import java.util.*

class FormatUtils {

    object FormatHelper {

        fun convertTimeForDisplay(inputDate: String): String {
            val dateTimeFormatterIn =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val dateTimeFormatterOut = SimpleDateFormat("HH:mm", Locale.getDefault())
            val parsedDate = dateTimeFormatterIn.parse(inputDate)
            return dateTimeFormatterOut.format(parsedDate)
        }


        fun convertCountryToCode(resources: Resources, countryName: String): String {
            val isoCountryCodes = Locale.getISOCountries()
            for (code in isoCountryCodes) {
                val locale = Locale("", code)
                if (countryName.equals(locale.displayCountry, true)) {
                    return code!!
                }
            }
            return resources.getString(R.string.unknown_country_code)
        }


    }


}