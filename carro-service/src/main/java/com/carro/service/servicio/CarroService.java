package com.carro.service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.carro.service.entidades.Carro;
import com.carro.service.respositorio.CarroRepository;

@Service
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;
	
	@Transactional(readOnly = true)
	public List<Carro>getAll(){
		return carroRepository.findAll();
	}
	@Transactional(readOnly = true)
	public Carro getCarroById(int id) {
		return carroRepository.findById(id).orElse(null);
	}
	@Transactional
	public Carro save(Carro carro) {
		Carro nuevoCarro = carroRepository.save(carro);
		return nuevoCarro;
	}
	@Transactional(readOnly = true)
    public List<Carro>byUsuarioId(int usuarioId){
		return carroRepository.findByUsuarioId(usuarioId);
	}
}
