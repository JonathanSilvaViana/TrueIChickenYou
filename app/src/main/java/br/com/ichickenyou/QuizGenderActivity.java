package br.com.ichickenyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class QuizGenderActivity extends AppCompatActivity {

    String getRecado;
    Intent pegaIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_gender);

        //define título da activity
        setTitle(R.string.app_name);

        //pega o intent da activity anterior
        pegaIntent = getIntent();

        getRecado = pegaIntent.getStringExtra("Homem");

        Toast.makeText(this, getRecado, Toast.LENGTH_SHORT).show();

    }
}