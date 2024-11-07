package br.com.topsaudehub.desafio.senior.dao;


import br.com.topsaudehub.desafio.senior.model.ContasMedicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContasMedicasRepository extends JpaRepository<ContasMedicas, Integer> {
    List<ContasMedicas> findByTipoContaining(String tipo);
    ContasMedicas findByEspecialidade(String cpf);
}