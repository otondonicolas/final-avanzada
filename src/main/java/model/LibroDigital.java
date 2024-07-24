package model;

import javax.persistence.Entity;

@Entity
public class LibroDigital extends Libro {

    public LibroDigital(String titulo) {
        super(null, titulo);
    }

    public LibroDigital() {

    }

    public LibroDigital(Integer id, String titulo) {
        super(id, titulo);
    }

    @Override
    public void prestar() {
        System.out.println("El libro digital " + getTitulo() + " ha sido prestado.");
    }

    @Override
    public String toString(){
        return getTitulo();
    }
}