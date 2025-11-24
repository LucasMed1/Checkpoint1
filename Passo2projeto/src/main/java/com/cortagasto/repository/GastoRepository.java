package com.cortagasto.repository;

import com.cortagasto.model.Gasto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Repositório em memória para gerenciar gastos.
 * Utiliza HashMap para armazenamento temporário.
 */
@Repository
public class GastoRepository {
    
    private final Map<Long, Gasto> gastos = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    /**
     * Salva um novo gasto.
     */
    public Gasto save(Gasto gasto) {
        if (gasto.getId() == null) {
            gasto.setId(idGenerator.getAndIncrement());
        }
        gastos.put(gasto.getId(), gasto);
        return gasto;
    }
    
    /**
     * Busca um gasto por ID.
     */
    public Optional<Gasto> findById(Long id) {
        return Optional.ofNullable(gastos.get(id));
    }
    
    /**
     * Lista todos os gastos.
     */
    public List<Gasto> findAll() {
        return new ArrayList<>(gastos.values());
    }
    
    /**
     * Remove um gasto por ID.
     */
    public boolean deleteById(Long id) {
        return gastos.remove(id) != null;
    }
    
    /**
     * Filtra gastos por categoria.
     */
    public List<Gasto> findByCategoria(String categoria) {
        return gastos.values().stream()
                .filter(gasto -> categoria == null || 
                        (gasto.getCategoria() != null && 
                         gasto.getCategoria().equalsIgnoreCase(categoria)))
                .collect(Collectors.toList());
    }
    
    /**
     * Filtra gastos por mês e ano.
     */
    public List<Gasto> findByMes(int ano, int mes) {
        YearMonth yearMonth = YearMonth.of(ano, mes);
        LocalDate inicio = yearMonth.atDay(1);
        LocalDate fim = yearMonth.atEndOfMonth();
        
        return gastos.values().stream()
                .filter(gasto -> {
                    LocalDate data = gasto.getData();
                    return data != null && 
                           !data.isBefore(inicio) && 
                           !data.isAfter(fim);
                })
                .collect(Collectors.toList());
    }
    
    /**
     * Calcula o total de gastos do mês atual.
     */
    public BigDecimal calcularTotalMesAtual() {
        YearMonth mesAtual = YearMonth.now();
        return findByMes(mesAtual.getYear(), mesAtual.getMonthValue())
                .stream()
                .map(Gasto::getValor)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

