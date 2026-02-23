package br.com.senai.dev_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.dev_store.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

