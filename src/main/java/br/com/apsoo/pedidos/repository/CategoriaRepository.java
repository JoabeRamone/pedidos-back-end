package br.com.apsoo.pedidos.repository;

import br.com.apsoo.pedidos.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query("SELECT ca FROM Categoria ca WHERE ca.nome like ?1")
    Categoria buscarPorNome(String nome);

    @Query
    Categoria findByNome(String nome);

}
