import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream

class Tests{
    val standardOut = System.out
    val outputStreamCaptor: ByteArrayOutputStream = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @Test
    fun day1part1(){
        Solution().Solve(File("test/input.txt").readLines())
        val output = outputStreamCaptor.toString()
        assert("142" == output.trim())
    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
    }
}
