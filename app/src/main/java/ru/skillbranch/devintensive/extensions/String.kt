package ru.skillbranch.devintensive.extensions

fun String.truncate( characters: Int = 16): String {
    var resString = this

    while(resString.get(resString.length - 1) == ' ') {
        resString = resString.substring(0,resString.length - 1)
    }

    while(resString.get(0) == ' ') {
        resString = resString.substring(1,resString.length)
    }


    if(resString.length <= characters) return resString

    val truncateString = resString.substring(0, characters)


    if(truncateString.get(truncateString.length - 1) == ' ') {
        return "${truncateString.substring(0, truncateString.length - 1)}..."
    }

    return "$truncateString..."
}

fun String.stripHtml(): String {
    var resString: String

//    for(i in this.indices) {
//        if
//
//    }

    return "в работе пока"

}


//*String.stripHtml
//Необходимо реализовать метод stripHtml для очистки строки от лишних пробелов, html тегов, escape последовательностей
//+1
//Реализуй extension позволяющий очистить строку от html тегов и html escape последовательностей ("& < > ' ""),
// а так же удалить пустые символы (пробелы) между словами если их больше 1. Необходимо вернуть модифицированную строку
//Пример:
//"<p class="title">Образовательное IT-сообщество Skill Branch</p>".stripHtml() //Образовательное IT-сообщество Skill Branch
//"<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml() //Образовательное IT-сообщество Skill Branch