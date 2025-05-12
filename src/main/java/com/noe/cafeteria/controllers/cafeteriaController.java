package com.noe.cafeteria.controllers;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
//metodo para editar un cliente en la base de datos
@PutMapping ("/editar-cliente/{id}") 
public ResponseEntity<cafeteria> actualizarcliente (@PathVariable Long id, @RequestBody cafeteria cliente) {
return cafeteriaRepository.findById(id).map(clienteExiste ->{
    clienteExiste.setNombre(cliente.getNombre());
    clienteExiste.setApellido(cliente.getApellido());
    clienteExiste.setTelefono(cliente.getTelefono());
    clienteExiste.setEmail(cliente.getEmail());
    clienteExiste.setImagenURL(cliente.getImagenURL());
    cafeteria actualizado = cafeteriaRepository.save(clienteExiste);
    return ResponseEntity.ok(actualizado);
}).orElse(ResponseEntity.notFound().build());
}
}

