package corsi.prog2.ministerio.modelo;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("Vigilante")
public class Vigilante extends Usuario {

//    @ManyToMany(mappedBy = "vigilantes")
//    private Set<Sucursal> sucursales;

    public Vigilante() {
        super();
    }

    public Vigilante(String codigo, String nombre, String password) {
        super(codigo, nombre, password);
    }

    // Getters y setters para sucursales
//    public Set<Sucursal> getSucursales() {
//        return sucursales;
//    }
//
//    public void setSucursales(Set<Sucursal> sucursales) {
//        this.sucursales = sucursales;
//    }
}
