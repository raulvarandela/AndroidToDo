package com.rvmarra17.todo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializar atributos
        this.tareas = new ArrayList<>();

        //incializar las vistas
        final Button botonInsertar = this.findViewById(R.id.boton);
        final ListView tareas = this.findViewById(R.id.tareas);

        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.inserta();
            }
        });

        this.adapatador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                this.tareas
        );


        tareas.setAdapter(adapatador);

        tareas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                elemina(position);
                return false;
            }
        });
    }

    private void actualizaLista() {

    }

    private void inserta() {
        AlertDialog.Builder DLG = new AlertDialog.Builder(this);
        final EditText ed_tarea = new EditText(this);

        DLG.setMessage("Introduzca la tarea");
        DLG.setView(ed_tarea);
        DLG.setNegativeButton("cancelar",null);
        DLG.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tarea = ed_tarea.getText().toString();
                adapatador.add(tarea);
                //adapatador.remove(adapatador.getItem(pos));
            }
        });

        DLG.create().show();
    }

    private void elemina(int pos){
        this.tareas.remove(pos);
        this.adapatador.notifyDataSetChanged();
    }

    private ArrayList<String> tareas;
    private ArrayAdapter<String> adapatador;
}