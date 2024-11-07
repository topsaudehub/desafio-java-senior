package br.com.topsaudehub.desafio.senior.controller;


import br.com.topsaudehub.desafio.senior.model.ContasMedicasResponse;
import br.com.topsaudehub.desafio.senior.model.ContasMedicas;
import br.com.topsaudehub.desafio.senior.usecase.service.ContasMedicasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ContasMedicasController {

    @Autowired
    private ContasMedicasService contasMedicasService;

    @PostMapping(value = "/calcular", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ContasMedicasResponse> calcular(@RequestBody ContasMedicas conta) {
        ContasMedicasResponse response = contasMedicasService.calcular(conta);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ContasMedicas>> listar() {
        return ResponseEntity.ok(contasMedicasService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ContasMedicas> findById(@PathVariable int id) {
        Optional<ContasMedicas> conta = contasMedicasService.findById(id);
        return conta.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ContasMedicas> findById(@PathVariable String cpf) {
        ContasMedicas conta = contasMedicasService.findByCPF(cpf);
        return ResponseEntity.ok().body(conta);
    }


    @GetMapping("/search")
    public ResponseEntity<List<ContasMedicas>> findByTipoContaining(@RequestParam String tipo) {
        return ResponseEntity.ok(contasMedicasService.findByTipoContaining(tipo));
    }
}
