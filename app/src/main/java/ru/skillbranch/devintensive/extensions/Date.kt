package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

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
        return when(res) {
            in -2 * SECOND until 0             -> "только что"
            in -45 * SECOND until -2 * SECOND  -> "через несколько секунд"
            in -75 * SECOND until -45 * SECOND -> "через минуту"
            in -45 * MINUTE until -75 * SECOND -> "через ${-(res / MINUTE).toInt()} " + declinationMinutes((res / MINUTE).toInt())
            in -75 * MINUTE until -45 * MINUTE -> "через час"
            in -22 * HOUR until -75 * MINUTE   -> "через ${-(res / HOUR).toInt()} " + declinationHours((res / HOUR).toInt())
            in -26 * HOUR until -22 * HOUR     -> "через день"
            in -360 * DAY until -26 * HOUR     -> "через ${-(res / DAY).toInt()} " + declinationDays((res / DAY).toInt())
            else                               -> "более чем через год"
        }
    } else {
        return when(res) {
            in 0 until 1 * SECOND            -> "только что"
            in 1 * SECOND until 45 * SECOND  -> "несколько секунд назад"
            in 45 * SECOND until 75 * SECOND -> "минуту назад"
            in 75 * SECOND until 45 * MINUTE -> "${(res / MINUTE).toInt()} " + declinationMinutes((res / MINUTE).toInt()) + " назад"
            in 45 * MINUTE until 75 * MINUTE -> "час назад"
            in 75 * MINUTE until 22 * HOUR   -> "${(res / HOUR).toInt()} " + declinationHours((res / HOUR).toInt()) + " назад"
            in 22 * HOUR until 26 * HOUR     -> "день назад"
            in 26 * HOUR until 360 * DAY     -> "${(res / DAY).toInt()} " + declinationDays((res / DAY).toInt()) + " назад"
            else                             -> "более года назад"
        }
    }
}


//private fun declinationSeconds(seconds: Int): String {
//    when(seconds) {
//        1, 21, 31, 41, 51 -> return "секунду"
//        2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54 -> return "секунды"
//        else -> return "секунд"
//    }
//}
//
//private fun declinationMinutes(minutes: Int): String {
//    when(minutes) {
//        1, 21, 31, 41, 51 -> return "минуту"
//        2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54 -> return "минуты"
//        else -> return "минут"
//    }
//}
//
//private fun declinationHours(hours: Int): String {
//    when(hours) {
//        1, 21 -> return "час"
//        2, 3, 4, 22, 23, 24 -> return "часа"
//        else -> return "часов"
//    }
//}
//
//private fun declinationDays(days: Int): String {
//    var cutDays = days
//
//    if(cutDays > 300) {
//        cutDays -= 300
//    }
//    if(cutDays > 200) {
//        cutDays -= 200
//    }
//    if(cutDays > 100) {
//        cutDays -= 100
//    }
//
//    when(cutDays) {
//        1, 21, 31, 41, 51, 61, 71, 81, 91 -> return "день"
//        2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54, 62, 63, 64, 72, 73, 74, 82, 83, 84, 92, 93, 94 -> return "дня"
//        else -> return "дней"
//    }
//}

private fun declinationSeconds(seconds: Int): String {
    var cutSeconds = abs(seconds) % 100
    if(cutSeconds in 10 .. 20) return "секунд"
    cutSeconds %= 10

    return when(cutSeconds) {
        1         -> "секунду"
        in 2 .. 4 -> "секунды"
        else      -> "секунд"
     }
}

private fun declinationMinutes(minutes: Int): String {
    var cutMinutes = abs(minutes) % 100
    if(cutMinutes in 10 .. 20) return "минут"
    cutMinutes %= 10

    return when(cutMinutes) {
        1         -> "минуту"
        in 2 .. 4 -> "минуты"
        else      -> "минут"
    }
}

private fun declinationHours(hours: Int): String {
    var cutHours = abs(hours) % 100
    if(cutHours in 10 .. 20) return "часов"
    cutHours %= 10

    return when(cutHours) {
        1         -> "час"
        in 2 .. 4 -> "часа"
        else      -> "часов"
    }
}

private fun declinationDays(days: Int): String {
    var cutDays = abs(days) % 100
    if(cutDays in 10 .. 20) return "дней"
    cutDays %= 10

    return when(cutDays) {
        1         -> "день"
        in 2 .. 4 -> "дня"
        else      -> "дней"
    }
}



//Необходимо реализовать метод plural для enum TimeUnits
// Реализуй метод plural для всех перечислений TimeUnits следующего вида TimeUnits.SECOND.plural(value:Int)
// возвращающую значение с праильно склоненной единицой измерения
//Пример:
//TimeUnits.SECOND.plural(1) //1 секунду
//TimeUnits.MINUTE.plural(4) //4 минуты
//TimeUnits.HOUR.plural(19) //19 часов
//TimeUnits.DAY.plural(222) //222 дня




enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        val timeUnit = this

        return when(timeUnit) {
            SECOND -> "$value ${declinationSeconds(value)}"
            MINUTE -> "$value ${declinationMinutes(value)}"
            HOUR   -> "$value ${declinationHours(value)}"
            DAY    -> "$value ${declinationDays(value)}"
        }
    }
}
