package com.hyg.proyecto.controller;

import java.util.Collection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hyg.proyecto.model.Role;
import com.hyg.proyecto.model.User;
import com.hyg.proyecto.service.UserService;

@Controller
public class AppController {

	@Autowired
	private UserService service;

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());

		return "signup_form";
	}

	@PostMapping("/process_register")
	public String processRegister(User user) {
		service.registerDefaultUser(user);

		return "register_success";
	}

	@GetMapping("/users")
	public String listUsers(Model model, Authentication authentication) {
		if (authentication != null) {
			// Obtén los roles del usuario autenticado
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

			if (authorities.stream().anyMatch(a -> a.getAuthority().equals("Admin"))) {
				// El usuario tiene el rol de administrador, realiza alguna acción para
				// administradores
				return "DashboardAdmin";
			} else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("Customer"))) {
				// El usuario tiene el rol de cliente, realiza alguna acción para clientes
				return "DashboardCliente";
			}
		}

		// Otros casos o roles no manejados
		return "DashboardCliente";
	}

	@GetMapping("/userlist")
	public String listUsers(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);

		return "users";
	}

	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable Long id, Model model) {
		User user = service.get(id);
		List<Role> listRoles = service.listRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "user_form";
	}

	@PostMapping("/users/save")
	public String saveUser(User user) {
		service.save(user);

		return "redirect:/users";
	}

}
