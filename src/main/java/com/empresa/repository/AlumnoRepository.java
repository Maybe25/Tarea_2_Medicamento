package com.empresa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{
	
	@Query("Select a from alumno a where dni like :dni")
	public abstract Alumno AlumnoPorDni(@Param("dni") String dni);
	
	
	public abstract List<Alumno> findByDni(String dni);

}
