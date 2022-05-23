package com.egmvdev.agenda.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.egmvdev.agenda.data.model.contacto;

import java.util.List;

@Dao
public interface contactoDAO {
    @Query("SELECT * FROM Contactos")
    List<contacto> getContactos();
    @Query("SELECT * FROM Contactos WHERE id=:id")
    contacto getContacto(int id);
    @Insert
    void addContacto(contacto c);
    @Delete
    void deleteContacto(contacto c);
    @Update
    void updateContacto(contacto c);
}
