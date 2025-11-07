package org.springtest.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springtest.demo.dto.UsuarioDto;
import org.springtest.demo.model.Usuario;
import org.springtest.demo.repository.UsuarioRepository;
import org.springtest.demo.repository.UsuarioObjRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository repository;
    private final UsuarioObjRepository repositoryObj;

    public UsuarioController(UsuarioRepository repository, UsuarioObjRepository repositoryObj) {
        this.repository = repository;
        this.repositoryObj = repositoryObj;
    }

    // Obtener todos los usuarios
    @GetMapping
    public List<Usuario> listar() {
        return repository.findAll();
    }

    // Crear un usuario
    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Actualizar usuario por ID
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        return repository.findById(id)
                .map(usuario -> {
                    usuario.setNombre(usuarioActualizado.getNombre());
                    usuario.setEmail(usuarioActualizado.getEmail());
                    usuario.setDescripcion(usuarioActualizado.getDescripcion());
                    return repository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        repository.deleteById(id);
        return "Usuario eliminado con ID: " + id;
    }

    @PutMapping("/{id}/descripcion")
    public ResponseEntity<String> actualizarDescripcion(
            @PathVariable Long id,
            @RequestBody String nuevaDescripcion) {

        repository.actualizarDescripcionUsuario(id, nuevaDescripcion);

        return ResponseEntity.ok("Descripción actualizada correctamente para el usuario con ID " + id);
    }
    // Actualizar usuario pasando un OBJ_USUARIO
    @PutMapping("/obj")
    public ResponseEntity<String> actualizarUsuarioObj(@RequestBody UsuarioDto usuario) {
        repositoryObj.actualizarUsuarioObj(usuario);
        return ResponseEntity.ok("Usuario actualizado correctamente usando OBJ_USUARIO");
    }

}
