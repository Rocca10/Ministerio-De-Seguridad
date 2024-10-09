package corsi.prog2.ministerio.modelo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositorioDeContratos extends JpaRepository<Contrato, Long> {
}