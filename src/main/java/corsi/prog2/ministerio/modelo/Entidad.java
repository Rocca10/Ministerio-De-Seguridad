    package corsi.prog2.ministerio.modelo;

    import javax.persistence.*;
    import java.io.Serializable;
    import java.util.HashSet;
    import java.util.Set;

    @Entity
    @Table(name = "entidades")
    public class Entidad implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false, unique = true, insertable = false, updatable = false)
        private Long id;

        @Column(nullable = false, unique = true)
        private String codigo;

        @Column(nullable = false)
        private String domicilio;

        @OneToMany(mappedBy = "entidad", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private Set<Sucursal> sucursales = new HashSet<>();



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

        public void setDomicilio(String domicilio) {
            this.domicilio = domicilio;
        }

        public Set<Sucursal> getSucursales() {
            return sucursales;
        }

        public void setSucursales(Set<Sucursal> sucursales) {
            this.sucursales = sucursales;
        }
    }
