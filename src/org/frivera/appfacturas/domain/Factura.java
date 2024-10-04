package org.frivera.appfacturas.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Factura {
    private int folio;
    private String descripcion;
    private Date fecha;
    private Cliente cliente;
    private ItemFactura[] items;
    private int indiceItems;
    private static final int MAX_ITEMS = 10;
    private static int ultimoFolio;

    public Factura(String descripcion, Cliente cliente) {
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.items = new ItemFactura[MAX_ITEMS];
        this.folio = ++ultimoFolio;
        this.fecha = new Date();
    }

    public int getFolio() {
        return folio;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }
    public ItemFactura[] getItems() {
        return items;
    }
    public void addItem(ItemFactura item) {
        if (indiceItems < MAX_ITEMS) {
            this.items[indiceItems++] = item;
        }
    }
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public double CalcularTotal(){
        double total = 0.0f;
        for (ItemFactura item : this.items) {
            if (item == null) {
                continue;
            }
            total += item.CalcularImporte();
        }return total;
    }
    public String generarDetalle(){
        StringBuilder detalle = new StringBuilder("Factura #");
            detalle.append(folio)
                .append("\n Cliente: ").append(this.cliente.getName())
                .append("\n NIF: ").append(cliente.getNif())
                .append("\n Descripcion: ").append(this.descripcion)
                .append("\n TOTAL: ").append(CalcularTotal())
                .append("\n");

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        detalle.append("Fecha: ").append(df.format(this.fecha))
                .append("\n");

        for (ItemFactura item : this.items) {
            if (item == null) {
                continue;
            }
            detalle.append(item.getProducto().getCodigo())
                    .append(" - ")
                    .append(item.getProducto().getNombre())
                    .append("\t\t")
                    .append(item.getProducto().getPrecio())
                    .append("\t\t")
                    .append(item.getCantidad())
                    .append("\t\t")
                    .append(item.CalcularImporte())
                    .append("\n\n");
        }
        detalle.append("\t\tGran Total:$")
        .append(CalcularTotal());
        return detalle.toString();
    }
}
