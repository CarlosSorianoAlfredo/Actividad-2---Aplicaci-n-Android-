package com.example.bd_sqlite_room_v2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import bd.EscuelaBD;
import controladores.AlumnoDAO;
import entidades.Alumno;

public class ActivityCambios extends Activity {

    EditText editTextNumControl, editTextNombre, editTextPrimerAp, editTextSegundoAp, editTextEdad, editTextSemestre, editTextCarrera;
    TextView textViewModificar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);

        editTextNumControl = findViewById(R.id.caja_num_control_cambios);
        editTextNombre = findViewById(R.id.caja_nombre_cambio);
        editTextPrimerAp = findViewById(R.id.caja_apellido_cambio);
        editTextSegundoAp = findViewById(R.id.caja_segundoAp_cambios);
        editTextEdad = findViewById(R.id.caja_edad_cambios);
        editTextSemestre = findViewById(R.id.caja_semestre_cambios);
        editTextCarrera = findViewById(R.id.caja_carrera_cambios);
        textViewModificar = findViewById(R.id.textView12);
    }

    public void buscarAlumno(View view) {
        String numControl = editTextNumControl.getText().toString();

        if (!numControl.isEmpty()) {

            AlumnoDAO alumnoDAO = EscuelaBD.getAppDatabase(this).alumnoDAO();


            Alumno alumno = alumnoDAO.buscarAlumnoPorNumControl(numControl);

            if (alumno != null) {

                editTextNombre.setText(alumno.getNombre());
                editTextPrimerAp.setText(alumno.getPrimerAp());
                editTextSegundoAp.setText(alumno.getSegundoAp());
                editTextEdad.setText(String.valueOf(alumno.getEdad()));
                editTextSemestre.setText(String.valueOf(alumno.getSemestre()));
                editTextCarrera.setText(alumno.getCarrera());


                textViewModificar.setVisibility(View.VISIBLE);
            } else {

                Toast.makeText(this, "Alumno no encontrado", Toast.LENGTH_SHORT).show();
            }
        } else {

            Toast.makeText(this, "Ingrese un número de control válido", Toast.LENGTH_SHORT).show();
        }
    }

    public void confirmarCambios(View view) {

        String numControl = editTextNumControl.getText().toString();
        String nombre = editTextNombre.getText().toString();
        String primerAp = editTextPrimerAp.getText().toString();
        String segundoAp = editTextSegundoAp.getText().toString();
        byte edad = Byte.parseByte(editTextEdad.getText().toString());
        byte semestre = Byte.parseByte(editTextSemestre.getText().toString());
        String carrera = editTextCarrera.getText().toString();

        if (!nombre.isEmpty() && !primerAp.isEmpty() && !numControl.isEmpty() && !segundoAp.isEmpty()
                && !editTextEdad.getText().toString().isEmpty() && !carrera.isEmpty()
                && !editTextSemestre.getText().toString().isEmpty()) {


            AlumnoDAO alumnoDAO = EscuelaBD.getAppDatabase(this).alumnoDAO();


            alumnoDAO.modificarAlumnoPorNumControl(nombre, primerAp, segundoAp, edad, semestre, carrera, numControl);


            Toast.makeText(this, "Cambios confirmados correctamente", Toast.LENGTH_SHORT).show();


            limpiarCampos();
        } else {

            Toast.makeText(this, "Complete todos los campos obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiarCampos() {
        editTextNumControl.setText("");
        editTextNombre.setText("");
        editTextPrimerAp.setText("");
        editTextSegundoAp.setText("");
        editTextEdad.setText("");
        editTextSemestre.setText("");
        editTextCarrera.setText("");
        textViewModificar.setVisibility(View.INVISIBLE);
    }
}
