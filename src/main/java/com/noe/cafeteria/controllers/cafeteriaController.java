package com.noe.cafeteria.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noe.cafeteria.model.cafeteria;
import com.noe.cafeteria.repository.cafeteriaRepository;

@RestController
@RequestMapping ("/cafeteria")
public class cafeteriaController {

    @Autowired
    private cafeteriaRepository cafeteriaRepository;

// metodo get para mostrar clientes
    @GetMapping("/mostar-cafeteria")
    public List<cafeteria> mostrarCafeteria () {
        return cafeteriaRepository.findAll();
    }
//metodo para insertar un cliente a la BD
@PostMapping("/insertar-cliente")
public cafeteria insertarCafeteria(@RequestBody cafeteria cliente) {
    return cafeteriaRepository.save(cliente);
}
}
