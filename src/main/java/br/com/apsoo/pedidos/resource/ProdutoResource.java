package br.com.apsoo.pedidos.resource;

import br.com.apsoo.pedidos.domain.Categoria;
import br.com.apsoo.pedidos.domain.Produto;
import br.com.apsoo.pedidos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("http://localhost:8081")
@RestController()
@RequestMapping(value = "/categorias/{idCategoria}/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> buscarProdutos() {
        List<Produto> produto = produtoService.buscarProdutos();
        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{idCategoria}")
    public ResponseEntity<?> buscarProdutosPorCategoria(@PathVariable("idCategoria") Integer id) {
        Categoria produtos = produtoService.buscarProdutosPorCategoria(id);
        return ResponseEntity.ok().body(produtos);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> adicionarProduto(@RequestBody Produto produto, @PathVariable("idCategoria") Integer idCategoria) {
        Produto produtoNovo = produtoService.salvarProduto(produto, idCategoria);
        return ResponseEntity.ok().body(produtoNovo);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> editarProduto(@RequestBody Produto produto, @PathVariable("idCategoria") Integer idcategoria) {
        Produto produtoAtualizado = produtoService.editarProduto(produto, idcategoria);
        return ResponseEntity.ok().body(produtoAtualizado);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@RequestBody Produto produto, @PathVariable("idCategoria") Integer idCategoria) {
        return produtoService.deletar(produto, idCategoria);
    }
}
