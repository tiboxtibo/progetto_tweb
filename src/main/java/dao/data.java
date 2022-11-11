package dao;

public class data {
    String giorno;
    int ora;

    public data(String giorno, int ora) {
        this.giorno = giorno;
        this.ora = ora;
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

    @Override
    public String toString() {
        return "data{" +
                "giorno='" + giorno + '\'' +
                ", ora=" + ora +
                '}';
    }
}
