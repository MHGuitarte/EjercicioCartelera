package Classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author M.H. Guitarte <guimhur@gmail.com>
 */
public class CCliente implements Serializable {

    private String nombre, apellido, dni;
    private int numCliente = 0;
    private ArrayList<CPelicula> peliAlquilada;

    public CCliente() {
    }

    /*Creamos una serie de constructores con distintas asignaciones de atributos
    e inicializaciones de variables, con el fin de poder instanciar un objeto de 
    esta clase de varias maneras distintas.*/
    public CCliente(String nombre, String apellido, String dni, int numCliente, ArrayList<CPelicula> peliAlquilada) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.numCliente +=1;
        this.peliAlquilada = peliAlquilada;
    }

    public CCliente(String nombre, String apellido, String dni, int numCliente) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.numCliente += 1;
    }

    public CCliente(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.numCliente += 1;
    }

    public CCliente(String nombre, String apellido, String dni, ArrayList<CPelicula> peliAlquilada) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.peliAlquilada = peliAlquilada;
        this.numCliente += 1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    public ArrayList<CPelicula> getPeliAlquilada() {
        return peliAlquilada;
    }

    public void setPeliAlquilada(ArrayList<CPelicula> peliAlquilada) {
        this.peliAlquilada = peliAlquilada;
    }

    @Override
    public String toString() {
        String peliculas = "";
        for (int i = 0; i < peliAlquilada.size(); i++) {
            peliculas += "Peli " + (i + 1) + ": " + peliAlquilada.get(i).getTitulo() + ", ";
        }
        return "nombre: " + nombre + ", apellido: " + apellido + ", dni: " + dni + ", numCliente: " + numCliente + ", Peliculas: " + peliculas;
    }

}
