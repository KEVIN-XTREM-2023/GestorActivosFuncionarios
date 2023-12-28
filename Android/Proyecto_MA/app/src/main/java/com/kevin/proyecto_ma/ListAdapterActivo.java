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

public class ListAdapterActivo extends ArrayAdapter<Activo> {
    public ListAdapterActivo(Context context, ArrayList<Activo> activoArrayList){
        super(context, R.layout.lista_activos,activoArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Activo activo = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lista_activos, parent, false);
        }
        TextView nomActivo = convertView.findViewById(R.id.txtNombreActivo);
        TextView idActivo = convertView.findViewById(R.id.txtCodigoActivo);
        TextView estActivo = convertView.findViewById(R.id.txtEstadoActivo);

        nomActivo.setText(activo.nombre);
        idActivo.setText(activo.id);
        estActivo.setText(activo.estado);
        return convertView;
    }

}
