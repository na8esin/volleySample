package volley

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@RunWith(AndroidJUnit4::class)
@SmallTest
class VolleySample {
    @Test
    fun test() {
        // Context of the app under test.
        val context = ApplicationProvider.getApplicationContext<Context>()
        runBlocking {
            val response = getData(context)
            Log.d("VolleySample", response)
        }
    }

    private suspend fun getData(context: Context) = suspendCoroutine { cont ->
        val queue = Volley.newRequestQueue(context)
        val url = "https://www.google.com/"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                cont.resume("Response is: ${response.substring(0, 500)}")
            },
            {
                Log.d("VolleySample", it.toString())
                cont.resume("Something went wrong!")
            }
        )
        queue.add(stringRequest)
    }
}