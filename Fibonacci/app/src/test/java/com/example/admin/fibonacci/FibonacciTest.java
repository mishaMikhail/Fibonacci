package com.example.admin.fibonacci;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(Parameterized.class)
public class FibonacciTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {0,"0"}, {1,"1"}, {2,"1"}, {3,"2"}, {5,"5"}, {10,"55"}, {25,"75025"}, {50,"12586269025"},
                new Object[]{60, "1548008755920"}, {100,"354224848179261915075"}, {1000, "43466557686937456435688527675040625802564660517371780402481729089536555417949051890403879840079255169295922593080322634775209689623239873322471161642996440906533187938298969649928516003704476137795166849228875"}
        });
    }

    private int input;
    private String expected;

    public FibonacciTest(int input, String expected){
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void fib_plain() throws Exception {
        //Fibonacci_count fib = new Fibonacci_count();
        assertEquals(expected, Fibonacci_count.plain(input).toString());
    }

    @Test
    public void fib_matrix() throws Exception {
        //Fibonacci_count fib = new Fibonacci_count();
        assertEquals(expected, Fibonacci_count.matrix(input).toString());
    }

    @Test
    public void fib_binet() throws Exception {
        //Fibonacci_count fib = new Fibonacci_count();
        assertEquals(expected, Fibonacci_count.binet(input).toString());
    }
}