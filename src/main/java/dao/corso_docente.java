package dao;

public class corso_docente {
    String username_docente;
    String nome_corso;

    public corso_docente(String username_docente, String nome_corso) {
        this.username_docente = username_docente;
        this.nome_corso = nome_corso;
    }

    public String getUsername_docente() {
        return username_docente;
    }

    public void setUsername_docente(String username_docente) {
        this.username_docente = username_docente;
    }

    public String getNome_corso() {
        return nome_corso;
    }

    public void setNome_corso(String nome_corso) {
        this.nome_corso = nome_corso;
    }

    @Override
    public String toString() {
        return "corso_docente{" +
                "username_docente='" + username_docente + '\'' +
                ", nome_corso='" + nome_corso + '\'' +
                '}';
    }
}
