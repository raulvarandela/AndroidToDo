package com.rvmarra17.todo.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Item {

    private String tarea;
    private Calendar fecha; //usar mejor calendar

    public Item(String tarea, Calendar fecha) {
        this.tarea = tarea;
        this.fecha = fecha;
    }

    public Item(String tarea, String fecha) throws ParseException {
        this.tarea = tarea;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(fecha);
        this.fecha = Calendar.getInstance();
        this.fecha.setTime(date);
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public  String getFechaAsString(){
        StringBuilder toret = new StringBuilder();
        toret.append(fecha.get(Calendar.DAY_OF_MONTH));
        toret.append("/");
        toret.append(fecha.get(Calendar.MONTH)+1);
        toret.append("/");
        toret.append(fecha.get(Calendar.YEAR));

        return toret.toString();
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        toret.append(this.tarea);
        toret.append("|");
        toret.append(this.getFechaAsString());

        return toret.toString();
    }
}
