package com.example.mob2lab

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Объявляем переменные
    private lateinit var imageViewArtwork: ImageView
    private lateinit var textViewTitle: TextView
    private lateinit var textViewAuthor: TextView
    private lateinit var buttonPrevious: Button
    private lateinit var buttonNext: Button

    // Список ID картинок
    private val imageIds = listOf(
        R.drawable.artwork1,
        R.drawable.artwork2,
        R.drawable.artwork3
    )

    // Список названий
    private val titles = listOf(
        "Sailing Under the Bridge",
        "Mountain Sunrise",
        "Urban Dreams"
    )

    // Список авторов и годов (ИСПРАВЛЕНО: был пустой!)
    private val authors = listOf(
        "Kat Kuan (2017)",
        "Alex Chen (2020)",
        "Maria Rodriguez (2019)"
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Проверка запуска
        Toast.makeText(this, "Приложение запущено!", Toast.LENGTH_SHORT).show()

        try {
            // Находим элементы
            imageViewArtwork = findViewById(R.id.imageViewArtwork)
            textViewTitle = findViewById(R.id.textViewTitle)
            textViewAuthor = findViewById(R.id.textViewAuthor)
            buttonPrevious = findViewById(R.id.buttonPrevious)
            buttonNext = findViewById(R.id.buttonNext)

            Toast.makeText(this, "Элементы найдены!", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Toast.makeText(this, "Ошибка: ${e.message}", Toast.LENGTH_LONG).show()
            return  // Выходим, если ошибка
        }

        // Показываем первую картинку
        showArtwork(currentIndex)

        // Обработчики кнопок
        buttonPrevious.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                showArtwork(currentIndex)
            }
        }

        buttonNext.setOnClickListener {
            if (currentIndex < imageIds.size - 1) {
                currentIndex++
                showArtwork(currentIndex)
            }
        }
    }

    private fun showArtwork(index: Int) {
        try {
            // Устанавливаем картинку
            imageViewArtwork.setImageResource(imageIds[index])

            // Устанавливаем текст
            textViewTitle.text = titles[index]
            textViewAuthor.text = authors[index]

            // Обновляем кнопки
            buttonPrevious.isEnabled = index > 0
            buttonNext.isEnabled = index < imageIds.size - 1

            // Добавляем описание для доступности
            val desc = "${titles[index]} by ${authors[index].substringBefore(" (")}"
            imageViewArtwork.contentDescription = desc

            Toast.makeText(this, "Показана картина $index", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Toast.makeText(this, "Ошибка картинки: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}