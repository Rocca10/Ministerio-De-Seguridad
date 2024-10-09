package corsi.prog2.ministerio.controlador;

import corsi.prog2.ministerio.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class ABMDetenidos {

    @Autowired
    private RepositorioDeDetenidos rd;

    @Autowired
    private RepositorioDeBandas rb;

    @Autowired
    private RepositorioDeUsuarios ru;

    @GetMapping("/detenidos")
    public String listarDetenidos(Model model) {
        model.addAttribute("detenidos", rd.findAll());
        model.addAttribute("bandas", rb.findAll());
        return "detenidos";
    }

    @PostMapping("/nuevo-detenido")
    public String nuevoDetenido(final RedirectAttributes redirectAttributes,
            @RequestParam String codigo,
            @RequestParam String nombre,
            @RequestParam(required = false) Long bandaId,
            @RequestParam(name = "admin") String admin){
        Detenido detenido = new Detenido();
        detenido.setCodigo(codigo);
        detenido.setNombre(nombre);

        // Si quiero seleccionar una banda
        if (bandaId != null) {
            Optional<Banda> banda = rb.findById(bandaId);
            banda.ifPresent(detenido::setBanda);
        } else {
            detenido.setBanda(null);  // No pertenece a ninguna banda
        }

        rd.saveAndFlush(detenido);

        Usuario usuario = ru.findById(admin).orElse(null);  // Obtener el usuario admin
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#detenidos";
    }

    @PostMapping("/editar-borrar-detenido")
    public String editarOBorrarDetenido(final RedirectAttributes redirectAttributes,
            @RequestParam Long id,
            @RequestParam String accion,
            @RequestParam String codigo,
            @RequestParam String nombre,
            @RequestParam(required = false) Long bandaId,
            @RequestParam(name = "admin") String admin) {
        if (accion.equals("Borrar")) {
            rd.deleteById(id);
        } else {
            Detenido detenido = rd.findById(id).orElse(null);
            if (detenido != null) {
                detenido.setCodigo(codigo);
                detenido.setNombre(nombre);

                // Si se seleccionó una banda
                if (bandaId != null) {
                    Optional<Banda> banda = rb.findById(bandaId);
                    banda.ifPresent(detenido::setBanda);
                } else {
                    detenido.setBanda(null);
                }


                rd.saveAndFlush(detenido);
            }
        }
        Usuario usuario = ru.findById(admin).orElse(null);
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#detenidos";
    }
}
