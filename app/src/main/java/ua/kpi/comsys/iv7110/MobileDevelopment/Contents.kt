package ua.kpi.comsys.iv7110.MobileDevelopment

import android.util.Log
import java.util.logging.Logger
import kotlin.math.ceil
import kotlin.random.Random.Default.nextInt

fun doContents(){
    // Part 1

    // Given string with format "Student1 - Group1; Student2 - Group2; ..."

    val studentsStr = "Бортнік Василь - ІВ-72; Чередніченко Владислав - ІВ-73; Гуменюк Олександр - ІВ-71; Корнійчук Ольга - ІВ-71; Киба Олег - ІВ-72; Капінус Артем - ІВ-73; Овчарова Юстіна - ІВ-72; Науменко Павло - ІВ-73; Трудов Антон - ІВ-71; Музика Олександр - ІВ-71; Давиденко Костянтин - ІВ-73; Андрющенко Данило - ІВ-71; Тимко Андрій - ІВ-72; Феофанов Іван - ІВ-71; Гончар Юрій - ІВ-73"

    // Task 1
    // Create dictionary:
    // - key is a group name
    // - value is sorted array with students

    var studentsGroups: Map<String, List<String>>

    // Your code begins

    studentsGroups = studentsStr.split("; ").map{it.split(" - ")}.groupBy({it.last()}, {it.first()})

    // Your code ends
    Log.i(LOG_TAG, studentsGroups.toString())

    // Given array with expected max points

    val points = listOf(5, 8, 12, 12, 12, 12, 12, 12, 15)

    // Task 2
    // Create dictionary:
    // - key is a group name
    // - value is dictionary:
    //   - key is student
    //   - value is array with points (fill it with random values, use function `randomValue(maxValue: Int) -> Int` )

    fun randomValue(maxValue: Int): Int {
        return when (nextInt(6)) {
            1 -> ceil(maxValue.toFloat() * 0.7).toInt()
            2 -> ceil(maxValue.toFloat() * 0.9).toInt()
            3, 4, 5 -> maxValue
            else -> 0
        }
    }

    var studentPoints: Map<String, Map<String, List<Int>>>

    // Your code begins

    studentPoints = studentsGroups.mapValues { it.value.associateWith { points.map{ p -> randomValue(p)} } }

    // Your code ends

    Log.i(LOG_TAG, studentPoints.toString())

    // Task 3
    // Create dictionary:
    // - key is a group name
    // - value is dictionary:
    //   - key is student
    //   - value is sum of student's points

    var sumPoints: Map<String, Map<String, Int>>

    // Your code begins

    sumPoints = studentPoints.mapValues { group -> group.value.mapValues { st ->  st.value.sum() } }

    // Your code ends

    Log.i(LOG_TAG, sumPoints.toString())

    // Task 4
    // Create dictionary:
    // - key is group name
    // - value is average of all students points

    var groupAvg: Map<String, Double>

    // Your code begins

    groupAvg = studentPoints.mapValues { group -> group.value.flatMap {st -> st.value }.average() }

    // Your code ends

    Log.i(LOG_TAG, groupAvg.toString())

    // Task 5
    // Create dictionary:
    // - key is group name
    // - value is array of students that have >= 60 points

    var passedPerGroup: Map<String, List<String>>

    // Your code begins

    passedPerGroup = sumPoints.mapValues { group -> group.value.filter { st -> st.value >= 60 }.map { st -> st.key } }

    // Your code ends

    Log.i(LOG_TAG, passedPerGroup.toString())

    // Example of output. Your results will differ because random is used to fill points.
    //
    //["ІВ-73": ["Гончар Юрій", "Давиденко Костянтин", "Капінус Артем", "Науменко Павло", "Чередніченко Владислав"], "ІВ-72": ["Бортнік Василь", "Киба Олег", "Овчарова Юстіна", "Тимко Андрій"], "ІВ-71": ["Андрющенко Данило", "Гуменюк Олександр", "Корнійчук Ольга", "Музика Олександр", "Трудов Антон", "Феофанов Іван"]]
    //
    //["ІВ-73": ["Давиденко Костянтин": [5, 8, 9, 12, 11, 12, 0, 0, 14], "Капінус Артем": [5, 8, 12, 12, 0, 12, 12, 12, 11], "Науменко Павло": [4, 8, 0, 12, 12, 11, 12, 12, 15], "Чередніченко Владислав": [5, 8, 12, 12, 11, 12, 12, 12, 15], "Гончар Юрій": [5, 6, 0, 12, 0, 11, 12, 11, 14]], "ІВ-71": ["Корнійчук Ольга": [0, 0, 12, 9, 11, 11, 9, 12, 15], "Музика Олександр": [5, 8, 12, 0, 11, 12, 0, 9, 15], "Гуменюк Олександр": [5, 8, 12, 9, 12, 12, 11, 12, 15], "Трудов Антон": [5, 0, 0, 11, 11, 0, 12, 12, 15], "Андрющенко Данило": [5, 6, 0, 12, 12, 12, 0, 9, 15], "Феофанов Іван": [5, 8, 12, 9, 12, 9, 11, 12, 14]], "ІВ-72": ["Киба Олег": [5, 8, 12, 12, 11, 12, 0, 0, 11], "Овчарова Юстіна": [5, 8, 12, 0, 11, 12, 12, 12, 15], "Бортнік Василь": [4, 8, 12, 12, 0, 12, 9, 12, 15], "Тимко Андрій": [0, 8, 11, 0, 12, 12, 9, 12, 15]]]
    //
    //["ІВ-72": ["Бортнік Василь": 84, "Тимко Андрій": 79, "Овчарова Юстіна": 87, "Киба Олег": 71], "ІВ-73": ["Капінус Артем": 84, "Науменко Павло": 86, "Чередніченко Владислав": 99, "Гончар Юрій": 71, "Давиденко Костянтин": 71], "ІВ-71": ["Корнійчук Ольга": 79, "Трудов Антон": 66, "Андрющенко Данило": 71, "Гуменюк Олександр": 96, "Феофанов Іван": 92, "Музика Олександр": 72]]
    //
    //["ІВ-71": 79.333336, "ІВ-72": 80.25, "ІВ-73": 82.2]
    //
    //["ІВ-72": ["Бортнік Василь", "Киба Олег", "Овчарова Юстіна", "Тимко Андрій"], "ІВ-73": ["Давиденко Костянтин", "Капінус Артем", "Чередніченко Владислав", "Гончар Юрій", "Науменко Павло"], "ІВ-71": ["Музика Олександр", "Трудов Антон", "Гуменюк Олександр", "Феофанов Іван", "Андрющенко Данило", "Корнійчук Ольга"]]

}
