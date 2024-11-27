package com.ralva.ventas.dto.category;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateCategoriaDTO {
    private String nombre;
    private String descripcion;
    private String estado;
    private String usuarioCreacion;
    private Date fechaCreacion;
}
