package com.egmvdev.agenda.data.network;

import com.egmvdev.agenda.data.model.empleado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("getInfoEmpleado")
    Call<empleado> getEmpleado(@Query("idEmpleado") int idEmpleado);
}
