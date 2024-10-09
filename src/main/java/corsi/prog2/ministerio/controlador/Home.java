package corsi.prog2.ministerio.controlador;

import corsi.prog2.ministerio.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class Home {

    @Autowired
    private RepositorioDeUsuarios ru;

    @Autowired
    private RepositorioDeSucursales rs;

    @Autowired
    private RepositorioDeEntidades re;

    @Autowired
    private RepositorioDeContratos rc;

    @Autowired
    private RepositorioDeDetenidos rd;

    @Autowired
    private RepositorioDeBandas rb;

    @Autowired
    private RepositorioDeAsaltos ra;

    @Autowired
    private RepositorioDeJueces rj;

    private String login(Model model, Usuario usuario) {
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("usuarios", ru.findAll());
            model.addAttribute("sucursales", rs.findAll());
            model.addAttribute("entidades", re.findAll());
            model.addAttribute("contratos", rc.findAll());
            model.addAttribute("detenidos",rd.findAll());
            model.addAttribute("bandas",rb.findAll());
            model.addAttribute("asaltos",ra.findAll());
            model.addAttribute("jueces",rj.findAll());
            return usuario.getRol().toLowerCase();
        }
        model.addAttribute("error", "No se encontró Código/Password");
        return "index";
    }

    @PostMapping("/login")
    public String home(Model model,
                       @RequestParam(name = "codigo", required = true) String codigo,
                       @RequestParam(name = "password", required = true) String password) {
        Usuario usuario = null;
        List<Usuario> match = ru.findByCodigoAndPassword(codigo, password);
        if (!match.isEmpty()) {
            usuario = match.get(0);
        }
        return login(model, usuario);
    }

    @GetMapping("/login")
    public String home(Model model) {
        return login(model, (Usuario) model.getAttribute("usuario"));
    }


    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @GetMapping("/resumen/{codigo}")
    public String resumen(final RedirectAttributes redirectAttributes,
                          @PathVariable(value = "codigo") String adminId) {
        Resumen r = null;
        redirectAttributes.addFlashAttribute("resumen", r);
        redirectAttributes.addFlashAttribute("usuario", ru.findById(adminId).get());
        return "redirect:/login#";
    }



}
