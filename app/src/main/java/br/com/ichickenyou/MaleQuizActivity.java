package br.com.ichickenyou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class MaleQuizActivity extends AppCompatActivity {


    ArrayAdapter<String> nomes_masculinos,gosta_de_fazer_ou_faz;
    TextView campo_nome_amigo, texto_fazer, texto_parte_galinha;
    AutoCompleteTextView nome_autocomplete, fazer_autocomplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_quiz);

        //encontra campo de texto para indicar nomes de amigos
        campo_nome_amigo = (TextView) findViewById(R.id.texto_sobre_amigo);

        //cria o array adapter de nomes masculinos
        nomes_masculinos = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.malenames));

        //encontro o campo de nomes para auto completar
        nome_autocomplete = (AutoCompleteTextView) findViewById(R.id.nome_autocomplete);

        //define o array adapter no campo de texto para nomes
        nome_autocomplete.setAdapter(nomes_masculinos);
        //delimita o tamanho do campo de texto para nomes
        nome_autocomplete.setThreshold(1);
        //define a largura do dropdown do autocomplete
        nome_autocomplete.setDropDownWidth(700);


        //encontra campo de texto para indicar ações
        texto_parte_galinha = (TextView) findViewById(R.id.texto_parte_galinha);


        //cria o array adapter de ações que o usuário gosta de fazer ou faz
        gosta_de_fazer_ou_faz = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.thingstodo));

        //encontra o campo para ações
        fazer_autocomplete = (AutoCompleteTextView) findViewById(R.id.fazer_autocomplete);
        //define o array adapter no campo de texto para o nome das ações
        fazer_autocomplete.setAdapter(gosta_de_fazer_ou_faz);
        //delimita o tamanho do campo de texto para ações
        nome_autocomplete.setThreshold(1);
        //define a largura do dropdown do autocomplete
        nome_autocomplete.setDropDownWidth(700);


        //encontra campo de texto para indicar ações
        texto_fazer = (TextView) findViewById(R.id.texto_fazer);





    }
}