package br.com.ichickenyou.ui.home;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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
import br.com.ichickenyou.AnimeActivity;
import br.com.ichickenyou.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    Button bt_play;
    MediaPlayer mp;

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

                //envia para uma nova activity
                //startActivity(new Intent(getContext(), AnimeActivity.class));


            }
        });

        return root;
    }

    public void comSom()
    {
        //dentro do contexto da aplicação seleciona o audio desejado
        mp = MediaPlayer.create(getContext(), R.raw.play);
        //ativa o som para o início de jogo
        mp.start();
    }
}