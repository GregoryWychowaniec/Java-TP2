import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Livre{
    public String titre;
    public int prix;
    public Calendar date;
    public String categorie;
    public int note;

    /**
    Méthode qui affiche les attributs d'un livre
     */
    protected void afficheInfos() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        System.out.println("    Titre : " + titre);
        System.out.println("    Prix : " + prix);
        System.out.println("    Date de sortie : " + sdf.format(date.getTime()));
        System.out.println("    Catégorie : " + categorie);
        System.out.println("    Note des lecteurs : " + note);
    }

    /**
    Méthode appelée pour afficher les infos sur un livre
     */
    public void afficher() {
        System.out.println("Livre : ");
        afficheInfos();
    }

}