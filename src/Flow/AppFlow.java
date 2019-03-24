package Flow;

import Classes.CCliente;
import Classes.CPelicula;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.lang.Object;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M.H. Guitarte <guimhur@gmail.com>
 */
public class AppFlow {
//DECLARACIÓN DE VARIABLES ÚTILES QUE USAREMOS POSTERIORMENTE

    public static final String MENU_PRINCIPAL = "CONTROL DE ALQUILER: \n"
            + "1 -> Añade una nueva película a la lista(-h para ayuda).\n"
            + "2 -> Muestra la lista de películas, ordenadas por Título.\n"
            + "3 -> Muestra la lista de películas en orden inverso.\n"
            + "4 -> Crea un nuevo cliente(-h para ayuda).\n"
            + "5 -> Añade una película a la lista de películas alquiladas de un cliente.\n"
            + "6 -> Guarda todos los clientes en un archivo(-h para ayuda).\n"
            + "7 -> Muestra la lista de clientes en consola.\n"
            + "fin -> Cierra el programa.";

    public static final String AYUDA_PELI = "AYUDA PELÍCULA:\n"
            + "Datos a introducir(Seguir orden estricto):\n"
            + "     ·Código película\n"
            + "     ·Título película\n"
            + "     ·Género película\n"
            + "     ·Precio alquiler\n"
            + "     ·Fecha(Formato: Año,mes,día)\n"
            + "     ·Director\n"
            + "     ·Sinópsis\n"
            + "     ·Duración(Formato: horas,minutos)";

    public static final String AYUDA_CLIENTE = "AYUDA CLIENTE:\n"
            + "Datos a introducir(Seguir orden estricto):\n"
            + "     ·Nombre cliente\n"
            + "     ·Apellidos cliente\n"
            + "     ·DNI cliente\n";

    public static final String GUARDAR_ARCHIVO = "AYUDA PARA GUARDAR ARCHIVO:\n"
            + "Únicamente deberá indicar el nombre del archivo donde se guardarán las películas.";

//Esta variable controlará el flujo dinámico de la aplicación
    private static String orden;

    //FUNCIONAMIENTO DE LA APLICACIÓN - MÉTODO MÁS IMPORTANTE
    public static void flow() {
        //Listas con las que vamos a trabajar
        TreeSet<CPelicula> listaPeliculas = new TreeSet<>();

        TreeMap<String, CCliente> listaClientes = new TreeMap<>();
        //Comienzo de la dinámica de la aplicación
        orden = "";
        while (!orden.toLowerCase().equals("fin")) {
            System.out.println("\n \n" + MENU_PRINCIPAL);
            orden = leerString();
            switch (orden) {

                case ("1"): {
                    listaPeliculas.add(registrarPelicula());
                    break;
                }
                case ("1 -h"): {
                    System.out.println("\n \n \n \n \n \n" + AYUDA_PELI + "\n \n \n");
                    leerString();
                    break;
                }
                case ("2"): {
                    mostrarPeliculas(listaPeliculas);
                    break;
                }
                case ("3"): {
                    mostrarInverso(listaPeliculas);
                    break;
                }

                case ("4"): {
                    addCliente(listaClientes);
                    break;
                }
                case ("4 -h"): {
                    System.out.println("\n \n \n \n \n \n" + AYUDA_CLIENTE + "\n \n \n");
                    leerString();
                    break;
                }
                case ("5"): {
                    System.out.println("Selecciona el DNI del cliente que alquila la película");
                    String str = leerString();

                    Set st = listaClientes.keySet();
                    Iterator itCliente = st.iterator();

                    while (itCliente.hasNext()) {

                        CCliente c = listaClientes.get(itCliente.next());

                        if (str.equals(c.getDni())) {
                            peliculaCliente(c.getPeliAlquilada(), listaPeliculas);
                        }
                    }
                    break;
                }
                case ("6"): {
                    guardar(listaClientes);
                    break;
                }
                case ("6 -h"): {
                    System.out.println("\n \n \n \n \n \n \n \n" + GUARDAR_ARCHIVO + "\n \n \n \n");
                    leerString();
                    break;
                }
                case ("7"): {
                    cargar();
                    break;
                }
                case ("fin"): {
                    orden = "fin";
                    break;
                }
                default: {
                    System.out.println("No se reconoce el comando introducido \n \n \n \n \n \n");
                    break;
                }
            }
        }
    }

