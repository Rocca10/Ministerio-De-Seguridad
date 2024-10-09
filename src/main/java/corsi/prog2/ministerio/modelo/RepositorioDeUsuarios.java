package corsi.prog2.ministerio.modelo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDeUsuarios extends JpaRepository<Usuario, String> {

    public List<Usuario> findByCodigoAndPassword(String codigo, String password);

    public List<Usuario> findByRol(String rol);
}
