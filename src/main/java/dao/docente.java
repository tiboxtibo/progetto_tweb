package dao;

public class docente {
    String nome_docente;
    String cognome_docente;
    String username_docente;

    public String getNome_docente() {
        return nome_docente;
    }

    public void setNome_docente(String nome_docente) {
        this.nome_docente = nome_docente;
    }

    public String getCognome_docente() {
        return cognome_docente;
    }

    public void setCognome_docente(String cognome_docente) {
        this.cognome_docente = cognome_docente;
    }

    public String getUsername_docente() {
        return username_docente;
    }

    public void setUsername_docente(String username_docente) {
        this.username_docente = username_docente;
    }

    public docente(String nome_docente, String cognome_docente, String username_docente) {
        this.nome_docente = nome_docente;
        this.cognome_docente = cognome_docente;
        this.username_docente = username_docente;
    }

    @Override
    public String toString() {
        return "docenti{" +
                "nome_docente='" + nome_docente + '\'' +
                ", cognome_docente='" + cognome_docente + '\'' +
                ", username_docente='" + username_docente + '\'' +
                '}';
    }
}
