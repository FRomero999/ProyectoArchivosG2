package org.example;

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
        fs.guardarProductosJson(productos,"salida.json");



    }




}
