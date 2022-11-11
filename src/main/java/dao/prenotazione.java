package dao;

public class prenotazione {
    String nome_corso;
    String username_utente;
    String username_docente;
    String giorno;
    int ora;
    int stato_prenotazione;
    int id_prenotazione;

    public prenotazione(String nome_corso, String username_utente, String username_docente, String giorno, int ora, int stato_prenotazione, int id_prenotazione) {
        this.nome_corso = nome_corso;
        this.username_utente = username_utente;
        this.username_docente = username_docente;
        this.giorno = giorno;
        this.ora = ora;
        this.stato_prenotazione = stato_prenotazione;
        this.id_prenotazione = id_prenotazione;
    }

    public String getNome_corso() {
        return nome_corso;
    }

    public void setNome_corso(String nome_corso) {
        this.nome_corso = nome_corso;
    }

    public String getUsername_utente() {
        return username_utente;
    }

    public void setUsername_utente(String username_utente) {
        this.username_utente = username_utente;
    }

    public String getUsername_docente() {
        return username_docente;
    }

    public void setUsername_docente(String username_docente) {
        this.username_docente = username_docente;
    }

    public String getGiorno() {
        return giorno;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }

    public int getOra() {
        return ora;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }

    public int getStato_prenotazione() {
        return stato_prenotazione;
    }

    public void setStato_prenotazione(int stato_prenotazione) {
        this.stato_prenotazione = stato_prenotazione;
    }

    public int getId_prenotazione() {
        return id_prenotazione;
    }

    public void setId_prenotazione(int id_prenotazione) {
        this.id_prenotazione = id_prenotazione;
    }

    @Override
    public String toString() {
        return "prenotazione{" +
                "nome_corso='" + nome_corso + '\'' +
                ", username_utente='" + username_utente + '\'' +
                ", username_docente='" + username_docente + '\'' +
                ", giorno='" + giorno + '\'' +
                ", ora=" + ora +
                ", stato_prenotazione=" + stato_prenotazione +
                ", id_prenotazione=" + id_prenotazione +
                '}';
    }
}
