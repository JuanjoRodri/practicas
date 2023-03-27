package com.example.SpringTestBBDD.Service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.SpringTestBBDD.Entity.Usuario;

public interface UsuarioService {
	
	public abstract List<Usuario> listAllUsuario();
	
	public abstract Usuario addUsuario(Usuario usuario);
	
	public abstract Usuario listUsuarioById(int id);

	public abstract void deleteUser(int id);
	
	public abstract Usuario actualizarUsuario(Usuario usuario);
	
	public abstract Page<Usuario> getAll(Pageable pageable);

}
