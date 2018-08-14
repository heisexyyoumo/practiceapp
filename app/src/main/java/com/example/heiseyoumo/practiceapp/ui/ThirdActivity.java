package com.example.heiseyoumo.practiceapp.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.heiseyoumo.practiceapp.R;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_value;
    private Button btn_edt;
    private Button btn_prb;
    private Button btn_dialog;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        initView();
    }

    private void initView() {
        edit_value = (EditText) findViewById(R.id.edit_value);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btn_edt = (Button) findViewById(R.id.btn_edt);
        btn_edt.setOnClickListener(this);
        btn_dialog = (Button) findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(this);
        btn_prb = (Button) findViewById(R.id.btn_prb);
        btn_prb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edt:
                String input = edit_value.getText().toString();
                Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_prb:
                if (progressBar.getVisibility() == View.GONE) {
                    progressBar.setVisibility(View.VISIBLE);
                }else{
                    progressBar.setVisibility(View.GONE);
                }
                break;
            case R.id.btn_dialog:
                AlertDialog.Builder dialog = new AlertDialog.Builder(ThirdActivity.this);
                dialog.setTitle("This is a dialog");
                dialog.setMessage("something is important");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;

        }
    }
}
