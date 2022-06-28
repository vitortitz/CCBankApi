/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.unifor.ccbankapi.service;

import br.com.unifor.ccbankapi.domain.Pessoa;
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
public class PessoaServiceTest {

    public PessoaServiceTest() {
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
     * Test of inserir method, of class PessoaService.
     */
    @Disabled
    @DisplayName("Deve retornar erro FALSE ao tentar inserir uma Pessoa NULL")
    public void testInserir_Pessoa() throws Exception {
        System.out.println("inserir");
        String mensagem = "Pessoa informada é nula!";
        Pessoa Pessoa = null;
        PessoaService instance = new PessoaService();
        ErroNegocioException erro = assertThrows(ErroNegocioException.class, () -> instance.inserir(Pessoa));
        assertEquals(mensagem, erro.getMessage());

        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of inserir method, of class PessoaService.
     */
    @Test
    public void testInserir_String() throws Exception {
        System.out.println("inserir");
        String PessoaJson = "";
        PessoaService instance = new PessoaService();
        boolean expResult = false;
        Pessoa result = instance.inserir(PessoaJson);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editar method, of class PessoaService.
     */
    @org.junit.jupiter.api.Test
    public void testEditar() throws ErroNegocioException {
        System.out.println("editar");
        Pessoa Pessoa = null;
        PessoaService instance = new PessoaService();
        instance.editar(Pessoa);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   

    /**
     * Test of excluir method, of class PessoaService.
     */
    @org.junit.jupiter.api.Test
    public void testExcluir() throws ErroNegocioException {
        System.out.println("excluir");
        Pessoa Pessoa = null;
        PessoaService instance = new PessoaService();
        instance.excluir(Pessoa);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
