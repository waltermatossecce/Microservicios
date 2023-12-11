package com.usuario.service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.feignsClients.MotoFeignClients;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.servicio.UsuarioService;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>>listarUsuarios(){
		List<Usuario> usuarios = usuarioService.getAll();
		if(usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Usuario>Obtenerusuario(@PathVariable int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
	    if (usuario==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}
	@PostMapping
	public ResponseEntity<Usuario>guardaUsuario(@RequestBody Usuario usuario){
		Usuario nuevoUsuario = usuarioService.save(usuario);
		return ResponseEntity.ok(nuevoUsuario);
	}
	//rest template de carros
	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity<List<Carro>>listarCarros(@PathVariable("usuarioId") int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		
		if (usuario==null) {
			return ResponseEntity.notFound().build();
		}
		
		List<Carro>carros= usuarioService.getCarros(id);
		return ResponseEntity.ok(carros);
	}
	//rest template de motos
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>>listaMotos(@PathVariable("usuarioId")int id){
	   Usuario usuario = usuarioService.getUsuarioById(id);
	   if (usuario==null) {
		return ResponseEntity.notFound().build();
	}
	   
	   List<Moto> motos = usuarioService.getMotos(id);
	   return ResponseEntity.ok(motos);
	}
	
	//Feignclients para guardar un carro
	@PostMapping("/carro/{usuarioId}")
	public ResponseEntity<Carro>guardarCarro(@PathVariable int usuarioId,@RequestBody Carro carro){
		
		Carro nuevoCarro = usuarioService.saveCarro(usuarioId, carro);
		return ResponseEntity.ok(nuevoCarro);
	}
	//feignClient para guardar una moto 
	@PostMapping("/motos/{usuarioId}")
	public ResponseEntity<Moto>saveMoto(@PathVariable("usuarioId")int usuarioId,@RequestBody Moto moto){
		
		Moto nuevaMoto = usuarioService.saveMoto(usuarioId, moto);
		return ResponseEntity.ok(nuevaMoto);
	}
	//feignClients de lista de usuarios de motos y lista
	
	@GetMapping("/todos/{usuarioId}")
	public ResponseEntity<Map<String, Object>>listarTodosLosVehiculos(@PathVariable("usuarioId") int usuarioId){
		
		Map<String,Object>resultado = usuarioService.getUsuariosAndVehiculos(usuarioId);
		return ResponseEntity.ok(resultado);
		
	}
}