    //Métodos de lectura de distintos tipos de dato (los que usaremos)
    public static String leerString() {
        try {
            InputStreamReader input = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(input);
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Entrada no obtenida. Se devolverá String vacío.");
            return "";
        }

    }

    public static float leerFloat() {
        try {
            return Float.parseFloat(leerString());
        } catch (NumberFormatException e) {
            System.err.println("Entrada no obtenida. Se devolverá Float vacío.");
            return 0.0f;
        }
    }

    public static int leerInt() {
        try {
            return Integer.parseInt(leerString());
        } catch (NumberFormatException e) {
            System.err.println("Entrada no obtenida. Se devolverá int vacío.");
            return 0;
        }
    }

    public static GregorianCalendar leerFecha() {
        //Método que pide una fecha al usuario y la devuelve como GregorianCalendar
        int[] fecha = new int[6];
        fecha[3] = 0;
        fecha[4] = 0;
        fecha[5] = 0;
        //Indicamos en un array de texto los tipos de datos que se le pedirán al usuario al introducir la fecha
        String[] stringu = new String[6];
        stringu[0] = "año";
        stringu[1] = "mes";
        stringu[2] = "día";
        stringu[3] = "horas";
        stringu[4] = "minutos";
        stringu[5] = "segundos";
        //Bucle para almacenar el año, el mes y el día que el usario escoja (no tiene control de fecha correcta)
        for (int i = 0; i < 3; i++) {
            try {
                System.out.println("Introduzca " + stringu[i]);
                fecha[i] = leerInt();
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }

        return new GregorianCalendar(fecha[0], fecha[1], fecha[2], fecha[3], fecha[4], fecha[5]);

    }

    //Métodos de las órdenes
    public static CPelicula registrarPelicula() {
        CPelicula p = new CPelicula();

        System.out.println("Código de la película");
        p.setCodigo(leerInt());
        System.out.println("Título de la película");
        p.setTitulo(leerString());
        System.out.println("Género de la película");
        p.setTipo(leerString());
        System.out.println("Precio de alquiler");
        p.setPrecio(leerFloat());
        System.out.println("Fecha de estreno");
        p.setFechaEstreno(leerFecha());
        System.out.println("Director");
        p.setDirector(leerString());
        System.out.println("Sinópsis");
        p.setSinopsis(leerString());
        System.out.println("Duración:");
        System.out.println("Horas");
        p.setHora(leerInt());
        System.out.println("Minutos");
        p.setMin(leerInt());
        return p;
    }

    public static void mostrarPeliculas(TreeSet<CPelicula> peliculas) {
        Iterator<CPelicula> itPeli = peliculas.iterator();

        System.out.println("\n \n \nLISTA TÍTULOS ÓRDEN ALFABÉTICO \n");

        while (itPeli.hasNext()) {
            System.out.println("Título película: " + itPeli.next().getTitulo());
        }
    }

    public static void mostrarInverso(TreeSet<CPelicula> peliculas) {
        Iterator<CPelicula> itPeliReves = peliculas.descendingIterator();

        System.out.println(" \n  \n  \nLISTA TÍTULOS ÓRDEN INVERSO \n");

        while (itPeliReves.hasNext()) {
            System.out.println("Título película: " + itPeliReves.next().getTitulo());
        }
    }

//Este método no se va a implementar todavía
    public static void peliculaCliente(ArrayList<CPelicula> peliculas, TreeSet<CPelicula> listaPeliculas) {
        System.out.println("Introduzca el código de la pelicula alquilada");
        int busqueda = leerInt();
        /*TODO: Se puede meter en CPelicula un atributo alquilado(bool) que 
          pase a true en este if si vamos a alquilar un objeto concreto.*/
        for (CPelicula p : listaPeliculas) {
            if (busqueda == p.getCodigo()) {
                peliculas.add(p);
            }
        }

    }

    public static void addCliente(TreeMap<String, CCliente> clientes) {
        CCliente c = new CCliente("", "", "", new ArrayList<>());

        System.out.println("Nombre del cliente");
        c.setNombre(leerString());
        System.out.println("Apellidos del cliente");
        c.setApellido(leerString());
        System.out.println("DNI del cliente");
        c.setDni(leerString());

        try {
            clientes.put(c.getDni(), c);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

//Métodos de control de creación, guardado y cargado de archivos
    public static File newFile() {
        File f;
        System.out.println("Nombre del archivo (el archivo se creará en el directorio raíz de la aplicación)");
        String s = leerString();
        try {
            f = new File(s + ".dat");
            if (!f.exists()) {
                f.createNewFile();
            }
            return f;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public static void guardar(TreeMap<String, CCliente> clientes) {
        /*Este método recoge todos los registros de la lista de clientes y los 
        guarda en el archivo que le especifiquemos.*/

 /*Lógica compleja: Creamos un nuevo archivo, y si dicho archivo está
        recién creado (es decir, ocupa 0 bits o -1 si es null), saltará el control
        de sobre escritura y guardará la lista de clientes automáticamente. Si el
        archivo ya contiene datos, el método comparará los datos a escribir con los
        ya escritos, para sobre escribir los que coincidan y escribir como nuevos
        los nunca escritos. Se supone que esto disminuye la probabilidad de corrupción
        de archivos.
*
*
*       Da errores, así que lo almacenamos en un comentario.
*
*
*
*

if (f.length() > 0) {

            try {

                FileInputStream streamIn = new FileInputStream(f);

                try {

                    ArrayList<CCliente> listaGuardado = new ArrayList<>();
                    boolean bool = true;

                    ObjectInputStream inputObj = new ObjectInputStream(streamIn);

                    Set st = clientes.keySet();
                    Iterator itCliente = st.iterator();

                    while (bool) {
                        try {
                            CCliente c = (CCliente) inputObj.readObject();
                            listaGuardado.add(c);
                        } catch (IOException | ClassNotFoundException e) {
                            inputObj.close();
                            bool = false;
                        }

                    }

                    FileOutputStream stream = new FileOutputStream(f);
                    ObjectOutputStream output = new ObjectOutputStream(stream);

                    int i = 0;
                    while (itCliente.hasNext()) {

                        if (listaGuardado.get(i).equals(clientes.get(itCliente.next()))) {
                            output.writeObject(clientes.get(itCliente.next()));
                        } else {
                            output.writeUnshared(clientes.get(itCliente.next()));
                        }
                        i++;
                    }

                    output.close();

                } catch (IOException e) {
                    e.printStackTrace(System.out);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace(System.out);
            }

        } else {
            try {
                FileOutputStream stream = new FileOutputStream(f);
                ObjectOutputStream output = new ObjectOutputStream(stream);

                Set st = clientes.keySet();
                Iterator itCliente = st.iterator();
                while (itCliente.hasNext()) {

                    output.writeUnshared(clientes.get(itCliente.next()));

                }
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }



         */
        File f = newFile();

        try {
            FileOutputStream stream = new FileOutputStream(f);
            ObjectOutputStream output = new ObjectOutputStream(stream);

            Set st = clientes.keySet();
            Iterator itCliente = st.iterator();
            while (itCliente.hasNext()) {

                output.writeUnshared(clientes.get(itCliente.next()));

            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

    }

    public static void cargar() {

        ArrayList<CCliente> clientes = new ArrayList<>();

        try {
            FileInputStream stream = new FileInputStream(newFile());
            try {
                ObjectInputStream input = new ObjectInputStream(stream);
                boolean bool = true;

                while (bool) {
                    try {
                        CCliente c = (CCliente) input.readObject();
                        clientes.add(c);

                    } catch (IOException | ClassNotFoundException e) {
                        for (int i = 0; i < clientes.size(); i++) {
                            System.out.println(clientes.get(i).toString());
                        }
                        bool = false;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }
}
