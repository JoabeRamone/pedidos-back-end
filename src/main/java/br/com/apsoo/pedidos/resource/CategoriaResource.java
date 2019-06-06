package br.com.apsoo.pedidos.resource;

import br.com.apsoo.pedidos.domain.Categoria;
import br.com.apsoo.pedidos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8081")
@RestController()
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Integer id) {
        Categoria categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok().body(categoria);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> buscarCategoria() {
        List<Categoria> categoria = categoriaService.buscarCategoria();
        return ResponseEntity.ok().body(categoria);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> adicionarCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok().body(categoriaService.salvarCategoria(categoria));
    }


    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable("id") Integer id) {
        return categoriaService.deletarPorId(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> atualizarPorId(@RequestBody Categoria categoria) {
        Categoria categoriaAtualizada = categoriaService.atualizarNome(categoria);
        return ResponseEntity.ok().body(categoriaAtualizada);
    }


}
