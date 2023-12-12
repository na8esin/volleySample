package media

import android.media.MediaCodecList
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class MediaCodecTest {
    /**
     * https://developer.android.com/guide/topics/media/media-formats?hl=ja#video-formats
     * の通り、android5.0以降では、H.265 HEVCが対応しているので、
     */
    @Test
    fun test() {
        MediaCodecList(MediaCodecList.REGULAR_CODECS).codecInfos.forEach {
            if (!it.isEncoder) {
                return@forEach
            }

            it.supportedTypes.forEach {type ->
                Log.d("MediaCodecTest", "codecInfo: ${it.name}, type: $type")
            }
        }
        // video/hevcは取れる
    }
}