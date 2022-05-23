package com.egmvdev.agenda.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;

import com.egmvdev.agenda.R;
import com.egmvdev.agenda.data.model.contacto;
import com.egmvdev.agenda.databinding.ItemContactoBinding;

public class contactoViewHolder extends RecyclerView.ViewHolder {
    private ItemContactoBinding binding;
    public contactoViewHolder(View itemView) {
        super(itemView);
        binding= ItemContactoBinding.bind(itemView);
    }
    public void bind(contacto C, Context contexto){
        binding.tvName.setText(C.getNombre()+" "+C.getApellidoP()+" "+ C.getApellidoM());
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




    }
}

