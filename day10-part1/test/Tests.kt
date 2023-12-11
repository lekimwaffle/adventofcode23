import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream

class Tests{
    private val standardOut: PrintStream = System.out
    private val outputStreamCaptor: ByteArrayOutputStream = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @Test
    fun day10part1simple(){
        Solution().solve(File("test/input-simple.txt").readLines())
        val output = outputStreamCaptor.toString()
        assert("4" == output.trim())
    }

    @Test
    fun day10part1complex(){
        Solution().solve(File("test/input-complex.txt").readLines())
        val output = outputStreamCaptor.toString()
        assert("8" == output.trim())
    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
    }
}
