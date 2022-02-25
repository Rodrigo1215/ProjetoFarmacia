package com.example.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmacia.model.Categoria;



@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long>{
	public List <Categoria> findAllByTarjaContainingIgnoreCase(String tarja);


}
