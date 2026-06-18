package com.redsolidaria.enjambre.repository;

import com.redsolidaria.enjambre.model.Sancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SancionRepository extends JpaRepository<Sancion, Long> {
    List<Sancion> findByUsuario_Id(Long usuarioId);
}
