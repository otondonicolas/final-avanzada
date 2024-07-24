package model;

import javax.persistence.Entity;

@Entity
public class LibroFisico extends Libro {

    public LibroFisico(String titulo) {
        super(null, titulo);
    }

    public LibroFisico() {

    }

    public LibroFisico(Integer id, String titulo) {
        super(id, titulo);
    }

    @Override
    public void prestar() {
        System.out.println("El libro físico " + getTitulo() + " ha sido prestado.");
    }

    @Override
    public String toString(){
        return getTitulo();
    }
}