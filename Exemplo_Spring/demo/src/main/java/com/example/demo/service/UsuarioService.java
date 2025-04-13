package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Usuario;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.repository.IUsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public Optional<UsuarioDTO> buscarporId(Long id) {

        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDTO);
    }

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        return usuarioMapper.toDTO(usuarioRepository.save(usuario));
    }
}
