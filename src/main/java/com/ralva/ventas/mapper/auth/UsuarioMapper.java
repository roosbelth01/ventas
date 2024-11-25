package com.ralva.ventas.mapper.auth;

import org.mapstruct.Mapper;

import com.ralva.ventas.domain.Usuario;
import com.ralva.ventas.dto.RegisterDTO;
import com.ralva.ventas.dto.UsuarioDTO;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toUsuarioEntity(RegisterDTO dto);
    RegisterDTO toUsuarioDto(Usuario entity);
    UsuarioDTO toRegisterDto(Usuario entity);
}
