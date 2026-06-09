package padroesestruturais.proxy;

public interface EstoqueBicicletas {

    String consultarDisponibilidade(String modeloBicicleta);

    String reservarBicicleta(String cliente, String modeloBicicleta);

    String cadastrarBicicleta(UsuarioLoja usuario, String modeloBicicleta, int quantidade);

    String removerBicicleta(UsuarioLoja usuario, String modeloBicicleta, int quantidade);

    int getQuantidade(String modeloBicicleta);
}
