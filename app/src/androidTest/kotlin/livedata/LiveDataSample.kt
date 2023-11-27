package livedata

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.test.filters.SmallTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class LiveDataSample {
    @Test
    fun switchMap() {
        val stringA = MutableLiveData<String?>(null)
        val transformed = stringA.switchMap {
            liveData {
                emit(it)
            }
        }
        runBlocking {
            withContext(Dispatchers.Main) {
                Log.d("LiveDataSample", transformed.value ?: "null")
                stringA.value = "1"
            }
        }
        Log.d("LiveDataSample", transformed.value ?: "null")

    }
}