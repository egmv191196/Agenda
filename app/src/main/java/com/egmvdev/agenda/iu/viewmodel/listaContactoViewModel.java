package com.egmvdev.agenda.iu.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.egmvdev.agenda.data.model.contacto;

import java.util.List;

public class listaContactoViewModel extends ViewModel {
    private MutableLiveData<List<contacto>> listaContactos;
    private MutableLiveData<Integer> bandera;
    public MutableLiveData<Integer> actualizarValor(){
        if (bandera == null) {
            bandera = new MutableLiveData<Integer>();
        }
        Log.i("cambios VM",bandera.toString());
        return bandera;
    }
}
