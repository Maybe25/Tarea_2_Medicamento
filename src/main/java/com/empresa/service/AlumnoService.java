package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Alumno;

public interface AlumnoService {
	
	public abstract Alumno insertaActualiza(Alumno obj);
	
	public abstract List<Alumno> listaAlumno();
	
	public abstract Optional<Alumno> buscaPorId(int idAlumno);
	
	public abstract void eliminaPorId(int idAlumno);
	
	public abstract Alumno buscarPorDni(String dni);
	
	public abstract List<Alumno> buscaPorDni2(String dni);
 
}
