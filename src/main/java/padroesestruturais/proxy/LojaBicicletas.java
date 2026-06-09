package padroesestruturais.proxy;

public class LojaBicicletas {

    private EstoqueBicicletas estoque;

    public LojaBicicletas(EstoqueBicicletas estoque) {
        this.estoque = estoque;
    }

    public String consultarEstoque(String modeloBicicleta) {
        return estoque.consultarDisponibilidade(modeloBicicleta);
    }

    public String reservarBicicleta(UsuarioLoja usuario, String modeloBicicleta) {
        if (!usuario.isVendedorOuGerente()) {
            return "Acesso negado. Apenas vendedor ou gerente pode reservar bicicletas.";
        }

        return estoque.reservarBicicleta(usuario.getNome(), modeloBicicleta);
    }

    public String cadastrarBicicleta(UsuarioLoja usuario, String modeloBicicleta, int quantidade) {
        return estoque.cadastrarBicicleta(usuario, modeloBicicleta, quantidade);
    }

    public String removerBicicleta(UsuarioLoja usuario, String modeloBicicleta, int quantidade) {
        return estoque.removerBicicleta(usuario, modeloBicicleta, quantidade);
    }

    public EstoqueBicicletas getEstoque() {
        return estoque;
    }
}
