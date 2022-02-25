package com.example.farmacia.repository;



import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.farmacia.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Long>{
	public List <Produto> findAllByDescricaoContainingIgnoreCase(String descricao);
	
	public List <Produto> findByPrecoBetween(BigDecimal start, BigDecimal end);
	
	public List <Produto> findByNomeContainingIgnoreCaseAndLaboratorioIgnoreCase (String nome, String laboratorio);
	
	Object findAllByNomeContainingIgnoreCase(String nome);

	

}
