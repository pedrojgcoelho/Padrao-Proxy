package padroesestruturais.proxy;

import java.util.HashMap;
import java.util.Map;

public class EstoqueBicicletasProxy implements EstoqueBicicletas {

    private EstoqueRealBicicletas estoqueReal;
    private Map<String, String> cacheConsultas;
    private int quantidadeAcessosAoEstoqueReal;

    public EstoqueBicicletasProxy() {
        this.cacheConsultas = new HashMap<>();
        this.quantidadeAcessosAoEstoqueReal = 0;
    }

    private EstoqueRealBicicletas getEstoqueReal() {
        if (estoqueReal == null) {
            estoqueReal = new EstoqueRealBicicletas();
        }

        return estoqueReal;
    }

    @Override
    public String consultarDisponibilidade(String modeloBicicleta) {
        if (cacheConsultas.containsKey(modeloBicicleta)) {
            return cacheConsultas.get(modeloBicicleta);
        }

        quantidadeAcessosAoEstoqueReal++;

        String resultado = getEstoqueReal().consultarDisponibilidade(modeloBicicleta);
        cacheConsultas.put(modeloBicicleta, resultado);

        return resultado;
    }

    @Override
    public String reservarBicicleta(String cliente, String modeloBicicleta) {
        quantidadeAcessosAoEstoqueReal++;

        String resultado = getEstoqueReal().reservarBicicleta(cliente, modeloBicicleta);
        limparCacheDoModelo(modeloBicicleta);

        return resultado;
    }

    @Override
    public String cadastrarBicicleta(UsuarioLoja usuario, String modeloBicicleta, int quantidade) {
        if (!usuario.isGerente()) {
            return "Acesso negado. Apenas gerente pode cadastrar bicicletas no estoque.";
        }

        quantidadeAcessosAoEstoqueReal++;

        String resultado = getEstoqueReal().cadastrarBicicleta(usuario, modeloBicicleta, quantidade);
        limparCacheDoModelo(modeloBicicleta);

        return resultado;
    }

    @Override
    public String removerBicicleta(UsuarioLoja usuario, String modeloBicicleta, int quantidade) {
        if (!usuario.isGerente()) {
            return "Acesso negado. Apenas gerente pode remover bicicletas do estoque.";
        }

        quantidadeAcessosAoEstoqueReal++;

        String resultado = getEstoqueReal().removerBicicleta(usuario, modeloBicicleta, quantidade);
        limparCacheDoModelo(modeloBicicleta);

        return resultado;
    }

    @Override
    public int getQuantidade(String modeloBicicleta) {
        quantidadeAcessosAoEstoqueReal++;
        return getEstoqueReal().getQuantidade(modeloBicicleta);
    }

    public boolean isEstoqueRealCarregado() {
        return estoqueReal != null;
    }

    public int getQuantidadeAcessosAoEstoqueReal() {
        return quantidadeAcessosAoEstoqueReal;
    }

    private void limparCacheDoModelo(String modeloBicicleta) {
        cacheConsultas.remove(modeloBicicleta);
    }
}
