package br.com.topsaudehub.desafio.senior.usecase;



import br.com.topsaudehub.desafio.senior.model.ContasMedicasResponse;
import br.com.topsaudehub.desafio.senior.model.ContasMedicas;

public interface CalculoContaInterface {
    ContasMedicasResponse calcular(ContasMedicas conta);
}