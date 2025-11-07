CREATE OR REPLACE PACKAGE PKG_OBJ_USUARIOS AS

    PROCEDURE ACTUALIZAR_USUARIO_OBJ (
        p_usuario IN OBJ_USUARIO
    );

    FUNCTION OBTENER_USUARIO (
        p_id IN USERS.ID%TYPE
    ) RETURN OBJ_USUARIO;

END PKG_OBJ_USUARIOS;
/


create OR REPLACE PACKAGE BODY PKG_OBJ_USUARIOS AS

    PROCEDURE ACTUALIZAR_USUARIO_OBJ (
        p_usuario IN OBJ_USUARIO
    )
    AS
    BEGIN
        UPDATE USERS
        SET
            NOMBRE = p_usuario.NOMBRE,
            EMAIL = p_usuario.EMAIL,
            DESCRIPCION = p_usuario.DESCRIPCION
        WHERE ID = p_usuario.ID;

        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Usuario no encontrado con ID: ' || p_usuario.ID);
        END IF;

        COMMIT;
    END ACTUALIZAR_USUARIO_OBJ;


    -- ✅ Función que devuelve un usuario como objeto
    FUNCTION OBTENER_USUARIO (
        p_id IN USERS.ID%TYPE
    ) RETURN OBJ_USUARIO
    AS
        v_obj OBJ_USUARIO;
    BEGIN
        SELECT OBJ_USUARIO(ID, NOMBRE, EMAIL, DESCRIPCION)
        INTO v_obj
        FROM USERS
        WHERE ID = p_id;

        RETURN v_obj;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END OBTENER_USUARIO;

END PKG_OBJ_USUARIOS;
/
