package com.techlab.pedidos;

import java.util.ArrayList;
import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.productos.LineaPedido;
import com.techlab.productos.Producto;


public class Pedido {
    private ArrayList<LineaPedido> lineasPedido;
    private int numeroPedido;
    private static int siguienteNumeroPedido = 1;

    public Pedido() {
        this.lineasPedido = new ArrayList<>();
        this.numeroPedido = siguienteNumeroPedido++;
    }

    public void agregarProducto(Producto producto, int cantidad) throws StockInsuficienteException {
        if (producto.getCantidadEnStock() < cantidad) {
            throw new StockInsuficienteException("Stock insuficiente para el producto: " + producto.getNombre());
        }
        

        boolean encontrado = false;
        for (int i = 0; i < lineasPedido.size(); i++) {
            LineaPedido linea = lineasPedido.get(i);
            if (linea.getProducto().getId() == producto.getId()) {
                linea.setCantidad(linea.getCantidad() + cantidad);
                encontrado = true;
            }
        }
        

        if (!encontrado) {
            lineasPedido.add(new LineaPedido(producto, cantidad));
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < lineasPedido.size(); i++) {
            LineaPedido linea = lineasPedido.get(i);
            total += linea.getProducto().getPrecio() * linea.getCantidad();
        }
        return total;
    }

    public void confirmarPedido() {
        for (int i = 0; i < lineasPedido.size(); i++) {
            LineaPedido linea = lineasPedido.get(i);
            linea.getProducto().disminuirStock(linea.getCantidad());
        }
        System.out.println("Pedido #" + numeroPedido + " confirmado. Total: $" + calcularTotal());
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public ArrayList<LineaPedido> getLineasPedido() {
        return lineasPedido;
    }
    
    public String mostrarInformacion() {
        StringBuilder info = new StringBuilder("Pedido #" + numeroPedido + ":\n");
        for (int i = 0; i < lineasPedido.size(); i++) {
            LineaPedido linea = lineasPedido.get(i);
            info.append("  - " + linea.getProducto().getNombre() + 
                      " (x" + linea.getCantidad() + "): $" + 
                      (linea.getProducto().getPrecio() * linea.getCantidad()) + "\n");
        }
        info.append("Total: $" + calcularTotal());
        return info.toString();
    }
}