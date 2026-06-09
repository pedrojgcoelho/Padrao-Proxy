package padroesestruturais.proxy;

public class LojaBicicletasProxyDemo {

    public static void main(String[] args) {
        EstoqueBicicletasProxy estoqueProxy = new EstoqueBicicletasProxy();
        LojaBicicletas loja = new LojaBicicletas(estoqueProxy);

        UsuarioLoja cliente = new UsuarioLoja("João", PerfilUsuario.CLIENTE);
        UsuarioLoja vendedor = new UsuarioLoja("Carlos", PerfilUsuario.VENDEDOR);
        UsuarioLoja gerente = new UsuarioLoja("Ana", PerfilUsuario.GERENTE);

        System.out.println("Estoque real carregado? " + estoqueProxy.isEstoqueRealCarregado());

        System.out.println(loja.consultarEstoque("Mountain Bike Aro 29"));
        System.out.println(loja.consultarEstoque("Mountain Bike Aro 29"));

        System.out.println(loja.reservarBicicleta(cliente, "Mountain Bike Aro 29"));
        System.out.println(loja.reservarBicicleta(vendedor, "Mountain Bike Aro 29"));

        System.out.println(loja.cadastrarBicicleta(vendedor, "Gravel Bike", 2));
        System.out.println(loja.cadastrarBicicleta(gerente, "Gravel Bike", 2));

        System.out.println(loja.consultarEstoque("Gravel Bike"));

        System.out.println("Estoque real carregado? " + estoqueProxy.isEstoqueRealCarregado());
        System.out.println("Acessos ao estoque real: " + estoqueProxy.getQuantidadeAcessosAoEstoqueReal());
    }
}
