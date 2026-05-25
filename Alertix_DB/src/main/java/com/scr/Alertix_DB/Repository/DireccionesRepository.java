package com.scr.Alertix_DB.Repository;

import com.scr.Alertix_DB.Model.Direcciones;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionesRepository extends JpaRepository<Direcciones, Long> {
    @Transactional
    @Query(value = "CALL registrarDirecciones(:barrio,:direccion,:pais,:ciudad,:departamento,:municipio,:codigoPostal,:latitud,:longitud)",
            nativeQuery = true)
    Integer registrarDirecciones(@Param("barrio") String barrio,
                                 @Param("direccion") String direccion,
                                 @Param("pais") String pais,
                                 @Param("ciudad") String ciudad,
                                 @Param("departamento") String departamento,
                                 @Param("municipio") String municipio,
                                 @Param("codigoPostal") String codigoPostal,
                                 @Param("latitud") double latitud,@Param("longitud")  double longitud);
}
