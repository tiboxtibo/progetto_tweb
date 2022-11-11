
package dao;

import java.util.Scanner;

import static dao.DAO.*;

public class test {
    public static void main(String[] args) {
        /*

        Scanner scan = new Scanner(System.in);

        Scanner scan_int = new Scanner(System.in);


        for(int num=-1;num!=0;){
            System.out.println("***********************");
            System.out.println("------MENU------");
            System.out.println("***********************");

            System.out.println("--------CORSI-------");
            System.out.println("-1- Inserisci un corso");
            System.out.println("-2- Elimina un corso");
            System.out.println("-3- Visualizza lista corsi");
            System.out.println("--------------------");

            System.out.println("--------DOCENTI-------");
            System.out.println("-4- Inserisci un docente");
            System.out.println("-5- Elimina un docente");
            System.out.println("-6- Visualizza lista docenti");
            System.out.println("--------------------");

            System.out.println("--------CORSO-DOCENTE-------");
            System.out.println("-7- Inserisci un corso-docente");
            System.out.println("-8- Elimina un corso-docente");
            System.out.println("-9- Visualizza lista corso-docente");
            System.out.println("--------------------");

            System.out.println("-10- Visualizza dato un corso la lista dei docenti che svolgono ripetizioni");

            System.out.println("--------------------");
            System.out.println("--------PRENOTAZIONI-------");
            System.out.println("-11- Inserisci una prenotazione");
            System.out.println("-12- Elimina una prenotazione");
            System.out.println("-13- Visualizza lista prenotazioni");
            System.out.println("--------------------");

            System.out.println("--------------------");

            System.out.println("-0- Per terminare il programma");



            if (num == 1) {
                System.out.println("--------------------");
                System.out.println("Inserisci il nome del corso da aggiungere:");
                String nome_corso_add = scan.next();
                add_corsi(nome_corso_add);
                System.out.println(view_corsi());
                System.out.println("--------------------");
            }
            else if(num==2) {
                System.out.println("--------------------");
                System.out.println(view_corsi());
                System.out.println("Inserisci il nome del corso da eliminare:");
                String nome_corso_del = scan.next();
                del_corsi(nome_corso_del);
                System.out.println(view_corsi());
                System.out.println("--------------------");
            }
            else if(num==3) {
                System.out.println("**********");
                System.out.println(view_corsi());
                System.out.println("**********");

            }
            else if(num==4) {
                System.out.println("--------------------");
                System.out.println("INSERISCI UN DOCENTE:");
                System.out.println("nome:");
                String nome_docente_add = scan.next();
                System.out.println("cognome:");
                String cognome_docente_add = scan.next();
                System.out.println("username:");
                String username_docente_add = scan.next();
                add_docenti(nome_docente_add,cognome_docente_add,username_docente_add);
                System.out.println(view_docenti());
                System.out.println("--------------------");
            }
            else if(num==5) {
                System.out.println("--------------------");
                System.out.println(view_docenti());
                System.out.println("ELIMINA UN DOCENTE:");

                System.out.println("username:");
                String username_docente_add = scan.next();
                del_docenti(username_docente_add);
                System.out.println(view_docenti());
                System.out.println("--------------------");
            }
            else if(num==6) {
                System.out.println("**********");
                System.out.println(view_docenti());
                System.out.println("**********");
            }
            else if(num==7) {
                System.out.println("--------------------");
                System.out.println(view_docenti());
                System.out.println(view_corsi());
                System.out.println("INSERISCI UN CORSO-DOCENTE");
                System.out.println("username docente:");
                String username_docente_add = scan.next();
                System.out.println("nome corso:");
                String nome_corso_add = scan.next();
                add_corso_docente(username_docente_add,nome_corso_add);
                System.out.println(view_corso_docente());
                System.out.println("--------------------");
            }
            else if(num==8) {
                System.out.println("--------------------");
                System.out.println(view_corso_docente());
                System.out.println("ELIMINA UN CORSO-DOCENTE");
                System.out.println("username docente:");
                String username_docente_del = scan.next();
                System.out.println("nome corso:");
                String nome_corso_del = scan.next();
                del_corso_docente(username_docente_del,nome_corso_del);
                System.out.println(view_corso_docente());
                System.out.println("--------------------");
            }
            else if(num==9) {
                System.out.println("**********");
                System.out.println(view_corso_docente());
                System.out.println("**********");
            }
            else if(num==10) {
                System.out.println("--------------------");
                System.out.println(view_corsi());
                System.out.println("Trova i docenti che offrono ripetizioni");
                System.out.println("Corso:");
                String nome_corso_scan = scan.next();

                System.out.println(view_prenotazioniCorso_corso_docente(nome_corso_scan));

                System.out.println("--------------------");
                System.out.println(view_corso_docente());
                System.out.println("--------------------");

            }
            else if(num==11) {
                System.out.println("--------------------");
                System.out.println(view_corso_docente());
                System.out.println(view_prenotazioni());
                System.out.println("Inserisci una prenotazione");
                System.out.println("Nome corso:");
                String nome_corso_pren = scan.next();
                System.out.println("Username utente:");
                String username_utente_pren = scan.next();
                System.out.println("Username docente:");
                String username_docente_pren = scan.next();
                System.out.println("Giorno:");
                String giorno_pren = scan.next();
                System.out.println("ora:");
                int ora_pren = scan.nextInt();


                add_prenotazione(nome_corso_pren,username_utente_pren,username_docente_pren,giorno_pren,ora_pren);


                System.out.println("Prenotazione correttamente aggiunta");

                System.out.println("--------------------");
                System.out.println(view_prenotazioni());
                System.out.println("--------------------");

            }
            else if(num==12) {
                System.out.println("--------------------");
                System.out.println(view_prenotazioni());
                System.out.println("Cancella una prenotazione");
                System.out.println("Nome corso:");
                String nome_corso_pren = scan.next();
                System.out.println("Username utente:");
                String username_utente_pren = scan.next();
                System.out.println("Username docente:");
                String username_docente_pren = scan.next();
                System.out.println("Giorno:");
                String giorno_pren = scan.next();
                System.out.println("ora:");
                int ora_pren = scan.nextInt();


                del_prenotazione(nome_corso_pren,username_utente_pren,username_docente_pren,giorno_pren,ora_pren);


                System.out.println("Prenotazione correttamente aggiunta");

                System.out.println("--------------------");
                System.out.println(view_prenotazioni());
                System.out.println("--------------------");

            }
            else if(num==13) {

                System.out.println("--------------------");
                System.out.println(view_prenotazioni());
                System.out.println("--------------------");

            }



            else if(num==0) {


            }


            System.out.println("--------------------");
            System.out.println("Inserisci comando:");
            num = scan_int.nextInt();

            System.out.println(num);

        }
        System.out.println("**************************");
        System.out.println(view_corsi());
        System.out.println("**************************");
        System.out.println(view_docenti());
        System.out.println("**************************");
        System.out.println(view_corso_docente());
        System.out.println("**************************");


    }

  */
}

    }

