package com.ralva.ventas.mapper.category;

import org.mapstruct.Mapper;

import com.ralva.ventas.domain.Categoria;
import com.ralva.ventas.dto.category.CategoriaDTO;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    Categoria toCategoriaDTO(CategoriaDTO categoriaDTO);
}
