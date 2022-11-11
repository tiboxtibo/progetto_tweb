package dao;

public class corsi {

    private String nome_corso;

    public corsi(String nome_corso) {
        this.nome_corso = nome_corso;
    }

    public String getNome_corso() {
        return nome_corso;
    }

    public void setNome_corso(String nome_corso) {
        this.nome_corso = nome_corso;
    }

    @Override
    public String toString() {
        return "corsi{" +
                "nome_corso='" + nome_corso + '\'' +
                '}';
    }
}
