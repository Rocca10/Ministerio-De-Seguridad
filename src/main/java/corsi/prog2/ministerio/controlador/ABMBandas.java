package corsi.prog2.ministerio.controlador;

import corsi.prog2.ministerio.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ABMBandas {

    @Autowired
    private RepositorioDeBandas rb;

    @Autowired
    private RepositorioDeUsuarios ru;

    @GetMapping("/bandas")
    public String listarBandas(Model model) {
        model.addAttribute("bandas", rb.findAll());
        return "bandas";
    }

    @PostMapping("/nueva-banda")
    public String nuevaBanda(final RedirectAttributes redirectAttributes,
                             @RequestParam(name = "numeroDeBanda") String numeroDeBanda,
                             @RequestParam(name = "cantMiembros") String cantMiembros,
                             @RequestParam(name = "admin") String admin) {
        Banda banda = new Banda();
        banda.setNumeroDeBanda(numeroDeBanda);
        banda.setCantMiembros(cantMiembros);

        rb.saveAndFlush(banda);

        Usuario usuario = ru.findById(admin).orElse(null);
        redirectAttributes.addFlashAttribute("usuario", usuario);

        return "redirect:/login#bandas";
    }

    @PostMapping("/editar-borrar-banda")
    public String editarBorrarBanda(final RedirectAttributes redirectAttributes,
                                    @RequestParam(name = "id") Long id,
                                    @RequestParam(name = "numeroDeBanda") String numeroDeBanda,
                                    @RequestParam(name = "cantMiembros") String cantMiembros,
                                    @RequestParam(name = "accion") String accion,
                                    @RequestParam(name = "admin") String admin) {
        if (accion.equals("Borrar")) {
            rb.deleteById(id);
        } else if (accion.equals("Editar")) {
            Banda editado = rb.findById(id).orElse(null);
            if (editado != null) {
                editado.setNumeroDeBanda(numeroDeBanda);
                editado.setCantMiembros(cantMiembros);
                rb.saveAndFlush(editado);
            }
        }

        Usuario usuario = ru.findById(admin).orElse(null);
        redirectAttributes.addFlashAttribute("usuario", usuario);

        return "redirect:/login#bandas";
    }

}
