package com.example.vpbtributos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class MainActivity extends AppCompatActivity {
    private EditText nomeEditText;
    private EditText remuneracaoEditText;
    private EditText semeaduraEditText;
    private TextView primiciaTextView;
    private TextView dizimoTextView;
    private TextView socorroTextView;
    private TextView gratidaoTextView;
    private TextView israelTextView;
    private TextView contribuicaoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomeEditText = findViewById(R.id.nomeEditText);
        remuneracaoEditText = findViewById(R.id.remuneracaoEditText);
        semeaduraEditText = findViewById(R.id.semeaduraEditText);
        primiciaTextView = findViewById(R.id.primiciaTextView);
        dizimoTextView = findViewById(R.id.dizimoTextView);
        socorroTextView = findViewById(R.id.socorroTextView);
        gratidaoTextView = findViewById(R.id.gratidaoTextView);
        israelTextView = findViewById(R.id.israelTextView);
        contribuicaoTextView = findViewById(R.id.contribuicaoTextView);

        Button calcularButton = findViewById(R.id.calcularButton);
        calcularButton.setOnClickListener(v -> calcular());

        FloatingActionButton shareButton = findViewById(R.id.shareButton);
        shareButton.setOnClickListener(v -> compartilharResultados());

        FloatingActionButton limparButton = findViewById(R.id.limparButton);
        limparButton.setOnClickListener(v -> limparCampos());
    }

    //metodo para calcular os tributos
    public void calcular() {
        if (remuneracaoEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, digite a remuneração.", Toast.LENGTH_SHORT).show();
            return;
        }

        double remuneracao = Double.parseDouble(remuneracaoEditText.getText().toString());

        if (semeaduraEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, digite a semeadura.", Toast.LENGTH_SHORT).show();
            return;
        }

        double semeadura = Double.parseDouble(semeaduraEditText.getText().toString());
        double primicia = Math.ceil(remuneracao / 30);
        double dizimo = Math.ceil((remuneracao - primicia) / 10);
        double socorro = Math.ceil((remuneracao * 2) / 100);
        double gratidao = Math.ceil(remuneracao / 1000);
        double israel = Math.ceil(remuneracao / 100);

        primiciaTextView.setText(" " + primicia);
        dizimoTextView.setText(" " + dizimo);
        socorroTextView.setText(" " + socorro);
        gratidaoTextView.setText("  " + gratidao);
        israelTextView.setText(" " + israel);

        double contribuicao = primicia + dizimo + socorro + gratidao + semeadura + israel;
        contribuicaoTextView.setText(" " + contribuicao);
    }
    //metodo que compartilha os dados inseridos pelo usuario e calculos
    @SuppressLint("QueryPermissionsNeeded")
    private void compartilharResultados() {
        String nome = nomeEditText.getText().toString();
        String remuneracao = remuneracaoEditText.getText().toString();
        String primicia = primiciaTextView.getText().toString();
        String dizimo = dizimoTextView.getText().toString();
        String socorro = socorroTextView.getText().toString();
        String gratidao = gratidaoTextView.getText().toString();
        String israel = israelTextView.getText().toString();
        String semeadura = semeaduraEditText.getText().toString();
        String contribuicao = contribuicaoTextView.getText().toString();

        String mensagem = "Resultados dos Tributos:" +
                "\nNome: " + nome +
                "\nRemuneração: " + remuneracao +
                "\nPrimícia: " + primicia +
                "\nDízimo: " + dizimo +
                "\nOferta de Socorro: " + socorro +
                "\nOferta de Gratidão: " + gratidao +
                "\nSemeadura: " + semeadura +
                "\nOferta para Israel: " + israel +
                "\n\nContribuição Total: " + contribuicao;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, mensagem);

        Intent chooser = Intent.createChooser(intent, "Compartilhar via");
        if (chooser.resolveActivity(getPackageManager()) != null) startActivity(chooser);
        else {
            Toast.makeText(this, "Nenhum aplicativo de compartilhamento disponível.", Toast.LENGTH_SHORT).show();
        }
    }
    //Limpa todos os campos
    private void limparCampos() {
        nomeEditText.setText("");
        remuneracaoEditText.setText("");
        semeaduraEditText.setText("");
        primiciaTextView.setText("");
        dizimoTextView.setText("");
        socorroTextView.setText("");
        gratidaoTextView.setText("");
        israelTextView.setText("");
        contribuicaoTextView.setText("");
    }
}