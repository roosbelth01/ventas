package com.ralva.ventas.service;

import java.util.Optional;

import com.ralva.ventas.domain.Rol;

public interface RolService {
    Optional<Rol> findByName(String name);
}
