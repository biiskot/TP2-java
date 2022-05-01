package com.company;

import classes.*;

import java.util.*;


public class Main {
    public static Inventaire stock = new Inventaire();
    public static CartePlats cartePlats = new CartePlats();
    public static CarteBoissons carteBoissons = new CarteBoissons();
    public static int nbCommande =0,chiffreAffaire=0;
    public static Map<Integer,Commande> ordersToPrepare = new HashMap<>();//Stocke les commandes à preparer en fonction de leur indiice pour cuisine et bar

    public static void main(String[] args) {
        System.out.println("Bienvenue dans votre logiciel de gestion pour restaurateur, selectionnez une action pour commencer");
        selectScreen();
    }

    static void selectScreen(){
        System.out.println("Quel écran souhaitez vous afficher?");
        System.out.println("1- Ecran prise de commande");
        System.out.println("2- Ecran cuisine");
        System.out.println("3- Ecran bar");
        System.out.println("4- Ecran Monitoring");
        System.out.println("5- Gérer l'équipe");
        System.out.println("6- Gérer le stock");
        System.out.println("7- Gérer équipe du jour");

        Scanner scanner = new Scanner(System.in);
        int choixEcran = scanner.nextInt();

        System.out.println("Vous avez choisi l'écran: " + choixEcran);

        switch(choixEcran){
            //Appel de la bonne fonction en fonction de l'entree utilisateur
            case 1 -> priseCommande(scanner);
            case 2 -> afficherCuisine(scanner);
            case 3 -> afficherBar(scanner);
            case 4 -> monitorRestaurant(scanner);
            case 5 -> manageEmployees(scanner);
            case 6 -> manageStock(scanner);
            case 7 -> manageDayTeam(scanner);
        }
    }

    static void priseCommande(Scanner scanner){
        Commande commande = new Commande(nbCommande+1); //+1 pour pas avoir de commande numéro 0
        boolean boolFin = false;
        while(!boolFin) {
            System.out.println("--- PRISE DE COMMANDE ---");
            System.out.println("--- Appuyez sur la touche correspondante au plat pour l'ajouter a la commande ---\n");

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
            int choixConso = scanner.nextInt();

            if(choixConso >=0) {
                commande.listeIdsConsos.add(choixConso-1);// On ajoute l'indice des plats à commander dans la liste
            }
            else{
                System.out.println("Mauvaise entry");
            }
//          --- CHECK SI COMMANDE FINIE---
            System.out.println("Commande terminée ? \n Y : Oui\n N: Non");
            char finCommande = scanner.next().charAt(0); // On lit le caractère

            if (finCommande == 'Y' || finCommande == 'y') {
                //On ajoute la commande a la liste à preparer
                ordersToPrepare.put(commande.id,commande);
                //On incrémente le nb de commandes pour les id/numeros de commandes
                nbCommande++;
                boolFin = true; //Si commande finie on sort de la boucle
                System.out.println("Appuyez sur B pour retourner en arrière ou sur P pour prendre une autre commande");
                char bp = scanner.next().charAt(0);
                if(bp =='B'||bp=='b'){
                    selectScreen();
                }
                else if(bp=='p'||bp=='P'){
                    priseCommande(scanner);
                }
            }
        }
    }

    static void afficherCuisine(Scanner scanner){
        System.out.println("--- CUISINE - PREPARATION ---");

        //Afficher les plats pour la commande n et booleen pour indiquer quand cest pret
        for(Map.Entry<Integer,Commande> n : ordersToPrepare.entrySet()){
                System.out.println("Commande n°"+n.getKey()+" : ");
                for(int i : n.getValue().listeIdsConsos) {
                    if(i < cartePlats.listeplats.size()) //Affiche que les plats car id des boissons > 10
                    System.out.println("-->"+cartePlats.listeplats.get(i));
                }
        }
        System.out.println("Entrez le numéro de commande pour laquelle tous les plats sont prêts : ");
        int choixCommande = scanner.nextInt();
        ordersToPrepare.get(choixCommande).platsReady = true;//Plats prêts
        //Une fois pret, check si les boissons de la commmande sont pretes aussi
        if(checkWholeOrder(choixCommande)){
            finaliserCommande(choixCommande);
        }
        else{
            System.out.println("Les boissons pour cette commande ne sont pas prêtes ! Allez réveiller le barman");
        }
        goBack(scanner);
    }

    static void afficherBar(Scanner scanner){
        System.out.println("--- BAR - PREPARATION ---");

        //Afficher les boissons pour la commande n et booleen pour indiquer quand cest pret
        for(Map.Entry<Integer,Commande> n : ordersToPrepare.entrySet()){
            System.out.println("Commande n°"+n.getKey()+" : ");
            for(int i : n.getValue().listeIdsConsos) {
                if(i >= cartePlats.listeplats.size()) //Affiche que les boissons car id des boissons > 10
                    System.out.println("-->"+carteBoissons.listeboissons.get(i-11));
            }
        }
        System.out.println("Entrez le numéro de commande pour laquelle toutes les boissons sont prêtes : ");
        int choixCommande = scanner.nextInt();
        ordersToPrepare.get(choixCommande).boissonsReady = true; //Boissons prêtes
        //Une fois pret, check si les plats de la commande sont prets aussi
        if (checkWholeOrder(choixCommande)){
            finaliserCommande(choixCommande);
        }
        else{
            System.out.println("Les plats pour cette commande ne sont pas prêts ! Allez réveiller le cuistot");
        }
        goBack(scanner);
    }

    static void monitorRestaurant(Scanner scanner){
        System.out.println("--- MONITORING RESTAURANT ---");

        System.out.println("Nombre de commandes : "+nbCommande+" / C.A : "+chiffreAffaire);


        System.out.println("Appuyez sur P si vous voulez imprimer la liste de course pour demain");
        char print = scanner.next().charAt(0);

        if(print == 'P' || print == 'p'){
            System.out.println("Liste de courses : ");
            for(Map.Entry<String, Integer> n : stock.stockIngredients.entrySet()){
                if(n.getValue()!=stock.baseQtte){
                    //On affiche les aliments à acheter en fonction du manque
                    System.out.println(n.getKey() + " : " + (stock.baseQtte-n.getValue()));
                }
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

    static void finaliserCommande(int idorder){
        System.out.println("Commande N°"+idorder+" envoyée !\nStock dispo : ");
        //On build
        for(int n : ordersToPrepare.get(idorder).listeIdsConsos){
            if (n < 11) {
                FactoryConsommable.Build(cartePlats.listeplats.get(n));//on build la conso voulue grace à l'indice
            }
            else if (n >=11){
                FactoryConsommable.Build(carteBoissons.listeboissons.get(n-11));
            }
        }
        stock.afficherStock();
        //On enleve la commande de ordersToPrepare
        ordersToPrepare.remove(idorder);
    }

    static boolean checkWholeOrder(int idorder){
        //Si boissons et plats prets
        if(ordersToPrepare.get(idorder).platsReady && ordersToPrepare.get(idorder).boissonsReady) {
            return true;
        }
        else{
            return false;
        }
    }

    static void goBack(Scanner sc){
        System.out.println("Appuyez sur B si vous voulez retourner en arrière");
        char choix = sc.next().charAt(0);
        if(choix == 'B' || choix == 'b'){
            selectScreen();
        }
    }

}





