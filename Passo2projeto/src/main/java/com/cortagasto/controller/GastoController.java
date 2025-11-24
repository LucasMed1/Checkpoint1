package com.cortagasto.controller;

import com.cortagasto.dto.GastoRequest;
import com.cortagasto.model.Gasto;
import com.cortagasto.service.GastoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Controller REST para gerenciamento de gastos pessoais.
 */
@RestController
@RequestMapping("/api/gastos")
@Tag(name = "Gastos", description = "API para gerenciamento de gastos pessoais")
public class GastoController {
    
    private final GastoService gastoService;
    
    @Autowired
    public GastoController(GastoService gastoService) {
        this.gastoService = gastoService;
    }
    
    @PostMapping
    @Operation(summary = "Registrar um novo gasto", 
               description = "Cria um novo registro de gasto com descrição, valor, data e categoria")
    public ResponseEntity<Gasto> criarGasto(@Valid @RequestBody GastoRequest request) {
        Gasto gasto = gastoService.criarGasto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(gasto);
    }
    
    @GetMapping
    @Operation(summary = "Listar todos os gastos", 
               description = "Retorna uma lista com todos os gastos registrados")
    public ResponseEntity<List<Gasto>> listarTodos() {
        List<Gasto> gastos = gastoService.listarTodos();
        return ResponseEntity.ok(gastos);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar gasto por ID", 
               description = "Retorna um gasto específico pelo seu identificador")
    public ResponseEntity<Gasto> buscarPorId(@PathVariable Long id) {
        return gastoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um gasto", 
               description = "Atualiza as informações de um gasto existente")
    public ResponseEntity<Gasto> atualizarGasto(
            @PathVariable Long id, 
            @Valid @RequestBody GastoRequest request) {
        return gastoService.atualizarGasto(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover um gasto", 
               description = "Remove um gasto do sistema")
    public ResponseEntity<Void> removerGasto(@PathVariable Long id) {
        if (gastoService.removerGasto(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Filtrar gastos por categoria", 
               description = "Retorna todos os gastos de uma categoria específica")
    public ResponseEntity<List<Gasto>> filtrarPorCategoria(@PathVariable String categoria) {
        List<Gasto> gastos = gastoService.filtrarPorCategoria(categoria);
        return ResponseEntity.ok(gastos);
    }
    
    @GetMapping("/mes")
    @Operation(summary = "Filtrar gastos por mês", 
               description = "Retorna todos os gastos de um mês específico (ano e mês)")
    public ResponseEntity<List<Gasto>> filtrarPorMes(
            @RequestParam int ano, 
            @RequestParam int mes) {
        List<Gasto> gastos = gastoService.filtrarPorMes(ano, mes);
        return ResponseEntity.ok(gastos);
    }
    
    @GetMapping("/total-mes-atual")
    @Operation(summary = "Total gasto no mês atual", 
               description = "Calcula e retorna o valor total gasto no mês atual")
    public ResponseEntity<Map<String, BigDecimal>> calcularTotalMesAtual() {
        BigDecimal total = gastoService.calcularTotalMesAtual();
        return ResponseEntity.ok(Map.of("total", total));
    }
}

