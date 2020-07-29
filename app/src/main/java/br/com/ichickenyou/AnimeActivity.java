package br.com.ichickenyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

public class AnimeActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2665;
    MediaPlayer mp;
    String idioma_coreano, outros_idiomas, preferencia_som, ativo, desativado;
    boolean se_idioma_coreano;
    SharedPreferences som;
    TextView mainTextView;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);

        comSom();

        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent saidasplashscreen = new Intent(AnimeActivity.this,
                                                  MainActivity.class );
                                          startActivity(saidasplashscreen);

                                          finish();
                                      }
                                  },
                //encerra o tempo de exibição
                SPLASH_TIME_OUT);

    }

    public void comSom()
    {

        //string que serve de nome para o arquivo de preferências
        preferencia_som = "Preferencia_som";
        //coleta o arquivo de preferências via a activity que mantém o fragment
        som = getSharedPreferences(preferencia_som, Context.MODE_PRIVATE);
        //abaixo as strings que denotam por nome os estados contidos no arquivo de preferências
        ativo = "ATIVADO";
        desativado = "DESATIVADO";

        //cria condicional
        if (som.contains(ativo) == true && som.contains(desativado) == false)
        {
            //dentro do contexto da aplicação seleciona o audio desejado
            mp = MediaPlayer.create(this, R.raw.abertura);
            //ativa o som para o início de jogo
            mp.start();
        }
        else
        {
            //se a opção de audio desativado for verdadeira, então a função faz um log no aplicativo
            Log.d("audio desativado", "audio desativado para tocar o som de play");
        }
    }
}