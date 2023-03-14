package com.example.laba5

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.scene.control.ChoiceBox
import javafx.scene.control.Label
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class Controller {

    private val usdUrl = "https://quote.ru/ticker/59111"
    private val eurUrl = "https://quote.ru/ticker/59090"
    private val cnuUrl = "https://quote.ru/ticker/59066"

    private val currenciesList = listOf("Доллар", "Евро", "Юань")

    @FXML
    private lateinit var currencySelector: ChoiceBox<String>

    @FXML
    private lateinit var priceLabel: Label

    @FXML
    fun initialize() {
//        getPrice(getHTML(usdUrl))
//        getPrice(getHTML(eurUrl))
//        getPrice(getHTML(cnuUrl))

        currencySelector.items = FXCollections.observableArrayList(currenciesList)
        currencySelector.setOnAction {
            when(currencySelector.value) {
                currenciesList[0] -> {
                    setPrice(usdUrl)
                }
                currenciesList[1] -> {
                    setPrice(eurUrl)
                }
                currenciesList[2] -> {
                    setPrice(cnuUrl)
                }
            }
        }

        currencySelector.value = currenciesList[0]
    }

    private fun getHTML(url: String): String {
        val urlObj = URL(url)
        val urlConnection = urlObj.openConnection() as HttpURLConnection

        var text = ""

        try {
            text = urlConnection.inputStream.bufferedReader().readText()

        } catch (e: IOException) {
            priceLabel.text = "Ошибка"

        } finally {
            urlConnection.disconnect()

        }

        return text
    }

    private fun setPrice(url: String) {

        val html = getHTML(url)

        var price = html.substring(html.indexOf("₽<!-- --> <!-- -->") + 18, html.length)
        price = price.substring(0, price.indexOf("<"))

        println(price)

        priceLabel.text = price
    }


}