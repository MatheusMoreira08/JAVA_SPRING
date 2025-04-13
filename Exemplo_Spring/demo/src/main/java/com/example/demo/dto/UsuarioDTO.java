package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {
    
    private long id;

    @NotBlank(message = "O nome é obrigatorio")
    private String nome;

    @Size(min = 11, max = 11, message = "Cpf deve conter 11 caracteres")
    private String cpf;

    @Email(message = "E-mail inválido")
    private String email;

    

}
