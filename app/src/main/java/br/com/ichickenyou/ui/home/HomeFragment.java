package br.com.ichickenyou.ui.home;

import android.content.Context;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import br.com.ichickenyou.GenderActivity;
import br.com.ichickenyou.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private static int SPLASH_TIME_OUT = 2665;

    Button bt_play;
    MediaPlayer mp;
    SharedPreferences som;
    String idioma_coreano, outros_idiomas, preferencia_som, ativo, desativado;
    boolean se_idioma_coreano;


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
                                                  //envia para uma nova activity
                                                  startActivity(new Intent(getContext(), GenderActivity.class));


                                                  //finish();
                                              }
                                          },
                        //encerra o tempo de exibição
                        SPLASH_TIME_OUT);

            }
        });

        return root;
    }

    public void comSom()
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

        else
        {
            //se a opção de audio desativado for verdadeira, então a função faz um log no aplicativo
            Log.d("audio desativado", "audio desativado para tocar o som de play");
        }
    }
}