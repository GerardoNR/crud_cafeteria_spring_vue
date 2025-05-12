package com.noe.cafeteria.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noe.cafeteria.model.cafeteria;
import com.noe.cafeteria.repository.cafeteriaRepository;

@RestController
@RequestMapping ("/cafeteria")
public class cafeteriaController {

    @Autowired
    private cafeteriaRepository cafeteriaRepository;

    @GetMapping("/mostar-cafeteria")
    public List<cafeteria> mostrarCafeteria () {
        return cafeteriaRepository.findAll();
    }

}
