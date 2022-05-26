package com.egmvdev.agenda.iu.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.egmvdev.agenda.core.retrofitHelper;
import com.egmvdev.agenda.data.model.empleado;
import com.egmvdev.agenda.data.network.ApiServices;
import com.egmvdev.agenda.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class login extends AppCompatActivity {
    private ActivityLoginBinding bind;
    private Context mCont=this;
    private boolean bandera=false;
    Retrofit retrofit = retrofitHelper.getRetrofit();
    ApiServices api = retrofit.create(ApiServices.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        getSupportActionBar().hide();
        bind.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressbar();
            }
        });
    }
    public void progressbar(){
        if(bind.etUsuario.getText().length()>0) {
            int numEmpleado = Integer.parseInt(bind.etUsuario.getText().toString());
            bind.btnLogin.setClickable(false);
            bind.etUsuario.setClickable(false);
            bandera = true;
            consultaUsuario(numEmpleado);
            bind.progressCircular.setVisibility(View.VISIBLE);
        }else{
            Toast.makeText(mCont, "Por favor ingresa tu numero de empleado", Toast.LENGTH_SHORT).show();
            bind.etUsuario.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(bind.etUsuario, InputMethodManager.SHOW_IMPLICIT);
        }
    }
    public void consultaUsuario(int numEmpleado){
        Call<empleado> respo = api.getEmpleado(numEmpleado);
        respo.enqueue(new Callback<empleado>() {
            @Override
            public void onResponse(Call<empleado> call, Response<empleado> response) {
                if(response.body().getStatusID()==1){
                    bind.progressCircular.setVisibility(View.GONE);
                    Intent intent;
                    intent = new Intent(getApplicationContext(), detallesUsuario.class);
                    intent.putExtra("empleado",response.body());
                    startActivity(intent);
                    finish();
                }
                else{
                    bind.btnLogin.setClickable(true);
                    bind.etUsuario.setClickable(true);
                    bind.etUsuario.setText("");
                    bind.progressCircular.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),response.body().getStatusDesc(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<empleado> call, Throwable t) {

            }
        });
    }

}