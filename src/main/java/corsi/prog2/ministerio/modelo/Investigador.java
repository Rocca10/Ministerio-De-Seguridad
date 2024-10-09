package corsi.prog2.ministerio.modelo;

import javax.persistence.Entity;

@Entity
public class Investigador extends Usuario {

    public Investigador() {
        super();
    }

    public Investigador(String codigo, String nombre, String password) {
        super(codigo, nombre, password);
    }

}
