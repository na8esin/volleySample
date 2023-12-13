package display

import android.content.Context
import android.util.Log
import android.view.WindowManager
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class DisplayModeTest {
    @Test
    fun logDisplayMode() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        // https://github.com/youtube/cobalt/blob/401d1c728256140d4b6d33e87943ee1b47f615f1/starboard/android/apk/app/src/main/java/dev/cobalt/util/DisplayUtil.java#L86
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        // こんな感じで出力される
        // Display mode: {id=1, width=1920, height=1080, fps=60.000004, alternativeRefreshRates=[]}
        // 4kはこんな感じ
        // Display mode: {id=1, width=3840, height=2160, fps=60.000004, alternativeRefreshRates=[]}
        Log.d("DisplayModeTest", "Display mode: ${display.mode}")

        // 4kを判別
        display.mode.physicalHeight == 2160 && display.mode.physicalHeight == 3840
    }
}