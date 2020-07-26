package br.com.ichickenyou;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Switch;

public class RegrasActivity extends AppCompatActivity {

    Switch aceitaounao;
    Typeface fonte_coreana;
    String idioma_coreano, outros_idiomas, nome_do_arquivo_de_persistencia, caminho_aceito;
    boolean se_idioma_coreano;
    SharedPreferences aceite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regras);

        //define ao título da activity
        setTitle(R.string.menu_rules);

        //tempo de transição da função de desinstalar o aplicativo e mudar fragment após mensagem de feedback agradecendo pela instalação
        int tempo_encerramento = 2800;
        //posiciona a ação de troca de intents
        Handler posicionador = new Handler();


    }
}