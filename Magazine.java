public class Magazine extends Livre {
    public int periodicite;

    /**
    Méthode qui permet de mettre à jour les attributs d'un magazine à partir d'un livre
     */
    public void createFromLivre(Livre l) {
        titre = l.titre;
        prix = l.prix;
        date = l.date;
        categorie = l.categorie;
        note = l.note;
    }
    
    /**
    Méthode qui affiche les informations d'un magazine
     */
    @Override
    public void afficher() {
        System.out.println("Magazine : ");
        afficheInfos();
        System.out.println("    Periodicite : " + periodicite);
    }
}