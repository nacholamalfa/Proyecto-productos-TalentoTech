package com.techlab;

import com.techlab.productos.*;
import com.techlab.excepciones.*;
import com.techlab.pedidos.Pedido;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Producto> inventario;
    private static ArrayList<Pedido> pedidos;
    private static Scanner scanner;

    public static void main(String[] args) {
        inventario = new ArrayList<>();
        pedidos = new ArrayList<>();
        scanner = new Scanner(System.in);

        

        inventario.add(new Producto("Laptop", 999.99, 10));
        inventario.add(new Producto("Mouse", 29.99, 20));
        inventario.add(new Producto("Teclado", 49.99, 15));
        
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = obtenerOpcionUsuario();
            
            switch (opcion) {
                case 1: 
                    agregarProducto();
                    break;
                case 2: 
                    listarProductos();
                    break;
                case 3: 
                    buscarActualizarProducto();
                    break;
                case 4: 
                    eliminarProducto();
                    break;
                case 5: 
                    crearPedido();
                    break;
                case 6: 
                    listarPedidos();
                    break;
                case 7: 
                    salir = true;
                    System.out.println("¡Gracias por usar el sistema!");
                    break;
                default: 
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
        
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTIÓN - TECHLAB ===");
        System.out.println("1. Agregar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Buscar/Actualizar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("5. Crear un pedido");
        System.out.println("6. Listar pedidos");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    private static int obtenerOpcionUsuario() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; 
        }
    }
    
    private static void agregarProducto() {
        System.out.println("\n=== AGREGAR PRODUCTO ===");
        
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        
        double precio = -1;
        while (precio < 0) {
            System.out.print("Precio del producto: ");
            try {
                precio = Double.parseDouble(scanner.nextLine());
                if (precio < 0) {
                    System.out.println("El precio no puede ser negativo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
        
        int stock = -1;
        while (stock < 0) {
            System.out.print("Cantidad en stock: ");
            try {
                stock = Integer.parseInt(scanner.nextLine());
                if (stock < 0) {
                    System.out.println("El stock no puede ser negativo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
        
        Producto nuevoProducto = new Producto(nombre, precio, stock);
        inventario.add(nuevoProducto);
        System.out.println("Producto agregado correctamente: " + nuevoProducto.mostrarInformacion());
    }
    
    private static void listarProductos() {
        System.out.println("\n=== LISTA DE PRODUCTOS ===");
        if (inventario.size() == 0) {
            System.out.println("No hay productos en el inventario.");
        } else {
            for (int i = 0; i < inventario.size(); i++) {
                System.out.println(inventario.get(i).mostrarInformacion());
            }
        }
    }
    
    private static void buscarActualizarProducto() {
        System.out.println("\n=== BUSCAR/ACTUALIZAR PRODUCTO ===");
        System.out.print("Ingrese ID o nombre del producto: ");
        String busqueda = scanner.nextLine();
        
        Producto productoEncontrado = null;
        
     
        try {
            int id = Integer.parseInt(busqueda);
            for (int i = 0; i < inventario.size(); i++) {
                if (inventario.get(i).getId() == id) {
                    productoEncontrado = inventario.get(i);
                }
            }
        } catch (NumberFormatException e) {
           
            for (int i = 0; i < inventario.size(); i++) {
                if (inventario.get(i).getNombre().equalsIgnoreCase(busqueda)) {
                    productoEncontrado = inventario.get(i);
                }
            }
        }
        
        if (productoEncontrado == null) {
            System.out.println("Producto no encontrado.");
        } else {
            System.out.println("Producto encontrado: " + productoEncontrado.mostrarInformacion());
            System.out.println("\n¿Desea actualizar este producto? (s/n): ");
            String respuesta = scanner.nextLine();
            
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.print("Nuevo nombre (deje en blanco para mantener): ");
                String nuevoNombre = scanner.nextLine();
                if (!nuevoNombre.isEmpty()) {
                    productoEncontrado.setNombre(nuevoNombre);
                }
                
                System.out.print("Nuevo precio (ingrese -1 para mantener): ");
                try {
                    double nuevoPrecio = Double.parseDouble(scanner.nextLine());
                    if (nuevoPrecio >= 0) {
                        productoEncontrado.setPrecio(nuevoPrecio);
                    }
                } catch (NumberFormatException e) {
                 
                }
                
                System.out.print("Nueva cantidad en stock (ingrese -1 para mantener): ");
                try {
                    int nuevoStock = Integer.parseInt(scanner.nextLine());
                    if (nuevoStock >= 0) {
                        productoEncontrado.setCantidadEnStock(nuevoStock);
                    }
                } catch (NumberFormatException e) {
                  
                }
                
                System.out.println("Producto actualizado: " + productoEncontrado.mostrarInformacion());
            }
        }
    }
    
    private static void eliminarProducto() {
        System.out.println("\n=== ELIMINAR PRODUCTO ===");
        System.out.print("Ingrese ID del producto a eliminar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            boolean eliminado = false;
            
            for (int i = 0; i < inventario.size(); i++) {
                if (inventario.get(i).getId() == id) {
                    System.out.println("Se eliminará: " + inventario.get(i).mostrarInformacion());
                    System.out.print("¿Está seguro? (s/n): ");
                    String confirmacion = scanner.nextLine();
                    
                    if (confirmacion.equalsIgnoreCase("s")) {
                        inventario.remove(i);
                        System.out.println("Producto eliminado correctamente.");
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                    eliminado = true;
                }
            }
            
            if (!eliminado) {
                System.out.println("No se encontró un producto con ese ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un ID válido.");
        }
    }
    
    private static void crearPedido() {
        System.out.println("\n=== CREAR PEDIDO ===");
        
        if (inventario.size() == 0) {
            System.out.println("No hay productos disponibles para crear un pedido.");
            return;
        }
        
        Pedido nuevoPedido = new Pedido();
        boolean finalizarPedido = false;
        
        while (!finalizarPedido) {
            System.out.println("\n1. Agregar producto al pedido");
            System.out.println("2. Finalizar y confirmar pedido");
            System.out.println("3. Cancelar pedido");
            System.out.print("Seleccione una opción: ");
            
            int opcion = -1;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida.");
                continue;
            }
            
            switch (opcion) {
                case 1:
                    listarProductos();
                    System.out.print("\nIngrese ID del producto a agregar: ");
                    try {
                        int idProducto = Integer.parseInt(scanner.nextLine());
                        Producto productoSeleccionado = null;
                        
                        for (int i = 0; i < inventario.size(); i++) {
                            if (inventario.get(i).getId() == idProducto) {
                                productoSeleccionado = inventario.get(i);
                            }
                        }
                        
                        if (productoSeleccionado == null) {
                            System.out.println("Producto no encontrado.");
                        } else {
                            System.out.print("Cantidad a agregar: ");
                            int cantidad = Integer.parseInt(scanner.nextLine());
                            
                            try {
                                nuevoPedido.agregarProducto(productoSeleccionado, cantidad);
                                System.out.println("Producto agregado al pedido.");
                                System.out.println(nuevoPedido.mostrarInformacion());
                            } catch (StockInsuficienteException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, ingrese un valor numérico válido.");
                    }
                    break;
                    
                case 2:
                    if (nuevoPedido.getLineasPedido().size() == 0) {
                        System.out.println("El pedido está vacío. No se puede confirmar.");
                    } else {
                        nuevoPedido.confirmarPedido();
                        pedidos.add(nuevoPedido);
                        finalizarPedido = true;
                    }
                    break;
                    
                case 3:
                    System.out.println("Pedido cancelado.");
                    finalizarPedido = true;
                    break;
                    
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    
    private static void listarPedidos() {
        System.out.println("\n=== LISTA DE PEDIDOS ===");
        if (pedidos.size() == 0) {
            System.out.println("No hay pedidos registrados.");
        } else {
            for (int i = 0; i < pedidos.size(); i++) {
                System.out.println("\n" + pedidos.get(i).mostrarInformacion());
            }
        }
    }
}