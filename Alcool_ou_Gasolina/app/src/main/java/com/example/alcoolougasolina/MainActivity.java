package com.example.alcoolougasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText campoAlcool, campoGasolina;
    private TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoAlcool    = findViewById(R.id.campoAlcool);
        campoGasolina  = findViewById(R.id.campoGasolina);
        textoResultado = findViewById(R.id.textoResultado);
    }

    public void calcular (View view) {

        // Transformando em String
        String alcool = campoAlcool.getText().toString();
        String gasolina = campoGasolina.getText().toString();

        boolean campoValidado = validarCampos (alcool, gasolina);

        if (campoValidado) {

            // Convertendo de String para double
            double precoAlcool = Double.parseDouble(alcool);
            double precoGasolina = Double.parseDouble(gasolina);
            double resultado = (precoAlcool / precoGasolina) * 1.0;

            if (resultado >= 0.7)
                textoResultado.setText("Melhor a se utilizar: gasolina");
            else
                textoResultado.setText("Melhor a se utilizar: álcool");

        } else
            textoResultado.setText("Preencha todos os campos!");
    }

    public boolean validarCampos (String precoAlcool, String precoGasolina) {

        boolean campoValidado = true;

        if (precoAlcool == null || precoAlcool.equals(""))
            campoValidado = false;
        else if (precoGasolina == null || precoGasolina.equals(""))
            campoValidado = false;

        return campoValidado;
    }
}