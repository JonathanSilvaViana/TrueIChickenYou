package br.com.ichickenyou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class SomActivity extends AppCompatActivity {

    String idioma_coreano, outros_idiomas, preferencia_som, com_som, sem_som, ativo, desativado;
    boolean se_idioma_coreano;
    RadioGroup asopcoes;
    RadioButton som_on, som_off;
    SharedPreferences som;
    Typeface letracoreana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_som);

        //define título da activity
        setTitle(R.string.sobre_som);

        //variável que define a string do idioma coreano
        idioma_coreano = "ko";

        //variável boolean que define se o idioma rastreado, nesse caso encapsulado da variável "idioma_coreano", acima;
        se_idioma_coreano = Locale.getDefault().getLanguage().equals(idioma_coreano);

        //essa variável coleta a string do idioma local que esteja no aparelho que execute a aplicação
        outros_idiomas = Locale.getDefault().getLanguage();

        setIdioma_coreano();

        //tratativa de efeitos sonoros

        //encontra o content das opções
        asopcoes = (RadioGroup)findViewById(R.id.asopcoes);

        //encontra o item da view de som on
        som_on = (RadioButton)findViewById(R.id.som_on);

        //encontra o item da view de som off
        som_off = (RadioButton)findViewById(R.id.som_off);

        //string que serve de nome da preferência de som no app
        preferencia_som = "Preferencia_som";

        //cria o arquivo de persistência de dados referente a som
        som = SomActivity.this.getSharedPreferences(preferencia_som, Context.MODE_PRIVATE);

        //prepara as configurações de preferências compartilhadas

        asopcoes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //instancia o objeto de edição de SharedPreferences
                SharedPreferences.Editor editor = som.edit();

                if (checkedId == R.id.som_on)
                {
                    //inclui valores significativos para ativaçãpo de audio
                    editor.putBoolean("ATIVADO", som_on.isChecked());
                    editor.putString("Audio", "com audio");
                    //remove as preferências conflitantes caso existam
                    editor.remove("DESATIVADO");
                    //aplica os procedimentos
                    editor.apply();

                    //inclui as normas de audio no arquivo de persistência
                    com_som = som.getString("Audio ativado", "com audio");

                }
                else  if (checkedId == R.id.som_off)
                {

                    //inclui valores significativos para ativaçãpo de audio
                    editor.putBoolean("DESATIVADO", som_off.isChecked());
                    editor.putString("Audio", "sem audio");
                    //remove as preferências conflitantes caso existam
                    editor.remove("ATIVADO");
                    //aplica os procedimentos
                    editor.apply();

                    //inclui as normas de audio no arquivo de persistência
                    sem_som = som.getString("Audio desativado", "sem audio");

                }
                else
                {
                    //se nenhuma opção foi selecionada, então faz um log durante o processo de debug mencionando o fato.
                    Log.d("Nenhum selecionado", "Nenhuma opção selecionada");

                    //inclui valores significativos para ativaçãpo de audio
                    editor.putBoolean("ATIVADO", som_on.isChecked());
                    editor.putString("Audio", "com audio");
                    //remove as preferências conflitantes caso existam
                    editor.remove("DESATIVADO");
                    //aplica os procedimentos
                    editor.apply();

                    //inclui as normas de audio no arquivo de persistência
                    com_som = som.getString("Audio ativado", "com audio");
                }

            }
        });

        //variáveis string de caracteriação de nome para valores comparativos em busca condicional
        ativo = "ATIVADO";
        desativado = "DESATIVADO";

        //verifica se o audio está ativado por meio de checagem no arquivos de preferências chamado preferencia_som, usando dupla checagem condicional, prevenindo de falhas no mesmo contexto.

        if(som.contains(ativo) == true && som.contains(desativado) == false)
        {
            //se som está ativado e se estiver desativado conter false, define a opção on como selecionada
            som_on.setChecked(true);
        }
        else if (som.contains(desativado) == true && som.contains(ativo) == false)
        {
            //se som está desativado e se estiver ativo conter false, define a opção off como selecionada
            som_off.setChecked(true);
        }
        else
        {
            //se nenhuma opção foi definida, cria um log no debug avisando que nenhuma preferência de audio foi escolhida
            Log.d("Sem definições de audio", "Nenhuma definição de audio");
        }

    }

    //alterações do idioma coreano
    private void setIdioma_coreano()
    {
        if (se_idioma_coreano == true)
        {
            som_on = (RadioButton) findViewById(R.id.som_on);
            som_off = (RadioButton) findViewById(R.id.som_off);
            letracoreana = ResourcesCompat.getFont(this, R.font.koreanfont);
            som_on.setTypeface(letracoreana);
            som_off.setTypeface(letracoreana);
        }
        else {
            //exibe um log do sistema dizendo qual o idioma rastreado
            Log.d("IDIOMA:", outros_idiomas);
        }
    }
}