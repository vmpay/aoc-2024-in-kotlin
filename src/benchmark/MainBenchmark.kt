package benchmark

import dayOnePartOne
import dayOnePartTwo
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.Warmup
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 10)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
class MainBenchmark {
    private var data = listOf<String>()

    @Setup
    fun setUp() {
        data = buildList {
            repeat(999) {
                add("${Random.nextInt()}   ${Random.nextInt()}")
            }
        }
    }

    @Benchmark
    fun dayOnePartOneBenchmark(): Int {
        return dayOnePartOne(data)
    }

    @Benchmark
    fun dayOnePartTwoBenchmark(): Int {
        return dayOnePartTwo(data)
    }
}