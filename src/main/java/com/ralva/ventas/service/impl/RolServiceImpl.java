package com.ralva.ventas.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ralva.ventas.domain.Rol;
import com.ralva.ventas.repository.RolRepository;
import com.ralva.ventas.service.RolService;

@Service
public class RolServiceImpl implements RolService{
    private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public Optional<Rol> findByName(String name) {
        return rolRepository.findByNombre(name);
    }



}
