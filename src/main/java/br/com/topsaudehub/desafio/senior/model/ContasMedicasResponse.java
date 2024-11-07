
package br.com.topsaudehub.desafio.senior.model;

import java.math.BigDecimal;

public class ContasMedicasResponse {
    private ContasMedicas conta;
    private BigDecimal valorFinal;

    public ContasMedicasResponse(ContasMedicas conta, BigDecimal valorFinal) {
        this.conta = conta;
        this.valorFinal = valorFinal;
    }

    public ContasMedicas getConta() {
        return conta;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }
}
