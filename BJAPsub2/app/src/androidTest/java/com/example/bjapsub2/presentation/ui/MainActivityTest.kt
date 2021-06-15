package com.example.bjapsub2.presentation.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.bjapsub2.R
import com.example.bjapsub2.data.data_utils.EspressoIdlingResources
import com.example.bjapsub2.data.data_utils.MockData
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest  {
    private val dummyMovies = MockData.generateDummyMovies()
    private val dummyTvShow = MockData.generateDummyTvShow()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource)
    }

    @Test
    fun loadMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        Espresso.onView(ViewMatchers.withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovies[0].title)))
        Espresso.onView(ViewMatchers.withId(R.id.image_poster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.image_poster_2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(ViewMatchers.withText("Released Date: ${dummyMovies[0].releasedDate}")))
        Espresso.onView(ViewMatchers.withId(R.id.tv_duration))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_duration))
            .check(ViewAssertions.matches(ViewMatchers.withText("Duration: ${dummyMovies[0].duration}")))
        Espresso.onView(ViewMatchers.withId(R.id.tv_overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_overview))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovies[0].sinopsis)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_genre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_genre))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovies[0].genre)))
    }

    @Test
    fun loadTvShow() {
        Espresso.onView(ViewMatchers.withText(R.string.tv_show)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_show))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailTvShow() {
        Espresso.onView(ViewMatchers.withText(R.string.tv_show)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        Espresso.onView(ViewMatchers.withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvShow[0].title)))
        Espresso.onView(ViewMatchers.withId(R.id.image_poster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.image_poster_2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(ViewMatchers.withText("Released Date: ${dummyTvShow[0].releasedDate}")))
        Espresso.onView(ViewMatchers.withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_duration))
            .check(ViewAssertions.matches(ViewMatchers.withText("Duration: ${dummyTvShow[0].duration}")))
        Espresso.onView(ViewMatchers.withId(R.id.tv_overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_overview))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvShow[0].sinopsis)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_genre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_genre))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvShow[0].genre)))
    }
}