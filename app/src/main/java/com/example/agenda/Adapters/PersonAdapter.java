package com.example.agenda.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.Models.Person;
import com.example.agenda.R;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonHolder> {
    private Context context;
    private int layout;
    private List<Person> personas;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClickListener(Person person, int position);
    }

    public class PersonHolder extends RecyclerView.ViewHolder {
        private TextView textViewName, textViewEdad, textViewTelefono;

        public PersonHolder(View view) {
            super(view);
            textViewName = (TextView) view.findViewById(R.id.textViewName);
            textViewEdad = (TextView) view.findViewById(R.id.textViewEdad);
            textViewTelefono = (TextView) view.findViewById(R.id.textViewTelefono);
        }

        public void bind(Person person, OnItemClickListener itemClickListener){
            textViewName.setText(person.getName());
            textViewEdad.setText(String.valueOf(person.getEdad()));
            textViewTelefono.setText(person.getTelefono());

            itemView.setOnClickListener((view) -> {
                itemClickListener.onItemClickListener(person, getAdapterPosition());
            });
        }
    }

    public PersonAdapter(int layout, List<Person> personas, OnItemClickListener itemClickListener) {
        this.layout = layout;
        this.personas = personas;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        PersonHolder personHolder = new PersonHolder(view);
        return personHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder holder, int position) {
        holder.bind(personas.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

}
