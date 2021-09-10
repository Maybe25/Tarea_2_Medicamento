package com.empresa.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Alumno;

import com.empresa.service.AlumnoService;

@RestController
@RequestMapping("/rest/alumno")
public class AlumnoController {
	
	@Autowired
	private AlumnoService service;
	
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Alumno>> listaAlumno(){
		List<Alumno> lista = service.listaAlumno();
		return  ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Alumno> registraAlumno(@RequestBody Alumno obj){
		Alumno objSalida = service.insertaActualiza(obj);
		if(objSalida ==null) {
			return ResponseEntity.noContent().build();
		}else {
		return ResponseEntity.ok(objSalida);
		}
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<Alumno> ActualizaAlumno(@RequestBody Alumno obj){
		if(obj==null) {
			return ResponseEntity.noContent().build();
		}else {
			Optional<Alumno> optAlumno = service.buscaPorId(obj.getIdAlumno());
			if (optAlumno.isPresent()) {

				return ResponseEntity.ok(service.insertaActualiza(obj));}
			else {
				return ResponseEntity.badRequest().build();
			}
		}	
	}
	
	@DeleteMapping("/{paramId}")
	@ResponseBody
	public ResponseEntity<Alumno> eliminarPorId(@PathVariable("paramId") int idAlumno){
		Optional<Alumno> optAlumno = service.buscaPorId(idAlumno);
		if(optAlumno.isPresent()) {
			service.eliminaPorId(idAlumno);
			Optional<Alumno> optEliminado = service.buscaPorId(idAlumno);
			if(optEliminado.isPresent()) {
				return ResponseEntity.badRequest().build();
			}else {
				return ResponseEntity.ok(optAlumno.get());
			}
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	
	//Alumno por ID
	//Metodo obtener por ID
		@GetMapping("/id/{id}")
		@ResponseBody
		public ResponseEntity<Alumno> AlumnoPorId(@PathVariable("id") int idAlumno) {
			Optional<Alumno> optAlumno = service.buscaPorId(idAlumno);
			if (optAlumno.isPresent()) {
				return ResponseEntity.ok(optAlumno.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		
		}
		
		
		//Alumno por DNI
				@GetMapping("/dnis/{paramDni}")
				@ResponseBody
				public ResponseEntity<List<Alumno>> listaAlumnoPorDni(@PathVariable("paramDni") String dni) {
					List<Alumno> listaAlumno = service.buscaPorDni2(dni);
					if (CollectionUtils.isEmpty(listaAlumno)) {
						return ResponseEntity.badRequest().build();
					} else {
						return ResponseEntity.ok(listaAlumno);
					}

				}
	
	
	//Alumno por DNI
		@GetMapping("/dni/{dni}")
		@ResponseBody
		public ResponseEntity<Alumno> AlumnoPorDni(@PathVariable("dni") String dni) {
			Alumno optAlumno = service.buscarPorDni(dni);
			if (optAlumno!=null) {
				return ResponseEntity.ok(optAlumno);
			} else {
				return ResponseEntity.notFound().build();
			}

		}
		
		
	

}
