package corsi.prog2.ministerio.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Admin")
@DiscriminatorValue("Admin")
public class Admin extends Usuario {

    public Admin() {
    }

    public Admin(String codigo, String nombre, String password) {
        super(codigo, nombre, password);
    }

}
