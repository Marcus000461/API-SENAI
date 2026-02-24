package br.com.senai.dev_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.senai.dev_store.entity.Product;
import br.com.senai.dev_store.exception.Response;
import br.com.senai.dev_store.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping // localhost:8080/products/create
    public Product criaProduto(@RequestBody Product entity) {
        Product saved = repository.save(entity);
        return saved;
    }

    @GetMapping
    public List<Product> retornaTodos() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public Response atualizaProduto(@PathVariable Long id, @RequestBody Product entity) {

        if (!repository.existsById(id)) {
            return new Response(404, "Não encontrado");
        }

        Product productAntigo = repository.findById(id).get();

        if (entity.getDataCadastro() != null) {
            productAntigo.setDataCadastro(entity.getDataCadastro());

        }

        productAntigo.setPreco(entity.getPreco());
        if(entity.getPreco() !=null){
             productAntigo.setPreco(entity.getPreco());
        }

        if (entity.getDescricao() != null) {
            productAntigo.setDescricao(entity.getDescricao());
        }

        repository.save(productAntigo);

        return new Response(200, "Produto Atualizado");
    }

    @DeleteMapping("/{id}")
    public Response deleteProduto(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return new Response(404, "Não encontrado");
        }

        repository.deleteById(id);

       return new Response(204, "Produto deletado");
    }

}
