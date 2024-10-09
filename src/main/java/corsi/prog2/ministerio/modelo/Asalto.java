package corsi.prog2.ministerio.modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="asaltos")
public class Asalto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, insertable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "detenido_id", nullable = false)
    private Detenido detenido;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaAsalto;

    @ManyToOne
    @JoinColumn(name = "juez_id", nullable = false)
    private Juez juez;

    @Column(nullable = false)
    private boolean condenado;

    @Column(nullable = true)
    private Integer tiempoCarcel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Detenido getDetenido() {
        return detenido;
    }

    public void setDetenido(Detenido detenido) {
        this.detenido = detenido;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Date getFechaAsalto() {
        return fechaAsalto;
    }

    public void setFechaAsalto(Date fechaAsalto) {
        this.fechaAsalto = fechaAsalto;
    }

    public Juez getJuez() {
        return juez;
    }

    public void setJuez(Juez juez) {
        this.juez = juez;
    }

    public boolean isCondenado() {
        return condenado;
    }

    public void setCondenado(boolean condenado) {
        this.condenado = condenado;
    }

    public Integer getTiempoCarcel() {
        return tiempoCarcel;
    }

    public void setTiempoCarcel(Integer tiempoCarcel) {
        this.tiempoCarcel = tiempoCarcel;
    }
}
