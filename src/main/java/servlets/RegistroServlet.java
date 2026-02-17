/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import models.Usuario;
import repositories.UsuarioDB;

/**
 *
 * @author angel
 */
@WebServlet(name = "RegistroServlet", urlPatterns = {"/registro"})
public class RegistroServlet extends HttpServlet {
    
    protected void  doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String nombre = request.getParameter("txt_name");
        String correo = request.getParameter("txt_correo");
        String contrasenia = request.getParameter("txt_contrasenia");
        String pseudonimo = request.getParameter("txt_pseudonimo");
        String estado = request.getParameter("rad_estado");
        String tipoCuenta = request.getParameter("sel_tipoCuenta");
        String fechaNacimientoSt = request.getParameter("txt_fecha_nacimiento");
        String terminos = request.getParameter("chk_terminos");
        
        if (nombre == null || correo == null) {
            response.sendRedirect("error.html");
            return;
        }
         // Valida si el correo existe, si existe no se puede realizar un nuevo registro por que ya existe
        if (UsuarioDB.existeCorreo(correo)) {
            response.sendRedirect("error.html");
            return;
        }
        
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoSt);
        boolean aceptoTerminos = terminos.equals("aceptado");
        
        Usuario usuario = new Usuario(nombre, correo, contrasenia, pseudonimo, estado, tipoCuenta, fechaNacimiento, aceptoTerminos);
     
        UsuarioDB.registrar(usuario);
        
        response.sendRedirect("iniciar-sesion.html");
        
        
    }
    



}
