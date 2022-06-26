package com.kaano8.closedpull

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kaano8.closedpull.api.data.ClosedPrDataModel
import com.kaano8.closedpull.api.data.ClosedPrException
import com.kaano8.closedpull.api.data.User
import com.kaano8.closedpull.extensions.mapToUiModel
import com.kaano8.closedpull.repository.ClosedPrRepository
import com.kaano8.closedpull.ui.main.ClosedPrViewModel
import com.kaano8.closedpull.ui.main.state.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ClosedPrViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    private lateinit var repository: ClosedPrRepository

    private lateinit var closedPrViewModel: ClosedPrViewModel

    @Mock
    private lateinit var uiStateObserver: Observer<UiState>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        closedPrViewModel = ClosedPrViewModel(repository)
    }

    @Test
    fun `test getClosedPr() when empty list is returned`() {
        testCoroutineRule.runBlockingTest {
            `when`(repository.getClosedPrs()).thenReturn(listOf())
            closedPrViewModel.getClosedPrs()
            closedPrViewModel.uiState.observeForever(uiStateObserver)
            assertEquals(UiState.NoClosedPrs, closedPrViewModel.uiState.value)
        }
    }

    @Test
    fun `test getClosedPr() when non empty list is returned`() {
        val data = listOf(getClosedPrDataModel())
        testCoroutineRule.runBlockingTest {
            `when`(repository.getClosedPrs()).thenReturn(data)
            closedPrViewModel.getClosedPrs()
            closedPrViewModel.uiState.observeForever(uiStateObserver)
            assertEquals(UiState.Success(data.map { it.mapToUiModel() }), closedPrViewModel.uiState.value)
        }
    }

    @Test
    fun `test getClosedPr() when http exception is returned`() {
        val exception = ClosedPrException(displayMessage = "generic exception")
        testCoroutineRule.runBlockingTest {
            `when`(repository.getClosedPrs()).thenThrow(exception)
            closedPrViewModel.getClosedPrs()
            closedPrViewModel.uiState.observeForever(uiStateObserver)
            assertEquals(UiState.Error(exception.displayMessage), closedPrViewModel.uiState.value)
        }
    }

    private fun getClosedPrDataModel() = ClosedPrDataModel(
        id = 1,
        title = "Hello, world!",
        state = "closed",
        user = User(title = "KaANO-8", avatarUrl = "", login = ""),
        createdAt = "28-02-2022",
        closedAt = "26-03-2022"
    )
}
