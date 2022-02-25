package com.example.farmacia.controller;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.farmacia.model.Produto;
import com.example.farmacia.repository.CategoriaRepository;
import com.example.farmacia.repository.ProdutoRepository;



@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descricao/{descricao}") // n podem existir 2 getmapping iguais
	public ResponseEntity<List<Produto>> getByDescricao(@PathVariable String descricao) {
		return ResponseEntity.ok(produtoRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@GetMapping("preco/{preco}/{preco1}")
	public ResponseEntity<List<Produto>> getBypreco(@PathVariable BigDecimal preco, @PathVariable BigDecimal preco1) {
		return ResponseEntity.ok(produtoRepository.findByPrecoBetween(preco , preco1));
	}
	
	@GetMapping("preco/{preco}/{preco1}")
	public ResponseEntity<List<Produto>> getBypreco(@PathVariable BigDecimal preco, @PathVariable BigDecimal preco1) {
		return ResponseEntity.ok(produtoRepository.findByPrecoBetween(preco , preco1));
	}
	
	@PostMapping
	public ResponseEntity<Produto> postProduto (@Valid @RequestBody Produto produto){
		if( produtoRepository.existsById(produto.getId()) == true) 
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
				
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produto) {
		if(categoriaRepository.existsById(produto.getId())) {
			return produtoRepository.findById(produto.getId()) // 
					.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto))) 
					.orElse(ResponseEntity.notFound().build()); 
					
		} 
			return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteProduto(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		produtoRepository.deleteById(id);	
	}
	
	
	
	
	

}
	