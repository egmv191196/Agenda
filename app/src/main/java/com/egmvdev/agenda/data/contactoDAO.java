package com.egmvdev.agenda.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.egmvdev.agenda.data.model.contacto;

import java.sql.Blob;
import java.util.List;

@Dao
public interface contactoDAO {
    @Query("DELETE FROM Contactos WHERE id=:id")
    void deleteContacto(int id);
    @Query("UPDATE Contactos SET nombre=:nombre, apellidoP=:apellidoP, apellidoM=:apellidoM, edad=:edad,phone=:telefono, sexo=:sexo, foto=:foto  WHERE id=:id")
    void updateContacto(int id, String nombre, String apellidoP, String apellidoM, int edad, Long telefono, int sexo, byte[] foto);
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
