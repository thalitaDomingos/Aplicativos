package com.example.a00_inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class SegundaActivity extends AppCompatActivity {

    private ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        imagem = findViewById(R.id.imagemResultado);

        // Gera um número aleatório (0 ou 1)
        int numero = new Random().nextInt(2);

        if (numero == 0)
            imagem.setImageResource(R.drawable.moeda_cara);
        else
            imagem.setImageResource(R.drawable.moeda_coroa);
    }

    public void botaoVoltar (View view) {
        finish();
    }
}