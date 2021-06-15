package com.example.bjapsub3

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.bjapsub3.util.EspressoIdlingResource
import com.example.bjapsub3.util.MockData
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private val dummyMovies = MockData.generateMockMovies()
    private val dummyTvShow = MockData.generateMockTvShow()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        Espresso.onView(withId(R.id.rv_movies))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovies() {
        Espresso.onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        Espresso.onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(withText(dummyMovies[0].title)))
        Espresso.onView(withId(R.id.image_poster))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.image_poster_2))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(withText("Released Date: ${dummyMovies[0].releasedDate}")))
        Espresso.onView(withId(R.id.tv_duration))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_duration))
            .check(ViewAssertions.matches(withText("Duration: ${dummyMovies[0].duration}")))
        Espresso.onView(withId(R.id.tv_overview))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_overview))
            .check(ViewAssertions.matches(withText(dummyMovies[0].sinopsis)))
        Espresso.onView(withId(R.id.tv_genre))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_genre))
            .check(ViewAssertions.matches(withText(dummyMovies[0].genre)))
    }

    @Test
    fun loadTvShow() {
        Espresso.onView(withText(R.string.tv_show)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_show))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailTvShow() {
        Espresso.onView(withText(R.string.tv_show)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        Espresso.onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(withText(dummyTvShow[0].title)))
        Espresso.onView(withId(R.id.image_poster))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.image_poster_2))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(withText("Released Date: ${dummyTvShow[0].releasedDate}")))
        Espresso.onView(withId(R.id.tv_duration))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_duration))
            .check(ViewAssertions.matches(withText("Duration: ${dummyTvShow[0].duration}")))
        Espresso.onView(withId(R.id.tv_overview))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_overview))
            .check(ViewAssertions.matches(withText(dummyTvShow[0].sinopsis)))
        Espresso.onView(withId(R.id.tv_genre))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.tv_genre))
            .check(ViewAssertions.matches(withText(dummyTvShow[0].genre)))
    }

    @Test
    fun loadFavoriteMovies(){
        Espresso.onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        Espresso.onView(withId(R.id.action_favorite)).perform(ViewActions.click())
        Espresso.onView(isRoot()).perform(ViewActions.pressBack())
        Espresso.onView(withId(R.id.action_favorite)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_movies_fav)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_movies_fav)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        Espresso.onView(withId(R.id.action_favorite)).perform(ViewActions.click())
        Espresso.onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadFavoriteTvShow(){
        Espresso.onView(withText("TV Show")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_show)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        Espresso.onView(withId(R.id.action_favorite)).perform(ViewActions.click())
        Espresso.onView(isRoot()).perform(ViewActions.pressBack())
        Espresso.onView(withId(R.id.action_favorite)).perform(ViewActions.click())
        Espresso.onView(withText("Favorite TV Show")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tvshow_fav)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rv_tvshow_fav)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        Espresso.onView(withId(R.id.action_favorite)).perform(ViewActions.click())
        Espresso.onView(isRoot()).perform(ViewActions.pressBack())
    }
}