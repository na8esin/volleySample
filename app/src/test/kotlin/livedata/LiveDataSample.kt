package livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test

class LiveDataSample {
    /**
     * Method getMainLooper in android.os.Looper not mocked.
     * で怒られる
     *
     * withContext(Dispatchers.Main)
     * をつけても
     * Module with the Main dispatcher had failed to initialize. For tests Dispatchers.setMain from kotlinx-coroutines-test module can be used
     * java.lang.IllegalStateException: Module with the Main dispatcher had failed to initialize. For tests Dispatchers.setMain from kotlinx-coroutines-test module can be used
     * と怒られる
     */
    @Test
    fun switchMap() {
        val stringA = MutableLiveData<String?>(null)
        val transformed = stringA.switchMap {
            liveData {
                delay(1000)
                emit(it)
            }
        }
        runBlocking {
            withContext(Dispatchers.Main) {
                println(transformed.value)
                stringA.value = "1"
                println(transformed.value)
            }
        }
    }
}