package br.com.ichickenyou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_som);

        //define título da activity
        setTitle(R.string.sobre_som);
    }
}