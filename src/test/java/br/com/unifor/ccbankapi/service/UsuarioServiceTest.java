/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.unifor.ccbankapi.service;

import br.com.unifor.ccbankapi.domain.Usuario;
import br.com.unifor.ccbankapi.exception.ErroNegocioException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author wander
 */

@Disabled
public class UsuarioServiceTest {

    public UsuarioServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of inserir method, of class UsuarioService.
     */
    @Disabled
    @DisplayName("Deve retornar erro FALSE ao tentar inserir uma Usuario NULL")
    public void testInserir_Usuario() throws Exception {
        System.out.println("inserir");
        String mensagem = "Usuario informada é nula!";
        Usuario Usuario = null;
        UsuarioService instance = new UsuarioService();
        ErroNegocioException erro = assertThrows(ErroNegocioException.class, () -> instance.inserir(Usuario));
        assertEquals(mensagem, erro.getMessage());

        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of inserir method, of class UsuarioService.
     */
    @Test
    public void testInserir_String() throws Exception {
        System.out.println("inserir");
        String UsuarioJson = "";
        UsuarioService instance = new UsuarioService();
        boolean expResult = false;
        Usuario result = instance.inserir(UsuarioJson);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editar method, of class UsuarioService.
     */
    @org.junit.jupiter.api.Test
    public void testEditar() throws ErroNegocioException {
        System.out.println("editar");
        Usuario Usuario = null;
        UsuarioService instance = new UsuarioService();
        instance.editar(Usuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

  

    /**
     * Test of excluir method, of class UsuarioService.
     */
    @org.junit.jupiter.api.Test
    public void testExcluir() throws ErroNegocioException {
        System.out.println("excluir");
        Usuario Usuario = null;
        UsuarioService instance = new UsuarioService();
        instance.excluir(Usuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
