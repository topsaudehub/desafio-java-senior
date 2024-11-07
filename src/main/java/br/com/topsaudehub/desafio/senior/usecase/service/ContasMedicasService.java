package br.com.topsaudehub.desafio.senior.usecase.service;

import br.com.topsaudehub.desafio.senior.dao.ContasMedicasRepository;
import br.com.topsaudehub.desafio.senior.model.ContasMedicasResponse;
import br.com.topsaudehub.desafio.senior.model.ContasMedicas;
import br.com.topsaudehub.desafio.senior.usecase.CalculoContaInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ContasMedicasService implements CalculoContaInterface {

    @Autowired
    private ContasMedicasRepository contasMedicasRepository;

    public Optional<ContasMedicas> findById(int id) {
        return contasMedicasRepository.findById(id);
    }

    public List<ContasMedicas> findAll() {
        return contasMedicasRepository.findAll();
    }

    public List<ContasMedicas> findByTipoContaining(String tipo) {
        return contasMedicasRepository.findByTipoContaining(tipo);
    }
    public ContasMedicas findByCPF(String cpf) {
        return contasMedicasRepository.findByEspecialidade(cpf);
    }

    @Override
    public ContasMedicasResponse calcular(ContasMedicas conta) {
        BigDecimal valorFinal;

        if ("CONSULTA".equals(conta.getTipo())) {
            valorFinal = calcularConsulta(conta);
        } else {
            valorFinal = calcularExame(conta);
        }

        ContasMedicasResponse response = new ContasMedicasResponse(conta, valorFinal);
        contasMedicasRepository.save(conta);
        return response;
    }

    private BigDecimal calcularConsulta(ContasMedicas conta) {
        BigDecimal taxa;
        if ("M".equals(conta.getSexo())) {
            taxa = conta.getIdade() > 40 ? BigDecimal.valueOf(0.25) :
                    conta.getIdade() > 20 ? BigDecimal.valueOf(0.20) : BigDecimal.ZERO;
        } else {
            taxa = conta.getIdade() > 40 ? BigDecimal.valueOf(0.20) :
                    conta.getIdade() > 20 ? BigDecimal.valueOf(0.15) : BigDecimal.ZERO;
        }
        return conta.getValor().multiply(BigDecimal.ONE.add(taxa));
    }

    private BigDecimal calcularExame(ContasMedicas conta) {
        BigDecimal taxaBase = switch (conta.getEspecialidade()) {
            case "Ecocardiograma" -> calcularTaxaEcocardiograma(conta);
            case "Colesterol" -> calcularTaxaColesterol(conta);
            case "Glicose" -> calcularTaxaGlicose(conta);
            default -> throw new IllegalArgumentException("Especialidade inválida");
        };
        return conta.getValor().multiply(BigDecimal.ONE.add(taxaBase));
    }

    private BigDecimal calcularTaxaEcocardiograma(ContasMedicas conta) {
        if ("M".equalsIgnoreCase(conta.getSexo())) {
            return conta.getIdade() > 50 ? BigDecimal.valueOf(0.27) :
                    conta.getIdade() > 20 ? BigDecimal.valueOf(0.25) : BigDecimal.valueOf(0.05);
        } else {
            return conta.getIdade() > 50 ? BigDecimal.valueOf(0.13) :
                    conta.getIdade() > 20 ? BigDecimal.valueOf(0.11) : BigDecimal.valueOf(0.01);
        }
    }

    private BigDecimal calcularTaxaColesterol(ContasMedicas conta) {
        if ("M".equalsIgnoreCase(conta.getSexo())) {
            return conta.getIdade() > 50 ? BigDecimal.valueOf(0.16) :
                    conta.getIdade() > 20 ? BigDecimal.valueOf(0.10) : BigDecimal.valueOf(0.06);
        } else {
            return conta.getIdade() > 50 ? BigDecimal.valueOf(0.105) :
                    conta.getIdade() > 20 ? BigDecimal.valueOf(0.05) : BigDecimal.valueOf(0.005);
        }
    }

    private BigDecimal calcularTaxaGlicose(ContasMedicas conta) {
        if ("M".equalsIgnoreCase(conta.getSexo())) {
            return conta.getIdade() > 50 ? BigDecimal.valueOf(0.11) :
                    conta.getIdade() > 20 ? BigDecimal.valueOf(0.09) : BigDecimal.valueOf(0.02);
        } else {
            return conta.getIdade() > 50 ? BigDecimal.valueOf(0.125) :
                    conta.getIdade() > 20 ? BigDecimal.valueOf(0.10) : BigDecimal.valueOf(0.015);
        }
    }


}