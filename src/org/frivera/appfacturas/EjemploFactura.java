package org.frivera.appfacturas;
import org.frivera.appfacturas.domain.*;

import java.util.Scanner;

public class EjemploFactura {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.setName("Fernando");
        cliente.setNif("2020303107");

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la descripcion de la factura:");
        String desc = sc.nextLine();
        Factura factura = new Factura(desc, cliente);

        Producto producto;
        String nombre;
        float precio;
        int cantidad;

        System.out.println();

        for (int i = 0; i < 2; i++) {
            producto = new Producto();
            System.out.print("Ingrese el id del producto " + producto.getCodigo() + ": ");
            nombre = sc.next();
            producto.setNombre(nombre);

            System.out.print("Ingrese el precio del producto " + ": ");
            precio = sc.nextFloat();
            producto.setPrecio(precio);

            System.out.print("Ingrese la cantidad: ");
            cantidad = sc.nextInt();

            ItemFactura item = new ItemFactura(cantidad, producto);
            factura.addItem(item);
            System.out.println();
        }
        System.out.println(factura.generarDetalle());
    }
}
