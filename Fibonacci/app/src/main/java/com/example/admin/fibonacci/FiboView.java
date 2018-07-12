package com.example.admin.fibonacci;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FiboView extends AppCompatActivity {
    private FiboPresenter presenter;
    private TextView tv_descr;
    private TextView tv_result;
    private EditText et_input;
    private ProgressBar progressBar;
    private Button bt_action;
    private String method;
    private boolean click = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        setContentView(R.layout.activity_main);
        tv_descr = findViewById(R.id.tv_descr);
        tv_result = findViewById(R.id.tv_result);
        et_input = findViewById(R.id.et_input);
        progressBar = findViewById(R.id.progressBar);
        bt_action = findViewById(R.id.button);
        presenter = new FiboPresenter(this);

        bt_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click == true) {
                    if (et_input.getText().toString().isEmpty())
                        return;
                    presenter.count(Integer.parseInt(et_input.getText().toString()));
                }
                else{
                    presenter.cancel();
                }
            }
        });
    }


    public void setClickEnadled(boolean enabled){
        click = enabled;
        et_input.setEnabled(click);
    }


    public void setDescription(String descr){
        tv_descr.setText(descr);
    }

    public void showResult(String res){
        tv_result.setText(res);
    }

    public void showProgress(){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress(){
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void setButtonText(String text){
        bt_action.setText(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,R.string.descr_plain);
        menu.add(0,1,0,R.string.descr_matrix);
        menu.add(0,1,0,R.string.descr_binet);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.setMethod(item.getTitle().toString(), item.getItemId());
        return super.onOptionsItemSelected(item);
    }

}
