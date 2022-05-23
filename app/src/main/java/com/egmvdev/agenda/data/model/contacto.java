package com.egmvdev.agenda.data.model;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Contactos")
public class contacto {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo (name = "apellidoP")
    private String apellidoP;
    @ColumnInfo (name = "apellidoM")
    private String apellidoM;
    @ColumnInfo(name = "edad")
    private int edad;
    @ColumnInfo(name = "phone")
    private Long phone;
    @ColumnInfo(name = "sexo")
    private String sexo;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] foto;
    public contacto(@NonNull int id, String nombre, String apellidoP, String apellidoM, int edad, Long phone, String sexo,byte[] foto){
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.edad = edad;
        this.phone = phone;
        this.sexo = sexo;
        this.foto=foto;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
