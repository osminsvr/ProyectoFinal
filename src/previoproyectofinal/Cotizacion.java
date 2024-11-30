package previoproyectofinal;

import java.util.Random;

public class Cotizacion {
    // Atributos
    Producto[] productos;
    int[] cantidades;
    Cliente cliente;
    int numProductos;
    int numeroProforma;

    // Constructor
    public Cotizacion(Cliente cliente) {
        this.productos = new Producto[50]; // Capacidad máxima de 50 productos
        this.cantidades = new int[50];
        this.cliente = cliente;
        this.numProductos = 0;
        this.numeroProforma = generarNumeroProforma();
    }

    // Método para generar un número de proforma aleatorio
    private int generarNumeroProforma() {
        Random random = new Random();
        return random.nextInt(10000) + 1; // Genera un número entre 1 y 10000
    }

    // Método para agregar un producto a la cotización
    public void agregarProducto(Producto producto, int cantidad) {
        if (numProductos < productos.length) {
            productos[numProductos] = producto;
            cantidades[numProductos] = cantidad;
            numProductos++;
        } else {
            System.out.println("No se pueden agregar más productos.");
        }
    }

    // Método para modificar la cantidad de un producto
    public void modificarCantidad(int indice, int nuevaCantidad) {
        if (indice >= 0 && indice < numProductos) {
            cantidades[indice] = nuevaCantidad;
        } else {
            System.out.println("Índice inválido.");
        }
    }

    // Método para calcular el total de la cotización
    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < numProductos; i++) {
            total += productos[i].getPrecioUnitario() * cantidades[i];
        }
        return cliente.aplicarDescuento(total);
    }

    // Método para generar la proforma
    public void generarProforma() {
        System.out.println("Proforma N°: " + numeroProforma);
        System.out.println("Proforma para: " + cliente.getNombre());
        System.out.println("-----------------------------------------");
        for (int i = 0; i < numProductos; i++) {
            Producto producto = productos[i];
            int cantidad = cantidades[i];
            double precioTotal = producto.getPrecioUnitario() * cantidad;
            System.out.printf("Producto: %s | Tipo de Cantidad: %s | Cantidad: %d | Precio Unitario: S/%.2f | Precio Total: S/%.2f%n",
                    producto.getNombre(), producto.getTipoCantidad(), cantidad, producto.getPrecioUnitario(), precioTotal);
        }
        System.out.println("-----------------------------------------");
        System.out.printf("Total (con descuento aplicado): S/%.2f%n", calcularTotal());
    }
}