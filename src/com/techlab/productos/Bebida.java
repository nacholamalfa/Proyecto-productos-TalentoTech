package com.techlab.productos;

public class Bebida extends Producto {
    private double litros;
    
    public Bebida(String nombre, double precio, int cantidadEnStock, double litros) {
        super(nombre, precio, cantidadEnStock);
        this.litros = litros;
    }
    
    public double getLitros() {
        return litros;
    }
    
    public void setLitros(double litros) {
        this.litros = litros;
    }
    
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + ", Contenido: " + litros + " litros";
    }
}
