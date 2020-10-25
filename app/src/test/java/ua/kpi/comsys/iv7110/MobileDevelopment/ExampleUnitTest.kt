package ua.kpi.comsys.iv7110.MobileDevelopment

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun creation_isCorrect() {
        val time0 = TimeVM()
        val time1 = TimeVM(13, 46, 57)
        val time2 = TimeVM(11, 10, 20)
        val time3 = TimeVM(1, 30, 90)
        assertEquals(time0.toString(), "00:00:00 AM")
        assertEquals(time1.toString(), "01:46:57 PM")
        assertEquals(time2.toString(), "11:10:20 AM")
        assertEquals(time3.toString(), "01:30:00 AM")
    }

    @Test
    fun functions_areCorrect() {
        val time0 = TimeVM()
        val time1 = TimeVM(13, 46, 57)
        val time2 = TimeVM(11, 10, 20)
        val time3 = TimeVM(1, 30, 90)

        assertEquals(time1.add(time3).toString(), "03:16:57 PM")
        assertEquals(time2.sub(time3).toString(), "09:40:20 AM")
        assertEquals(addTimes(time1, time2).toString(), "00:57:17 AM")
        assertEquals(subTimes(time0, time2).toString(), "00:49:40 PM")
    }
}