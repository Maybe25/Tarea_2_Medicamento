package com.empresa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Alumno;
import com.empresa.entity.Medicamento;
import com.empresa.service.MedicamentoService;

@RestController
@RequestMapping("/rest/medicamento")
public class MedicamentoController {
	
	@Autowired
	private MedicamentoService service;
	
	//Metodo Listar Medicamento
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Medicamento>> listaMedicamentos(){
		List<Medicamento> lista = service.listaMedicamento();
		return ResponseEntity.ok(lista);
		
	}
	
	
	//Metodo eliminar Medicamento por ID
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Medicamento> eliminarPorId(@PathVariable("id")int idMedicamento){
		Optional<Medicamento> optMedicamento = service.obtienePorId(idMedicamento);
		if(optMedicamento.isPresent()) {
			service.eliminarMedicamento(idMedicamento);
			return ResponseEntity.ok(optMedicamento.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}

	//Metodo de insertar y actualizar Medicamentos
	@PostMapping
	@ResponseBody
	public ResponseEntity<Medicamento> registraAlumno(@RequestBody Medicamento obj){
		Medicamento objSalida = service.insertaActualiza(obj);
		if(objSalida == null) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(objSalida);
		}
		
		
	}

	//Metodo obtener por ID
	@GetMapping("/id/{id}")
	@ResponseBody
	public ResponseEntity<Medicamento> listaPorId(@PathVariable("id") int idMedicamento) {
		Optional<Medicamento> optMedicamento = service.obtienePorId(idMedicamento);
		if (optMedicamento.isPresent()) {
			return ResponseEntity.ok(optMedicamento.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	
	}
	
	//Metodo obtener por Nombre containing
	@GetMapping("nombre/{prefix}")
	@ResponseBody
	public ResponseEntity<List<Medicamento>> medicamentoPorNombre(@PathVariable("prefix")String nombre){
		if(!nombre.isEmpty()) {
			return  ResponseEntity.ok(service.obtienePorNombreLike(nombre));
		}
		return ResponseEntity.notFound().build();
		
	}
	
	//Metodo obtener por stock mayor que //
	
	@GetMapping("/stock/{stock}")
	@ResponseBody
	public ResponseEntity<List<Medicamento>> medicamentoPorStockMayorQue(@PathVariable("stock")int stockMedicamento){
		Optional<Integer> optStock = Optional.of(stockMedicamento);
		if(!optStock.isEmpty()) {
			return ResponseEntity.ok(service.obtieneStockMayorQue(stockMedicamento));
		}
		return ResponseEntity.notFound().build();
	}
	
	

}
