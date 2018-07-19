package com.acj.spa.security.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.acj.spa.entities.Usuario;
import com.acj.spa.repositories.UsuarioRepository;



@Component
public class CustomUserDetailService implements UserDetailsService{
	//SPRING SECURITY GERENCIANDO USUARIO
	
	private final UsuarioRepository userR;
	
	@Autowired
	public CustomUserDetailService(UsuarioRepository userRepository) {
		this.userR = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario user = userR.findByEmail(email);
		
		List<GrantedAuthority> authoritListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER","ROLE_ADMIN");
		List<GrantedAuthority> authoritListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
		//RETURNA UMA LISTA E ACESSA AO BANCO, TODOS OS REGISTRO DE USUARIOS COM VARIAVEL BOOLEAN ADMIN TRUE E COLOCADO COMO ADMIN, AOS OUTROS RECEBE PERFIL USER
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getSenha(), user.isAdmin() ? authoritListAdmin : authoritListUser);
	}

}
