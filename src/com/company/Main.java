package com.company;

import classes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;




public class Main {
    public static Inventaire stock = new Inventaire();
    public static CartePlats cartePlats = new CartePlats();
    public static CarteBoissons carteBoissons = new CarteBoissons();

    public static void main(String[] args) {
        System.out.println("Bienvenue dans votre logiciel de gestion pour restaurateur, selectionnez une action pour commencer");
        System.out.println("Quel écran souhaitez vous afficher?");
        System.out.println("1- Ecran prise de commande");
        System.out.println("2- Ecran cuisine");
        System.out.println("3- Ecran bar");
        System.out.println("4- Ecran Monitoring");
        System.out.println("5- Gerer l'equipe");
        System.out.println("6- Gerer le stock");// Peut etre pas utile ? Stock deja géré dans la création d'un plat
        System.out.println("7- Gerer equipe du jour");

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
        List<Integer> commande = new ArrayList<>();
        boolean boolFin = false;
        while(!boolFin) {
            System.out.println("--- PRISE DE COMMANDE ---");
            System.out.println("--- Appuyez sur la touche correspondante au plat pour l'ajouter a la commande ---\n");
            System.out.println("------");
            stock.afficherStock();
            System.out.println("------");

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
                commande.add(choixConso);// On ajoute l'indice des plats à commander dans la liste
            }
            else{
                System.out.println("Mauvaise entry");
            }

            System.out.println("Commande terminée ? \n Y : Oui\n N: Non");
            char finCommande = scanner.next().charAt(0); // On lit le caractère

            if (finCommande == 'Y' || finCommande == 'y') {
                preparerCommande(commande);//On commence la préparation de la commande quand la prise est finie
                boolFin = true; //Si commande finie on sort de la boucle
            }
        }
    }

    static void afficherCuisine(Scanner scanner){
        System.out.println("--- CUISINE - PREPARATION ---");
    }

    static void afficherBar(Scanner scanner){
        System.out.println("--- BAR - PREPARATION ---");
    }

    static void monitorRestaurant(Scanner scanner){
        System.out.println("--- MONITORING RESTAURANT ---");
        int nbCommandes, chiffreAffaire;

        System.out.println("Appuyez sur P si vous voulez imprimer la liste de course pour demain");
        char print = scanner.next().charAt(0);

        if(print == 'P' || print == 'p'){
            System.out.println("Liste de courses : ");
            for(Map.Entry<String, Integer> n : stock.stockIngredients.entrySet()){
                if(n.getValue()!=stock.baseQtte){
                    //On affiche les aliments à acheter en focntion du manque
                    System.out.println(n.getKey() + " : " + (stock.baseQtte-n.getValue()));
                }
            }
        }
    }

    static void manageEmployees(Scanner scanner){
        System.out.println("--- AJOUT OU RETRAIT D'EMPLOYES ---");
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
    }

    static void manageDayTeam(Scanner scanner){
        System.out.println("--- PROGRAMMATION EMPLOYES POUR LA SOIREE ---");
    }

    static void preparerCommande(List<Integer>list){
        for(int n : list){
            FactoryConsommable.Build(cartePlats.listeplats.get(n - 1));//on build la conso voulue
        }
    }
}



