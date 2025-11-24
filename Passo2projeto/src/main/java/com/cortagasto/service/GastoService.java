package com.cortagasto.service;

import com.cortagasto.dto.GastoRequest;
import com.cortagasto.model.Gasto;
import com.cortagasto.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço que contém a lógica de negócio para gerenciamento de gastos.
 */
@Service
public class GastoService {
    
    private final GastoRepository gastoRepository;
    
    @Autowired
    public GastoService(GastoRepository gastoRepository) {
        this.gastoRepository = gastoRepository;
    }
    
    /**
     * Cria um novo gasto.
     */
    public Gasto criarGasto(GastoRequest request) {
        Gasto gasto = new Gasto();
        gasto.setDescricao(request.getDescricao());
        gasto.setValor(request.getValor());
        gasto.setData(request.getData());
        gasto.setCategoria(request.getCategoria() != null ? request.getCategoria() : "Outros");
        
        return gastoRepository.save(gasto);
    }
    
    /**
     * Lista todos os gastos.
     */
    public List<Gasto> listarTodos() {
        return gastoRepository.findAll();
    }
    
    /**
     * Busca um gasto por ID.
     */
    public Optional<Gasto> buscarPorId(Long id) {
        return gastoRepository.findById(id);
    }
    
    /**
     * Atualiza um gasto existente.
     */
    public Optional<Gasto> atualizarGasto(Long id, GastoRequest request) {
        Optional<Gasto> gastoOpt = gastoRepository.findById(id);
        
        if (gastoOpt.isPresent()) {
            Gasto gasto = gastoOpt.get();
            gasto.setDescricao(request.getDescricao());
            gasto.setValor(request.getValor());
            gasto.setData(request.getData());
            gasto.setCategoria(request.getCategoria() != null ? request.getCategoria() : "Outros");
            
            return Optional.of(gastoRepository.save(gasto));
        }
        
        return Optional.empty();
    }
    
    /**
     * Remove um gasto.
     */
    public boolean removerGasto(Long id) {
        return gastoRepository.deleteById(id);
    }
    
    /**
     * Filtra gastos por categoria.
     */
    public List<Gasto> filtrarPorCategoria(String categoria) {
        return gastoRepository.findByCategoria(categoria);
    }
    
    /**
     * Filtra gastos por mês.
     */
    public List<Gasto> filtrarPorMes(int ano, int mes) {
        return gastoRepository.findByMes(ano, mes);
    }
    
    /**
     * Calcula o total gasto no mês atual.
     */
    public BigDecimal calcularTotalMesAtual() {
        return gastoRepository.calcularTotalMesAtual();
    }
}

