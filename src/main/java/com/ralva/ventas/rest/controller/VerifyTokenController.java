package com.ralva.ventas.rest.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyTokenController {
    @RequestMapping("/token")
    public String token() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Hola si funciona el token de acceso!";
    }

    @RequestMapping("/admin")
    public String admin() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Hola bienvenido Admin!";
    }

    @RequestMapping("/user")
    public String user() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Hola bienvenido User!";
    }
}
