package previoproyectofinal;

import java.util.Scanner;

public class Menu {
    // Método para mostrar el menú principal y gestionar las opciones
    public void mostrarMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = null; // Crear cliente para poder reutilizarlo en diferentes partes del código
        Cotizacion cotizacion = null; // Crear cotizacion para poder reutilizarlo en diferentes partes del código
        boolean salir = false;

        while (!salir) {
            System.out.println("\nMenú Principal");
            System.out.println("1. Crear cliente");
            System.out.println("2. Crear cotización");
            System.out.println("3. Agregar producto a cotización");
            System.out.println("4. Modificar cantidad de producto");
            System.out.println("5. Generar proforma");
            System.out.println("6. Modificar precio de producto");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = 0;
            boolean opcionValida = false;

            // Validar que la opción ingresada sea un número entre 1 y 7
            while (!opcionValida) {
                if (scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                    if (opcion >= 1 && opcion <= 7) {
                        opcionValida = true;
                    } else {
                        System.out.println("Opción inválida. Intente nuevamente.");
                    }
                } else {
                    System.out.println("Entrada no válida. Por favor ingrese un número del 1 al 7.");
                    scanner.next(); // Limpiar el buffer del scanner
                }
                scanner.nextLine(); // Limpiar el buffer
            }

            switch (opcion) {
                case 1:
                    // Crear cliente
                    System.out.print("Ingrese el nombre del cliente: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese los años que el cliente ha estado con la empresa: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Entrada no válida. Por favor ingrese un número.");
                        scanner.next(); // Limpiar el buffer
                    }
                    int aniosCliente = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    cliente = new Cliente(nombre, aniosCliente);
                    System.out.println("Cliente creado exitosamente.");
                    break;
                case 2:
                    // Crear cotización
                    // Obligar a primero crear cliente para tener data de proforma y descuento
                    if (cliente == null) {
                        System.out.println("Primero debe crear un cliente.");
                    } else {
                        cotizacion = new Cotizacion(cliente);
                        System.out.println("Cotización creada exitosamente.");
                    }
                    break;
                case 3:
                    // Agregar producto(s) a la cotización
                    if (cotizacion == null) {
                        System.out.println("Primero debe crear una cotización.");
                    } else {
                        boolean seguirAgregando = true;
                        while (seguirAgregando) {
                            System.out.print("Ingrese el nombre del producto: ");
                            String nombreProducto = scanner.nextLine();
                            System.out.print("Ingrese el precio unitario del producto: ");
                            while (!scanner.hasNextDouble()) {
                                System.out.println("Entrada no válida. Por favor ingrese un número.");
                                scanner.next(); // Limpiar el buffer
                            }
                            double precioUnitario = scanner.nextDouble();
                            scanner.nextLine(); // Limpiar el buffer
                            System.out.print("Ingrese el tipo de cantidad (horas, días, jornadas, etc.): ");
                            String tipoCantidad = scanner.nextLine();
                            System.out.print("Ingrese la cantidad: ");
                            while (!scanner.hasNextInt()) {
                                System.out.println("Entrada no válida. Por favor ingrese un número.");
                                scanner.next(); // Limpiar el buffer
                            }
                            int cantidad = scanner.nextInt();
                            scanner.nextLine(); // Limpiar el buffer

                            Producto producto = new Producto(nombreProducto, precioUnitario, tipoCantidad);
                            cotizacion.agregarProducto(producto, cantidad);
                            System.out.println("Producto agregado exitosamente.");

                            // Mostrar productos actuales en la cotización
                            System.out.println("\nProductos en la cotización:");
                            for (int i = 0; i < cotizacion.numProductos; i++) {
                                Producto p = cotizacion.productos[i];
                                int c = cotizacion.cantidades[i];
                                System.out.printf("%d. Producto: %s | Tipo de Cantidad: %s | Cantidad: %d | Precio Unitario: S/%.2f%n",
                                        i, p.getNombre(), p.getTipoCantidad(), c, p.getPrecioUnitario());
                            }

                            // Preguntar si desea agregar otro producto
                            char respuesta;
                            do {
                                System.out.print("¿Desea agregar otro producto? (s/n): ");
                                respuesta = scanner.next().toLowerCase().charAt(0);
                                scanner.nextLine(); // Limpiar el buffer
                                if (respuesta != 's' && respuesta != 'n') {
                                    System.out.println("Respuesta inválida. Intente nuevamente.");
                                }
                            } while (respuesta != 's' && respuesta != 'n');

                            if (respuesta != 's') {
                                seguirAgregando = false;
                            }
                        }
                    }
                    break;
                case 4:
                    // Modificar cantidad de producto
                    if (cotizacion == null) {
                        System.out.println("Primero debe crear una cotización.");
                    } else {
                        int indice;
                        do {
                            System.out.print("Ingrese el índice del producto que desea modificar (0 a " + (cotizacion.numProductos - 1) + "): ");
                            while (!scanner.hasNextInt()) {
                                System.out.println("Entrada no válida. Por favor ingrese un número.");
                                scanner.next(); // Limpiar el buffer
                            }
                            indice = scanner.nextInt();
                            scanner.nextLine(); // Limpiar el buffer
                            if (indice < 0 || indice >= cotizacion.numProductos) {
                                System.out.println("Índice inválido. Intente nuevamente.");
                            }
                        } while (indice < 0 || indice >= cotizacion.numProductos);

                        System.out.print("Ingrese la nueva cantidad: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Entrada no válida. Por favor ingrese un número.");
                            scanner.next(); // Limpiar el buffer
                        }
                        int nuevaCantidad = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer

                        cotizacion.modificarCantidad(indice, nuevaCantidad);
                    }
                    break;
                case 5:
                    // Generar proforma
                    if (cotizacion == null) {
                        System.out.println("Primero debe crear una cotización.");
                    } else {
                        cotizacion.generarProforma();
                    }
                    break;
                case 6:
                    // Modificar precio de producto
                    if (cotizacion == null) {
                        System.out.println("Primero debe crear una cotización.");
                    } else {
                        int indice;
                        do {
                            System.out.print("Ingrese el índice del producto que desea modificar (0 a " + (cotizacion.numProductos - 1) + "): ");
                            while (!scanner.hasNextInt()) {
                                System.out.println("Entrada no válida. Por favor ingrese un número.");
                                scanner.next(); // Limpiar el buffer
                            }
                            indice = scanner.nextInt();
                            scanner.nextLine(); // Limpiar el buffer
                            if (indice < 0 || indice >= cotizacion.numProductos) {
                                System.out.println("Índice inválido. Intente nuevamente.");
                            }
                        } while (indice < 0 || indice >= cotizacion.numProductos);
                        System.out.print("Ingrese el nuevo precio unitario: ");
                        while (!scanner.hasNextDouble()) {
                            System.out.println("Entrada no válida. Por favor ingrese un número.");
                            scanner.next(); // Limpiar el buffer
                        }
                        double nuevoPrecio = scanner.nextDouble();
                        scanner.nextLine(); // Limpiar el buffer

                        cotizacion.productos[indice].modificarPrecio(nuevoPrecio);
                        System.out.println("Precio del producto modificado exitosamente.");
                    }
                    break;
                case 7:
                    // Salir
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
        scanner.close();
    }
}
