package corsi.prog2.ministerio.controlador;

import corsi.prog2.ministerio.modelo.Juez;
import corsi.prog2.ministerio.modelo.RepositorioDeJueces;
import corsi.prog2.ministerio.modelo.RepositorioDeUsuarios;
import corsi.prog2.ministerio.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ABMJueces {

    @Autowired
    private RepositorioDeJueces rj;

    @Autowired
    private RepositorioDeUsuarios ru;

    @GetMapping("/jueces")
    public String listarJueces(Model model) {
        model.addAttribute("jueces", rj.findAll());
        return "jueces";
    }

    @PostMapping("/nuevo-juez")
    public String nuevoJuez(final RedirectAttributes redirectAttributes,
                            @RequestParam(name = "claveJuzgado") String claveJuzgado,
                            @RequestParam(name = "nombre") String nombre,
                            @RequestParam(name = "aniosServicio") Integer aniosServicio,
                            @RequestParam(name = "admin") String admin) {

        Juez juez = new Juez();
        juez.setClaveJuzgado(claveJuzgado);
        juez.setNombre(nombre);
        juez.setAniosServicio(aniosServicio);

        rj.saveAndFlush(juez);

        Usuario usuario = ru.findById(admin).orElse(null);
        redirectAttributes.addFlashAttribute("usuario", usuario);

        return "redirect:/login#jueces";
    }

    @PostMapping("/editar-borrar-juez")
    public String editarBorrarJuez(final RedirectAttributes redirectAttributes,
                                   @RequestParam(name = "id") Long id,
                                   @RequestParam(name = "claveJuzgado") String claveJuzgado,
                                   @RequestParam(name = "nombre") String nombre,
                                   @RequestParam(name = "aniosServicio") Integer aniosServicio,
                                   @RequestParam(name = "accion") String accion,
                                   @RequestParam(name = "admin") String admin) {
        if (accion.equals("Borrar")) {
            rj.deleteById(id);
        } else if (accion.equals("Editar")) {
            Juez editado = rj.findById(id).orElse(null);
            if (editado != null) {
                editado.setClaveJuzgado(claveJuzgado);
                editado.setNombre(nombre);
                editado.setAniosServicio(aniosServicio);
                rj.saveAndFlush(editado);
            }
        }

        Usuario usuario = ru.findById(admin).orElse(null);
        redirectAttributes.addFlashAttribute("usuario", usuario);

        return "redirect:/login#jueces";
    }
}
