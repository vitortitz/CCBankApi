/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.unifor.ccbankapi.service;

import br.com.unifor.ccbankapi.domain.Conta;
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
public class ContaServiceTest {

    public ContaServiceTest() {
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
     * Test of inserir method, of class ContaService.
     */
    @Disabled
    @DisplayName("Deve retornar erro FALSE ao tentar inserir uma Conta NULL")
    public void testInserir_Conta() throws Exception {
        System.out.println("inserir");
        String mensagem = "Conta informada é nula!";
        Conta Conta = null;
        ContaService instance = new ContaService();
        ErroNegocioException erro = assertThrows(ErroNegocioException.class, () -> instance.inserir(Conta));
        assertEquals(mensagem, erro.getMessage());

        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of inserir method, of class ContaService.
     */
    @Test
    public void testInserir_String() throws Exception {
        System.out.println("inserir");
        String ContaJson = "";
        ContaService instance = new ContaService();
        boolean expResult = false;
        Conta result = instance.inserir(ContaJson);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editar method, of class ContaService.
     */
    @org.junit.jupiter.api.Test
    public void testEditar() throws ErroNegocioException {
        System.out.println("editar");
        Conta Conta = null;
        ContaService instance = new ContaService();
        instance.editar(Conta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTodasContas method, of class ContaService.
     */
    @org.junit.jupiter.api.Test
    public void testGetTodasContas() {
        System.out.println("getTodasContas");
        ContaService instance = new ContaService();
        List<Conta> expResult = null;
        List<Conta> result = instance.getTodasContas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluir method, of class ContaService.
     */
    @org.junit.jupiter.api.Test
    public void testExcluir() throws ErroNegocioException {
        System.out.println("excluir");
        Conta Conta = null;
        ContaService instance = new ContaService();
        instance.excluir(Conta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
