package com.company;

import classes.CarteBoissons;
import classes.CartePlats;
import classes.Consommable;
import classes.Inventaire;
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
            case 4 -> monitorEmployees(scanner);
            case 5 -> manageEmployees(scanner);
            case 6 -> manageStock(scanner);
            case 7 -> manageDayTeam(scanner);
        }
    }
    static void priseCommande(Scanner scanner){
        System.out.println("--- PRISE DE COMMANDE ---\n");
        System.out.println("--- Appuyez sur la touche correspondante au plat pour l'ajouter a la commande ---\n");
        int i = 0;
        for(Consommable n : cartePlats.listeplats){
            i++;
            System.out.println(i +" "+ n.name);
        }
        int choixPlat = scanner.nextInt();
    }
    static void afficherCuisine(Scanner scanner){
        System.out.println("--- CUISINE - PREPARATION ---");
    }
    static void afficherBar(Scanner scanner){
        System.out.println("--- BAR - PREPARATION ---");
    }
    static void monitorEmployees(Scanner scanner){
        System.out.println("--- MONITORING RESTAURANT ---");
    }
    static void manageEmployees(Scanner scanner){
        System.out.println("--- AJOUT OU RETRAIT D'EMPLOYES ---");
    }
    static void manageStock(Scanner scanner){
        System.out.println("--- GERER LE STOCK ---");
    }
    static void manageDayTeam(Scanner scanner){
        System.out.println("--- PROGRAMMATION EMPLOYES POUR LA SOIREE ---");
    }
}



