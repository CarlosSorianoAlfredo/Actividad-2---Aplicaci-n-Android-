package com.example.bd_sqlite_room_v2;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import bd.EscuelaBD;
import controladores.AlumnoDAO;

public class ActivityBajas extends Activity {

    EditText numControl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);

        numControl = findViewById(R.id.caja_num_control_bajas);
    }

    public void EliminarAlumno(View view) {
        // Obtén el número de control del EditText
        String numeroControl = numControl.getText().toString();

        // Verifica si el número de control no está vacío
        if (!numeroControl.isEmpty()) {
            // Obten la instancia del DAO de la base de datos
            AlumnoDAO alumnoDAO = EscuelaBD.getAppDatabase(this).alumnoDAO();

            // Llama al método de eliminación usando el número de control
            alumnoDAO.eliminarAlumnoPorNumControl(numeroControl);

            // Muestra un mensaje indicando que el alumno fue eliminado
            Toast.makeText(this, "Alumno eliminado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            // Muestra un mensaje si el número de control está vacío
            Toast.makeText(this, "Ingrese un número de control válido", Toast.LENGTH_SHORT).show();
        }
    }
}
