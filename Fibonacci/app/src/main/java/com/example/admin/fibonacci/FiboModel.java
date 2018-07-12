package com.example.admin.fibonacci;

import android.os.AsyncTask;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class FiboModel {
    CountTask currentTask;

    FiboModel(){
        currentTask = null;

    }

    public void count(int[] par, CountResultCallback callback){
        currentTask = new CountTask(callback);
        currentTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, par[0], par[1]);
    }

    public void cancel(){
        if(currentTask != null){
            currentTask.cancel(true);
            currentTask = null;
        }
    }

    //public void matrixCount(int[] par, CountResultCallback callback){
    //    MatrixCountTask mcTask = new MatrixCountTask(callback);
    //    mcTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, par[0], par[1]);
    //}
//
    //public void binetCount(int[] par, CountResultCallback callback){
    //    BinetCountTask bcTask = new BinetCountTask(callback);
    //    bcTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, par[0], par[1]);
    //}

    private class CountTask extends AsyncTask<Integer,Void,String>{

        private final CountResultCallback callback;

        CountTask(CountResultCallback callback) {
            this.callback = callback;
        }

        private String plainAddition(int n){

            if (n == 0) return BigInteger.ZERO.toString();
            if (n <= 2) return BigInteger.ONE.toString();
            BigInteger x = BigInteger.ONE;
            BigInteger y = BigInteger.ONE;
            BigInteger res = BigInteger.ZERO;
            for (int i = 2; i < n; i++)
            {
                if(isCancelled())
                    return null;
                res = x.add(y);
                x = y;
                y = res;
            }
            return res.toString();
        }

        private String matrixCount(int n){

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
                if(isCancelled())
                    return null;
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
            return rc.toString();
        }

        private String binetCount(int n){

            double index = Math.pow(5, 0.5);
            double left = (1 + index) / 2;
            double right = (1 - index) / 2;

            BigDecimal top = BigDecimal.valueOf(left).pow(n).subtract(BigDecimal.valueOf(right).pow(n));
            BigDecimal res = top.divide(BigDecimal.valueOf(index), 0, RoundingMode.HALF_UP);
            if(isCancelled())
                return null;
            return res.toString();
        }

        @Override
        protected String doInBackground(Integer... params) {
            switch(params[1]){
                case 1: default: return plainAddition(params[0]);
                case 2: return matrixCount(params[0]);
                case 3: return binetCount(params[0]);
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(callback != null){
                callback.onCounted(s);
            }
        }
    }

    //private class MatrixCountTask extends AsyncTask<Integer,Void,String>{
//
    //    private final CountResultCallback callback;
//
    //    MatrixCountTask(CountResultCallback callback) {
    //        this.callback = callback;
    //    }
//
    //    @Override
    //    protected String doInBackground(Integer... params) {
    //        int n = params[0];
    //        BigInteger a = BigInteger.ONE;
    //        BigInteger ta;
    //        BigInteger b = BigInteger.ONE;
    //        BigInteger tb;
    //        BigInteger c = BigInteger.ONE;
    //        BigInteger rc = BigInteger.ZERO;
    //        BigInteger tc;
    //        BigInteger d = BigInteger.ZERO;
    //        BigInteger rd = BigInteger.ONE;
//
    //        while (n>=1)
    //        {
    //            if(isCancelled())
    //                return null;
    //            if (n % 2 != 0)    // Если степень нечетная
    //            {
    //                // Умножаем вектор R на матрицу A
    //                tc = rc;
    //                rc = rc.multiply(a).add(rd.multiply(c));
    //                rd = tc.multiply(b).add(rd.multiply(d));
    //            }
//
    //            // Умножаем матрицу A на саму себя
    //            ta = a; tb = b; tc = c;
    //            a = a.multiply(a).add(b.multiply(c));
    //            b = ta.multiply(b).add(b.multiply(d));
    //            c = c.multiply(ta).add(d.multiply(c));
    //            d = tc.multiply(tb).add(d.multiply(d));
//
    //            n = n/2;  // Уменьшаем степень вдвое
    //        }
    //        return rc.toString();
    //    }
//
    //    @Override
    //    protected void onPostExecute(String s) {
    //        super.onPostExecute(s);
    //        if(callback != null){
    //            callback.onCounted(s);
    //        }
    //    }
    //}
//
    //private class BinetCountTask extends AsyncTask<Integer,Void,String>{
//
    //    private final CountResultCallback callback;
//
    //    BinetCountTask(CountResultCallback callback) {
    //        this.callback = callback;
    //    }
//
    //    @Override
    //    protected String doInBackground(Integer... params) {
    //        int n = params[0];
    //        double index = Math.pow(5, 0.5);
    //        double left = (1 + index) / 2;
    //        double right = (1 - index) / 2;
//
    //        BigDecimal top = BigDecimal.valueOf(left).pow(n).subtract(BigDecimal.valueOf(right).pow(n));
    //        BigDecimal res = top.divide(BigDecimal.valueOf(index), 0, RoundingMode.HALF_UP);
    //        if(isCancelled())
    //            return null;
    //        return res.toString();
    //    }
//
    //    @Override
    //    protected void onPostExecute(String s) {
    //        super.onPostExecute(s);
    //        if(callback != null){
    //            callback.onCounted(s);
    //        }
    //    }
    //}

    interface CountResultCallback{
        void onCounted(String res);
    }
}
