package com.willyan.spring_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.willyan.spring_jpa.entity.Autor;
import com.willyan.spring_jpa.entity.InfoAutor;
import com.willyan.spring_jpa.projection.AutorInfoProjection;
import com.willyan.spring_jpa.service.AutorService;

import java.util.List;

@RestController
@RequestMapping("autores")
public class AutorController {

    @Autowired
    private AutorService dao;

    @PostMapping
    public Autor salvar(@RequestBody Autor autor) {
        dao.save(autor);
        return autor;
    }

    @PutMapping
    public Autor atualizar(@RequestBody Autor autor) {
        dao.update(autor);
        return autor;
    }

    @DeleteMapping("{id}")
    public String remover(@PathVariable Long id) {
        dao.delete(id);
        return "Autor id = " + id + " foi excluído com sucesso";
    }

    @GetMapping("{id}")
    public Autor getById(@PathVariable Long id) {
        return dao.findById(id);
    }

    @GetMapping
    public List<Autor> getAll() {
        return dao.findAll();
    }

    @GetMapping("nomeOrSobrenome")
    public List<Autor> getAutoresByNomeOrSobrenome(@RequestParam String termo) {
        return dao.findAllByNomeOrSobrenome(termo);
    }

    @GetMapping("total")
    public Long getTotalDeAutores() {
        return dao.getTotalElements();
    }

    @PutMapping("{id}/info")
    public Autor salvarInfoAutor(@PathVariable Long id, @RequestBody InfoAutor infoAutor) {
        return dao.saveInfoAutor(infoAutor, id);
    }

    @GetMapping("info")
    public List<Autor> getByCargo(@RequestParam String cargo) {
        return dao.findByCargo(cargo);
    }

    @GetMapping("autor-info")
    public AutorInfoProjection salvarInfoAutor(@RequestParam Long id) {
        return dao.findAutorInfoById(id);
    }


}
