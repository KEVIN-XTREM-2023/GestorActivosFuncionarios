package com.kevin.proyecto_ma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import kevin.proyecto_ma.R;
import java.util.ArrayList;

public class ListAdapterPersona extends ArrayAdapter<Persona> {

    public ListAdapterPersona(Context context, ArrayList<Persona> personaArrayList){
        super(context, R.layout.lista_persona,personaArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Persona persona = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lista_persona, parent, false);
        }
        TextView nomPersona = convertView.findViewById(R.id.txtNombrePersona);
        TextView cedPersona = convertView.findViewById(R.id.txtCedulaPersona);
        TextView estPersona = convertView.findViewById(R.id.txtEstado);

        nomPersona.setText(persona.nombre+" "+persona.apellido);
        cedPersona.setText(persona.cedula);
        estPersona.setText(persona.estado);
        return convertView;
    }
}
