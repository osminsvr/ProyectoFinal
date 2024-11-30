package previoproyectofinal;

public class Producto {
    // Atributos
    public String nombre;
    public double precioUnitario;
    public String tipoCantidad; // Ej: horas, días, jornadas, etc.

    // Constructor
    public Producto(String nombre, double precioUnitario, String tipoCantidad) {
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.tipoCantidad = tipoCantidad;
    }

    // Método para modificar el precio del producto
    public void modificarPrecio(double nuevoPrecio) {
        this.precioUnitario = nuevoPrecio;
    }

    // Método para mostrar detalles del producto
    public void mostrarDetalles() {
        System.out.println("Producto: " + nombre);
        System.out.println("Precio Unitario: S/" + precioUnitario);
        System.out.println("Tipo de Cantidad: " + tipoCantidad);
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public String getTipoCantidad() {
        return tipoCantidad;
    }
}