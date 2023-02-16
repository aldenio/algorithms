import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*

// Complete the freqQuery function below.
fun freqQuery(queries: Array<Array<Int>>): Array<Int> {
    var response : MutableList<Int> = mutableListOf()
    val mapa : MutableMap<Int,Int> = mutableMapOf()
    for (q in queries){
        when (q[0]) {
            1 -> mapa.put(q[1], (mapa.get(q[1])?:0) + 1) 
            2 -> {
                val q1 = mapa.get(q[1])?:1           
                mapa.put(q[1], q1-1);
            }
            3 ->{
                if (mapa.values.contains(q[1])){
                    response.add(1)
                } else {
                    response.add(0)
                }
            }
            else -> throw IllegalArgumentException()
        }
    }
    return response.toTypedArray()
}

fun main(args: Array<String>) {
    val q = readLine()!!.trim().toInt()

    val queries = Array<Array<Int>>(q, { Array<Int>(2, { 0 }) })

    for (i in 0 until q) {
        queries[i] = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()
    }

    val ans = freqQuery(queries)

    println(ans.joinToString("\n"))
}
