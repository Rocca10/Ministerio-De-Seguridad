package corsi.prog2.ministerio.modelo;

import org.springframework.data.jpa.repository.JpaRepository;
import corsi.prog2.ministerio.modelo.Sucursal;
import java.util.List;

public interface RepositorioDeSucursales extends JpaRepository<Sucursal, Long> {
    List<Sucursal> findByEntidadId(Long entidadId);
}
