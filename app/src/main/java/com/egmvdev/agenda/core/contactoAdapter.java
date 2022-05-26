package com.egmvdev.agenda.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.egmvdev.agenda.R;
import com.egmvdev.agenda.data.model.contacto;

import java.util.ArrayList;

public class contactoAdapter extends RecyclerView.Adapter<contactoViewHolder> {
    private ArrayList<contacto> listContactos;
    public Context contexto;
    public contactoAdapter(Context cont, ArrayList<contacto> list){
        listContactos=list;
        contexto= cont;
    }

    @Override
    public contactoViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_contacto, viewGroup, false);
        return new contactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(contactoViewHolder viewHolder, int position) {
        viewHolder.bind(listContactos.get(position), contexto);

    }

    @Override
    public int getItemCount() {
        return listContactos.size();
    }
}
