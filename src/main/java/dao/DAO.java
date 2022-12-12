package dao;

import java.sql.*;
import java.util.ArrayList;


public class DAO {

    private String url1;
    private String user;
    private String password ;

    public DAO(String url1,String user,String password) {
        this.url1 = url1;
        this.user = user;
        this.password =password;

        registerDriver();
    }

    public static void registerDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("Driver correttamente registrato");
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public boolean add_utente(String username_utente, String password_utente) {
        Connection conn1 = null;
        ArrayList<docente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            String ruolo = "0";
            Statement st = conn1.createStatement();
            st.executeUpdate("INSERT INTO `utente`(`username_utente`,`password`,`ruolo`) VALUES ('" + username_utente + "','" + password_utente + "','" + ruolo + "')");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

        return false;
    }

    public boolean add_corso(String nome_corso) {
        Connection conn1 = null;
        ArrayList<corso> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            st.executeUpdate("INSERT INTO `corso`(`nome_corso`) VALUES ('" + nome_corso + "')");
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

        return false;
    }

    public boolean del_corso(String nome_corso) {
        Connection conn1 = null;
        ArrayList<corso> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            st.executeUpdate("DELETE FROM `corso` WHERE  nome_corso = ('" + nome_corso + "')");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

        return false;
    }

    public ArrayList<corso> view_corso() {
        Connection conn1 = null;
        ArrayList<corso> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM corso ");

            while (rs.next()) {
                corso c = new corso(rs.getString("nome_corso"));
                out.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public boolean add_docente(String nome_docente, String cognome_docente, String username_docente) {
        Connection conn1 = null;
        ArrayList<docente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            st.executeUpdate("INSERT INTO `docente`(`nome_docente`,`cognome_docente`,`username_docente`) VALUES ('" + nome_docente + "','" + cognome_docente + "','" + username_docente + "')");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

        return false;
    }

    public boolean del_docente(String username_docente) {
        Connection conn1 = null;
        ArrayList<docente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            st.executeUpdate("DELETE FROM docente WHERE username_docente = ('" + username_docente + "')");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

        return false;
    }

    public ArrayList<docente> view_docente() {
        Connection conn1 = null;
        ArrayList<docente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM docente ");

            while (rs.next()) {
                docente d = new docente(rs.getString("nome_docente"), rs.getString("cognome_docente"), rs.getString("username_docente"));
                out.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public boolean add_corso_docente(String username_docente, String nome_corso) {
        Connection conn1 = null;
        ArrayList<corso_docente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st1 = conn1.createStatement();
            ResultSet rs1 = st1.executeQuery("SELECT * FROM corso WHERE  nome_corso = ('" + nome_corso + "') ");

            Statement st2 = conn1.createStatement();
            ResultSet rs2 = st2.executeQuery("SELECT * FROM docente WHERE  username_docente = ('" + username_docente + "') ");

            if(rs1.next() && rs2.next()){
                Statement st = conn1.createStatement();
                st.executeUpdate("INSERT INTO `corso_docente`(`username_docente`,`nome_corso`) VALUES ('" + username_docente + "','" + nome_corso + "')");
                return true;
            }
            else{
                return false;
            }



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return false;
    }

    public boolean del_corso_docente(String username_docente, String nome_corso) {
        Connection conn1 = null;
        ArrayList<corso_docente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            st.executeUpdate("DELETE FROM corso_docente WHERE username_docente = ('" + username_docente + "') and  nome_corso = ('" + nome_corso + "')");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return false;
    }

    public ArrayList<corso_docente> view_corso_docente() {
        Connection conn1 = null;
        ArrayList<corso_docente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM corso_docente ");

            while (rs.next()) {
                corso_docente cd = new corso_docente(rs.getString("username_docente"), rs.getString("nome_corso"));
                out.add(cd);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public ArrayList<String> view_prenotazioniCorso_corso_docente(String nome_corso) {
        Connection conn1 = null;
        ArrayList<String> out = new ArrayList<>();
        //ArrayList<corso_docente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            //ResultSet rs = st.executeQuery("SELECT * FROM corso_docente WHERE nome_corso = ('" + nome_corso + "')");
            ResultSet rs = st.executeQuery("SELECT username_docente FROM corso_docente WHERE nome_corso = ('" + nome_corso + "')");


            while (rs.next()) {
                String cd = new String(rs.getString("username_docente"));
                out.add(cd);
            }
            /*
            while (rs.next()) {
                corso_docente cd = new corso_docente(rs.getString("username_docente"), rs.getString("nome_corso"));
                out.add(cd);
            }}

            */


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public boolean add_prenotazione(String nome_corso, String username_utente, String username_docente, String giorno, int ora) {
        Connection conn1 = null;
        ArrayList<prenotazione> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            int stato_prenotazione=1;

            if(username_utente.equals("guest")){
                return false;
            }

            Statement st2 = conn1.createStatement();
            ResultSet pren = st2.executeQuery("SELECT * FROM prenotazione WHERE username_utente = ('" + username_utente + "') and stato_prenotazione = 1 ");

            ArrayList<String> data3 = new ArrayList<>();

            //metto in un array list i giorni e le ore in cui l'utente è occupato
            while (pren.next()) {
                String giorno2 = pren.getString("giorno");
                int ora2 = pren.getInt("ora");

                data d = new data(giorno2,ora2);

                String data00 = d.toString();

                data3.add(data00);

            }
            data dd = new data(giorno,ora);
            String data01 = dd.toString();
            //System.out.println(data3);

            //se l'utente è gia occupato in quell'ora
            if(data3.contains(data01)){
                return false;
            }
            else{
                Statement st = conn1.createStatement();
                st.executeUpdate("INSERT INTO `prenotazione`(`nome_corso`,`username_utente`,`username_docente`,`giorno`,`ora`,`stato_prenotazione`) VALUES ('" + nome_corso + "','" + username_utente + "','" + username_docente + "','" + giorno + "','" + ora + "','" + stato_prenotazione + "')");
                return true;
            }



        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return false;
    }

    public ArrayList<prenotazione> view_prenotazioni() {
        Connection conn1 = null;
        ArrayList<prenotazione> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM prenotazione ");

            while (rs.next()) {
                prenotazione p = new prenotazione(rs.getString("nome_corso"), rs.getString("username_utente"), rs.getString("username_docente"), rs.getString("giorno"), rs.getInt("ora"), rs.getInt("stato_prenotazione"), rs.getInt("id_prenotazione"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public ArrayList<prenotazione> view_prenotazioni_utente( String username_utente) {
        Connection conn1 = null;
        ArrayList<prenotazione> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM prenotazione WHERE username_utente = ('" + username_utente + "') ");

            while (rs.next()) {
                prenotazione p = new prenotazione(rs.getString("nome_corso"), rs.getString("username_utente"), rs.getString("username_docente"), rs.getString("giorno"), rs.getInt("ora"), rs.getInt("stato_prenotazione"), rs.getInt("id_prenotazione"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public boolean del_prenotazione(String nome_corso, String username_utente, String username_docente, String giorno, int ora, String id_prenotazione) {
        Connection conn1 = null;
        ArrayList<prenotazione> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            st.executeUpdate("UPDATE prenotazione SET stato_prenotazione=-1 WHERE nome_corso = ('" + nome_corso + "') and username_utente = ('" + username_utente + "') and  username_docente = ('" + username_docente + "') and giorno = ('" + giorno + "') and ora = ('" + ora+ "')and id_prenotazione = ('" + id_prenotazione+ "')");

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

        return false;
    }

    public boolean prenota_prenotazione(String nome_corso, String username_utente, String username_docente, String giorno, int ora, String id_prenotazione) {
        Connection conn1 = null;
        ArrayList<prenotazione> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            st.executeUpdate("UPDATE prenotazione SET stato_prenotazione=0 WHERE nome_corso = ('" + nome_corso + "') and username_utente = ('" + username_utente + "') and  username_docente = ('" + username_docente + "') and giorno = ('" + giorno + "') and ora = ('" + ora+ "') and id_prenotazione = ('" + id_prenotazione+ "')");

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

        return false;
    }

    public ArrayList<prenotazione> view_prenotazioni_prenotabili() {
        Connection conn1 = null;
        ArrayList<prenotazione> out = new ArrayList<>();

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();

            ResultSet cor = st.executeQuery("SELECT * FROM corso_docente ");

            while (cor.next()) {

                String nome_corso1 = cor.getString("nome_corso");
                String username_utente1 = "prova";
                String username_docente1 = cor.getString("username_docente");
                String giorno1 = "prova";
                int ora1 = 0;
                int stato_prenotazione1 = 0;//ovvero da prenotare
                int id_prenotazione1 = 0;//default

                conn1 = DriverManager.getConnection(url1, user, password);
                st = conn1.createStatement();
                ResultSet pren = st.executeQuery("SELECT * FROM prenotazione WHERE username_docente = ('" + username_docente1 + "') and stato_prenotazione = 1 ");

                ArrayList<String> data3 = new ArrayList<>();

                //metto in un array list i giorni e le ore in cui il docente è occupato
                while (pren.next()) {
                    String giorno2 = pren.getString("giorno");
                    int ora2 = pren.getInt("ora");

                    data d = new data(giorno2,ora2);

                    String data00 = d.toString();

                    data3.add(data00);

                }

                for (int i = 17; i <= 19; i++) {
                        giorno1 = "lunedi";
                        ora1 = i;

                        data d = new data(giorno1,ora1);
                        String data01 = d.toString();

                        if(data3.contains(data01)){

                        }
                        else {
                            prenotazione p = new prenotazione(nome_corso1, username_utente1, username_docente1, giorno1, ora1, stato_prenotazione1, id_prenotazione1);
                            out.add(p);
                        }

                    }
                for (int i = 17; i <= 19; i++) {
                    giorno1 = "martedi";
                    ora1 = i;

                    data d = new data(giorno1,ora1);
                    String data01 = d.toString();

                    if(data3.contains(data01)){

                    }
                    else {
                        prenotazione p = new prenotazione(nome_corso1, username_utente1, username_docente1, giorno1, ora1, stato_prenotazione1, id_prenotazione1);
                        out.add(p);
                    }

                }
                for (int i = 17; i <= 19; i++) {
                    giorno1 = "mecoledii";
                    ora1 = i;

                    data d = new data(giorno1,ora1);
                    String data01 = d.toString();

                    if(data3.contains(data01)){

                    }
                    else {
                        prenotazione p = new prenotazione(nome_corso1, username_utente1, username_docente1, giorno1, ora1, stato_prenotazione1, id_prenotazione1);
                        out.add(p);
                    }

                }
                for (int i = 17; i <= 19 ; i++) {
                    giorno1 = "giovedi";
                    ora1 = i;

                    data d = new data(giorno1,ora1);
                    String data01 = d.toString();

                    if(data3.contains(data01)){

                    }
                    else {
                        prenotazione p = new prenotazione(nome_corso1, username_utente1, username_docente1, giorno1, ora1, stato_prenotazione1, id_prenotazione1);
                        out.add(p);
                    }

                }
                for (int i = 17; i <= 19 ; i++) {
                    giorno1 = "venerdi";
                    ora1 = i;

                    data d = new data(giorno1,ora1);
                    String data01 = d.toString();

                    if(data3.contains(data01)){

                    }
                    else {
                        prenotazione p = new prenotazione(nome_corso1, username_utente1, username_docente1, giorno1, ora1, stato_prenotazione1, id_prenotazione1);
                        out.add(p);
                    }

                }
                data3.clear();


            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public ArrayList<prenotazione> view_prenotazioni_prenotabili_corso(String nome_corso) {
        Connection conn1 = null;
        ArrayList<prenotazione> out = new ArrayList<>();

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();

            ResultSet cor = st.executeQuery("SELECT * FROM corso_docente WHERE nome_corso = ('" + nome_corso + "')");

            while (cor.next()) {

                String nome_corso1 = cor.getString("nome_corso");
                String username_utente1 = "prova";
                String username_docente1 = cor.getString("username_docente");
                String giorno1 = "prova";
                int ora1 = 0;
                int stato_prenotazione1 = 0;//ovvero da prenotare
                int id_prenotazione1 = 0;//default

                conn1 = DriverManager.getConnection(url1, user, password);
                st = conn1.createStatement();
                ResultSet pren = st.executeQuery("SELECT * FROM prenotazione WHERE username_docente = ('" + username_docente1 + "') and stato_prenotazione = 1 ");

                ArrayList<String> data3 = new ArrayList<>();

                //metto in un array list i giorni e le ore in cui il docente è occupato
                while (pren.next()) {
                    String giorno2 = pren.getString("giorno");
                    int ora2 = pren.getInt("ora");

                    data d = new data(giorno2,ora2);

                    String data00 = d.toString();

                    data3.add(data00);

                }

                for (int i = 17; i <= 19; i++) {
                    giorno1 = "lunedi";
                    ora1 = i;

                    data d = new data(giorno1,ora1);
                    String data01 = d.toString();

                    if(data3.contains(data01)){

                    }
                    else {
                        prenotazione p = new prenotazione(nome_corso1, username_utente1, username_docente1, giorno1, ora1, stato_prenotazione1, id_prenotazione1);
                        out.add(p);
                    }

                }
                for (int i = 17; i <= 19; i++) {
                    giorno1 = "martedi";
                    ora1 = i;

                    data d = new data(giorno1,ora1);
                    String data01 = d.toString();

                    if(data3.contains(data01)){

                    }
                    else {
                        prenotazione p = new prenotazione(nome_corso1, username_utente1, username_docente1, giorno1, ora1, stato_prenotazione1, id_prenotazione1);
                        out.add(p);
                    }

                }
                for (int i = 17; i <= 19; i++) {
                    giorno1 = "mecoledii";
                    ora1 = i;

                    data d = new data(giorno1,ora1);
                    String data01 = d.toString();

                    if(data3.contains(data01)){

                    }
                    else {
                        prenotazione p = new prenotazione(nome_corso1, username_utente1, username_docente1, giorno1, ora1, stato_prenotazione1, id_prenotazione1);
                        out.add(p);
                    }

                }
                for (int i = 17; i <= 19 ; i++) {
                    giorno1 = "giovedi";
                    ora1 = i;

                    data d = new data(giorno1,ora1);
                    String data01 = d.toString();

                    if(data3.contains(data01)){

                    }
                    else {
                        prenotazione p = new prenotazione(nome_corso1, username_utente1, username_docente1, giorno1, ora1, stato_prenotazione1, id_prenotazione1);
                        out.add(p);
                    }

                }
                for (int i = 17; i <= 19 ; i++) {
                    giorno1 = "venerdi";
                    ora1 = i;

                    data d = new data(giorno1,ora1);
                    String data01 = d.toString();

                    if(data3.contains(data01)){

                    }
                    else {
                        prenotazione p = new prenotazione(nome_corso1, username_utente1, username_docente1, giorno1, ora1, stato_prenotazione1, id_prenotazione1);
                        out.add(p);
                    }

                }
                data3.clear();


            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public int login(String username_utente, String password_utente) {
        Connection conn1 = null;
        int r = -1;//non trovato

        try {
            conn1 = DriverManager.getConnection(url1, user, password);

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM utente WHERE  username_utente = ('" + username_utente + "') and password = ('" + password_utente + "')");

            while (rs.next()) {
                r = rs.getInt("ruolo");
            }




        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return r;
    }
}

