package corsi.prog2.ministerio.modelo;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String domicilio;
    private String cantidadEmpleados;

    @ManyToOne
    @JoinColumn(name = "entidad_id")
    private Entidad entidad;

//    @ManyToMany
//    @JoinTable(
//            name = "sucursal_vigilante",
//            joinColumns = @JoinColumn(name = "sucursal_id"),
//            inverseJoinColumns = @JoinColumn(name = "vigilante_id")
//    )
//    private Set<Vigilante> vigilantes;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String direccion) {
        this.domicilio = direccion;
    }


    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

//    public Set<Vigilante> getVigilantes() {
//        return vigilantes;
//    }
//
//    public void setVigilantes(Set<Vigilante> vigilantes) {
//        this.vigilantes = vigilantes;
//    }

    public String getCantidadEmpleados() {
        return cantidadEmpleados;
    }

    public void setCantidadEmpleados(String cantidadEmpleados) {
        this.cantidadEmpleados = cantidadEmpleados;
    }
}
