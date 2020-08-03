package br.com.ichickenyou.ui.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import br.com.ichickenyou.GenderActivity;
import br.com.ichickenyou.R;
import br.com.ichickenyou.RegrasActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private static int SPLASH_TIME_OUT = 2665, tempo_encerramento = 0;
    Button bt_play;
    Handler posicionador = new Handler();
    MediaPlayer mp;
    SharedPreferences som, aceite;
    String idioma_coreano, outros_idiomas, preferencia_som, ativo, desativado, caminho_aceito, aceito;
    boolean se_idioma_coreano;
    Intent vaiaostermos;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        bt_play = (Button) root.findViewById(R.id.bt_play);

        bt_play.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                    comSom();

                    new Handler().postDelayed(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      //envia para uma nova activity a partir do método a seguir

                                                      checaAceite();

                                                      //finish();
                                                  }
                                              },
                            //encerra o tempo de exibição
                            SPLASH_TIME_OUT);

            }
        });

        return root;
    }

    private void checaAceite()
    {
        //caminho do nome de arquivo de preferências
        caminho_aceito = "ACEITE";
        //coleta o arquivo de preferências via a activity que mantém o fragment
        aceite = getActivity().getSharedPreferences(caminho_aceito, Context.MODE_PRIVATE);
        //string de aceite
        aceito = "ACEITO";

        if (aceite.contains(aceito) == true)
        {
            startActivity(new Intent(getContext(), GenderActivity.class));
            Log.d("vigente", "termos aceitos");
        }
        else
        {
            //cria um menu que questiona o usuário se deseja aceitar as regras do aplicativo caso existam, ou se prefere deixar para um momento posterior

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

            //define o título

            alertDialogBuilder.setTitle(R.string.aceite_primeiro);

            alertDialogBuilder

                    //define o subtítulo

                    .setMessage(R.string.motivar)

                    .setCancelable(false)

                    //botão que o usuário pode deixar para decidir posteriormente

                    .setPositiveButton(R.string.depois,

                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int id) {

                                    getActivity().moveTaskToBack(true);

                                    //mata o processo

                                    android.os.Process.killProcess(android.os.Process.myPid());

                                    //minimiza o aplicativo similar a uma função de sair

                                    System.exit(1);

                                }

                            })

                    .setNegativeButton(R.string.ir_regras, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            //envia a activity de termos
                            vaiaostermos = new Intent(getActivity(), RegrasActivity.class);
                            startActivity(vaiaostermos);

                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();

            //exibe o componente de menu dialog

            alertDialog.show();

        }

    }

    private void comSom()
    {
        //string que serve de nome para o arquivo de preferências
        preferencia_som = "Preferencia_som";
        //coleta o arquivo de preferências via a activity que mantém o fragment
        som = getActivity().getSharedPreferences(preferencia_som, Context.MODE_PRIVATE);
        //abaixo as strings que denotam por nome os estados contidos no arquivo de preferências
        ativo = "ATIVADO";
        desativado = "DESATIVADO";

        //cria condicional
        if (som.contains(ativo) == true && som.contains(desativado) == false) {

            //dentro do contexto da aplicação seleciona o audio desejado
            mp = MediaPlayer.create(getContext(), R.raw.play);
            //ativa o som para o início de jogo
            mp.start();

        }

        else if (som.contains(ativo) == false && som.contains(desativado) == false)
        {
            //dentro do contexto da aplicação seleciona o audio desejado
            mp = MediaPlayer.create(getActivity(), R.raw.play);
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