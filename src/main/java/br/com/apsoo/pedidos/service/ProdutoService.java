package br.com.apsoo.pedidos.service;

import br.com.apsoo.pedidos.domain.Categoria;
import br.com.apsoo.pedidos.domain.Produto;
import br.com.apsoo.pedidos.repository.ProdutoRepository;
import br.com.apsoo.pedidos.service.exception.ObjectNotFoundException;
import br.com.apsoo.pedidos.service.exception.ProdutoExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaService categoriaService;


    public List<Produto> buscarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos;
    }

    public Produto buscarPorId(Integer id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        return produtoOptional.orElseThrow(() -> new ObjectNotFoundException(
                "O objeto com o Id: " + id +
                        " da classe " + Produto.class.getName() +
                        " não foi encontrado."));

    }

    public Categoria buscarProdutosPorCategoria(Integer idCategoria) {
        return categoriaService.buscarPorId(idCategoria);
    }

    public Produto salvarProduto(Produto produto, Integer idCategoria) {
        validarProdutoExistente(produto, idCategoria);

        Produto produtoNovo = new Produto();
        List<Categoria> categorias = new ArrayList<>();
        Categoria categoria = categoriaService.buscarPorId(idCategoria);
        categorias.add(categoria);
        produtoNovo.setCategorias(categorias);
        produtoNovo.setNome(produto.getNome());
        produtoNovo.setPreco(produto.getPreco());
        categoriaService.adicionarProdutoNaLista(produto, idCategoria);

        return produtoRepository.save(produtoNovo);
    }

    private void validarProdutoExistente(Produto produto, Integer idCategoria) {
        Produto produtoExistente = produtoRepository.buscarNomeNaCategoria(produto.getNome());
        if (Objects.nonNull(produtoExistente)) {
            List<Categoria> categorias = produtoExistente.getCategorias();
            for (Categoria categoria : categorias) {
                if (categoria.getId().equals(idCategoria)) {
                    throw new ProdutoExistenteException("Produto já existe na Categoria!");
                }
            }
        }
    }

    public Produto editarProduto(Produto produto, Integer idcategoria) {
        validarProdutoExistente(produto, idcategoria);
        Produto produtoAtualizado = buscarPorId(produto.getId());
        produtoAtualizado.setNome(produto.getNome());
        produtoAtualizado.setPreco(produto.getPreco());
        return produtoRepository.save(produtoAtualizado);
    }


    public ResponseEntity<?> deletar(Produto produto, Integer idCategoria) {
        Categoria categoria = categoriaService.buscarPorId(idCategoria);

        List<Produto> produtos = categoria.getProdutos();

        for (Produto produtosCategoria: produtos) {
            if (produtosCategoria.getId().equals(produto.getId())){
               produtos.remove(produto);
               break;
            }
        }
        produtoRepository.deleteById(produto.getId());
        return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
    }
}
