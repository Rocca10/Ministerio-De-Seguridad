package corsi.prog2.ministerio.modelo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bandas")
public class Banda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, insertable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroDeBanda;

    @Column(nullable = false, unique = false)
    private String cantMiembros;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDeBanda() {
        return numeroDeBanda;
    }

    public void setNumeroDeBanda(String numeroDeBanda) {
        this.numeroDeBanda = numeroDeBanda;
    }

    public String getCantMiembros() {
        return cantMiembros;
    }

    public void setCantMiembros(String numeroDeMiembros) {
        this.cantMiembros = numeroDeMiembros;
    }
}
