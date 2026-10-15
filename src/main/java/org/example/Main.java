package org.example;

import org.example.services.ConfigService;
import org.example.services.FileService;

import java.io.*;
import java.util.Properties;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {

        ConfigService config = new ConfigService();
        FileService fs = new FileService(config);

        fs.cargarProductosArchivoFiltrado("samsung").forEach(System.out::println);

    }




}
