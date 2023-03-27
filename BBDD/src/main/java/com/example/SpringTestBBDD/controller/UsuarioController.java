package com.example.SpringTestBBDD.controller;



import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.SpringTestBBDD.Entity.Usuario;
import com.example.SpringTestBBDD.Service.UsuarioService;


@Controller
@RequestMapping("/users")
public class UsuarioController {
	
	@Autowired
	@Qualifier("usuarioservice")
	private UsuarioService usuarioService;

//	@GetMapping("/")
//	public ModelAndView listAllUsuarios() {
		
		
//		ModelAndView mav = new ModelAndView("list1");
//		mav.addObject("usuarios",usuarioService.listAllUsuario());
//		mav.addObject("user",new Usuario());
//		return mav;
//	}
	@GetMapping("/")
	public String mostrarTodosPagina(@RequestParam Map<String, Object> params, Model model) {
		int page= params.get("page") !=null ? (Integer.valueOf(params.get("page").toString())-1): 0;
		
		PageRequest pageRequest= PageRequest.of(page, 1);
		Page<Usuario> pageUsuario= usuarioService.getAll((Pageable) pageRequest);
		int totalPage = pageUsuario.getTotalPages();
		if(totalPage >0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages",pages);
		}
		model.addAttribute("list", pageUsuario.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "/users";
		
	
		
	}
	
	@GetMapping("/user")
	public ModelAndView getUsuarios() {
		
		
		ModelAndView mav = new ModelAndView("list1");
		mav.addObject("usuarios",usuarioService.listAllUsuario());
		mav.addObject("user",new Usuario());
		return mav;
	}
	
	
	@PostMapping("/adduser")
	public String addUsers(@ModelAttribute(name="user")Usuario usuario) {
	
		usuarioService.addUsuario(usuario);
		return "redirect:/users/user";
		
	}
	@GetMapping("/usuario/{id}")
	public String mostrarUsuarioPorId (@PathVariable int id,Model model){
		Usuario usuario = usuarioService.listUsuarioById(id);
		model.addAttribute("usuario",usuario);
		model.addAttribute("accion","visualizar");
		return "plantilla_usuario";			
	}
	@GetMapping("/deleteUsuario/{id}")
	public String deleteUser(Model model,@PathVariable int id) {
		usuarioService.deleteUser(id);
		return"redirect:/users/";
	}
	@GetMapping("/editar/{id}")
	public String editarUsuario(@PathVariable int id,Model model) {
		model.addAttribute("usuario",usuarioService.listUsuarioById(id));
		model.addAttribute("accion","editar");
		return "plantilla_usuario";
	}
	@PostMapping("/actualizar/{id}")
	public String actualizarUsuario(@PathVariable int id,@ModelAttribute ("usuario")Usuario usuario,Model model) {
		Usuario usuarioexistente = usuarioService.listUsuarioById(id);
		usuarioexistente.setId(id);
		usuarioexistente.setNombre(usuario.getNombre());
		usuarioexistente.setApellidos(usuario.getApellidos());
		usuarioexistente.setTelefono(usuario.getTelefono());
		usuarioexistente.setCorreo(usuario.getCorreo());
		usuarioService.actualizarUsuario(usuarioexistente);
		return "redirect:/users/";
	}
}	
	
	/*
	 * /users
	 *  GET /users/user/:id
	 *  POST /users/user
	 *  PUT /users/user
	 *  DELETE /users/user/:id
	 */
	

