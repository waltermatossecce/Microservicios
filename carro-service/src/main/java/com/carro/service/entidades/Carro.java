package com.carro.service.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String marca;
	private String modelo;
	private int usuarioId;
}
