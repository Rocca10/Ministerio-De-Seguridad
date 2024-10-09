package corsi.prog2.ministerio.modelo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDeJueces extends JpaRepository<Juez, Long> {
}
