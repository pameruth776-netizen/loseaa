-- =========================================================================
-- SCRIPT DE ACTUALIZACIÓN DEL SCHEMA DE BASE DE DATOS PARA SANCIONES
-- =========================================================================
--
-- PROPÓSITO:
-- Modificar la restricción (Foreign Key) de la columna 'administrador_id' 
-- en la tabla 'sanciones' para que apunte a la tabla principal 'usuarios(id)'
-- en lugar de 'administradores(id)'. Esto previene fallas de integridad 
-- si el administrador se registró de forma directa o simplificada en la tabla principal.
--
-- INSTRUCCIONES:
-- 1. Ejecute este script en su cliente SQL (MySQL Workbench, phpMyAdmin, DBeaver, etc.) 
--    conectado a la base de datos de su proyecto.
-- 2. Asegúrese de que el nombre del constraint 'FK_...' sea el correcto. 
--    Puede buscarlo ejecutando: SHOW CREATE TABLE sanciones;
-- =========================================================================

-- 1. Intentamos eliminar el constraint de clave foránea anterior.
-- Nota: Si Hibernate generó un nombre de constraint aleatorio diferente (ej: FKc85yd8shd73hshd),
-- reemplace 'FK_sanciones_administrador' con dicho nombre.
ALTER TABLE sanciones DROP FOREIGN KEY IF EXISTS FK_sanciones_administrador;
ALTER TABLE sanciones DROP FOREIGN KEY IF EXISTS FK_sanciones_administrador_id;

-- 2. Asegurar que la columna 'administrador_id' sea BIGINT y acepte valores NULL.
ALTER TABLE sanciones MODIFY COLUMN administrador_id BIGINT NULL;

-- 3. Crear el nuevo constraint apuntando a la tabla 'usuarios' (id).
ALTER TABLE sanciones ADD CONSTRAINT FK_sanciones_usuario_admin
FOREIGN KEY (administrador_id) REFERENCES usuarios(id)
ON DELETE SET NULL;
