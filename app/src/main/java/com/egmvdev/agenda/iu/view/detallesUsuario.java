package com.egmvdev.agenda.iu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import com.egmvdev.agenda.R;
import com.egmvdev.agenda.data.model.empleado;
import com.egmvdev.agenda.databinding.ActivityDetallesUsuarioBinding;

public class detallesUsuario extends AppCompatActivity {
    private ActivityDetallesUsuarioBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityDetallesUsuarioBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        Intent i=getIntent();
        empleado empleado= (empleado) i.getSerializableExtra("empleado");
        getSupportActionBar().hide();
        //getActionBar().setTitle("Detalle de empleado");
        bind.tvID.setText("ID de empleado:  "+empleado.getDatos().getEmpID());
        bind.tvNombre.setText("Nombre del empleado: "+ empleado.getDatos().getNombre() +" "+ empleado.getDatos().getApPaterno()+" "+ empleado.getDatos().getApMaterno() );
        bind.tvEdad.setText("Edad: "+ empleado.getDatos().getInfo().getEdad()+" años");
        bind.tvSexo.setText("Sexo: "+ empleado.getDatos().getInfo().getSexoDesc());
        bind.tvPuesto.setText("Puesto del empleado: "+empleado.getDatos().getPuesto().getPuestoDesc());
        bind.tvGerencia.setText("Gerencia del empleado: " + empleado.getDatos().getGerencia().getGerenciaDesc());
        bind.btnAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), listaContacto.class);
                startActivity(intent);
            }
        });
    }

}