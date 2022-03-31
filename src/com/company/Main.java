package com.company;

import classes.Inventaire;
import java.util.Scanner;


public class Main {
    public static Inventaire stock = new Inventaire();

    public static void main(String[] args) {
        System.out.println("Bienvenue dans votre logiciel de gestion pour restaurateur, selectionnez une action pour commencer");
        System.out.println("Quel écran souhaitez vous afficher?");
        System.out.println("1- Ecran prise de commande");
        System.out.println("2- Ecran cuisine");
        System.out.println("3- Ecran bar");
        System.out.println("4- Ecran Monitoring");

        Scanner scanner = new Scanner(System.in);
        int choixEcran = scanner.nextInt();

        System.out.println("Vous avez choisi l'écran: " + choixEcran);

        switch(choixEcran){
            //Appel de la bonne fonction en fonction de l'entree utilisateur
            case 1 -> priseCommande();
            case 2 -> afficherCuisine();
            case 3 -> afficherBar();
            case 4 -> monitorEmployees();
        }
    }
    static void priseCommande(){

    }
    static void afficherCuisine(){

    }
    static void afficherBar(){

    }
    static void monitorEmployees(){

    }
}



