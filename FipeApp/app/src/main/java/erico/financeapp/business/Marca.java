package erico.financeapp.business;

/**
 * Created by erico on 10/06/16.
 */
public class Marca {

    private int id;
    private String descricao;

    public Marca() {}

    public Marca(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
