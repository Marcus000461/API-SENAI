package br.com.senai.dev_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.senai.dev_store.entity.Product;
import br.com.senai.dev_store.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping // localhost:8080/products/create
    public Product criaProduto(@RequestBody Product entity){
        Product saved = repository.save(entity);
        return saved;
    }

    @GetMapping
    public List<Product> retornaTodos(){
        return repository.findAll();
    } 

    @PutMapping("/{id}")
    public Product atualizaProduto(@PathVariable Long id, @RequestBody Product entity){
        Product productAntigo = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        productAntigo.setDescricao(entity.getDescricao());
        productAntigo.setDataCadastro(entity.getDataCadastro());
        productAntigo.setPreco(entity.getPreco());


        return repository.save(productAntigo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id){
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
