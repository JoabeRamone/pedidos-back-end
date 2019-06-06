package br.com.apsoo.pedidos.service;

import br.com.apsoo.pedidos.domain.Categoria;
import br.com.apsoo.pedidos.domain.Produto;
import br.com.apsoo.pedidos.repository.CategoriaRepository;
import br.com.apsoo.pedidos.service.exception.CategoriaExistenteException;
import br.com.apsoo.pedidos.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscarPorId(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                "O objeto com o Id: " + id +
                        " da classe " + Categoria.class.getName() +
                " não foi encontrado."));
    }

    public List<Categoria> buscarCategoria() {
        List<Categoria> categoria = categoriaRepository.findAll();
        return categoria;
    }

    public Categoria salvarCategoria(Categoria novaCategoria){
        validarCategoriaExistente(novaCategoria);
        return categoriaRepository.save(novaCategoria);
    }

    public ResponseEntity<?> deletarPorId(Integer id) {
        categoriaRepository.deleteById(id);
        return new ResponseEntity<Categoria>(HttpStatus.NO_CONTENT);
    }

    private void validarCategoriaExistente(Categoria categoria) {
        Categoria categoriaExistente = categoriaRepository.findByNome(categoria.getNome());

        if(Objects.nonNull(categoriaExistente)){
            throw new CategoriaExistenteException("Categoria já existe!");
        }
    }

    public Categoria atualizarNome(Categoria categoriaAtualizada) {
        validarCategoriaExistente(categoriaAtualizada);
        return categoriaRepository.save(categoriaAtualizada);
    }

    protected void adicionarProdutoNaLista(Produto produto, Integer idCategoria) {
        Categoria categoria = buscarPorId(idCategoria);

        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);

        categoria.setProdutos(produtos);
    }
}
