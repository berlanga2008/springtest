CREATE SEQUENCE SEQ_USERS START WITH 1 INCREMENT BY 1 NOCACHE;

CREATE TABLE USERS (
                       ID NUMBER PRIMARY KEY,
                       NOMBRE VARCHAR2(100) NOT NULL,
                       EMAIL VARCHAR2(100) NOT NULL UNIQUE,
                       DESCRIPCION VARCHAR2(255)
);

CREATE OR REPLACE TRIGGER TRG_USERS_BI
BEFORE INSERT ON USERS
FOR EACH ROW
WHEN (NEW.ID IS NULL)
BEGIN
SELECT SEQ_USERS.NEXTVAL INTO :NEW.ID FROM dual;
END;
/


INSERT INTO USERS (NOMBRE, EMAIL, DESCRIPCION) VALUES
('Juan Pérez', 'juan.perez@example.com', 'Usuario administrador del sistema');

INSERT INTO USERS (NOMBRE, EMAIL, DESCRIPCION) VALUES
    ('María López', 'maria.lopez@example.com', 'Responsable de recursos humanos');

INSERT INTO USERS (NOMBRE, EMAIL, DESCRIPCION) VALUES
    ('Carlos García', 'carlos.garcia@example.com', 'Usuario de prueba');
