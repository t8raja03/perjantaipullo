package org.oranki.perjantaipullo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> selected_numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // onClick-metodi MainActivityn ainoalle napille, mennään SelectActivityyn
    public void onClickSelect(View view) {
        Intent intent = new Intent(this, SelectActivity.class);
        startActivity(intent);
    }

}