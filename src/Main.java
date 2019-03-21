
import Classes.CCliente;
import Classes.CPelicula;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author M.H. Guitarte <guimhur@gmail.com>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Creamos el TreeSet y añadimos las 5 primeras películas
        TreeSet<CPelicula> listaPeliculas = new TreeSet<CPelicula>();
        try {

            listaPeliculas.add(new CPelicula(1, "Esta es de Nolan", "Bélica", 5.25f, new GregorianCalendar(2009, 01, 11, 10, 25, 51), "Nolan", "Gente que muere", 2, 20));
            listaPeliculas.add(new CPelicula(3, "Esta es de Polanski", "Drama", 6.25f, new GregorianCalendar(1998, 01, 11, 10, 25, 51), "Polanski", "Soy un viejo", 1, 10));
            listaPeliculas.add(new CPelicula(7, "Esta es de Spielberg", "Drama", 2.50f, new GregorianCalendar(1979, 01, 11, 10, 25, 51), "Spielberg", "Gano Money", 1, 50));
            listaPeliculas.add(new CPelicula(256, "Esta es de Kitano", "Humor", 3.00f, new GregorianCalendar(1956, 01, 11, 10, 25, 51), "Kitano", "Grito mucho en japonés", 3, 00));
            listaPeliculas.add(new CPelicula(12, "Esta es de Bergman", "Rara", 8.40f, new GregorianCalendar(1946, 01, 11, 10, 25, 51), "Bergman", "No entiendo ná", 2, 35));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Creamos Iterador para órden alfabético e iteramos por consola los resultados
        Iterator<CPelicula> itPeli = listaPeliculas.iterator();

        System.out.println("ITERATOR ALFABÉTICO \n \n \n");

        while (itPeli.hasNext()) {
            System.out.println("Título película: " + itPeli.next().getTitulo());
        }

        //Repetimos el último proceso iterando al sentido contrario
        Iterator<CPelicula> itPeliReves = listaPeliculas.descendingIterator();

        System.out.println(" \n  \n  \nITERATOR AL REVÉS \n \n \n");

        while (itPeliReves.hasNext()) {
            System.out.println("Título película: " + itPeliReves.next().getTitulo());
        }

        //Añadimos dos nuevas películas y las mostramos por consola
        try {
            listaPeliculas.add(new CPelicula(24, "Esta es de Almodóvar", "Drama", 5.25f, new GregorianCalendar(2009, 01, 11, 10, 25, 51), "Almodóvar", "Mucho grito", 2, 20));
            listaPeliculas.add(new CPelicula(10, "Esta es de Bay", "Explosiones", 5.25f, new GregorianCalendar(2009, 01, 11, 10, 25, 51), "Bay", "Efectos cucos", 2, 20));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Iterator<CPelicula> itPeli2 = listaPeliculas.iterator();

        System.out.println("\n \n \nAQUI HEMOS AÑADIDO DOS PELICULAS MÁS \n \n");

        while (itPeli2.hasNext()) {
            System.out.println("Título película: " + itPeli2.next().getDirector());
        }

// Creamos tres ArrayList de Peliculas para cada cliente -> Peliculas escogidas de manera estática.
        ArrayList<CPelicula> lp1 = new ArrayList<CPelicula>();
        ArrayList<CPelicula> lp2 = new ArrayList<CPelicula>();
        ArrayList<CPelicula> lp3 = new ArrayList<CPelicula>();
        try {
            lp1.add(new CPelicula(1, "Esta es de Bay", "Explosiones", 5.25f, new GregorianCalendar(2009, 01, 11, 10, 25, 51), "Bay", "Efectos cucos", 2, 20));
            lp1.add(new CPelicula(3, "Esta es de Polanski", "Drama", 6.25f, new GregorianCalendar(1998, 01, 11, 10, 25, 51), "Polanski", "Soy un viejo", 1, 10));
            lp1.add(new CPelicula(12, "Esta es de Bergman", "Rara", 8.40f, new GregorianCalendar(1946, 01, 11, 10, 25, 51), "Bergman", "No entiendo ná", 2, 35));

            lp2.add(new CPelicula(1, "Esta es de Bay", "Explosiones", 5.25f, new GregorianCalendar(2009, 01, 11, 10, 25, 51), "Bay", "Efectos cucos", 2, 20));
            lp2.add(new CPelicula(256, "Esta es de Kitano", "Humor", 3.00f, new GregorianCalendar(1956, 01, 11, 10, 25, 51), "Kitano", "Grito mucho en japonés", 3, 00));

            lp3.add(new CPelicula(3, "Esta es de Polanski", "Drama", 6.25f, new GregorianCalendar(1998, 01, 11, 10, 25, 51), "Polanski", "Soy un viejo", 1, 10));
            lp3.add(new CPelicula(10, "Esta es de Almodóvar", "Drama", 5.25f, new GregorianCalendar(2009, 01, 11, 10, 25, 51), "Almodóvar", "Mucho grito", 2, 20));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Creamos 3 clientes y los añadimos a un TreeMap
        CCliente c1 = new CCliente("Pepe", "Perez", "11234", 2, lp1);
        CCliente c2 = new CCliente("Carlos", "Guzmán", "22124", 3, lp2);
        CCliente c3 = new CCliente("Eustiquio", "Martínez", "66124", 4, lp3);

        TreeMap<String, CCliente> listaClientes = new TreeMap<>();
        try {

            listaClientes.put(c1.getDni(), c1);
            listaClientes.put(c2.getDni(), c2);
            listaClientes.put(c3.getDni(), c3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Creamos el archivo clientes.dat y guardamos ahí nuestra lista de clientes
        File file = new File("clientes.dat");

        try {
            FileOutputStream streamF = new FileOutputStream(file);
            ObjectOutputStream output = new ObjectOutputStream(streamF);
            Set st = listaClientes.keySet();
            Iterator itCliente = st.iterator();
//Por cada cliente que encuentre en el iterador agregará una entrada al fichero
            while (itCliente.hasNext()) {
                output.writeObject(listaClientes.get(itCliente.next()));
            }

            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//Leemos el archivo para comprobarlo
        try {
            FileInputStream streamI = new FileInputStream(file);
            ObjectInputStream input = new ObjectInputStream(streamI);
            ArrayList<CCliente> listaVuelta = new ArrayList<CCliente>();
            boolean res = true;

//Mientras res sea true, casteamos la lectura del fichero en un CCliente, y si este no es nulo, lo añadimos a la lista
//Cuando c sea nulo, res pasa a ser false y salimos del bucle
            while (res) {
                try {
                    CCliente c = (CCliente) input.readObject();
                    listaVuelta.add(c);

                } catch (Exception e) {
                    for (int i = 0; i < listaVuelta.size(); i++) {
                        System.out.println(listaVuelta.get(i).toString());
                    }
                    input.close();
                    res = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
