package com.example.a00_inicio.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a00_inicio.R;
import com.example.a00_inicio.adapter.PostagemAdapter;
import com.example.a00_inicio.model.Postagem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerPostagem;
    private List<Postagem> postagens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerPostagem = findViewById(R.id.recyclerPostagem);

        // Definir Layout

        // HORIZONTAL E VERTICAL
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //layoutManager.setOrientation(LinearLayout.HORIZONTAL); // pode ser vertical ou horizontal

        // COM GRID -> spanCount = numero de colunas
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerPostagem.setLayoutManager(layoutManager);

        // Definir adapter
        this.prepararPostagens();
        PostagemAdapter adapter = new PostagemAdapter(postagens);
        recyclerPostagem.setAdapter(adapter);
    }

    public void prepararPostagens() {

        Postagem p = new Postagem("Thalita Domingos", "Viagem Legal!!!", R.drawable.imagem1);
        this.postagens.add(p);

        p = new Postagem("Ana Paula", "Viajando...", R.drawable.imagem2);
        this.postagens.add(p);

        p = new Postagem("Thiago Miguel", "Paris :D", R.drawable.imagem3);
        this.postagens.add(p);

        p = new Postagem("Camile Domingos", "Nas montanhas...", R.drawable.imagem4);
        this.postagens.add(p);
    }

}