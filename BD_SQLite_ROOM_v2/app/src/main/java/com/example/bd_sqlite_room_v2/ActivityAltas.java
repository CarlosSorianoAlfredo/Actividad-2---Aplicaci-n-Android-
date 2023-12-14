package com.example.bd_sqlite_room_v2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import bd.EscuelaBD;
import entidades.Alumno;

public class ActivityAltas extends Activity {

    EditText cajaNumControl, cajaNombre, caja_primer_ap, caja_segundo_ap, caja_edad, caja_semestre, caja_carrera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);

        cajaNumControl = findViewById(R.id.caja_num_control_bajas);
        cajaNombre =findViewById(R.id.caja_nombre);
        caja_primer_ap = findViewById(R.id.caja_primer_apellido);
        caja_segundo_ap = findViewById(R.id.caja_segundo_apellido);
        caja_edad = findViewById(R.id.caja_edad);
        caja_semestre = findViewById(R.id.caja_semestre);
        caja_carrera = findViewById(R.id.caja_carrera);
    }

    public void agregarAlumno(View v){
        Log.i("MSJ->", "metodo agregar");

        EscuelaBD bd = EscuelaBD.getAppDatabase(getBaseContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("MSJ->", "CORRECTO");
                bd.alumnoDAO().agregarAlumno(
                        new Alumno(cajaNumControl.getText().toString(), cajaNombre.getText().toString(), caja_primer_ap.getText().toString(), caja_segundo_ap.getText().toString(), Byte.parseByte(caja_edad.getText().toString()), Byte.parseByte(caja_semestre.getText().toString()), caja_carrera.getText().toString()));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "REGISTRO AGREGADO CORRECTAMENTE",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();



    }
}
