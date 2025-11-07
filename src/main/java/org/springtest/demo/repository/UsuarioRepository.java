package org.springtest.demo.repository;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springtest.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Procedure(procedureName = "PKG_USUARIOS.ACTUALIZAR_DESCRIPCION")
    void actualizarDescripcionUsuario(
            @Param("p_id") Long id,
            @Param("p_descripcion") String descripcion
    );
}
