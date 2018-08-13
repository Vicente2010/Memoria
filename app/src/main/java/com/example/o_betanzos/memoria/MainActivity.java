package com.example.o_betanzos.memoria;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void generarArchivo(View v){
        try{

            EditText edtNombre  = (EditText) findViewById(R.id.edtNombre);
            String   nombre     = edtNombre.getText().toString();

            FileOutputStream outputStream = null;
            outputStream = openFileOutput("MiArchivo.txt", Context.MODE_PRIVATE);
            outputStream.write(nombre.getBytes());
            outputStream.close();

            Toast.makeText(MainActivity.this, "El archivo se ha creado",Toast.LENGTH_SHORT).show();
            edtNombre.setText("");

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Huo un error en la escritura del archivo",Toast.LENGTH_SHORT).show();
        }

    }

    public void guardarPreferencias(View v){
        EditText edtNombre = (EditText) findViewById(R.id.edtNombre);
        EditText edtCorreo = (EditText) findViewById(R.id.edtCorreo);

        String nombre = edtNombre.getText().toString();
        String correo = edtCorreo.getText().toString();

        SharedPreferences miPreferenciaCompartida =
                getSharedPreferences("MisDatosPersonales",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = miPreferenciaCompartida.edit();

        editor.putString("nombre", nombre);
        editor.putString("correo",correo);

        editor.commit();

        Toast.makeText(MainActivity.this, "Se ha creado la Preferencia Compartida", Toast.LENGTH_SHORT).show();
        edtNombre.setText("");
        edtCorreo.setText("");
    }

    public void mostrarPreferencias(View v){

        SharedPreferences miPreferenciaCompartida = getSharedPreferences("MisDatosPersonales",Context.MODE_PRIVATE);

        String nombre = miPreferenciaCompartida.getString("nombre","no existe esta variable");
        String correo = miPreferenciaCompartida.getString("correo","no existe esta variable");

        TextView tvPreferenciaCompartida =(TextView) findViewById(R.id.tvPreferenciaCompartida);
        String preferencia = "\nNombre: " + nombre + " \nCorreo: " + correo ;

        tvPreferenciaCompartida.setText(preferencia);

    }

}
