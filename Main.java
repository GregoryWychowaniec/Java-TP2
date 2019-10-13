import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Collections;

public class Main{
    //Liste qui contient les livre et magazines
    private static ArrayList<Livre> bibliotheque;
    //Scanner pour les entrées utilisateur
    private static Scanner scanner;

    public static void main(String[] args) {
        //Initialisation des attributs
        bibliotheque = new ArrayList<Livre>();

        int choix = -1;
        //Menu intéractif
        while (choix != 0) {
            System.out.println("-------Menu-------");
            System.out.println("1 - Ajouter un livre ou un magazine");
            System.out.println("2 - Afficher la bibliothèque");
            System.out.println("3 - Afficher les livres");
            System.out.println("4 - Afficher les magazines");
            System.out.println("5 - Trier la bibliothèque par ordre alphabétique");
            System.out.println("6 - Trier la bibliothèque par date de sortie");
            System.out.println("0 - Quitter");
            System.out.print("Choix : ");
            scanner = new Scanner(System.in);
            try {
                choix = scanner.nextInt();
            } catch (InputMismatchException e) { //Récupère l'exception si un entier n'est pas rentré
                System.out.println("Veuillez entrer un entier");
                choix = -1;
            }
            //Cas en fonction de la réponse utilisateur
            switch (choix) {
                case 1: //Ajouter un livre
                    try {
                        ajouter();
                    } catch (InputMismatchException e) { //On récupère l'exception en cas de mauvaise entrée utilisateur
                        System.out.println("Erreur lors de la saisie des informations");
                    }
                    break;
                case 2: //Afficher la bibliothèque
                    afficher();
                    break;
                case 3: //Afficher seulement les livres de la bibliothèque
                    afficherLivres();
                    break;
                case 4: //Afficher seulement les magazines de la bibliothèque
                    afficherMagazines();
                    break;
                case 5: //Trier la bibliothèque par ordre alphabétique
                    trierAlpha();
                    break;
                case 6: //Trier la bibliothèque par date de sortie
                    trierDate();
                    break;
                case 0: //Sortie du programme
                    System.out.println("Bonne journée");
                    break;
                default:
                    System.out.println("Choix non disponible");
            }
            System.out.println();
        }
    }

    /**
    Méthode pour ajouter un livre dans la bibliothèque
    Peut retourner une exception en cas de mauvaise entrée utilisateur
     */
    public static void ajouter() throws InputMismatchException {
        try {
            //Ajout d'un élément dans la liste
            //Demande des informations pour créer l'élément
            Livre newLivre = new Livre();

            //Titre du livre
            System.out.print("Titre du livre : ");
            newLivre.titre = scanner.next();

            //Prix du livre
            System.out.print("Prix du livre : ");
            newLivre.prix = scanner.nextInt();

            //Date du livre
            System.out.println("Date de sortie du livre : ");
            System.out.print(" - Jour : ");
            int jour = scanner.nextInt();
            System.out.print(" - Mois : ");
            int mois = scanner.nextInt();
            System.out.print(" - Année : ");
            int annee = scanner.nextInt();
            Calendar calendar = new GregorianCalendar(annee, mois-1, jour);
            newLivre.date = calendar;
            
            //Categorie
            System.out.print("Categorie du livre : ");
            newLivre.categorie = scanner.next();

            //Note du livre
            System.out.print("Note du livre : ");
            newLivre.note = scanner.nextInt();

            //Periodicite, et spécification Magazine
            System.out.println("Périodicité du magazine");
            System.out.print(" Entrer 0 si c'est un livre : ");
            int periodicite = scanner.nextInt();
            if (periodicite > 0) { //Cas d'un magazine
                Magazine newMagazine = new Magazine();
                newMagazine.createFromLivre(newLivre);
                newLivre = null;
                newMagazine.periodicite = periodicite;
                bibliotheque.add(newMagazine);
            } else {
                bibliotheque.add(newLivre);
            }
        } catch (InputMismatchException e) { //Exception en cas de mauvaise entrée utilisateur
            throw e;
        }
    }

    /**
    Méthode pour afficher la totalité de la bibliothèque
     */
    public static void afficher() {
        for(Livre l : bibliotheque) {
            l.afficher();
        }
    }

    /**
    Méthode pour afficher seulement les livres de la bibliothèque
     */
    public static void afficherLivres() {
        for(Livre l : bibliotheque) {
            if(!(l instanceof Magazine)) { //On récupère seulement les objets qui ne sont pas des instances de Magazine
                l.afficher();
            }
        }
    }

    /**
    Méthode pour afficher seulement les magazines de la bibliothèque
     */
    public static void afficherMagazines() {
        for(Livre l : bibliotheque) {
            if(l instanceof Magazine) { //On récupère seulement les objets qui sont des instances de Magazine
                l.afficher();
            }
        }
    }

    /**
    Méthode pour trier la bibliothèque par ordre alphabétique
     */
    public static void trierAlpha() {
        Collections.sort(bibliotheque, new SortParAlpha());
        //On peut aussi directement utiliser un appel lambda pour éviter de créer une classe
        //Collections.sort(bibliotheque, (a, b) -> a.titre.compareTo(b.titre));
        System.out.println("Bibliothèque triée par ordre alphabétique");
    }

    /**
    Méthode pour trier la bibliothèque par date de sortie
     */
    public static void trierDate() {
        Collections.sort(bibliotheque, new SortParDate());
        //Pareil pour l'appel lambda
        //Collections.sort(bibliotheque, (a, b) -> a.date.compareTo(b.date));
        System.out.println("Bibliothèque triée par date de sortie");
    }


    /**
    Classe qui implémente Comparator pour trier par rapport au titre du livre
     */
    private static class SortParAlpha implements Comparator<Livre> {
        public int compare(Livre a, Livre b) {
            return a.titre.compareTo(b.titre);
        }
    }

    /**
    Classe qui implémente Comparator pour trier par rapport à la date de sortie du livre
     */
    private static class SortParDate implements Comparator<Livre> {
        public int compare(Livre a, Livre b) {
            return a.date.compareTo(b.date);
        }
    }

    //On peut implémenter d'autres classes Comparator si on veut faire un tri sur d'autres attributs
}
