package org.example;

import org.example.models.Producto;
import org.example.services.ConfigService;
import org.example.services.FileService;
import tools.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Properties;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {

        ConfigService config = new ConfigService();
        FileService fs = new FileService(config);


        var productos = fs.cargarProductosArchivoNIO();
        productos.addFirst( new Producto(2L,null,40.0,40));
        productos.forEach(System.out::println);
        fs.guardarProductosJson(productos,"salida.json");



    }




}
