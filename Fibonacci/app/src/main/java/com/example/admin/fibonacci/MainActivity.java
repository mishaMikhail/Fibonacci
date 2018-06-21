package com.example.admin.fibonacci;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
String method;
TextView description;
TextView result;
ProgressBar progressBar;
EditText editText;
CountTask countTask;
Button button;
Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        description = findViewById(R.id.tv_descr);
        result = findViewById(R.id.tv_result);
        method = getString(R.string.descr_plain);
        description.setText("Fibonacci number will be calculated via " + method);
        progressBar = findViewById(R.id.progressBar);
        editText = findViewById(R.id.et_input);
        button = findViewById(R.id.button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(R.string.descr_plain);
        menu.add(R.string.descr_matrix);
        menu.add(R.string.descr_binet);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        method = item.toString();
        description.setText("Fibonacci number will be calculated via " + method);
        description.setVisibility(View.VISIBLE);
        result.setText("");
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {

        if (editText.getText().toString().isEmpty())
            return;

        if (countTask != null){
            if (countTask.getStatus() == AsyncTask.Status.RUNNING){
                CountTask ct = countTask;
                countTask = null;
                ct.cancel(true);
                button.setText("Go");
                progressBar.setVisibility(View.INVISIBLE);
                result.setText(R.string.op_cancel);
                editText.setEnabled(true);
                return;
            }
        }

        countTask = new CountTask();
        countTask.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public class CountTask extends AsyncTask<Void,Void,String>{
        String m;
        int num;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
            editText.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            button.setText("Cancel");
            num = Integer.parseInt(editText.getText().toString());
            m = method;
            result.setText(R.string.calcul);
        }

        @Override
        protected String doInBackground(Void... voids) {
            BigInteger res;
                switch (m) {
                    default:
                    case "Plain addition":
                        res = Fibonacci_count.plain(num);
                        break;
                    case "Matrix method":
                        res = Fibonacci_count.matrix(num);
                        break;
                    case "Binet formula":
                        res = Fibonacci_count.binet(num);
                        break;
                }
                if(isCancelled())
                    return null;
            return res.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            description.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            String res = "Result via " + m + ":\n"+ s;
            result.setText(res);
            button.setText("Go");
            editText.setEnabled(true);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            //button.setText("Go");
            //progressBar.setVisibility(View.INVISIBLE);
            //result.setText(R.string.op_cancel);
            //editText.setEnabled(true);
        }
    }
}