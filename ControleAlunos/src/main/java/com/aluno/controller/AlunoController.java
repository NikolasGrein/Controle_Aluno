package com.aluno.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aluno.entities.Aluno;
import com.aluno.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
	private final AlunoService alunoService;
	
	@Autowired
	public AlunoController(AlunoService alunoService) {
		this.alunoService = alunoService;
	}
	@GetMapping("/{id}")

    public ResponseEntity<Aluno> buscaAlunoControlId(@PathVariable Long id) {
		Aluno aluno = alunoService.buscaAlunoId(id); 
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
        
    @GetMapping("/")

    public ResponseEntity<List<Aluno>> buscaTodosAlunoControl() { 
        List<Aluno> aluno = alunoService.buscaTodosAlunos();
        return ResponseEntity.ok(aluno);
    }
        
    @PostMapping("/")
    public ResponseEntity<Aluno> salvaAlunoControl(@RequestBody Aluno cliente) { 
        Aluno salvaAluno = alunoService.salvaAluno(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvaAluno);
    }

    @PutMapping("/{id}")

    public ResponseEntity<Aluno> alteraAlunoControl(@PathVariable Long id, @RequestBody Aluno aluno) { 
    	Aluno alteraAluno = alunoService.alterarAluno(id, aluno);
        if (alteraAluno != null) {
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<String> apagaAlunoControl(@PathVariable Long id) { 
        boolean apagar = alunoService.apagarAluno(id);
        if (apagar) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
