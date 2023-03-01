package com.example.pedrapapeloutesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selecionadoPedra(View view) {
        this.escolhaUsuario("pedra");
    }

    public void selecionadoPapel(View view) {
        this.escolhaUsuario("papel");
    }

    public void selecionadoTesoura(View view) {
        this.escolhaUsuario("tesoura");
    }


    public void escolhaUsuario (String escolhaUsuario) {

        // Salvando a imagem com id imagemResultado na variavel imagem
        ImageView imagem = findViewById(R.id.imagemResultado);


        // Salva o conteudo do texto do id resultadoFinal na variavel texto
        TextView texto = findViewById(R.id.resultadoFinal);

        // Gerando uma opcao aleatória para o app
        int numero = new Random().nextInt(3);
        String[] opcoes = {"pedra", "papel", "tesoura"};
        String escolhaApp = opcoes[numero];

        switch (escolhaApp) {
            case "pedra":
                // troca a imagem atual para a imagem com nome 'pedra'
                imagem.setImageResource(R.drawable.pedra);
                break;

            case "papel":
                imagem.setImageResource(R.drawable.papel);
                break;

            case "tesoura":
                imagem.setImageResource(R.drawable.tesoura);
                break;
        }

        if (escolhaApp == escolhaUsuario) {
            texto.setText("Empate!");
        }

        else if ((escolhaUsuario == "pedra" && escolhaApp == "tesoura") ||
                (escolhaUsuario == "papel" && escolhaApp == "pedra") ||
                (escolhaUsuario == "tesoura" && escolhaApp == "papel")) {

            texto.setText("Você ganhou :D");
        }

        else {
            texto.setText("Você perdeu :(");
        }
    }
}