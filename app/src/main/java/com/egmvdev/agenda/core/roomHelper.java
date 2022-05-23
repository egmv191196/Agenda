package com.egmvdev.agenda.core;

import android.content.Context;

import androidx.room.Room;

import com.egmvdev.agenda.data.contactoDAO;
import com.egmvdev.agenda.data.model.contacto;
import com.egmvdev.agenda.data.contactoDataBase;

import java.util.List;

public class roomHelper {
    private static roomHelper sroomHelper;
    private contactoDAO mContactoDAO;

    private  roomHelper(Context context){
        Context appContext=context.getApplicationContext();
        contactoDataBase database = Room.databaseBuilder(appContext, contactoDataBase.class, "contacto")
                .allowMainThreadQueries().build();
        mContactoDAO= database.getContactoDAO();
    }
    public  static roomHelper get(Context context){
        if(sroomHelper== null){
            sroomHelper= new roomHelper(context);
        }
        return sroomHelper;
    }
    public List<contacto> getContactos(){
        return mContactoDAO.getContactos();
    }
    public contacto getContacto(int id){
        return mContactoDAO.getContacto(id);
    }
    public void addContacto(contacto c){
        mContactoDAO.addContacto(c);
    }
}
