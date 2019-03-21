package Classes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author M.H. Guitarte <guimhur@gmail.com>
 */
public class CPelicula extends CEspectaculo implements Comparator<CPelicula>, Comparable<CPelicula>, Serializable {

    private String director, sinopsis;
    private int hora, min;

    public CPelicula() {
    }

    public CPelicula(String director, String sinopsis, int hora, int min) {
        this.director = director;
        this.sinopsis = sinopsis;
        this.hora = hora;
        this.min = min;
    }

    public CPelicula(int codigo, String titulo, String tipo, float precio, Calendar fechaEstreno, String director, String sinopsis, int hora, int min) {
        super(codigo, titulo, tipo, precio, fechaEstreno);
        this.director = director;
        this.sinopsis = sinopsis;
        this.hora = hora;
        this.min = min;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.director);
        hash = 97 * hash + Objects.hashCode(this.sinopsis);
        hash = 97 * hash + this.hora;
        hash = 97 * hash + this.min;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final CPelicula other = (CPelicula) obj;
            if (this.hora != other.hora) {
                return false;
            }
            if (this.min != other.min) {
                return false;
            }
            if (!Objects.equals(this.director, other.director)) {
                return false;
            }
            if (!Objects.equals(this.sinopsis, other.sinopsis)) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }

    @Override
    public int compareTo(CPelicula o) {
        try {
            return this.titulo.compareTo(o.titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    @Override
    public int compare(CPelicula o1, CPelicula o2) {
        try {
            return o1.getCodigo() - o2.getCodigo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

}
