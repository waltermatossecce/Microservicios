package com.moto.service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moto.service.entidad.Moto;
import com.moto.service.repositorio.MotoRepository;

@Service
public class MotoService {

	@Autowired
	private MotoRepository motoRepository ;
	
	@Transactional(readOnly = true)
	public List<Moto>getAll(){
		return motoRepository.findAll();
	}
	@Transactional(readOnly = true)
	public Moto getMotoById(int id) {
		return motoRepository.findById(id).orElse(null);
	}
	@Transactional
	public Moto save(Moto moto) {
		Moto nuevoMoto = motoRepository.save(moto);
		return nuevoMoto;
	}
	@Transactional(readOnly = true)
    public List<Moto>byUsuarioId(int usuarioId){
		return motoRepository.findByUsuarioId(usuarioId);
	}
}
