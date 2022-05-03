package com.company;

import classes.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static Inventaire stock = new Inventaire();
    public static CartePlats cartePlats = new CartePlats();
    public static CarteBoissons carteBoissons = new CarteBoissons();
    public static int nbCommande =0,chiffreAffaire=0;
    public static Map<Integer,Commande> ordersToPrepare = new HashMap<>();//Stocke les commandes à preparer en fonction de leur indiice pour cuisine et bar

    public static void main(String[] args) {
        System.out.println("Bienvenue dans votre logiciel de gestion pour restaurateur, selectionnez une action pour commencer");
        System.out.println(cartePlats.listeplats.size());

        selectScreen();
    }

    static void selectScreen(){
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
                case 7 -> manageDayTeam(scanner);
                case 0 -> System.exit(0);
            }
        }
        else{
            System.out.println("Entrez un nombre entier");
            selectScreen();
        }
    }

    static void priseCommande(Scanner scanner){
        Commande commande = new Commande(nbCommande+1); //+1 pour pas avoir de commande numéro 0
        boolean boolFin = false;
        int choix = -1;
        System.out.println("--- PRISE DE COMMANDE ---");
        int i = 0;
        for (String n : cartePlats.listeplats) {
            i++;
            System.out.println(i + " " + n);
        }
        System.out.println("---------");
        for (String n : carteBoissons.listeboissons) {
            i++;
            System.out.println(i + " " + n);
        }
        System.out.println("--- Entrez le numéro correspondant au plat pour l'ajouter a la commande ---");
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
                if (choix > 0) {
                    commande.listeIdsConsos.add(choix - 1);// On ajoute l'indice des plats à commander dans la liste
                    if(choix >= cartePlats.listeplats.size()+1){
                        commande.containsBoisson = true;
                    }
                    if(choix < cartePlats.listeplats.size()+1){
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
        }while(!boolFin);
    }

    static void afficherCuisine(Scanner scanner){
        System.out.println("--- CUISINE - PREPARATION ---");
        boolean smthngtoprepare = false;
        if(ordersToPrepare.size()>0) {
            //Afficher les plats pour la commande
            for (Map.Entry<Integer, Commande> n : ordersToPrepare.entrySet()) {
                    System.out.println("Commande n°" + n.getKey() + " : ");
                    if(n.getValue().containsPlat){
                        smthngtoprepare = true;
                        for (int i : n.getValue().listeIdsConsos) {
                            if (i < cartePlats.listeplats.size()) { //Affiche que les plats car id des boissons > 10
                                System.out.println("-->" + cartePlats.listeplats.get(i));
                            }
                        }
                    }
                    else{
                        System.out.println("--> Pas de plats à préparer pour cette commande");
                    }
            }
            if(smthngtoprepare) {
                System.out.println("Entrez le numéro de commande pour laquelle tous les plats sont prêts : ");
                int choixCommande = scanner.nextInt();
                ordersToPrepare.get(choixCommande).platsReady = true;//Plats prêts
                ordersToPrepare.get(choixCommande).servirPlats(cartePlats);//On fait le service des plats
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
        boolean smthngtoprepare = false;
        System.out.println("--- BAR - PREPARATION ---");
        if(ordersToPrepare.size()>0) {
            //Afficher les plats pour la commande
            for (Map.Entry<Integer, Commande> n : ordersToPrepare.entrySet()) {
                System.out.println("Commande n°" + n.getKey() + " : ");
                if(n.getValue().containsBoisson){
                    for (int i : n.getValue().listeIdsConsos) {
                        if (i >= cartePlats.listeplats.size()) { //Affiche que les boissons
                            smthngtoprepare = true;
                            System.out.println("-->" + carteBoissons.listeboissons.get(i-cartePlats.listeplats.size()));
                        }
                    }
                }
                else{
                    System.out.println("--> Pas de boissons à préparer pour cette commande");
                }
            }
           if(smthngtoprepare) {
                System.out.println("Entrez le numéro de commande pour laquelle tous les plats sont prêts : ");
                int choixCommande = scanner.nextInt();
                ordersToPrepare.get(choixCommande).boissonsReady = true;//Boissons prêtes
                ordersToPrepare.get(choixCommande).servirBoissons(carteBoissons);//On fait le service des boissons
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
        String element, date;
        Scanner tempsc = new Scanner(System.in);

        System.out.println("--- MONITORING RESTAURANT ---");
        System.out.println("Nombre de commandes : "+nbCommande+" - C.A de la journée: "+chiffreAffaire);

        System.out.println("Appuyez sur P si vous voulez imprimer la liste de course pour demain, B sinon pour retourner au sélécteur d'écran");
        char print = scanner.next().charAt(0);

        if(print == 'P' || print == 'p'){
            System.out.println("--- Entrez la date du jour JJ-MM-AA (pas de '/') ---");
            System.out.print("==>");
            date = tempsc.nextLine();

            Path path = Paths.get("src/listes_course/liste_"+date+".txt");

            try (FileOutputStream stream = new FileOutputStream(path.toString())){
                System.out.println("Liste de courses : ");

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString(),true))){
                    for(Map.Entry<String, Integer> n : stock.stockIngredients.entrySet()){
                        if(n.getValue()!=stock.baseQtte){
                            //On affiche les aliments à acheter en fonction du manque
                            element=(n.getKey() + " : " + (stock.baseQtte-n.getValue())); //Trouve la quantité à acheter par rapport à la quantité de base
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
        goBack(scanner);
    }

    static void manageEmployees(Scanner scanner){
        System.out.println("--- AJOUT OU RETRAIT D'EMPLOYES ---");
        goBack(scanner);
    }

    static void manageStock(Scanner scanner){
        System.out.println("--- GERER LE STOCK ---\n");
        System.out.println("1 - AJOUTER");
        System.out.println("2 - RETIRER");
        int choix = scanner.nextInt();
        int i = 0;
        List<String> tabtmp = new ArrayList<>();

        System.out.println("Choisissez un ingrédient");
        for(Map.Entry<String, Integer> n : stock.stockIngredients.entrySet()){
            tabtmp.add(n.getKey());
            i++;
            System.out.println(i +" " + n);
        }
        int choixIng = scanner.nextInt();

        System.out.println("Entrez la quantité à ajouter ou retirer ==> ");

        int qty = scanner.nextInt();

        if(choix == 1){
           stock.addItem(tabtmp.get(choixIng-1),qty);
        }
        else if(choix == 2){
          stock.removeItem(tabtmp.get(choixIng-1),qty);
        }
        stock.afficherStock();
        goBack(scanner);
    }

    static void manageDayTeam(Scanner scanner){
        System.out.println("--- PROGRAMMATION EMPLOYES POUR LA SOIREE ---");
        goBack(scanner);
    }

    static void finaliserCommande(int idorder,Commande c){
        System.out.println("Commande N°"+idorder+" totalement servie !");

        //puisque tout a été servi, on imprime le reçu
        //et on affiche le stock avec les ingrédients utilisés pour la commande
        imprimerTicket(c);
        stock.afficherStock();

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
                System.out.println("Fichier crée " + fout);
                //Ecriture :
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString(),true))){
                    writer.write("Commande n° : "+c.id);
                    writer.newLine();
                    for(String n : c.ticketCaisse) {
                        writer.write("---"+n);
                        writer.newLine();
                    }
                    writer.newLine();
                    writer.write("TOTAL : "+c.addition);
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
    static void goBack(Scanner sc){
        System.out.println("Appuyez sur B si vous voulez retourner en arrière");
        try{
            char choix = sc.next().charAt(0);
            if(choix == 'B' || choix == 'b'){
                selectScreen();
            }
        }
        catch(Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}





