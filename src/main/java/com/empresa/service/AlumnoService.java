package com.empresa.service;

import java.util.List;

import com.empresa.entity.Alumno;

public interface AlumnoService {
	
	public Alumno insertaActualiza(Alumno obj);
	
	public List<Alumno> listaAlumno();

}
