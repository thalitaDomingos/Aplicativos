package com.example.lista_de_tarefas.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lista_de_tarefas.R;
import com.example.lista_de_tarefas.adapter.TarefaAdapter;
import com.example.lista_de_tarefas.helper.DbHelper;
import com.example.lista_de_tarefas.helper.RecyclerItemClickListener;
import com.example.lista_de_tarefas.helper.TarefaDAO;
import com.example.lista_de_tarefas.model.Tarefa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private Tarefa tarefaSelecionada;
    private List<Tarefa> listaTarefas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar Recycler
        recyclerView = findViewById(R.id.recyclerView);

        // Adicionar Evento de Clique
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                // Recuperar tarefa para edicao
                                Tarefa tarefaSelecionada = listaTarefas.get(position);

                                // Enviar tarefa para a segunda tela
                                Intent intent = new Intent(MainActivity.this, SegundaTelaActivity.class);
                                intent.putExtra("tarefaSelecionada", tarefaSelecionada);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                                // Recuperar tarefa para deletar
                                tarefaSelecionada = listaTarefas.get(position);

                                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                                // Configurar titulo e mensagem
                                dialog.setTitle("Confirmar Exclusão");
                                dialog.setMessage("Deseja excluir a tarefa: " + tarefaSelecionada.getNomeTarefa() + "?");

                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                                        if (tarefaDAO.deletar(tarefaSelecionada)) {
                                            carregarListaTarefas();
                                            Toast.makeText(getApplicationContext(),
                                                    "Sucesso ao excluir tarefa!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(),
                                                    "Erro ao excluir tarefa!", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                                dialog.setNegativeButton("Não", null);

                                // Exibir dialog
                                dialog.create();
                                dialog.show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SegundaTelaActivity.class);
                startActivity(intent);
            }
        });
    }

    public void carregarListaTarefas () {

        // Listar Tarefas
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        listaTarefas = tarefaDAO.listar();


        // Configurar um adapter
        tarefaAdapter = new TarefaAdapter(listaTarefas);


        // Configurar Recycler View
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(tarefaAdapter);
    }

    @Override
    protected void onStart() {
        carregarListaTarefas();
        super.onStart();
    }
}