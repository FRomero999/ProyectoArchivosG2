package org.example;

import org.example.models.Producto;
import org.example.services.ConfigService;
import org.example.services.FileService;
import tools.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
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

        fs.guardarProductosBinario(productos,"data.bin");

        System.out.println("salida bin....");
        fs.cargarProductosBinario("data.bin").forEach(System.out::println);

        try {
            URI url = new URI("https://cesurformacion.com");
            var bfr = new BufferedReader(
                    new InputStreamReader(url.toURL().openStream()));
            bfr.lines()
                    .filter(line -> line.contains("CESUR") )
                    .forEach(System.out::println);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




}
