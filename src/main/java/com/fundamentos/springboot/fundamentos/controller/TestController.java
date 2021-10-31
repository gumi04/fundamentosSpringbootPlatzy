package com.fundamentos.springboot.fundamentos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping  //sirve para aceptar todas las solicitudes dentro de este metodo
    @ResponseBody //para responder un cuerpo a nivel de servicio
    public ResponseEntity<String> function(){
        return new ResponseEntity<>("hola desde controlador", HttpStatus.OK);
    }
}
