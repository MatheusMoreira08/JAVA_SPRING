package com.example.demo.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;
import org.springframework.http.HttpStatus;

@Tag(name = "Usuario", description = "Endpoints para gerenciamento de usuários")
@RestController
@RequestMapping("api/usuarios")

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Buscar por ID", description = "Retorna os detalhes de um usuário especifico")
    @GetMapping("/{id}")

    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        Optional<UsuarioDTO> usuarioDTO = usuarioService.buscarporId(id);
        return usuarioDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Criar um novo usuário", description = "Cadastra um novo usuário no sistema")
    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioDTO>> criarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        try {
            // tenta salvar
            UsuarioDTO savedUsuario = usuarioService.salvar(usuarioDTO);

            // retorna sucesso com o usuario salvo
            ApiResponse<UsuarioDTO> response = new ApiResponse<>(savedUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            // cria um erro com a mensagem especifica
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage());
            ApiResponse<UsuarioDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            // cria um erro generico
            ErrorResponse errorResponse = new ErrorResponse("Error interno", e.getMessage());
            ApiResponse<UsuarioDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
