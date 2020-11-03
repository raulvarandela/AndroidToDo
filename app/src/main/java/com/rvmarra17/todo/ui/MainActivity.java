package com.rvmarra17.todo.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.rvmarra17.todo.R;
import com.rvmarra17.todo.core.Adaptador;
import com.rvmarra17.todo.core.Item;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

        this.adapatador = new Adaptador(this, this.tareas);


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
        final AlertDialog.Builder DLG_tarea = new AlertDialog.Builder(this);
        final AlertDialog.Builder DLG_fecha = new AlertDialog.Builder(this);
        final EditText ed_tarea = new EditText(this);
        final DatePicker ed_fecha = new DatePicker(this);
        final String[] tempTarea = new String[1];
        final Date[] tempDate;

        DLG_tarea.setMessage("Introduzca la tarea");
        DLG_tarea.setView(ed_tarea);
        DLG_tarea.setNegativeButton("cancelar", null);
        DLG_tarea.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String tarea = ed_tarea.getText().toString();

                //mostrar el segundo dialogo
                DLG_fecha.setMessage("Introduce la fecha");
                DLG_fecha.setView(ed_fecha);
                DLG_fecha.setNegativeButton("cancelar", null);
                DLG_fecha.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapatador.add(new Item(tarea, buildDate(ed_fecha.getDayOfMonth(), ed_fecha.getMonth(), ed_fecha.getYear())));
                    }
                });
                DLG_fecha.create().show();

            }
        });

        DLG_tarea.create().show();
    }

    private Calendar buildDate(int dia, int mes, int anho) {
        Calendar fecha = Calendar.getInstance();
        fecha.set(Calendar.DAY_OF_MONTH, dia);
        fecha.set(Calendar.MONTH, mes);
        fecha.set(Calendar.YEAR, anho);

        return fecha;
    }

    private void elemina(int pos) {
        this.tareas.remove(pos);
        this.adapatador.notifyDataSetChanged();
    }

    private ArrayList<Item> tareas;
    private Adaptador adapatador;


    @Override
    protected void onPause() {
        super.onPause();

        final SharedPreferences pref = this.getPreferences(MODE_PRIVATE);
        final SharedPreferences.Editor prefEditor = pref.edit();
        StringBuilder str_tareas = new StringBuilder();

        for (int i = 0; i < tareas.size(); i++) {
            str_tareas.append(tareas.get(i).toString());
            str_tareas.append("|");
        }

        prefEditor.putString("tareas", str_tareas.toString());
        prefEditor.apply();

    }

    @Override
    protected void onResume() {
        super.onResume();

        tareas.removeAll(tareas);
        adapatador.notifyDataSetChanged();

        final SharedPreferences pref = this.getPreferences(MODE_PRIVATE);
        final String str_tareas = pref.getString("tareas", "");
        final String[] array_tareas = str_tareas.split("\\|");

        if (array_tareas.length != 1 || !array_tareas[0].equals("")) {
            if (array_tareas.length != 0) {
                for (int i = 0; i < array_tareas.length; i+=2) {
                    try {
                        //añado las tareas
                        Item aux = new Item(array_tareas[i], array_tareas[i + 1]);
                        adapatador.add(aux);
                        //comprobación del calendario
                       // comprobarCalendario(array_tareas[i+1]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }




    }

    private void comprobarCalendario(String calendario) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(calendario);
        Calendar calendarioTarea = Calendar.getInstance();
        calendarioTarea.setTime(date);

        Calendar calendarioActual = Calendar.getInstance();

        if((calendarioTarea.get(Calendar.DAY_OF_MONTH) == calendarioActual.get(Calendar.DAY_OF_MONTH)) && (calendarioTarea.get(Calendar.MONTH) == calendarioActual.get(Calendar.MONTH)) && (calendarioTarea.get(Calendar.YEAR) == calendarioActual.get(Calendar.YEAR))){
            Toast.makeText(this,"Tienes una tarea pendiente",Toast.LENGTH_LONG);
        }

    }

}