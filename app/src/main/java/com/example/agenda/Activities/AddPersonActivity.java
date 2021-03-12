package com.example.agenda.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.Models.Person;
import com.example.agenda.R;

public class AddPersonActivity extends AppCompatActivity {

    private EditText editTextName, editTextEdad, editTextTelefono;
    private Button buttonGuardar;
    private boolean isEdit;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        bindUIToReference();

        setIsEdit();

        setTitleActivity();

        if(isEdit) {
            person = Person.findById(Person.class, getIntent().getExtras().getLong("id"));
            bindDataToFields();
        }

        buttonGuardar.setOnClickListener((view) -> {
            if(checkFields()){
                if(!isEdit)
                    person = new Person(editTextName.getText().toString(), editTextTelefono.getText().toString(), Integer.parseInt(editTextEdad.getText().toString()));
                else {
                    person.setName(editTextName.getText().toString());
                    person.setEdad(Integer.parseInt(editTextEdad.getText().toString()));
                    person.setTelefono(editTextTelefono.getText().toString());
                }
                person.save();
                goToMain();
            }
        });
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setIsEdit() {
        isEdit = getIntent().getExtras() != null;
    }

    private void setTitleActivity() {
        if(isEdit)
            setTitle("Editar Persona");
        else
            setTitle("Agregar Persona");
    }

    private void bindDataToFields() {
        editTextName.setText(person.getName());
        editTextEdad.setText(String.valueOf(person.getEdad()));
        editTextTelefono.setText(person.getTelefono());
    }

    private void bindUIToReference() {
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEdad = (EditText) findViewById(R.id.editTextEdad);
        editTextTelefono = (EditText) findViewById(R.id.editTextTelefono);
        buttonGuardar = (Button) findViewById(R.id.buttonSave);
    }

    private boolean checkFields() {
        if(!TextUtils.isEmpty(editTextName.getText().toString())) {
            Toast.makeText(this, "El campo nombre esta vacio", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(!TextUtils.isEmpty(editTextEdad.getText().toString())) {
            Toast.makeText(this, "El campo edad esta vacio", Toast.LENGTH_LONG).show();
            return true;
        }
        if(!TextUtils.isEmpty(editTextTelefono.getText().toString())) {
            Toast.makeText(this, "El campo Telefono esta vacio", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}