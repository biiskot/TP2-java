package com.company;

import classes.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static int nbCommande =0,chiffreAffaire=0;
    public static Map<Integer,Commande> ordersToPrepare = new HashMap<>();//Stocke les commandes à preparer en fonction de leur indiice pour cuisine et bar

    public static void main(String[] args) {
        System.out.println("Bienvenue dans votre logiciel de gestion pour restaurateur, selectionnez une action pour commencer");
        selectScreen();
    }

    static void selectScreen(){
        clearConsole();
        int choixEcran;
        System.out.println("Quel écran souhaitez vous afficher?");
        System.out.println("1- Ecran prise de commande");
        System.out.println("2- Ecran cuisine");
        System.out.println("3- Ecran bar");
        System.out.println("4- Ecran Monitoring");
        System.out.println("5- Gérer l'équipe");
        System.out.println("6- Gérer le stock");
        System.out.println("7- Gérer équipe du jour");
        System.out.println("0- Quitter le programme");

        Scanner scanner = new Scanner(System.in);
        System.out.print("==> ");
        if(scanner.hasNextInt()) {
            choixEcran = scanner.nextInt();
            switch(choixEcran){
                //Appel de la bonne fonction en fonction de l'entree utilisateur
                case 1 -> priseCommande(scanner);
                case 2 -> afficherCuisine(scanner);
                case 3 -> afficherBar(scanner);
                case 4 -> monitorRestaurant(scanner);
                case 5 -> manageEmployees(scanner);
                case 6 -> manageStock(scanner);
               // case 7 -> manageDayTeam(scanner);
                case 0 -> System.exit(0);
            }
        }
        else{
            System.out.println("Entrez un nombre entier");
            selectScreen();
        }
    }

    static void priseCommande(Scanner scanner){
        clearConsole();
        Commande commande = new Commande(nbCommande+1); //+1 pour pas avoir de commande numéro 0
        boolean boolFin = false;
        int choix = -1;
        System.out.println("--- PRISE DE COMMANDE ---");
        int i = 0;
        for (String n : CartePlats.listeplats) {
            i++;
            System.out.println(i + " " + n);
        }
        System.out.println("---------");
        for (String n : CarteBoissons.listeboissons) {
            i++;
            System.out.println(i + " " + n);
        }
        System.out.println("Voulez vous un menu centenaire Y/N");
        char menu = scanner.next().charAt(0);
        if(menu == 'Y' || menu=='y') menuCentenaire(scanner,commande);

        System.out.println("--- Entrez le numéro correspondant à la conso pour l'ajouter a la commande ---");
        System.out.println("--- Entrez 0 si la prise de commande est terminée ---");
        do {
            System.out.print("==> ");
            if(scanner.hasNextInt()) {
                choix = scanner.nextInt();
            }
            else {
                System.out.println("Entrez un nombre entier");
                scanner.nextLine();
            }
                if (choix > 0 && choix <= (CarteBoissons.listeboissons.size()+CartePlats.listeplats.size())) {
                    commande.listeIdsConsos.add(choix - 1);// On ajoute l'indice des plats à commander dans la liste
                    if(choix >= CartePlats.listeplats.size()+1){
                        commande.containsBoisson = true;
                    }
                    if(choix < CartePlats.listeplats.size()+1){
                        commande.containsPlat = true;
                    }
                }
                //          --- CHECK SI COMMANDE FINIE--
                else if (choix == 0) {
                    System.out.println("Commande terminée");
                    //Check si contient boisson ou plat
                    if(!commande.containsPlat){
                        commande.platsReady = true; //On dit que les plats sont prêts s'il n'y en a pas pour éviter de passer par l'écran cuisine
                    }
                    if(!commande.containsBoisson){
                        commande.boissonsReady = true; //Idem pour les boissons
                    }
                    //On ajoute la commande a la liste à preparer
                    ordersToPrepare.put(commande.id, commande);
                    //On incrémente le nb de commandes pour les id/numeros de commandes
                    nbCommande++;
                    boolFin = true; //Si commande finie on sort de la boucle
                    System.out.println("Appuyez sur B pour retourner en arrière ou sur P pour prendre une autre commande");
                    char bp = scanner.next().charAt(0);
                    if (bp == 'B' || bp == 'b') {
                        selectScreen();
                    } else if (bp == 'p' || bp == 'P') {
                        priseCommande(scanner);
                    }
                }
                else {
                    System.out.println("Entry incorrect");
                }
                choix = -1;
        }while(!boolFin);
    }

    static void afficherCuisine(Scanner scanner){
        clearConsole();
        System.out.println("--- CUISINE - PREPARATION ---");
        boolean smthngtoprepare = false;
        if(ordersToPrepare.size()>0) {
            //Afficher les plats pour la commande
            for (Map.Entry<Integer, Commande> n : ordersToPrepare.entrySet()) {
                if(!n.getValue().platsReady) {
                    System.out.println("Commande n°" + n.getKey() + " : ");
                    if (n.getValue().containsPlat) {
                        smthngtoprepare = true;
                        for (int i : n.getValue().listeIdsConsos) {
                            if (i < CartePlats.listeplats.size()) { //Affiche que les plats car id des boissons > 10
                                System.out.println("-->" + CartePlats.listeplats.get(i));
                            }
                        }
                    } else {
                        System.out.println("--> Pas de plats à préparer pour cette commande");
                    }
                }
            }
            if(smthngtoprepare) {
                System.out.println("Entrez le numéro de commande pour laquelle tous les plats sont prêts : ");
                int choixCommande = scanner.nextInt();
                ordersToPrepare.get(choixCommande).platsReady = true;//Plats prêts
                ordersToPrepare.get(choixCommande).servirPlats();//On fait le service des plats
                //Une fois pret, check si les boissons de la commmande sont pretes aussi
                if (checkWholeOrder(ordersToPrepare.get(choixCommande))) {
                    finaliserCommande(choixCommande, ordersToPrepare.get(choixCommande));
                } else {
                    System.out.println("Les boissons pour cette commande ne sont pas prêtes ! Allez réveiller le barman");
                }
            }
        }
        else{
            System.out.println("Aucune commande n'a été passée");
        }
        System.out.println("Appuyez sur B si vous voulez retourner en arrière,S sinon");
        char choix = scanner.next().charAt(0);
        if(choix == 'B' || choix == 'b'){
            selectScreen();
        }
        else if(choix == 'S' || choix == 's') {
            afficherCuisine(scanner);
        }
    }

    static void afficherBar(Scanner scanner){
        clearConsole();
        boolean smthngtoprepare = false;
        System.out.println("--- BAR - PREPARATION ---");
        if(ordersToPrepare.size()>0) {
            //Afficher les plats pour la commande
            for (Map.Entry<Integer, Commande> n : ordersToPrepare.entrySet()) {
                if(!n.getValue().boissonsReady){
                    System.out.println("Commande n°" + n.getKey() + " : ");
                    if (n.getValue().containsBoisson) {
                        for (int i : n.getValue().listeIdsConsos) {
                            if (i >= CartePlats.listeplats.size()) { //Affiche que les boissons
                                smthngtoprepare = true;
                                System.out.println("-->" + CarteBoissons.listeboissons.get(i - CartePlats.listeplats.size()));
                            }
                        }
                    } else {
                        System.out.println("--> Pas de boissons à préparer pour cette commande");
                    }
                }
            }
           if(smthngtoprepare) {
                System.out.println("Entrez le numéro de commande pour laquelle tous les plats sont prêts : ");
                int choixCommande = scanner.nextInt();
                ordersToPrepare.get(choixCommande).boissonsReady = true;//Boissons prêtes
                ordersToPrepare.get(choixCommande).servirBoissons();//On fait le service des boissons
                //Une fois pret, check si les plats de la commmande sont prêts aussi
                if (checkWholeOrder(ordersToPrepare.get(choixCommande))) {
                    finaliserCommande(choixCommande, ordersToPrepare.get(choixCommande));
                } else {
                    System.out.println("Les plats pour cette commande ne sont pas prêts ! Allez réveiller le barman");
                }
            }
        }
        else{
            System.out.println("Aucune commande n'a été passée");
        }
        System.out.println("Appuyez sur B si vous voulez retourner en arrière,S sinon");
        char choix = scanner.next().charAt(0);
        if(choix == 'B' || choix == 'b'){
            selectScreen();
        }
        else if(choix == 'S' || choix == 's') {
            afficherBar(scanner);
        }
    }

    static void monitorRestaurant(Scanner scanner){
        clearConsole();
        String element, date;
        Scanner tempsc = new Scanner(System.in);

        System.out.println("--- MONITORING RESTAURANT ---");
        System.out.println("Nombre de commandes : "+nbCommande+" - C.A de la journée: "+chiffreAffaire);

        //--- PARTIE IMPRESSION LISTE COURSES---
        System.out.println("Appuyez sur P si vous voulez imprimer la liste de course pour demain, B sinon pour retourner au sélécteur d'écran");
        char print = scanner.next().charAt(0);

        if(print == 'P' || print == 'p'){
            System.out.println("--- Entrez la date du jour JJ-MM-AA (pas de '/') ---");
            System.out.print("==>");
            date = tempsc.nextLine();

            Path path = Paths.get("src/listes_course/liste_"+date+".txt");

            try (FileOutputStream fout = new FileOutputStream(path.toString())){ //try-with-ressources pour fermer le fichier automatiquement
                System.out.println("Liste de courses : ");

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString(),true))){
                    for(Map.Entry<String, Integer> n : Inventaire.stockIngredients.entrySet()){
                        if(n.getValue()!=Inventaire.baseQtte){
                            //On affiche les aliments à acheter en fonction du manque
                            element=(n.getKey() + " : " + (Inventaire.baseQtte-n.getValue())); //Trouve la quantité à acheter par rapport à la quantité de base
                            System.out.println(element);
                            //Ecriture :
                            writer.write(element);
                            writer.newLine();
                        }
                    }
                    System.out.println("\nEcriture effectuée");
                }

                catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        else if(print=='B' || print=='b'){
            selectScreen();//On retourne an arrière si on ne veut pas print la
        }
        goBack(scanner);
    }

    static void manageEmployees(Scanner scanner){
        clearConsole();
        System.out.println("--- AJOUT OU RETRAIT D'EMPLOYES ---");
        goBack(scanner);
    }

    static void manageStock(Scanner scanner){
        clearConsole();
        boolean fini = false;
        System.out.println("--- GERER LE STOCK ---\n");
        System.out.println("1 - AJOUTER");
        System.out.println("2 - RETIRER");
        int choix = scanner.nextInt();
        int i;
        List<String> tabtmp = new ArrayList<>();
        while(!fini) {
            i = 0;
            System.out.println("Choisissez un ingrédient");
            for (Map.Entry<String, Integer> n : Inventaire.stockIngredients.entrySet()) {
                tabtmp.add(n.getKey());
                i++;
                System.out.println(i + " " + n);
            }
            int choixIng = scanner.nextInt();

            System.out.println("Entrez la quantité à ajouter ou retirer ==> ");

            int qty = scanner.nextInt();

            if (choix == 1) {
                Inventaire.addItem(tabtmp.get(choixIng - 1), qty);
            } else if (choix == 2) {
                Inventaire.removeItem(tabtmp.get(choixIng - 1), qty);
            }
            Inventaire.afficherStock();
            System.out.println("Appuyez sur 1 pour continuer, 0 sinon");
            int c = scanner.nextInt();
            if(c==0){
                fini = true;
            }
        }
        goBack(scanner);
    }

    /*
    static void manageDayTeam(Scanner scanner){
        clearConsole();
        System.out.println("--- PROGRAMMATION EMPLOYES POUR LA SOIREE ---");
        goBack(scanner);
    }
*/
    static void finaliserCommande(int idorder,Commande c){
        System.out.println("Commande N°"+idorder+" totalement servie !");

        //puisque tout a été servi, on imprime le reçu
        //et on affiche le stock avec les ingrédients utilisés pour la commande
        imprimerTicket(c);
        Inventaire.afficherStock();

        //On enleve la commande de ordersToPrepare
        ordersToPrepare.remove(idorder);
    }

    static boolean checkWholeOrder(Commande c){
        //True si boissons et plats prets
        return c.platsReady && c.boissonsReady;
    }

    static void imprimerTicket(Commande c){
        Path path = Paths.get("src/tickets_de_caisse/ticket_commande"+c.id+".txt");
        try (FileOutputStream fout = new FileOutputStream(path.toString())){
                //Ecriture :
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString(),true))){
                    writer.write("Commande n° : "+c.id);
                    writer.newLine();
                    for(String n : c.ticketCaisse) {
                        writer.write("---"+n);
                        writer.newLine();
                    }
                    writer.newLine();
                    if(c.menuCentenaire)
                        writer.write("Menu Centenaire, TOTAL : "+c.addition+"€");
                    else
                        writer.write("Menu Centenaire, TOTAL : "+c.addition+"€");
                   // writer.close(); pas utile avec try-with-ressources
                    System.out.println("Ecriture effectuée");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static void menuCentenaire(Scanner scanner, Commande commande){
        int choix=-1;
        int nbboissons=0,nbplats=0;
        boolean boolFin = false;
        commande.menuCentenaire = true;//On indique que la commande s'agit d'un menu centenaire

        System.out.println("--- Entrez le numéro correspondant à la conso pour l'ajouter a la commande ---");
        do {
            if(nbboissons>=7){
                System.out.println("Attention toutes les boissons ont été choisies, séléctionnez uniquement un plat");
            }
            if(nbplats>=7){
                System.out.println("Attention tous les plats ont été choisis, séléctionnez uniquement une boisson");
            }

            System.out.print("==> ");
            if(scanner.hasNextInt() && nbboissons <=7 && nbplats <=7) {
                choix = scanner.nextInt();
            }
            else {
                System.out.println("Réessayez");
                scanner.nextLine();
            }
            if (choix > 0 && choix <= (CarteBoissons.listeboissons.size()+CartePlats.listeplats.size())) {
                commande.listeIdsConsos.add(choix - 1);// On ajoute l'indice des plats à commander dans la liste
                if(choix >= CartePlats.listeplats.size()+1){
                    commande.containsBoisson = true;
                    nbboissons++;
                }
                if(choix < CartePlats.listeplats.size()+1){
                    commande.containsPlat = true;
                    nbplats++;
                }
            }
            else {
                System.out.println("Entry incorrect");
            }
            //          --- CHECK SI COMMANDE FINIE--
            if (nbplats >= 7 && nbboissons >=7) {
                System.out.println("Commande terminée");
                //On ajoute la commande a la liste à preparer
                ordersToPrepare.put(commande.id, commande);
                //On incrémente le nb de commandes pour les id/numeros de commandes
                nbCommande++;
                boolFin = true; //Si commande finie on sort de la boucle
                System.out.println("Appuyez sur B pour retourner en arrière ou sur P pour prendre une autre commande");
                char bp = scanner.next().charAt(0);
                if (bp == 'B' || bp == 'b') {
                    selectScreen();
                } else if (bp == 'p' || bp == 'P') {
                    priseCommande(scanner);
                }
            }

            choix = -1;
        }while(!boolFin);
    }

    static void goBack(Scanner sc){
        System.out.println("Appuyez sur B pour retourner en arrière");
        try{
            char choix = sc.next().charAt(0);
            if(choix == 'B' || choix == 'b'){
                selectScreen();
            }
            else {
                goBack(sc);
            }
        }
        catch(Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}





