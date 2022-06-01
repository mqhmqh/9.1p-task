package com.example.lostandfound;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.UUID;

public class AddAdvertActivity extends AppCompatActivity{

    public RadioGroup radioGroup;
    public RadioButton btl1;
    public RadioButton btf1;
    public EditText etn1;
    public EditText etp1;
    public EditText etd1;
    public EditText etd2;
    public EditText etl1;
    public Button bts1;
    public String type;
    



    private DatabaseHelper dbHelper;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advert);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        etn1 = findViewById(R.id.etn1);
        etp1 = findViewById(R.id.etp1);
        etd1 = findViewById(R.id.etd1);
        etd2 = findViewById(R.id.etd2);
        etl1 = findViewById(R.id.etl1);
        bts1 = findViewById(R.id.bts1);
        radioGroup = findViewById(R.id.radioGroup);
        btl1 = findViewById(R.id.btl1);
        btf1 = findViewById(R.id.btf1);
        dbHelper = new DatabaseHelper(this,"LocalDatabase.db",null,1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == btl1.getId()){
                    type = btl1.getText().toString();
                }
                if(i == btf1.getId()){
                    type = btf1.getText().toString();
                }
            }
        });

        bts1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                String u_id = UUID.randomUUID().toString();
                String name = etn1.getText().toString();
                String phone = etp1.getText().toString();
                String description = etd1.getText().toString();
                String date = etd2.getText().toString();
                String location = etl1.getText().toString();
                contentValues.put("u_id",u_id);
                contentValues.put("name",name);
                contentValues.put("phone",phone);
                contentValues.put("description",description);
                contentValues.put("date",date);
                contentValues.put("location",location);
                contentValues.put("type",type);
                db.insert("Item",null,contentValues);
                Toast.makeText(AddAdvertActivity.this,"Add successfully",Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }


}