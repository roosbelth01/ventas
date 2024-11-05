package com.ralva.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ralva.ventas.domain.Usuario;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    //Usuario findByUsername(String username);
    Optional<Usuario> findByUsername(String username);

    Boolean existsByUsername(String name);
}
