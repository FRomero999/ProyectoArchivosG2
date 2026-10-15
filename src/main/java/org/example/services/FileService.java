package org.example.services;

import org.example.models.Producto;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio encargado de la persistencia de {@link Producto} en archivos de texto.
 * <p>
 * Utiliza el separador {@code ";"} para delimitar los campos de cada producto y
 * ofrece dos formas de lectura: mediante {@link java.io BufferedReader} clasico
 * y mediante NIO ({@link java.nio.file.Files}).
 */
public class FileService {

    private String fileName;

    public FileService(ConfigService config) {
        this.fileName = config.getProperty("fichero");
    }

    /**
     * Guarda la lista de productos en el archivo configurado, sobrescribiendo su contenido.
     * Cada producto se escribe en una linea con el formato
     * {@code id;nombre;precio;stock}.
     *
     * @param productos lista de productos a persistir
     * @throws RuntimeException si ocurre un error de entrada/salida durante la escritura
     */
    public void guardarProductosArchivo(List<Producto> productos) {
        try( var bfr =  new BufferedWriter( new FileWriter(fileName))) {

            for( Producto producto : productos){
                bfr.write(
                        producto.id()+";"+
                                producto.nombre()+";"+
                                producto.precio()+";"+
                                producto.stock()
                );
                bfr.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Carga los productos desde el archivo configurado usando un
     * {@link java.io.BufferedReader}.
     *
     * @return lista de productos leidos del archivo
     * @throws IllegalStateException si {@link #fileName} no ha sido establecido (es {@code null})
     * @throws RuntimeException si el archivo no existe o ocurre un error de entrada/salida
     */
    public List<Producto> cargarProductosArchivo() {

        if(fileName == null){
            throw new IllegalStateException("fileName is null");
        }

        var productos = new ArrayList<Producto>();

        try (var bfr = new BufferedReader(new FileReader(fileName))) {
            String line;
            while( (line = bfr.readLine()) != null ) {
                productos.add(crearProductoDeString(line));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }


    /**
     * Carga los productos desde el archivo configurado usando la API NIO
     * ({@link java.nio.file.Files#readAllLines(java.nio.file.Path)}).
     *
     * @return lista de productos leidos del archivo
     * @throws RuntimeException si ocurre un error de entrada/salida durante la lectura
     */
    public List<Producto> cargarProductosArchivoNIO() {
        var productos = new ArrayList<Producto>();

        Path archivo = Path.of(fileName);

        var lineas = new ArrayList<String>();

        try {
            lineas = (ArrayList<String>) Files.readAllLines(archivo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        lineas.forEach(line -> productos.add(crearProductoDeString(line)) );

        return productos;
    }

    /**
     * Carga los productos desde el archivo configurado y devuelve solo aquellos
     * cuyas lineas contienen la palabra clave indicada, sin distinguir mayusculas
     * ni minusculas.
     *
     * @param keyword texto a buscar dentro de cada linea del archivo
     * @return lista de productos que coinciden con la palabra clave
     * @throws RuntimeException si ocurre un error de entrada/salida durante la lectura
     */
    public  List<Producto> cargarProductosArchivoFiltrado(String keyword) {
        Path path = Path.of(fileName);
        List<Producto> salida = new ArrayList<Producto>();

        try {
            Files.lines(path)
                    .filter(line ->line.toLowerCase().contains(keyword.toLowerCase()))
                    .forEach( line -> salida.add(FileService.crearProductoDeString(line)));

/*            salida = Files.lines(path)
                    .filter(line ->line.toLowerCase().contains(keyword.toLowerCase()))
                    .map(line -> FileService.crearProductoDeString(line))
                    .collect(Collectors.toList());
*/

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    // Helpers

    /**
     * Convierte una linea de texto en un objeto {@link Producto}.
     * <p>
     * La linea debe tener el formato {@code id;nombre;precio;stock}.
     *
     * @param line linea de texto a parsear
     * @return el {@link Producto} construido a partir de la linea
     */
    private static Producto crearProductoDeString(String line) {
        String[] campos = line.split(";");
        return new Producto(
                Long.parseLong(campos[0]),
                campos[1],
                Double.parseDouble(campos[2]),
                Integer.parseInt(campos[3])
        );
    }

}
