package previoproyectofinal;

public class Cliente {
    // Atributos
    public String nombre;
    public int aniosCliente;

    // Constructor
    public Cliente(String nombre, int aniosCliente) {
        this.nombre = nombre;
        this.aniosCliente = aniosCliente;
    }

    // Método para aplicar descuento basado en la antigüedad del cliente
    public double aplicarDescuento(double total) {
        if (aniosCliente >= 5) {
            return total * 0.9; // 10% de descuento si el cliente tiene 5 o más años
        } else if (aniosCliente >= 2) {
            return total * 0.95; // 5% de descuento si el cliente tiene entre 2 y 4 años
        } else {
            return total; // Sin descuento si el cliente tiene menos de 2 años
        }
    }

    // Método para mostrar detalles del cliente
    public void mostrarDetalles() {
        System.out.println("Cliente: " + nombre);
        System.out.println("Años como cliente: " + aniosCliente);
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public int getAniosCliente() {
        return aniosCliente;
    }
}