package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Producto (
        Long id,
        @JsonProperty("name")
        String nombre,
        @JsonProperty("price")
        Double precio,
        @JsonIgnore
        Integer stock
) implements Serializable {

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
