package com.example.SpringTestBBDD.ServiceAPI;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;

import com.example.SpringTestBBDD.Entity.Usuario;

public interface ServiceAPI {

	Page<Usuario> getAll(Pageable pageable);
	
}
