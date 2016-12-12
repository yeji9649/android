package com.example.yeji.mylogger;

import android.app.Activity;
import android.os.Bundle;

import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class TaskActivity extends Activity {
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    ListView lv;
    InputMethodManager imm;
    private GPSInfo gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, list);

        lv = (ListView) findViewById(R.id.lv);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        lv.setAdapter(adapter);

        findViewById(R.id.btn_edit).setOnClickListener(clickListener);
        findViewById(R.id.btn_delete).setOnClickListener(clickListener);



    }

    private Button.OnClickListener clickListener = new Button.OnClickListener() {
        //private View.OnClickListener clickListener = new View.OnClickListener() {

        @Override 
        public void onClick(View v) {
            // TODO Auto-generated method stub
            EditText et = (EditText) findViewById(R.id.editText);
            if (v.getId() == R.id.btn_edit) {
                // 추가 버튼
                if (et.getText().length() != 0) {
                    list.add(et.getText().toString());
                    et.setText("");
                    // 갱신되었음을 어댑터에 통보한다.
                    adapter.notifyDataSetChanged();
                    imm.hideSoftInputFromWindow(et.getWindowToken(), 0);

                }
            }
            else if (v.getId() == R.id.btn_delete) {
                // 삭제 버튼
                int pos = lv.getCheckedItemPosition();
                if (pos != ListView.INVALID_POSITION) {
                    list.remove(pos);
                    lv.clearChoices();
                    adapter.notifyDataSetChanged();
                }
            }
        }
    };
}
