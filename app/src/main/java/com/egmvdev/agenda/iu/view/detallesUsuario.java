package com.egmvdev.agenda.iu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.egmvdev.agenda.R;
import com.egmvdev.agenda.databinding.ActivityDetallesUsuarioBinding;

public class detallesUsuario extends AppCompatActivity {
    private ActivityDetallesUsuarioBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityDetallesUsuarioBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        getActionBar().setTitle("Detalle de empleado");
    }

}