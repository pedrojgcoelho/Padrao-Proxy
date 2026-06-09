package padroesestruturais.proxy;

import java.util.HashMap;
import java.util.Map;

public class EstoqueRealBicicletas implements EstoqueBicicletas {

    private Map<String, Integer> bicicletas;

    public EstoqueRealBicicletas() {
        this.bicicletas = new HashMap<>();
        carregarEstoqueInicial();
    }

    private void carregarEstoqueInicial() {
        bicicletas.put("Mountain Bike Aro 29", 2);
        bicicletas.put("Speed Bike Carbon", 1);
        bicicletas.put("Bicicleta Urbana", 3);
    }

    @Override
    public String consultarDisponibilidade(String modeloBicicleta) {
        int quantidade = getQuantidade(modeloBicicleta);

        if (quantidade > 0) {
            return "Estoque disponível para " + modeloBicicleta
                    + ": " + quantidade + " unidade(s).";
        }

        return "Estoque indisponível para " + modeloBicicleta + ".";
    }

    @Override
    public String reservarBicicleta(String cliente, String modeloBicicleta) {
        if (getQuantidade(modeloBicicleta) <= 0) {
            return "Reserva não realizada. Bicicleta indisponível: " + modeloBicicleta + ".";
        }

        bicicletas.put(modeloBicicleta, getQuantidade(modeloBicicleta) - 1);

        return "Reserva realizada para " + cliente
                + ": " + modeloBicicleta + ".";
    }

    @Override
    public String cadastrarBicicleta(UsuarioLoja usuario, String modeloBicicleta, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        bicicletas.put(modeloBicicleta, getQuantidade(modeloBicicleta) + quantidade);

        return "Cadastro realizado por " + usuario.getNome()
                + ": " + quantidade + " unidade(s) da bicicleta "
                + modeloBicicleta + ".";
    }

    @Override
    public String removerBicicleta(UsuarioLoja usuario, String modeloBicicleta, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        if (getQuantidade(modeloBicicleta) < quantidade) {
            return "Remoção não realizada. Quantidade insuficiente para "
                    + modeloBicicleta + ".";
        }

        bicicletas.put(modeloBicicleta, getQuantidade(modeloBicicleta) - quantidade);

        return "Remoção realizada por " + usuario.getNome()
                + ": " + quantidade + " unidade(s) da bicicleta "
                + modeloBicicleta + ".";
    }

    @Override
    public int getQuantidade(String modeloBicicleta) {
        return bicicletas.getOrDefault(modeloBicicleta, 0);
    }
}
