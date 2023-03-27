package com.example.SpringTestBBDD.Service.Impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.SpringTestBBDD.Entity.Usuario;
import com.example.SpringTestBBDD.Service.UsuarioService;
import com.example.SpringTestBBDD.repository.UsuarioRepository;

@Service("usuarioservice")
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	@Qualifier("usuariorepository")
	private UsuarioRepository usuarioRepository;
	
	@Override
	public List<Usuario> listAllUsuario() {
		
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario addUsuario(Usuario usuario) {
		
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario listUsuarioById(int id) {
		
		
		return usuarioRepository.findById(id).orElse(null); 
	}

	@Override
	public void deleteUser(int id) {
	
		usuarioRepository.deleteById(id);
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {
		
		return usuarioRepository.save(usuario);
	}

	@Override
	public Page<Usuario> getAll(Pageable pageable) {

		return usuarioRepository.findAll(pageable);
	}


}
