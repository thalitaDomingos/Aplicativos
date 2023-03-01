package com.example.a00_inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
    }

    public void abrirSnackBar (View view) {
        Snackbar.make(
                view,
                "Botão pressionado",
                Snackbar.LENGTH_INDEFINITE // INDEFINITE NÃO FECHA -> Pode ser short ou long também
        ).setAction("Confirmar", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        button.setText("Botão Abrir Alterado");
                    }
                }).show();
    }
}