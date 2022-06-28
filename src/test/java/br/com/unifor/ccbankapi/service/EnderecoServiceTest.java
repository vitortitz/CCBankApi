/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.unifor.ccbankapi.service;

import br.com.unifor.ccbankapi.domain.Endereco;
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
public class EnderecoServiceTest {

    public EnderecoServiceTest() {
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
     * Test of inserir method, of class EnderecoService.
     */
    @Disabled
    @DisplayName("Deve retornar erro FALSE ao tentar inserir uma Endereco NULL")
    public void testInserir_Endereco() throws Exception {
        System.out.println("inserir");
        String mensagem = "Endereco informada é nula!";
        Endereco Endereco = null;
        EnderecoService instance = new EnderecoService();
        ErroNegocioException erro = assertThrows(ErroNegocioException.class, () -> instance.inserir(Endereco));
        assertEquals(mensagem, erro.getMessage());

        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of inserir method, of class EnderecoService.
     */
    @Test
    public void testInserir_String() throws Exception {
        System.out.println("inserir");
        String EnderecoJson = "";
        EnderecoService instance = new EnderecoService();
        boolean expResult = false;
        Endereco result = instance.inserir(EnderecoJson);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editar method, of class EnderecoService.
     */
    @org.junit.jupiter.api.Test
    public void testEditar() throws ErroNegocioException {
        System.out.println("editar");
        Endereco Endereco = null;
        EnderecoService instance = new EnderecoService();
        instance.editar(Endereco);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTodasEnderecos method, of class EnderecoService.
     */
    @org.junit.jupiter.api.Test
    public void testGetTodasEnderecos() {
        System.out.println("getTodasEnderecos");
        EnderecoService instance = new EnderecoService();
        List<Endereco> expResult = null;
        List<Endereco> result = instance.getTodasEnderecos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluir method, of class EnderecoService.
     */
    @org.junit.jupiter.api.Test
    public void testExcluir() throws ErroNegocioException {
        System.out.println("excluir");
        Endereco Endereco = null;
        EnderecoService instance = new EnderecoService();
        instance.excluir(Endereco);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
