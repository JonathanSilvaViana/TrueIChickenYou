package br.com.ichickenyou;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import static br.com.ichickenyou.BuildConfig.APPLICATION_ID;

public class RegrasActivity extends AppCompatActivity {

    Switch aceitaounao;
    Typeface fonte_coreana;
    String idioma_coreano, outros_idiomas, nome_do_arquivo_de_persistencia, caminho_aceito;
    boolean se_idioma_coreano;
    SharedPreferences aceite;
    //tempo de transição da função de desinstalar o aplicativo e mudar fragment após mensagem de feedback agradecendo pela instalação
    int tempo_encerramento = 2800;
    //posiciona a ação de troca de intents
    Handler posicionador = new Handler();
    Intent homeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regras);

        //define ao título da activity
        setTitle(R.string.menu_rules);

        //encontra o botão tipo switch, que concede ao usuário a opção de aceitar ou não as regras do aplicativo
        aceitaounao = (Switch) findViewById(R.id.toggleButton);

        //variável que procederá o nome do arquivo de persistência
        nome_do_arquivo_de_persistencia = "ACEITE";

        //cria a persistência de aceite de normas
        aceite = this.getSharedPreferences(nome_do_arquivo_de_persistencia, Context.MODE_PRIVATE);

        //essa função permite o aceite das regras que constituem o aplicativo
        aceitaounao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //executa condicional quando checado
                if(aceitaounao.isChecked())
                {

                    try {
                        //cria as configurações se o aplicativo tiver as regras aceitas, no arquivo de persistência
                        SharedPreferences.Editor editor = aceite.edit();
                        editor.putString("regras aceitas", "aceitado");
                        editor.putBoolean("ACEITO", aceitaounao.isChecked());
                        //editor.commit();
                        editor.apply();

                        //feedback de agradecimento pelo aceite
                        Toast.makeText(RegrasActivity.this, R.string.grato, Toast.LENGTH_SHORT).show();

                        //inclui as normas de aceite no arquivo de persistência
                        String aceitei = aceite.getString("regras aceitas", "aceitado");
                        Boolean aceito = aceite.getBoolean("ACEITO", aceitaounao.isChecked());
                        aceitaounao.setChecked(true);

                        //chama o fragment de home
                        posicionador.postDelayed(new Runnable() {
                                                     @Override
                                                     public void run()
                                                     {
                                                        homeActivity = new Intent(RegrasActivity.this, MainActivity.class);
                                                        startActivity(homeActivity);

                                                        finish();
                                                     }
                                                 },

                                tempo_encerramento);

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(RegrasActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                    }

                }

                else
                {
                    //agradece o uso e solicita a desinstalação avisando em três idiomas distintos
                    String recusou = getResources().getString(R.string.aindasimgrato);
                    Toast.makeText(RegrasActivity.this, recusou, Toast.LENGTH_LONG).show();


                    //desinstala o aplicativo em caso de recusa das regras

                    posicionador.postDelayed(new Runnable() {

                        public void run() {

                            Uri packageURI = Uri.parse("package:br.com.ichickenyou");
                            Intent uninstallIntent =
                                    new Intent(Intent.ACTION_DELETE, packageURI); //compatível para todas edições do android
                            startActivity(uninstallIntent);
                        }
                    }, tempo_encerramento);

                }
            }

        });

        //define o texto do botão de aceite ou não

        //instancia o nome da variável gravada no arquivo de persistência
        caminho_aceito = "ACEITO";
        //busca o nome da variável de persistência informada tida como verdadeira
        aceite.getBoolean(caminho_aceito, true);

        //condição se houver variável de aceite, define o botão como marcado de aceite, se não define como contrário.
        if (aceite.contains(caminho_aceito) == true)
        {
            aceitaounao.setChecked(true);
        }
        else {
            aceitaounao.setChecked(false);
        }

        //define o texto do botão de aceite ou não

        //instancia o nome da variável gravada no arquivo de persistência
        caminho_aceito = "ACEITO";
        //busca o nome da variável de persistência informada tida como verdadeira
        aceite.getBoolean(caminho_aceito, true);

        //condição se houver variável de aceite, define o botão como marcado de aceite, se não define como contrário.
        if (aceite.contains(caminho_aceito) == true)
        {
            aceitaounao.setChecked(true);
        }
        else {
            aceitaounao.setChecked(false);
        }

    }
}