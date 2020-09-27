package br.com.ichickenyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class GenderActivity extends AppCompatActivity {

    ImageButton btMale, btFemale, btDoubtorPreferDontSay, manyoranothergenders;
    Intent envia_homem, envia_mulher, envia_btDoubtorPreferDontSay, envia_manyoranothergenders;
    String man, woman, anyGender, gender, impossible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        //define título da activity
        setTitle(R.string.app_name);

        //define strings

        man = "Homem"; woman = "Mulher"; anyGender = "qualquergenero"; impossible = "impossivel";

        //encontra os botões e abaixo redireciona como um router para as opções

        btMale = (ImageButton)findViewById(R.id.btMale);

        btMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                envia_homem();
            }
        });

        btFemale = (ImageButton)findViewById(R.id.btFemale);

        btFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                envia_mulher();
            }
        });

        btDoubtorPreferDontSay = (ImageButton)findViewById(R.id.btDoubtorPreferDontSay);

        btDoubtorPreferDontSay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                envia_manyoranothergenders();
            }
        });

        manyoranothergenders = (ImageButton)findViewById(R.id.manyoranothergenders);

        manyoranothergenders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                envia_manyoranothergenders();
            }
        });

    }

    //envia para homem
    public void envia_homem()
    {
        envia_homem = new Intent(this, MaleQuizActivity.class);
        startActivity(envia_homem);
    }

    //envia para mulher
    public void envia_mulher()
    {
        envia_mulher = new Intent(this, FemaleQuizActivity.class);
        startActivity(envia_mulher);
    }

    //envia para qualquer gênero
    public void envia_manyoranothergenders()
    {
        envia_manyoranothergenders = new Intent(this, AnyGenderQuizActivity.class);
        startActivity(envia_manyoranothergenders);
    }
}