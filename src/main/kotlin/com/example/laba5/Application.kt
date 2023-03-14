package com.example.laba5

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import java.net.HttpURLConnection
import java.net.URL

class HelloApplication : Application() {
    override fun start(stage: Stage) {

        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("view.fxml"))
        val scene = Scene(fxmlLoader.load())
        stage.title = "Лаба 5"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)


}