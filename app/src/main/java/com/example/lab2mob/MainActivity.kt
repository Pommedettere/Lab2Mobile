package com.example.lab2mob

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var artworkImage: ImageView
    private lateinit var artworkTitle: TextView
    private lateinit var artworkAuthor: TextView
    private lateinit var artworkYear: TextView
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button

    private val artworks = ArtworkDataSource.artworks

    companion object {
        private const val CURRENT_INDEX_KEY = "current_index"
    }

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(CURRENT_INDEX_KEY, 0)
        }

        // Инициализация компонентов UI
        initializeViews()

        // Обновление отображения
        updateArtworkDisplay()

        // Настройка слушателей кнопок
        setupButtonListeners()
    }

    private fun initializeViews() {
        artworkImage = findViewById(R.id.artwork_image)
        artworkTitle = findViewById(R.id.artwork_title)
        artworkAuthor = findViewById(R.id.artwork_author)
        artworkYear = findViewById(R.id.artwork_year)
        previousButton = findViewById(R.id.previous_button)
        nextButton = findViewById(R.id.next_button)
    }

    private fun setupButtonListeners() {
        previousButton.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateArtworkDisplay()
            }
        }

        nextButton.setOnClickListener {
            if (currentIndex < artworks.size - 1) {
                currentIndex++
                updateArtworkDisplay()
            }
        }
    }

    private fun updateArtworkDisplay() {
        val currentArtwork = artworks[currentIndex]

        // Установка изображения
        artworkImage.setImageResource(currentArtwork.imageResId)

        artworkImage.contentDescription = getString(
            R.string.artwork_image_description,
            getString(currentArtwork.titleResId),
            getString(currentArtwork.authorResId)
        )

        artworkTitle.text = getString(currentArtwork.titleResId)
        artworkAuthor.text = getString(currentArtwork.authorResId)

        // Установка года
        currentArtwork.yearResId?.let {
            artworkYear.text = getString(it)
            artworkYear.visibility = TextView.VISIBLE
        } ?: run {
            artworkYear.visibility = TextView.GONE
        }
        updateNavigationButtons()
    }

    private fun updateNavigationButtons() {
        previousButton.isEnabled = currentIndex > 0

        nextButton.isEnabled = currentIndex < artworks.size - 1

        previousButton.contentDescription = if (previousButton.isEnabled) {
            getString(R.string.previous_button_description)
        } else {
            getString(R.string.previous_button_disabled)
        }

        nextButton.contentDescription = if (nextButton.isEnabled) {
            getString(R.string.next_button_description)
        } else {
            getString(R.string.next_button_disabled)
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_INDEX_KEY, currentIndex)
    }

    override fun onPause() {
        super.onPause()
    }
}