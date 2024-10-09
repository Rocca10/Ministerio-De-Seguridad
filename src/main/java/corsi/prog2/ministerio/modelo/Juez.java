package corsi.prog2.ministerio.modelo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "jueces")
public class Juez implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, insertable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String claveJuzgado; // Clave interna del juzgado

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer aniosServicio;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaveJuzgado() {
        return claveJuzgado;
    }

    public void setClaveJuzgado(String claveJuzgado) {
        this.claveJuzgado = claveJuzgado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAniosServicio() {
        return aniosServicio;
    }

    public void setAniosServicio(Integer aniosServicio) {
        this.aniosServicio = aniosServicio;
    }
}
