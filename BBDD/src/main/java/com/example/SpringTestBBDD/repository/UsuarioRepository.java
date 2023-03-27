package com.example.SpringTestBBDD.repository;

import java.awt.print.Pageable;
import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringTestBBDD.Entity.Usuario;

@Repository("usuariorepository")
public interface UsuarioRepository extends JpaRepository<Usuario,Serializable>{
	
	public abstract Optional<Usuario> findById(int id);

	//public abstract Page<Usuario> findAllPages(Pageable pageable);


}
