package sample;
/**
 * Created by fatima on 25/12/16.
 */
public class RecetteModel {
private String nom;
    private String photo;
    private String etapes;
    private int id;
    private String quantite;

    public RecetteModel(int id,String nom, String quantite) {
        this.nom = nom;
        this.id = id;
        this.quantite = quantite;
    }

    public RecetteModel(String nom, String photo, String etapes) {
        this.nom = nom;
        this.photo = photo;
        this.etapes = etapes;

    }

    @Override
    public String toString() {
        return "RecetteModel{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
