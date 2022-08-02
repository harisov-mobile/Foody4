package ru.internetcloud.foody4.data.util

import org.jsoup.Jsoup

fun parseHtml(inputString: String): String {

    return Jsoup.parse(inputString).text()
}