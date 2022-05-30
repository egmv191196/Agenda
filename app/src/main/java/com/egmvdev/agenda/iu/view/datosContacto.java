package com.egmvdev.agenda.iu.view;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.egmvdev.agenda.R;
import com.egmvdev.agenda.core.roomHelper;
import com.egmvdev.agenda.data.model.contacto;
import com.egmvdev.agenda.databinding.ActivityDatosContactoBinding;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class datosContacto extends AppCompatActivity {
    private ActivityDatosContactoBinding bind;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private roomHelper dbHelper;
    private ArrayList<String> menu =new ArrayList<String>();
    private contacto contactoEditar;
    private boolean bandera = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind= ActivityDatosContactoBinding.inflate(getLayoutInflater());
        getSupportActionBar().hide();
        setContentView(bind.getRoot());
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        LlenarMenu();
        if(b!=null)
        {
            bandera=true;
            contactoEditar = (contacto) b.getSerializable("contacto");
            bind.etNombre.setText(contactoEditar.getNombre());
            bind.etApellidoP.setText(contactoEditar.getApellidoP());
            bind.etApellidoM.setText(contactoEditar.getApellidoM());
            bind.etEdad.setText(((Integer)contactoEditar.getEdad()).toString());
            bind.etTelefono.setText(contactoEditar.getPhone().toString());
            bind.etSexo.setSelection(contactoEditar.getSexo());
            if (contactoEditar.getFoto().length != 0) {
                BitmapDrawable bd = new BitmapDrawable(BitmapFactory.decodeByteArray(contactoEditar.getFoto(), 0, contactoEditar.getFoto().length));
                //Drawable d = (Drawable) bd;
                Bitmap originalBitmap = bd.getBitmap();
                bind.imgFoto.setImageBitmap(originalBitmap);
                //binding.imgView.setImageDrawable(roundedDrawable);
            }
            bind.btnGuardar.setText("Guardar Cambios");
        }
        dbHelper=roomHelper.get(this);
        bind.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permisoHabilitado();
            }
        });
        bind.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bind.etNombre.getText().length()>0 && bind.etApellidoP.getText().length()>0 && bind.etApellidoM.getText().length()>0 && bind.etEdad.getText().length()>0 && bind.etTelefono.getText().length()>0){
                    if(bandera==false){
                        dbHelper.addContacto(guardarContacto());
                        finish();
                    }else{
                        contacto c= guardarContacto();
                        dbHelper.udpateContacto(contactoEditar.getId(),c.getNombre(),c.getApellidoP(),c.getApellidoM(),c.getEdad(),c.getPhone(),c.getSexo(),c.getFoto());
                        finish();
                    }
                }else{
                    Toast.makeText(datosContacto.this, "Por favor revisa que todos los campos esten llenos1", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Log.i("Foto",imageBitmap.toString());
            bind.imgFoto.setImageBitmap(imageBitmap);
        }
    }
    private void permisoHabilitado(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            checkCameraHardware();
        }else{
            solicitarPermiso();
        }
    }
    private void solicitarPermiso(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
            Toast.makeText(this,"Es necesario activar la camara para tomar fotos",Toast.LENGTH_LONG).show();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 123){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                checkCameraHardware();
            }else{
                Toast.makeText(this,"Error al activar el permiso",Toast.LENGTH_LONG).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void checkCameraHardware() {
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            lanzarActivityCamera.launch(takePictureIntent);
        } else {
            Toast.makeText(this,"El dispositivo no cuenta con una camara",Toast.LENGTH_LONG).show();
        }
    }
    ActivityResultLauncher<Intent> lanzarActivityCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode()== Activity.RESULT_OK){
                    Intent data = result.getData();
                    Bundle extras = data.getExtras();
                    Log.i("Foto",extras.toString());
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    bind.imgFoto.setImageBitmap(imageBitmap);
                }
            });
    public contacto guardarContacto(){
        String nombre =bind.etNombre.getText().toString();
        String apellidoP =bind.etApellidoP.getText().toString();
        String apellidoM =bind.etApellidoM.getText().toString();
        String edad =bind.etEdad.getText().toString();
        String telefono =bind.etTelefono.getText().toString();
        int sexo = bind.etSexo.getSelectedItemPosition();
        Bitmap bmp = ((BitmapDrawable)bind.imgFoto.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return new contacto(nombre,apellidoP,apellidoM,Integer.parseInt(edad),Long.parseLong(telefono),sexo,byteArray);
    }
    private void LlenarMenu(){
        menu.add("Masculino");
        menu.add("Femenino");
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.sexo_list_item, menu);
        bind.etSexo.setAdapter(adapter);
    }

}