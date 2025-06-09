package com.techlab.productos;

public class Comida extends Producto {
    private double peso;
    
    public Comida(String nombre, double precio, int cantidadEnStock, double peso) {
        super(nombre, precio, cantidadEnStock);
        this.peso = peso;
    }
    
    public double getPeso() {
        return peso;
    }
    
    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + ", Peso: " + peso + " gramos";
    }
}