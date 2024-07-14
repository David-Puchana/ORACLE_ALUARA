package com.alura.screenmatch.modelos;

import com.alura.screenmatch.exception.ErrorinDurationConversionException;
import com.google.gson.annotations.SerializedName;

public class Titulo implements Comparable<Titulo>{

    @SerializedName("Title")
    private String nombre;
    private int fechaDeLanzamiento;
    @SerializedName("Year")
    private boolean incluidoEnElPlan;
    private double sumaDeLasEvaluaciones;
    private int totalDeEvaluaciones;
    private int duracionEnMinutos;

    public Titulo(String nombre, int fechaDeLanzamiento) {
        this.nombre = nombre;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public Titulo(TitleOmdb myTitleOmdb) {
        this.nombre = myTitleOmdb.title();
        this.fechaDeLanzamiento = Integer.valueOf(myTitleOmdb.year()) ;
        if(myTitleOmdb.runtime().contains("N/A")){
            throw new ErrorinDurationConversionException("N/A data, impossible to convert to integer");
        }
        this.duracionEnMinutos = Integer.valueOf(myTitleOmdb.runtime().split(" ")[0]);
    }

    public String getNombre() {
        return nombre;
    }

    public int getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public boolean isIncluidoEnElPlan() {
        return incluidoEnElPlan;
    }

    public int getDuracionEnMinutos() {
        return duracionEnMinutos;
    }

    public int getTotalDeEvaluaciones() {
        return totalDeEvaluaciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaDeLanzamiento(int fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public void setIncluidoEnElPlan(boolean incluidoEnElPlan) {
        this.incluidoEnElPlan = incluidoEnElPlan;
    }

    public void setDuracionEnMinutos(int duracionEnMinutos) {
        this.duracionEnMinutos = duracionEnMinutos;
    }

    public void muestraFichaTecnica(){
        System.out.println("Nombre de la película: " + nombre);
        System.out.println("Año de lanzamiento: " + fechaDeLanzamiento);
    }

    public void evalua(double nota){
        sumaDeLasEvaluaciones += nota;
        totalDeEvaluaciones++;
    }

    public double calculaMediaEvaluaciones(){
        return sumaDeLasEvaluaciones / totalDeEvaluaciones;
    }

    @Override
    public int compareTo(Titulo otroTitulo) {
        return this.getNombre().compareTo(otroTitulo.getNombre());
    }

    @Override
    public String toString() {
        return "Titulo \n" +
                "Nombre: " + nombre + '\n' +
                "Fecha de Lanzamiento: " + fechaDeLanzamiento + '\n' +
                "Duracion en Minutos: " + duracionEnMinutos;
    }
}
