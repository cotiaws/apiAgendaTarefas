package br.com.cotiinformatica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.repositories.TarefaRepository;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {
	
	@Autowired
	private TarefaRepository tarefaRepository;

	@GetMapping("tarefas-finalizado")
	public ResponseEntity<?> getTarefasFinalizado() {
		var lista = tarefaRepository.groupByFinalizado();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("tarefas-prioridade")
	public ResponseEntity<?> getTarefasPrioridade() {
		var lista = tarefaRepository.groupByPrioridade();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("tarefas-categoria")
	public ResponseEntity<?> getTarefasCategoria() {
		var lista = tarefaRepository.groupByCategoria();
		return ResponseEntity.ok(lista);
	}
}
