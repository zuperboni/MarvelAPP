package com.marvelapp.br

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marvelapp.br.sdk.API
import com.marvelapp.br.sdk.response.comic.ComicDetalheResponse
import com.marvelapp.br.viewmodel.PersonagemViewModel
import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

class PersonagemViewModelTest {
    @get: Rule
    val taskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: PersonagemViewModel
    private val api = mock<API>()
    private val anValidID = Random.nextInt()

    @Before
    fun before_each_test() {

    }

    @Test fun givenAnValidId_ShouldLoadComic() {
        // Given

        // When
        // Then
    }

}