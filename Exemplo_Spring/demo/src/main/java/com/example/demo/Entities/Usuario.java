package com.example.demo.Entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "usuario")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Exemplo completo@Column(nullable = false, unique = true, name = "usu_cpf") 
    private long id; 
    @Column(name = "usu_nome") 
    private String nome;
    @Column(nullable = false, unique = true, name = "usu_cpf") 
    private String cpf; 
    @Column(name = "usu_email") 
    private String email;
    @Column(nullable = false, name = "usu_senha")   
    private String senha;
    @Column(name = "usu_telefone")  
    private String telefone;
    @Column(name = "usu_dtNascimento")  
    private LocalDate dtNascimento;
     


}
