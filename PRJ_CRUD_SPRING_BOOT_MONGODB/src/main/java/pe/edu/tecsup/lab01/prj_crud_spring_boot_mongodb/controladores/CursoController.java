package pe.edu.tecsup.lab01.prj_crud_spring_boot_mongodb.controladores;

import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.tecsup.lab01.prj_crud_spring_boot_mongodb.modelo.documents.Curso;
import pe.edu.tecsup.lab01.prj_crud_spring_boot_mongodb.servicios.CursoService;

@Controller
@SessionAttributes("curso")
public class CursoController {

    @Autowired
    private CursoService servicio;

    // --- LISTAR ---
    @GetMapping({"/", "/listar"})
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Cursos CJAVA");
        model.addAttribute("cursos", servicio.listar());
        return "listarView";
    }

    // --- CREAR ---
    @GetMapping("/form")
    public String crear(Map<String, Object> model) {
        Curso curso = new Curso();
        model.put("curso", curso);
        model.put("titulo", "Formulario de Curso");
        return "formView";
    }

    // --- EDITAR ---
    @GetMapping("/form/{id}")
    public String editar(@PathVariable("id") String id, Map<String, Object> model) {
        Curso curso = servicio.buscar(id);
        if (curso == null) {
            return "redirect:/listar";
        }
        model.put("curso", curso);
        model.put("titulo", "Editar Curso");
        return "formView";
    }

    // --- GUARDAR ---
    @PostMapping("/form")
    public String guardar(@Valid @ModelAttribute("curso") Curso curso,
                          BindingResult result,
                          Model model,
                          SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Curso");
            return "formView";
        }

        servicio.grabar(curso);
        status.setComplete();
        return "redirect:/listar";
    }

    // --- ELIMINAR ---
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") String id) {
        Curso curso = servicio.buscar(id);
        if (curso != null) {
            servicio.eliminar(id);
        }
        return "redirect:/listar";
    }
}
