package br.com.ichickenyou.ui.dashboard;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import br.com.ichickenyou.CRUDActivity;
import br.com.ichickenyou.GenderActivity;
import br.com.ichickenyou.R;
import br.com.ichickenyou.RegrasActivity;
import br.com.ichickenyou.SomActivity;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    Intent bug_email_report, envia_som, envia_para_regras, envia_para_crud;

    ImageButton bug_bt, music_bt, rules_bt, crud_bt;

    String idioma_coreano, outros_idiomas, preferencia_som, com_som, sem_som, ativo, desativado, meuemail, assunto, mensagem, enunciadoIntentReportEmail;

    MediaPlayer mp;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //bug

        bug_bt = (ImageButton) root.findViewById(R.id.bug_bt);

        bug_bt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                comSom();
                reportaviaemail();

            }
        });

        //som

        music_bt = (ImageButton) root.findViewById(R.id.music_bt);

        music_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviaparaSom();
            }
        });

        //regras

        rules_bt = (ImageButton) root.findViewById(R.id.rules_bt);

        rules_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviaparaRegras();
            }
        });

        crud_bt = (ImageButton) root.findViewById(R.id.crud_bt);

        crud_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Envia_para_crud();
            }
        });


        return root;
    }


    //metodo que reporta bug via e-mail por intent
    private void reportaviaemail()
    {
        //variáveis tipo coleta de string e sumário de strings necessárias para o envio de e-mail, todas com opções de tradução
        meuemail = getString(R.string.meuemail);
        assunto = getString(R.string.assuntoemail);
        String[] recipients = meuemail.split(",");
        mensagem = getString(R.string.mensagememail);
        enunciadoIntentReportEmail = getString(R.string.enunciadointentbug);

        //chama o intent de e-mail:

        //cria o tipo de intent
        bug_email_report = new Intent(Intent.ACTION_SEND);

        //as duas linhas seguintes de código incluem o e-mail destinado ao aviso do bug

        //para versões mais antigas
        bug_email_report.putExtra(Intent.EXTRA_EMAIL, meuemail);
        //para versões mais recentes do sistema operacional
        bug_email_report.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { meuemail });
        //inclui o título da mensagem que será contida no e-mail
        bug_email_report.putExtra(Intent.EXTRA_SUBJECT, assunto);
        //inclui a instrução inicial de como compor a mensagem
        bug_email_report.putExtra(Intent.EXTRA_TEXT, mensagem);

        //cria o tipo de mensagem
        bug_email_report.setType("message/rfc822");
        //inicia o serviço do intent
        startActivity(Intent.createChooser(bug_email_report, enunciadoIntentReportEmail));

    }

    private void comSom()
    {
        //dentro do contexto da aplicação seleciona o audio desejado
        mp = MediaPlayer.create(getContext(), R.raw.telaazulsomwindows);
        //ativa o som para o início de jogo
        mp.start();
    }

    private void enviaparaSom()
    {
        envia_som = new Intent(getActivity(), SomActivity.class);
        startActivity(envia_som);
    }

    private void enviaparaRegras()
    {
        envia_para_regras = new Intent(getActivity(), RegrasActivity.class);
        startActivity(envia_para_regras);
    }

    private void Envia_para_crud()
    {
        envia_para_crud = new Intent(getActivity(), CRUDActivity.class);
        startActivity(envia_para_crud);
    }
}