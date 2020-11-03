package com.rvmarra17.todo.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rvmarra17.todo.R;

import java.util.List;

public class Adaptador extends ArrayAdapter {


    public Adaptador(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Context contexto = this.getContext();
        final LayoutInflater inflador = LayoutInflater.from(contexto);
        final Item tarea = (Item) this.getItem(position);

        if (convertView == null) {
            convertView = inflador.inflate(R.layout.layout_item, null);
        }

        final TextView ED_TAREA = convertView.findViewById(R.id.tarea);
        final TextView ED_FECHA = convertView.findViewById(R.id.fecha);

        ED_TAREA.setText(tarea.getTarea());
        ED_FECHA.setText(tarea.getFechaAsString());

        return convertView;
    }
}
