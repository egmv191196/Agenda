package com.egmvdev.agenda.iu.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.egmvdev.agenda.core.contactoAdapter;
import com.egmvdev.agenda.core.roomHelper;
import com.egmvdev.agenda.data.model.contacto;
import com.egmvdev.agenda.databinding.ActivityListaContactoBinding;

import java.util.ArrayList;

public class listaContacto extends AppCompatActivity {
    private ActivityListaContactoBinding bind;
    protected contactoAdapter cAdapter;
    private ArrayList<contacto> listaContactos;
    private Context mCont=this;
    //Manejador de ROOM
    private roomHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityListaContactoBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        getActionBar().setTitle("Lista de Contactos");
        dbHelper=roomHelper.get(this);
        listaContactos= new ArrayList<contacto>();
        iniciarRecycler();
        bind.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(mCont, datosContacto.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        llen();
    }

    private void iniciarRecycler(){
        cAdapter = new contactoAdapter(this, listaContactos);
        bind.rvContactos.setLayoutManager(new LinearLayoutManager(this));
        bind.rvContactos.setAdapter(cAdapter);
    }
    private void llen(){
        listaContactos.clear();
        Log.i("Contactos", dbHelper.getContactos().toString());
            listaContactos.addAll(dbHelper.getContactos());
        //Log.i("Contactos", String.valueOf(listaContactos.size()));


    }
}