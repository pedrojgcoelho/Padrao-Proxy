package padroesestruturais.proxy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LojaBicicletasProxyTest {

    @Test
    public void deveCarregarEstoqueRealSomenteQuandoNecessario() {
        EstoqueBicicletasProxy proxy = new EstoqueBicicletasProxy();

        assertFalse(proxy.isEstoqueRealCarregado());

        proxy.consultarDisponibilidade("Mountain Bike Aro 29");

        assertTrue(proxy.isEstoqueRealCarregado());
    }

    @Test
    public void deveConsultarDisponibilidadePeloProxy() {
        EstoqueBicicletasProxy proxy = new EstoqueBicicletasProxy();
        LojaBicicletas loja = new LojaBicicletas(proxy);

        assertEquals(
                "Estoque disponível para Mountain Bike Aro 29: 2 unidade(s).",
                loja.consultarEstoque("Mountain Bike Aro 29")
        );
    }

    @Test
    public void deveUsarCacheNaSegundaConsulta() {
        EstoqueBicicletasProxy proxy = new EstoqueBicicletasProxy();

        assertEquals(
                "Estoque disponível para Mountain Bike Aro 29: 2 unidade(s).",
                proxy.consultarDisponibilidade("Mountain Bike Aro 29")
        );

        assertEquals(
                "Estoque disponível para Mountain Bike Aro 29: 2 unidade(s).",
                proxy.consultarDisponibilidade("Mountain Bike Aro 29")
        );

        assertEquals(1, proxy.getQuantidadeAcessosAoEstoqueReal());
    }

    @Test
    public void naoDevePermitirClienteReservarBicicleta() {
        EstoqueBicicletasProxy proxy = new EstoqueBicicletasProxy();
        LojaBicicletas loja = new LojaBicicletas(proxy);
        UsuarioLoja cliente = new UsuarioLoja("João", PerfilUsuario.CLIENTE);

        assertEquals(
                "Acesso negado. Apenas vendedor ou gerente pode reservar bicicletas.",
                loja.reservarBicicleta(cliente, "Mountain Bike Aro 29")
        );
    }

    @Test
    public void devePermitirVendedorReservarBicicleta() {
        EstoqueBicicletasProxy proxy = new EstoqueBicicletasProxy();
        LojaBicicletas loja = new LojaBicicletas(proxy);
        UsuarioLoja vendedor = new UsuarioLoja("Carlos", PerfilUsuario.VENDEDOR);

        assertEquals(
                "Reserva realizada para Carlos: Mountain Bike Aro 29.",
                loja.reservarBicicleta(vendedor, "Mountain Bike Aro 29")
        );

        assertEquals(1, proxy.getQuantidade("Mountain Bike Aro 29"));
    }

    @Test
    public void naoDevePermitirVendedorCadastrarBicicleta() {
        EstoqueBicicletasProxy proxy = new EstoqueBicicletasProxy();
        LojaBicicletas loja = new LojaBicicletas(proxy);
        UsuarioLoja vendedor = new UsuarioLoja("Carlos", PerfilUsuario.VENDEDOR);

        assertEquals(
                "Acesso negado. Apenas gerente pode cadastrar bicicletas no estoque.",
                loja.cadastrarBicicleta(vendedor, "Gravel Bike", 2)
        );
    }

    @Test
    public void devePermitirGerenteCadastrarBicicleta() {
        EstoqueBicicletasProxy proxy = new EstoqueBicicletasProxy();
        LojaBicicletas loja = new LojaBicicletas(proxy);
        UsuarioLoja gerente = new UsuarioLoja("Ana", PerfilUsuario.GERENTE);

        assertEquals(
                "Cadastro realizado por Ana: 2 unidade(s) da bicicleta Gravel Bike.",
                loja.cadastrarBicicleta(gerente, "Gravel Bike", 2)
        );

        assertEquals(2, proxy.getQuantidade("Gravel Bike"));
    }

    @Test
    public void devePermitirGerenteRemoverBicicleta() {
        EstoqueBicicletasProxy proxy = new EstoqueBicicletasProxy();
        LojaBicicletas loja = new LojaBicicletas(proxy);
        UsuarioLoja gerente = new UsuarioLoja("Ana", PerfilUsuario.GERENTE);

        assertEquals(
                "Remoção realizada por Ana: 1 unidade(s) da bicicleta Bicicleta Urbana.",
                loja.removerBicicleta(gerente, "Bicicleta Urbana", 1)
        );

        assertEquals(2, proxy.getQuantidade("Bicicleta Urbana"));
    }

    @Test
    public void deveLimparCacheAposReserva() {
        EstoqueBicicletasProxy proxy = new EstoqueBicicletasProxy();
        LojaBicicletas loja = new LojaBicicletas(proxy);
        UsuarioLoja vendedor = new UsuarioLoja("Carlos", PerfilUsuario.VENDEDOR);

        assertEquals(
                "Estoque disponível para Mountain Bike Aro 29: 2 unidade(s).",
                loja.consultarEstoque("Mountain Bike Aro 29")
        );

        assertEquals(
                "Reserva realizada para Carlos: Mountain Bike Aro 29.",
                loja.reservarBicicleta(vendedor, "Mountain Bike Aro 29")
        );

        assertEquals(
                "Estoque disponível para Mountain Bike Aro 29: 1 unidade(s).",
                loja.consultarEstoque("Mountain Bike Aro 29")
        );
    }
}
