package br.com.apsoo.pedidos.repository;

import br.com.apsoo.pedidos.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("SELECT pro FROM Produto pro WHERE pro.nome like ?1")
    Produto buscarNomeNaCategoria(String nome);

}
