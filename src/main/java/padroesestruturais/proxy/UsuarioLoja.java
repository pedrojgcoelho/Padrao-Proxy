package padroesestruturais.proxy;

public class UsuarioLoja {

    private String nome;
    private PerfilUsuario perfil;

    public UsuarioLoja(String nome, PerfilUsuario perfil) {
        this.nome = nome;
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public boolean isGerente() {
        return PerfilUsuario.GERENTE.equals(perfil);
    }

    public boolean isVendedorOuGerente() {
        return PerfilUsuario.VENDEDOR.equals(perfil) || PerfilUsuario.GERENTE.equals(perfil);
    }
}
