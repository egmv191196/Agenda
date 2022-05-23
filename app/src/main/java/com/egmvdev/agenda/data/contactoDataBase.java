package com.egmvdev.agenda.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.egmvdev.agenda.data.model.contacto;

@Database(entities = {contacto.class},version = 1)
public abstract class contactoDataBase extends RoomDatabase {
    public abstract contactoDAO getContactoDAO();
}
