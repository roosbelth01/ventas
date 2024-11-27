package com.ralva.ventas.dto.category;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String estado;
    private String usuarioCreacion;
    private Date fechaCreacion;
    private String usuarioModifica;
    private Date fechaModifica;
}
