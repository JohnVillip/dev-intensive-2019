package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?>{

        val firstName: String?
        val lastName: String?

        if(fullName == null || fullName == "" || fullName == " " || fullName == "  " || fullName == "   ") {
            firstName = null
            lastName = null
        } else {
            val parts: List<String>? = fullName?.split(" ")

            firstName = parts?.getOrNull(0)
            lastName = parts?.getOrNull(1)
        }

        return firstName to lastName
    }

    fun transliteration(payload: String, devider: String = " "): String {
        var resultString = ""

        var n = 0

        while(n < payload.length) {
            var letter = payload.get(n).toString()

            if(letter == " ") {
                resultString = resultString + devider
                n++
                continue
            }

            resultString = resultString + transformLetter(letter)

            n++
        }


        return resultString
    }

    private fun transformLetter(letter: String): String {
        when(letter) {
            "а" -> return "a"
            "б" -> return "b"
            "в" -> return "v"
            "г" -> return "g"
            "д" -> return "d"
            "е" -> return "e"
            "ё" -> return "e"
            "ж" -> return "zh"
            "з" -> return "z"
            "и" -> return "i"
            "й" -> return "i"
            "к" -> return "k"
            "л" -> return "l"
            "м" -> return "m"
            "н" -> return "n"
            "о" -> return "o"
            "п" -> return "p"
            "р" -> return "r"
            "с" -> return "s"
            "т" -> return "t"
            "у" -> return "u"
            "ф" -> return "f"
            "х" -> return "h"
            "ц" -> return "c"
            "ч" -> return "ch"
            "ш" -> return "sh"
            "щ" -> return "sh'"
            "ъ" -> return ""
            "ы" -> return "i"
            "ь" -> return ""
            "э" -> return "e"
            "ю" -> return "yu"
            "я" -> return "ya"
            "А" -> return "A"
            "Б" -> return "B"
            "В" -> return "V"
            "Г" -> return "G"
            "Д" -> return "D"
            "Е" -> return "E"
            "Ё" -> return "E"
            "Ж" -> return "Zh"
            "З" -> return "Z"
            "И" -> return "I"
            "Й" -> return "I"
            "К" -> return "K"
            "Л" -> return "L"
            "М" -> return "M"
            "Н" -> return "N"
            "О" -> return "O"
            "П" -> return "P"
            "Р" -> return "R"
            "С" -> return "S"
            "Т" -> return "T"
            "У" -> return "U"
            "Ф" -> return "F"
            "Х" -> return "H"
            "Ц" -> return "C"
            "Ч" -> return "Ch"
            "Ш" -> return "Sh"
            "Щ" -> return "Sh'"
            "Э" -> return "E"
            "Ю" -> return "Yu"
            "Я" -> return "Ya"
            else -> return letter
        }
    }


    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstLetter: String?
        val lastLetter: String?

        when(firstName) {
            null, "", " ", "  ", "   " -> firstLetter = ""
            else -> firstLetter = firstName.get(0).toString().toUpperCase()
        }

        when(lastName) {
            null, "", " ", "  ", "   " -> lastLetter = ""
            else -> lastLetter = lastName.get(0).toString().toUpperCase()
        }

        if(firstLetter == "" && lastLetter == "") {
            return null
        }

        return firstLetter + lastLetter
    }
}