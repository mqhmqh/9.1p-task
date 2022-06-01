package com.example.lostandfound;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public String u_id;
    public TextView tvDA1;
    public TextView tvDA2;
    public TextView tvDA3;
    public Button btDA1;
    private DatabaseHelper dbHelper;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        tvDA1 = findViewById(R.id.tvDA1);
        tvDA2 = findViewById(R.id.tvDA2);
        tvDA3 = findViewById(R.id.tvDA3);
        btDA1 = findViewById(R.id.btDA1);
        dbHelper = new DatabaseHelper(this,"LocalDatabase.db",null,1);
        u_id = getIntent().getStringExtra("u_id");
        tvDA1.setText(getIntent().getStringExtra("description"));
        tvDA2.setText(getIntent().getStringExtra("date"));
        tvDA3.setText(getIntent().getStringExtra("location"));
        btDA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Item","u_id=?",new String[]{u_id});
                Intent i = new Intent(DetailActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}