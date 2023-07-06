package com.ar.ndifabio.reservaDeTurnos.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {


    @GetMapping("/adminsIndex")
    public String adminsIndex(Authentication authentication) {
        if (authentication != null && authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
            // Lógica para mostrar contenido para el rol de administrador

            return "adminsIndex";
        } else {
            return "redirect:/denied-page"; // Redirecciona a una página de acceso denegado si el usuario no tiene el rol adecuado
        }
    }

    @GetMapping("/odontologosList")
    public String showodonto(){
        return "odontologosList.html";
    }
    @GetMapping("/pacientesList")
    public String pacientesList(Authentication authentication) {
        if (authentication != null && authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
            // Lógica para mostrar contenido para el rol de administrador

            return "pacientesList";
        } else {
            return "redirect:/denied-page"; // Redirecciona a una página de acceso denegado si el usuario no tiene el rol adecuado
        }
    }
    @GetMapping("/usersIndex")
    public String showToUsers(){
        return "usersIndex.html";
    }
    /*@GetMapping("/adminsIndex")
    public String showToAdmins(){
        return "adminsIndex.html";
    }*/

    @GetMapping("/denied-page")
    public String showDenied(){
        return "denied-page.html";
    }


}
