package com.algaworks.algafoods.domain.repository;

import com.algaworks.algafoods.domain.model.Usuario;
import com.algaworks.algafoods.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
