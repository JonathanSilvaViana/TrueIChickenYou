package br.com.ichickenyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class GenderActivity extends AppCompatActivity {

    ImageButton btMale, btFemale, btDoubtorPreferDontSay, manyoranothergenders;
    Intent envia_homem, envia_mulher, envia_btDoubtorPreferDontSay, envia_manyoranothergenders;
    String man, woman, anyGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        //define título da activity
        setTitle(R.string.app_name);

        btMale = (ImageButton)findViewById(R.id.btMale);

        btMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                envia_homem();
            }
        });

    }

    public void envia_homem()
    {
        envia_homem = new Intent(this, QuizGenderActivity.class);
        man = "Homem";
        envia_homem.putExtra(man, man);
        startActivity(envia_homem);
    }

    public void envia_mulher()
    {

    }

    public void envia_btDoubtorPreferDontSay()
    {

    }

    public void envia_manyoranothergenders()
    {

    }
}