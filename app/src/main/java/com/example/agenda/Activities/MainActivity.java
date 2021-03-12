package com.example.agenda.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.agenda.Adapters.PersonAdapter;
import com.example.agenda.Models.Person;
import com.example.agenda.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PersonAdapter personAdapter;
    private List<Person> personas;
    private FloatingActionButton buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindUIToReference();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        buttonAdd.setOnClickListener((view) -> {
            Intent intent = new Intent(this, AddPersonActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPerson();
        personAdapter = new PersonAdapter(R.layout.item_persona, personas, (person, position) -> {
            Intent intent = new Intent(MainActivity.this, AddPersonActivity.class);
            intent.putExtra("id", person.getId());
            startActivity(intent);
        });
        recyclerView.setAdapter(personAdapter);
    }

    private void getPerson() {
        personas = Person.listAll(Person.class);
    }

    private void bindUIToReference() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerPerson);
        buttonAdd = (FloatingActionButton) findViewById(R.id.buttonAdd);
    }
}