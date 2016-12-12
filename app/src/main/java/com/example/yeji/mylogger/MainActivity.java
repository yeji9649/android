package com.example.yeji.mylogger;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);

    }
    public void onClick_task(View view)
    {
        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }
    public void onClick_log(View view)
    {
        Intent intent = new Intent(this, LogActivity.class);
        startActivity(intent);
    }
    public void onClick_note(View view)
    {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }
    public void onClick_report(View view)
    {
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }
}
