package com.aprendizagem.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.aprendizagem.models.Tool;
import com.aprendizagem.repositories.ToolRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ToolController {
	
	@Autowired
	ToolRepository toolRepository;
	

	@GetMapping("/tools")
	public ResponseEntity<List<Tool>> getAll(){
		
		List<Tool> toolList = toolRepository.findAll();
		
		if(toolList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			for(Tool tool : toolList) {
				long id = tool.getId();
				
				tool.add(linkTo(methodOn(ToolController.class).getOne(id)).withSelfRel()); // O metodo getOne(id) é o metodo abaixo e o withSelfRel() cria o link para cada item
			}
		}
		return new ResponseEntity<List<Tool>>(toolList, HttpStatus.OK);
	}
	
	
	@GetMapping("/tools/{id}")
	public ResponseEntity<Tool> getOne(@PathVariable(value = "id") Long id){
		
		Optional<Tool> tool = toolRepository.findById(id);
		
		if(tool.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			tool.get().add(linkTo(methodOn(ToolController.class).getAll()).withRel("Lista de Tools")); // O metodo getAll() é o metodo acima e o withRel("Lista de Tools") cria o link que retorna para a lista
			return new ResponseEntity<Tool>(tool.get(), HttpStatus.OK);
		}
	}
}










