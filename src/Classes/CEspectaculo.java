package Classes;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author M.H. Guitarte <guimhur@gmail.com>
 */
public abstract class CEspectaculo implements Serializable{

    protected int codigo;
    protected String titulo, tipo;
    protected float precio;
    protected Calendar fechaEstreno;

    public CEspectaculo() {
    }

    public CEspectaculo(int codigo, String titulo, String tipo, float precio, Calendar fechaEstreno) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.tipo = tipo;
        this.precio = precio;
        this.fechaEstreno = fechaEstreno;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Calendar getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(Calendar fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

}
