package com.example.agenda.Models;

import com.orm.SugarRecord;

public class Person extends SugarRecord {
    private String name, telefono;
    private int edad;

    public Person() {

    }

    public Person(String name, String telefono, int edad) {
        this.name = name;
        this.telefono = telefono;
        this.edad = edad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
