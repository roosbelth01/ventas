package com.ralva.ventas.mapper.auth;

import org.mapstruct.Mapper;

import com.ralva.ventas.domain.Usuario;
import com.ralva.ventas.dto.auth.RegisterDTO;
import com.ralva.ventas.dto.auth.UsuarioDTO;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toUsuarioEntity(RegisterDTO dto);
    RegisterDTO toUsuarioDto(Usuario entity);
    UsuarioDTO toRegisterDto(Usuario entity);
}
