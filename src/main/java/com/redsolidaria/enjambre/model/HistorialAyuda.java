package com.redsolidaria.enjambre.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

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

    @OneToMany(mappedBy = "historialAyuda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Incidencia> incidencias = new ArrayList<>();

    public HistorialAyuda(SolicitudAyuda solicitud) {
        this.solicitud = solicitud;
        this.fechaFinalizacion = LocalDateTime.now();
        this.calificacion = "SIN_CALIFICAR";
    }
}
