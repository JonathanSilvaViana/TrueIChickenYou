package br.com.ichickenyou;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



public class MainActivity extends AppCompatActivity {

    View encerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_encerrar)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);



        encerrar = findViewById(R.id.navigation_encerrar);

        encerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finishApp();

            }
        });




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.navigation_home:
                Log.d("home", "home fragment");
                return true;
            case R.id.navigation_dashboard:
                Log.d("settings", "settings fragment");
                return true;
            case R.id.navigation_notifications:
                Log.d("notifications", "notifications fragment");
                return true;
            case R.id.navigation_finish:
                Log.d("finish", "finish fragment");
                Toast.makeText(this, "finish", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void finishApp()
    {
//cria um menu que questiona o usuário se deseja encerrar a aplicação
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        //define título ao menu
        alertDialogBuilder.setTitle(R.string.enunciado);
        alertDialogBuilder
                //define um enunciado
                .setMessage(R.string.querencerrar)
                .setCancelable(false)
                //define o botão de encerramento da aplicação
                .setPositiveButton(R.string.fechar,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                //encerra o processo
                                android.os.Process.killProcess(android.os.Process.myPid());
                                //minimiza o aplicativo similar a uma função de sair
                                System.exit(1);
                            }
                        })

                .setNegativeButton(R.string.naofechar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //cancela a opção de encerramento do dialog
                        dialog.cancel();
                        //passa um feedback enquanto executar o teste
                        Log.d("Não encerrou", "a navigation drawer não recebeu o encerramento");
                    }
                });

        //cria o evento de chamado
        AlertDialog alertDialog = alertDialogBuilder.create();
        //chama o evento do menu de dialogo
        alertDialog.show();
    }

}