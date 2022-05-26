package com.egmvdev.agenda.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.ColumnInfo;

import com.egmvdev.agenda.R;
import com.egmvdev.agenda.data.model.contacto;
import com.egmvdev.agenda.databinding.ItemContactoBinding;
import com.egmvdev.agenda.iu.view.datosContacto;

import java.io.Serializable;

public class contactoViewHolder extends RecyclerView.ViewHolder {
    private ItemContactoBinding binding;
    private roomHelper dbHelper;
    public contactoViewHolder(View itemView) {
        super(itemView);
        binding= ItemContactoBinding.bind(itemView);
    }
    public void bind(contacto C, Context contexto){
        dbHelper=roomHelper.get(contexto);
        binding.tvId.setText(Integer.toString(C.getId()));
        binding.tvName.setText(C.getNombre()+" "+C.getApellidoP()+" "+ C.getApellidoM()+" ID: "+ C.getId());
        binding.telefono.setText(Long.toString(C.getPhone()));
        //Byte[] to Drawable
        if (C.getFoto().length != 0) {
            BitmapDrawable bd = new BitmapDrawable(BitmapFactory.decodeByteArray(C.getFoto(), 0, C.getFoto().length));
            //Drawable d = (Drawable) bd;
            Bitmap originalBitmap = bd.getBitmap();
            //creamos el drawable redondeado
            RoundedBitmapDrawable roundedDrawable =
                    RoundedBitmapDrawableFactory.create(contexto.getResources(), originalBitmap);

            //asignamos el CornerRadius
            //roundedDrawable.setCornerRadius(originalBitmap.getHeight());
            roundedDrawable.setCircular(true);
            binding.imgView.setImageDrawable(roundedDrawable);
        }
        Drawable originalDrawable = contexto.getResources().getDrawable(R.drawable.perfil);
        //Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();
        binding.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(contexto, "Click en el boton de editar del id: "+ C.getId(), Toast.LENGTH_SHORT).show();
                Intent intent;
                intent = new Intent(contexto, datosContacto.class);
                intent.putExtra("contacto", (Serializable) C);
                contexto.startActivity(intent);
            }
        });
        binding.elimnar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(contexto, "Click en el boton de eliminar", Toast.LENGTH_SHORT).show();
                dbHelper.deleteContacto(C.getId());
            }
        });
    }
}

