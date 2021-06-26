package projeto.crud.model;

public enum TipoPerfil {

    ADMIN("ADMIN"), SOMENTELEITURA("SomenteLeirura");

    private String descricao;

    TipoPerfil(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
