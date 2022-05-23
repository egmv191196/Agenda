package com.egmvdev.agenda.iu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        getSupportActionBar().hide();
        Retrofit retrofit = retrofitHelper.getRetrofit();
        ApiServices api = retrofit.create(ApiServices.class);
        bind.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<empleado> respo = api.getEmpleado(Integer.parseInt(bind.etUsuario.getText().toString()));
                respo.enqueue(new Callback<empleado>() {
                    @Override
                    public void onResponse(Call<empleado> call, Response<empleado> response) {
                        if(response.body().getStatusID()==1){
                            Intent intent;
                            intent = new Intent(mCont, listaContacto.class);
                            intent.putExtra("empleado",response.body());
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(getApplicationContext(),response.body().getStatusDesc(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<empleado> call, Throwable t) {

                    }
                });

                /**/
            }
        });
    }

}