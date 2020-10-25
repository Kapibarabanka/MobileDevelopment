package ua.kpi.comsys.iv7110.MobileDevelopment

import java.util.*

private val maxH = 23
private val maxM = 59
private val maxS = 59

// IV-7110, Varvara Molchanova
class TimeVM(hours: Int, minutes: Int, seconds: Int) {
    val h: Int = if (hours in 0..maxH) hours else 0
    val m: Int = if (minutes in 0..maxM) minutes else 0
    val s: Int = if (seconds in 0..maxM) seconds else 0
    constructor() : this(0, 0, 0)
    constructor(time: Date) : this(time.hours, time.minutes, time.seconds)

    override fun toString(): String {
        val zz = if (h < 12) "AM" else "PM"
        val h12 = h % 12
        return "%02d:%02d:%02d %s".format(h12, m, s, zz)
    }

    fun add(that: TimeVM): TimeVM {
        val s = this.s + that.s
        val m = this.m + that.m + (if (s > maxS) 1 else 0)
        val h = this.h + that.h + (if (m > maxM) 1 else 0)
        return TimeVM(
            h % (maxH + 1),
            m % (maxM + 1),
            s % (maxS + 1))
    }

    fun sub(that: TimeVM): TimeVM {
        val s = this.s - that.s
        val m = this.m - that.m - (if (s < 0) 1 else 0)
        val h = this.h - that.h - (if (m < 0) 1 else 0)
        return TimeVM(
            (h + maxH + 1) % (maxH + 1),
            (m + maxM + 1) % (maxM + 1),
            (s + maxS + 1) % (maxS + 1))
    }
}

fun addTimes(t1: TimeVM, t2: TimeVM): TimeVM {return t1.add(t2)}

fun subTimes(t1: TimeVM, t2: TimeVM): TimeVM {return t1.sub(t2)}
