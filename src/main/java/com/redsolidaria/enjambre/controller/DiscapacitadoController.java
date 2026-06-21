package com.redsolidaria.enjambre.controller;

import com.redsolidaria.enjambre.model.HistorialAyuda;
import com.redsolidaria.enjambre.model.Incidencia;
import com.redsolidaria.enjambre.model.Usuario;
import com.redsolidaria.enjambre.repository.HistorialAyudaRepository;
import com.redsolidaria.enjambre.repository.IncidenciaRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/discapacitado")
public class DiscapacitadoController {

    @Autowired
    private HistorialAyudaRepository historialAyudaRepository;

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    // Página principal del discapacitado (donde redirige después del login)
    @GetMapping("/inicio")
    public String inicio() {
        return "Users/Disca/botonAyuda";
    }
    
    @GetMapping("/ayuda")
    public String ayuda() {
        return "Users/Disca/botonAyuda";
    }
    
    @GetMapping("/donaciones")
    public String donaciones() {
        return "Users/Disca/donacionesDis";
    }
    
    @GetMapping("/foro")
    public String foro() {
        return "Users/Disca/foroDis";
    }
    
    @GetMapping("/historial")
    public String historial(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            List<HistorialAyuda> historiales =
                historialAyudaRepository.findBySolicitud_Discapacitado_IdOrderByFechaFinalizacionDesc(usuario.getId());
            model.addAttribute("historial", historiales);

            // Para cada historial, buscar si el usuario ya reportó una incidencia.
            // Guardamos: historialId -> incidencia (o null si no reportó).
            // Así el template evita acceder a colecciones LAZY.
            Map<Long, Incidencia> miIncidenciaMap = new HashMap<>();
            for (HistorialAyuda h : historiales) {
                // Buscar incidencia donde el denunciante es el usuario actual
                List<Incidencia> encontradas = incidenciaRepository
                    .findByHistorialAyuda_IdAndDenunciante_Id(h.getId(), usuario.getId());
                miIncidenciaMap.put(h.getId(), encontradas.isEmpty() ? null : encontradas.get(0));
            }
            model.addAttribute("miIncidenciaMap", miIncidenciaMap);
        }
        return "Users/Disca/historialAyuda";
    }
}