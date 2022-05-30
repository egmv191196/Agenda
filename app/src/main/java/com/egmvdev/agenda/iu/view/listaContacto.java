package com.egmvdev.agenda.iu.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.egmvdev.agenda.core.SwipeControllerActions;
import com.egmvdev.agenda.core.contactoAdapter;
import com.egmvdev.agenda.core.roomHelper;
import com.egmvdev.agenda.core.swipeController;
import com.egmvdev.agenda.data.model.contacto;
import com.egmvdev.agenda.databinding.ActivityListaContactoBinding;
import com.egmvdev.agenda.iu.viewmodel.listaContactoViewModel;

import java.io.Serializable;
import java.util.ArrayList;

public class listaContacto extends AppCompatActivity {
    private ActivityListaContactoBinding bind;
    protected contactoAdapter cAdapter;
    private ArrayList<contacto> listaContactos;
    private Context mCont=this;
    //Manejador de ROOM
    private roomHelper dbHelper;
    private listaContactoViewModel listaContactoVM;
    swipeController swipeController = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityListaContactoBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        getSupportActionBar().hide();
        dbHelper=roomHelper.get(this);
        listaContactoVM= new ViewModelProvider(this).get(listaContactoViewModel.class);// View model
        //Observador
        final Observer<Integer> observador= new Observer<Integer>(){
            @Override
            public void onChanged(Integer integer) {
                Log.i("cambios observador",listaContactoVM.toString());
                llen();
            }
        };
        listaContactoVM.actualizarValor().observe(this,observador);

        listaContactos= new ArrayList<contacto>();
        iniciarRecycler();
        bind.rvContactos.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                llen();
                return false;
            }
        });
        bind.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(mCont, datosContacto.class);
                startActivity(intent);
                //listaContactoVM.actualizarValor().postValue(1);
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
        bind.rvContactos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        bind.rvContactos.setAdapter(cAdapter);
        swipeController = new swipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                dbHelper.deleteContacto(listaContactos.get(position).getId());
                listaContactoVM.actualizarValor().postValue(1);
                //Toast.makeText(mCont, "Se presiono el boton derecho en la posicion"+ position + listaContactos.get(position).getNombre(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLeftClicked(int position) {
                Intent intent;
                intent = new Intent(getApplicationContext(), datosContacto.class);
                intent.putExtra("contacto", (Serializable) listaContactos.get(position));
                startActivity(intent);
                //Toast.makeText(mCont, "Se presiono el boton izquierdoen la posicion" + position + listaContactos.get(position).getNombre(), Toast.LENGTH_SHORT).show();
            }
        },this);

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(bind.rvContactos);

        bind.rvContactos.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }
    private void llen(){
        listaContactos.clear();
        listaContactos.addAll(dbHelper.getContactos());
        cAdapter.notifyDataSetChanged();
    }
}