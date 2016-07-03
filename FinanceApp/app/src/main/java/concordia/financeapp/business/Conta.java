package concordia.financeapp.business;

import com.orm.SugarRecord;

public class Conta extends SugarRecord {

    private String nome;
    private Double saldoInicial;

    public Conta() {

    }

    public Conta(String nome) {
        super();
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Conta) {
            if (getId() == null) {
                return this == o;
            }
            return getId().equals(((Conta) o).getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getId() == null ? super.hashCode() : getId().hashCode();
    }
}
