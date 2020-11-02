package com.rvmarra17.todo.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {

    private String tarea;
    private Date fecha; //usar mejor calendar

    public Item(String tarea, Date fecha) {
        this.tarea = tarea;
        this.fecha = fecha;
    }

    public Item(String tarea, String fecha) throws ParseException {
        this.tarea = tarea;
        this.fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        toret.append(tarea);
        toret.append(" ");
        toret.append(this.getFechaFormat());

        return  toret.toString();
    }

    public String getFechaFormat(){
        StringBuilder toret = new StringBuilder();
        toret.append(Integer.toString(fecha.getDate()));
        toret.append("/");
        toret.append(Integer.toString(fecha.getMonth()+1));
        toret.append("/");
        toret.append(Integer.toString(fecha.getYear()+1900));


        return  toret.toString();

    }
}
