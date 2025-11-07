CREATE OR REPLACE PACKAGE PKG_USUARIOS AS

    -- Procedimiento: Actualiza la descripción de un usuario por ID
    PROCEDURE ACTUALIZAR_DESCRIPCION (
        p_id IN USERS.ID%TYPE,
        p_descripcion IN USERS.DESCRIPCION%TYPE
    );

END PKG_USUARIOS;
/

CREATE OR REPLACE PACKAGE BODY PKG_USUARIOS AS

    PROCEDURE ACTUALIZAR_DESCRIPCION (
        p_id IN USERS.ID%TYPE,
        p_descripcion IN USERS.DESCRIPCION%TYPE
    )
    AS
BEGIN
UPDATE USERS
SET DESCRIPCION = p_descripcion
WHERE ID = p_id;

IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20001, 'Usuario no encontrado con ID: ' || p_id);
END IF;

COMMIT;
END ACTUALIZAR_DESCRIPCION;

END PKG_USUARIOS;
/
/*
BEGIN
    PKG_USUARIOS.ACTUALIZAR_DESCRIPCION(
        p_id => 1,
        p_descripcion => 'Descripción actualizada desde Package'
    );
END;
/
*/
