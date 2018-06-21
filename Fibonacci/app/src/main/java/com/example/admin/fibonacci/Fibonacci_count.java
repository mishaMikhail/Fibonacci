package com.example.admin.fibonacci;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Created by admin on 20.06.2018.
 */

public class Fibonacci_count {
    public Fibonacci_count() {

    }

    public static BigInteger plain(int n){
        if(n == 0) return BigInteger.ZERO;
        if (n <= 2) return BigInteger.ONE;
        BigInteger x = BigInteger.ONE;
        BigInteger y = BigInteger.ONE;
        BigInteger res = BigInteger.ZERO;
        for (int i = 2; i < n; i++)
        {
            res = x.add(y);
            x = y;
            y = res;
        }
        return res;
    }

    public static BigInteger matrix(int n){

        BigInteger a = BigInteger.ONE;
        BigInteger ta;
        BigInteger b = BigInteger.ONE;
        BigInteger tb;
        BigInteger c = BigInteger.ONE;
        BigInteger rc = BigInteger.ZERO;
        BigInteger tc;
        BigInteger d = BigInteger.ZERO;
        BigInteger rd = BigInteger.ONE;

        while (n>=1)
        {
            if (n % 2 != 0)    // Если степень нечетная
            {
                // Умножаем вектор R на матрицу A
                tc = rc;
                rc = rc.multiply(a).add(rd.multiply(c));
                rd = tc.multiply(b).add(rd.multiply(d));
            }

            // Умножаем матрицу A на саму себя
            ta = a; tb = b; tc = c;
            a = a.multiply(a).add(b.multiply(c));
            b = ta.multiply(b).add(b.multiply(d));
            c = c.multiply(ta).add(d.multiply(c));
            d = tc.multiply(tb).add(d.multiply(d));

            n = n/2;  // Уменьшаем степень вдвое
        }
        return rc;
    }

    public static BigInteger binet(int n) {

        double index = Math.pow(5, 0.5);
        double left = (1 + index) / 2;
        double right = (1 - index) / 2;

        BigDecimal top = BigDecimal.valueOf(left).pow(n).subtract(BigDecimal.valueOf(right).pow(n));
        BigDecimal res = top.divide(BigDecimal.valueOf(index), 0, RoundingMode.HALF_UP);
        return res.toBigInteger();
    }

}
