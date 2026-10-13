package org.example.models;

public record Producto(
        Long id,
        String nombre,
        Double precio,
        Integer stock
) {

    public Producto{
        if(id == null){
            throw new IllegalArgumentException("El id no puede ser nulo");
        }
        if(precio < 0){
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if(stock < 0){
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }

}
