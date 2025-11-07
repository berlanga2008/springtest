package org.springtest.demo.repository;

import org.springtest.demo.dto.UsuarioDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Struct;

@Repository
public class UsuarioObjRepository {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioObjRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void actualizarUsuarioObj(UsuarioDto usuario) {
        jdbcTemplate.execute((Connection con) -> {
            try (var call = con.prepareCall("{ call PKG_OBJ_USUARIOS.ACTUALIZAR_USUARIO_OBJ(?) }")) {

                // ⚙️ Construir el objeto STRUCT con los atributos en el orden del tipo Oracle
                Object[] atributos = new Object[]{
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getEmail(),
                        usuario.getDescripcion()
                };

                // Crear STRUCT con el nombre del tipo Oracle (asegúrate de usar el schema correcto)
                Struct objUsuario = con.createStruct("OBJ_USUARIO", atributos);

                call.setObject(1, objUsuario);
                call.execute();
            }
            return null;
        });
    }
}
