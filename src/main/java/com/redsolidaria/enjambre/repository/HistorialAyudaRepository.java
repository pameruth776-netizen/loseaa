package com.redsolidaria.enjambre.repository;

import com.redsolidaria.enjambre.model.HistorialAyuda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface HistorialAyudaRepository extends JpaRepository<HistorialAyuda, Long> {
    
    List<HistorialAyuda> findBySolicitud_Discapacitado_IdOrderByFechaFinalizacionDesc(Long discapacitadoId);
    
    List<HistorialAyuda> findBySolicitud_VoluntarioAceptado_IdOrderByFechaFinalizacionDesc(Long voluntarioId);

    long countBySolicitud_VoluntarioAceptado_Id(Long voluntarioId);

    @Query("SELECT h FROM HistorialAyuda h WHERE (h.incidenciaDiscapacitado IS NOT NULL AND h.incidenciaDiscapacitado != '') OR (h.incidenciaVoluntario IS NOT NULL AND h.incidenciaVoluntario != '') ORDER BY h.fechaFinalizacion DESC")
    List<HistorialAyuda> findIncidenciasReportadas();
}
