package org.example;

import org.example.models.Producto;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.FileService.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        FileService.setFileName("productos.txt");

        String keyword = "28";

        List<Producto> salida = cargarProductosArchivoFiltrado(keyword);

        salida.forEach(System.out::println);

    }




}
