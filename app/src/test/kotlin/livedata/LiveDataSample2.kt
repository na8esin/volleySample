package livedata

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.volleysample.TopViewModel
import getOrAwaitValue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LiveDataSample2 {
    // Subject under test
    private lateinit var topViewModel: TopViewModel

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        topViewModel = TopViewModel()
    }
    @Test
    fun simple() {
        topViewModel.updateMessage("Hello")

        val value = topViewModel.message.getOrAwaitValue()
        assertThat(value, `is`("Hello"))
    }

    @Test
    fun switchMap() {
        topViewModel.updateMessage("Hello")

        val value = topViewModel.transformedMessage.getOrAwaitValue()
        assertThat(value, `is`("Hello World"))    }
}