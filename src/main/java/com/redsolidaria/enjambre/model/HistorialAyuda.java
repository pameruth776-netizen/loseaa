package com.redsolidaria.enjambre.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "historial_ayudas")
public class HistorialAyuda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "solicitud_id", nullable = false, unique = true)
    private SolicitudAyuda solicitud;

    @Column(name = "fecha_finalizacion", nullable = false)
    private LocalDateTime fechaFinalizacion = LocalDateTime.now();

    @Column(name = "calificacion", nullable = false)
    private String calificacion = "SIN_CALIFICAR";

    @Column(name = "comentario_discapacitado", columnDefinition = "TEXT")
    private String comentarioDiscapacitado;

    @Column(name = "comentario_voluntario", columnDefinition = "TEXT")
    private String comentarioVoluntario;

    @Column(name = "incidencia_discapacitado", columnDefinition = "TEXT")
    private String incidenciaDiscapacitado;

    @Column(name = "evidencia_discapacitado_url")
    private String evidenciaDiscapacitadoUrl;

    @Column(name = "incidencia_voluntario", columnDefinition = "TEXT")
    private String incidenciaVoluntario;

    @Column(name = "evidencia_voluntario_url")
    private String evidenciaVoluntarioUrl;

    public HistorialAyuda(SolicitudAyuda solicitud) {
        this.solicitud = solicitud;
        this.fechaFinalizacion = LocalDateTime.now();
        this.calificacion = "SIN_CALIFICAR";
    }
}
