package com.cieep.sim_a_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private final int CANTIDAD_CIGARROS = 20;
    private final int DINERO_PAQUETE = 5;

    // Contadores:
    private int dineroAhorrado = 0;
    private int cigarrosRestantes = 20;

    // Formateo numero
    private NumberFormat numberFormat;

    // Views
    private ImageButton btnFumar;
    private TextView lblContadorCigarros;
    private TextView lblContadorDinero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaCosas();

        btnFumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cigarrosRestantes--;
                if (cigarrosRestantes <= 0) {
                    cigarrosRestantes = CANTIDAD_CIGARROS;
                    dineroAhorrado += DINERO_PAQUETE;
                }
                actualizaVista();
            }
        });

    }

    private void inicializaCosas() {
        numberFormat = NumberFormat.getCurrencyInstance();
        btnFumar = findViewById(R.id.btnFumarVirtual);
        lblContadorCigarros = findViewById(R.id.lblCigarrosRestantes);
        lblContadorDinero = findViewById(R.id.lblDineroRestante);

        actualizaVista();
    }

    private void actualizaVista() {
        lblContadorDinero.setText(numberFormat.format(dineroAhorrado));
        lblContadorCigarros.setText(String.valueOf(cigarrosRestantes));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("DINERO", dineroAhorrado);
        outState.putInt("CIGARROS", cigarrosRestantes);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        dineroAhorrado = savedInstanceState.getInt("DINERO");
        cigarrosRestantes = savedInstanceState.getInt("CIGARROS");
        actualizaVista();
    }
}