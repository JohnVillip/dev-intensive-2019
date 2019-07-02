package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date{
    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value  * SECOND
        TimeUnits.MINUTE -> value  * MINUTE
        TimeUnits.HOUR -> value  * HOUR
        TimeUnits.DAY -> value  * DAY
    }

    this.time = time

    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {

    var time = this.time

    var time2 = date.time

    var res = time2 - time

    if(res < 0) {
        res -= 100
        when(res) {
            in -2 * SECOND until 0 -> return "только что"
            in -45 * SECOND until -2 * SECOND -> return "через несколько секунд"
            in -75 * SECOND until -45 * SECOND -> return "через минуту"
            in -45 * MINUTE until -75 * SECOND -> return "через ${-(res / MINUTE).toInt()} " + declinationMinutes(-(res / MINUTE).toInt())
            in -75 * MINUTE until -45 * MINUTE -> return "через час"
            in -22 * HOUR until -75 * MINUTE -> return "через ${-(res / HOUR).toInt()} " + declinationHours(-(res / HOUR).toInt())
            in -26 * HOUR until -22 * HOUR -> return "через день"
            in -360 * DAY until -26 * HOUR   -> return "через ${-(res / DAY).toInt()} " + declinationDays(-(res / DAY).toInt())
            else -> return "более чем через год"
        }
    } else {
        when(res) {
            in 0 until 1 * SECOND -> return "только что"
            in 1 * SECOND until 45 * SECOND -> return "несколько секунд назад"
            in 45 * SECOND until 75 * SECOND -> return "минуту назад"
            in 75 * SECOND until 45 * MINUTE -> return "${(res / MINUTE).toInt()} " + declinationMinutes((res / MINUTE).toInt()) + " назад"
            in 45 * MINUTE until 75 * MINUTE -> return "час назад"
            in 75 * MINUTE until 22 * HOUR -> return "${(res / HOUR).toInt()} " + declinationHours((res / HOUR).toInt()) + " назад"
            in 22 * HOUR until 26 * HOUR -> return "день назад"
            in 26 * HOUR until 360 * DAY -> return "${(res / DAY).toInt()} " + declinationDays((res / DAY).toInt()) + " назад"
            else -> return "более года назад"
        }
    }
}


//
//26ч - 360д "N дней назад"
//
//>360д "более года назад"

private fun declinationMinutes(minutes: Int): String {
    when(minutes) {
        1, 21, 31, 41 -> return "минуту"
        2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44 -> return "минуты"
        else -> return "минут"
    }
}

private fun declinationHours(hours: Int): String {
    when(hours) {
        1, 21 -> return "час"
        2, 3, 4 -> return "часа"
        else -> return "часов"
    }
}

private fun declinationDays(days: Int): String {
    var cutDays = days

    if(cutDays > 300) {
        cutDays -= 300
    }
    if(cutDays > 200) {
        cutDays -= 200
    }
    if(cutDays > 100) {
        cutDays -= 100
    }

    when(cutDays) {
        1, 21, 31, 41, 51, 61, 71, 81, 91 -> return "день"
        2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54, 62, 63, 64, 72, 73, 74, 82, 83, 84, 92, 93, 94 -> return "дня"
        else -> return "дней"
    }
}






enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}
