package corsi.prog2.ministerio.controlador;

import corsi.prog2.ministerio.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ABMAsaltos {

    @Autowired
    private RepositorioDeAsaltos ra;

    @Autowired
    private RepositorioDeDetenidos rd;

    @Autowired
    private RepositorioDeSucursales rs;

    @Autowired
    private RepositorioDeJueces rj;

    @Autowired
    private RepositorioDeUsuarios ru;

    @GetMapping("/asaltos")
    public String listarAsaltos(Model model) {
        List<Asalto> asaltos = ra.findAll();
        List<Detenido> detenidos = rd.findAll();
        List<Sucursal> sucursales = rs.findAll();
        List<Juez> jueces = rj.findAll();

        model.addAttribute("asaltos", asaltos);
        model.addAttribute("detenidos", detenidos);
        model.addAttribute("sucursales", sucursales);
        model.addAttribute("jueces", jueces);
        return "asaltos";  // Nombre de la vista HTML (asaltos.html)
    }

    @PostMapping("/nuevo-asalto")
    public String nuevoAsalto(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "detenidoId") Long detenidoId,
            @RequestParam(name = "sucursalId") Long sucursalId,
            @RequestParam(name = "fechaAsalto") String fechaAsalto,
            @RequestParam(name = "juezId") Long juezId,
            @RequestParam(name = "condenado", required = false) Boolean condenado,
            @RequestParam(name = "tiempoCarcel", required = false) Integer tiempoCarcel,
            @RequestParam(name = "admin") String admin){

        Detenido detenido = rd.findById(detenidoId).orElse(null);
        Sucursal sucursal = rs.findById(sucursalId).orElse(null);
        Juez juez = rj.findById(juezId).orElse(null);

        if (detenido != null && sucursal != null && juez != null) {
            Asalto asalto = new Asalto();
            asalto.setDetenido(detenido);
            asalto.setSucursal(sucursal);
            asalto.setFechaAsalto(java.sql.Date.valueOf(fechaAsalto));
            asalto.setJuez(juez);
            asalto.setCondenado(condenado != null ? condenado : false);
            asalto.setTiempoCarcel(tiempoCarcel != null ? tiempoCarcel : 0);

            ra.saveAndFlush(asalto);
        }

        Usuario usuario = ru.findById(admin).orElse(null);
        redirectAttributes.addFlashAttribute("usuario", usuario);

        return "redirect:/login#asaltos";
    }

    @PostMapping("/editar-borrar-asalto")
    public String editarBorrarAsalto(final RedirectAttributes redirectAttributes,
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "detenidoId") Long detenidoId,
            @RequestParam(name = "sucursalId") Long sucursalId,
            @RequestParam(name = "fechaAsalto") String fechaAsalto,
            @RequestParam(name = "juezId") Long juezId,
            @RequestParam(name = "condenado", required = false) Boolean condenado,
            @RequestParam(name = "tiempoCarcel", required = false) Integer tiempoCarcel,
            @RequestParam(name = "accion") String accion,
            @RequestParam(name = "admin") String admin){

        if (accion.equals("Borrar")) {
            ra.deleteById(id);
        } else if (accion.equals("Editar")) {
            Asalto asalto = ra.findById(id).orElse(null);
            Detenido detenido = rd.findById(detenidoId).orElse(null);
            Sucursal sucursal = rs.findById(sucursalId).orElse(null);
            Juez juez = rj.findById(juezId).orElse(null);

            if (asalto != null && detenido != null && sucursal != null && juez != null) {
                asalto.setDetenido(detenido);
                asalto.setSucursal(sucursal);
                asalto.setFechaAsalto(java.sql.Date.valueOf(fechaAsalto));
                asalto.setJuez(juez);
                asalto.setCondenado(condenado != null ? condenado : false);
                asalto.setTiempoCarcel(tiempoCarcel != null ? tiempoCarcel : 0);

                ra.saveAndFlush(asalto);
            }
        }

        Usuario usuario = ru.findById(admin).orElse(null);
        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/login#asaltos";
    }
}
