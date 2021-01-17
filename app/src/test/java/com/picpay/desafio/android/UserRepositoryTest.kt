package com.picpay.desafio.android


import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.UserRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class UserRepositoryTest {
    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var errorResponseBody: ResponseBody

    private lateinit var users: List<User>
    private lateinit var response: Response<List<User>>


    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        users = arrayListOf(
            User("img1.jpg", "Fulano de Tal", 1111, "@fulano.detal"),
            User("img2.jpg", "Ciclano de Tal", 2222, "@ciclano.detal"),
        )
        response = Response.success(users)
    }

    @Test
    fun `given response ok when fetching results then return a list with elements`() {
        runBlockingTest {
            whenever(userRepository.getUsers()).thenReturn(response)

            val userResponse = userRepository.getUsers()

            assertNotNull(userResponse)
            assertEquals(2, userResponse.body()?.size)
        }
    }

    @Test
    fun `given response ok when fetching empty results then return an empty list`() {
        runBlockingTest {
            response = Response.success(arrayListOf())
            whenever(userRepository.getUsers()).thenReturn(response)

            val userResponse = userRepository.getUsers()

            assertNotNull(userResponse)
            assertEquals(0, userResponse.body()?.size)
        }
    }

    @Test
    fun `given response failure when fetching results then return exception`() {
        runBlockingTest {
            val responseError = Response.error<List<User>>(500, errorResponseBody)
            whenever(userRepository.getUsers()).thenReturn(responseError)

            val userResponse = userRepository.getUsers()

            assertNotNull(userResponse.errorBody())
        }
    }

    @After
    fun after() {
        users = emptyList()
        response = Response.success(null)
    }
}

