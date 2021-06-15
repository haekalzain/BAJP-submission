package com.example.bjapsub1.presentation.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.bjapsub1.R
import com.example.bjapsub1.presentation.data.MockData
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val dummyMovies = MockData.generateMockMovies()
    private val dummyTvShow = MockData.generateMockTvShow()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withText(R.string.movies)).perform(click())
        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_title))
            .check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.image_poster))
            .check(matches(isDisplayed()))
        onView(withId(R.id.image_poster_2))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date))
            .check(matches(withText("Released Date: ${dummyMovies[0].releasedDate}")))
        onView(withId(R.id.tv_release_date))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration))
            .check(matches(withText("Duration: ${dummyMovies[0].duration}")))
        onView(withId(R.id.tv_overview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview))
            .check(matches(withText(dummyMovies[0].sinopsis)))
        onView(withId(R.id.tv_genre))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre))
            .check(matches(withText(dummyMovies[0].genre)))
    }

    @Test
    fun loadTvShow() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rv_show))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_title))
            .check(matches(withText(dummyTvShow[0].title)))
        onView(withId(R.id.image_poster))
            .check(matches(isDisplayed()))
        onView(withId(R.id.image_poster_2))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date))
            .check(matches(withText("Released Date: ${dummyTvShow[0].releasedDate}")))
        onView(withId(R.id.tv_duration))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration))
            .check(matches(withText("Duration: ${dummyTvShow[0].duration}")))
        onView(withId(R.id.tv_overview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview))
            .check(matches(withText(dummyTvShow[0].sinopsis)))
        onView(withId(R.id.tv_genre))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre))
            .check(matches(withText(dummyTvShow[0].genre)))
    }

}