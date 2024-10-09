    package corsi.prog2.ministerio.controlador;

    import corsi.prog2.ministerio.modelo.Entidad;
    import corsi.prog2.ministerio.modelo.RepositorioDeEntidades;
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
    public class ABMEntidades {

        @Autowired
        private RepositorioDeEntidades re;

        @Autowired
        private RepositorioDeUsuarios ru;

        @GetMapping("/entidades")
        public String listarEntidades(Model model) {
            model.addAttribute("entidades", re.findAll());
            return "entidades";
        }

        @PostMapping("/nueva-entidad")
        public String nuevaEntidad(final RedirectAttributes redirectAttributes,
                                   @RequestParam(name = "codigo") String codigo,
                                   @RequestParam(name = "domicilio") String domicilio,
                                   @RequestParam(name = "admin") String admin) {

            Entidad entidad = new Entidad();
            entidad.setCodigo(codigo);
            entidad.setDomicilio(domicilio);

            re.saveAndFlush(entidad);

            Usuario usuario = ru.findById(admin).orElse(null);  // Obtener el usuario admin
            redirectAttributes.addFlashAttribute("usuario", usuario);


            return "redirect:/login#entidades";
        }

        @PostMapping("/editar-borrar-entidad")
        public String editarBorrarEntidad(final RedirectAttributes redirectAttributes,
                                          @RequestParam(name = "id") Long id,
                                          @RequestParam(name = "codigo") String codigo,
                                          @RequestParam(name = "domicilio") String domicilio,
                                          @RequestParam(name = "accion") String accion,
                                          @RequestParam(name = "admin") String admin) {
            if (accion.equals("Borrar")) {
                re.deleteById(id);
            } else if (accion.equals("Editar")) {
                Entidad editado = re.findById(id).orElse(null);
                if (editado != null) {
                    editado.setCodigo(codigo);
                    editado.setDomicilio(domicilio);
                    re.saveAndFlush(editado);
                }
            }

            Usuario usuario = ru.findById(admin).orElse(null);
            redirectAttributes.addFlashAttribute("usuario", usuario);

            return "redirect:/login#entidades";
        }
    }
