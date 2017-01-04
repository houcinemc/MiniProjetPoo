package database;

import java.sql.*;

public class DBConnection {

    private Connection c = null;

    private Statement statement = null;
    private boolean etat;

    public DBConnection() {
        this.etat = false;
    }

    /**
     * Methode pour le connection avec la base de données
     */
    public void connect() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            etat = false;
        }
        etat = true;
    }

    /**
     * Methode pour la create de la table dans la base de données
     * WARNING : elle se fait une seule fois
     *
     * @return Etat de l'execution de la methode
     */
    public boolean contract() {

        if (!etat)
            return false;

        try {
            statement = c.createStatement();
            String table1 = "CREATE TABLE RECETTE " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " NOM           VARCHAR(100)   NOT NULL, " +
                    " PHOTO         VARCHAR(100)   NOT NULL, " +
                    " ETAPES        TEXT NOT NULL);";

            String table2 = "CREATE TABLE INGREDIENT " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " NOM           VARCHAR(100)   NOT NULL);";

            String table3 = "CREATE TABLE INGREDIENT_RECETTE " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " RECETTE_ID            INT   NOT NULL, " +
                    " INGREDIENT_ID         INT     NOT NULL," +
                    " QUANTITE              INT NOT NULL," +
                    "FOREIGN KEY(RECETTE_ID) REFERENCES RECETTE(id)," +
                    "FOREIGN KEY(INGREDIENT_ID) REFERENCES INGREDIENT(id));";

            statement.executeUpdate(table1); //exection de la requete
            statement.executeUpdate(table2); //exection de la requete
            statement.executeUpdate(table3); //exection de la requete
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Methode pour l'insertion des données dans la base de données
     *
     * @return Etat de l'execution de la methode
     */
    public boolean insertRecette(String nom, String photo, String etape) {
        if (!etat)
            return false;

        try {
            statement = c.createStatement();

            // Requete d'insertion de la donnee
            String sql = "INSERT INTO RECETTE (NOM,PHOTO,ETAPES) " +
                    "VALUES ('" + nom + "', '" + photo + "', '" + etape + "');";

            statement.executeUpdate(sql); //exection de la requete

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * Methode pour l'insertion des données dans la base de données
     *
     * @return Etat de l'execution de la methode
     */
    public boolean insertIngredient(String nom) {
        if (!etat)
            return false;

        try {
            statement = c.createStatement();

            // Requete d'insertion de la donnee
            String sql = "INSERT INTO INGREDIENT (NOM) " +
                    "VALUES ('" + nom + "');";

            statement.executeUpdate(sql); //exection de la requete

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * Methode pour l'insertion des données dans la base de données
     *
     * @return Etat de l'execution de la methode
     */
    public boolean insertIngredientRecette(int id_recette, int id_ingredient, int quantite) {
        if (!etat)
            return false;

        try {
            statement = c.createStatement();

            // Requete d'insertion de la donnee
            String sql = "INSERT INTO INGREDIENT_RECETTE (RECETTE_ID, INGREDIENT_ID, QUANTITE) " +
                    "VALUES (" + id_recette + ", " + id_ingredient + ", " + quantite + ");";

            statement.executeUpdate(sql); //exection de la requete

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * Methode pour l'execution de la requete dans la base de données
     *
     * @return Etat de l'execution de la methode
     */
    public boolean affichageRecette() {
        if (!etat)
            return false;


        try {
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM RECETTE;"); //exection de la requete

            // boucle pour l'affichage des données (plusieurs tuples)
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String photo = rs.getString("photo");
                String etapes = rs.getString("etapes");

                System.out.println("- - - - - RECETTE - - - - -");
                System.out.println("ID = " + id);
                System.out.println("NOM = " + nom);
                System.out.println("PHOTO = " + photo);
                System.out.println("ETAPES = " + etapes);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Methode pour l'execution de la requete dans la base de données
     *
     * @return Etat de l'execution de la methode
     */
    public boolean affichageIngredient() {
        if (!etat)
            return false;


        try {
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM INGREDIENT;"); //exection de la requete

            // boucle pour l'affichage des données (plusieurs tuples)
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");


                System.out.println("- - - - - INGREDIENT - - - - -");
                System.out.println("ID = " + id);
                System.out.println("NOM = " + nom);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Methode pour l'execution de la requete dans la base de données
     *
     * @return Etat de l'execution de la methode
     */
    public boolean affichageQuantite() {
        if (!etat)
            return false;


        try {
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM INGREDIENT_RECETTE;"); //exection de la requete

            // boucle pour l'affichage des données (plusieurs tuples)
            while (rs.next()) {
                int id = rs.getInt("id");
                int recette_id = rs.getInt("recette_id");
                int ingredient_id = rs.getInt("ingredient_id");
                int quantite = rs.getInt("quantite");

                System.out.println("- - - - - QUANTITE - - - - -");
                System.out.println("ID = " + id);
                System.out.println("RECETTE_ID = " + recette_id);
                System.out.println("INGREDIENT_ID = " + ingredient_id);
                System.out.println("QUANTITE = " + quantite);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isEtat() {
        return etat;
    }

    public void close() {
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param ingredients la liste des ingedients naicessaire (3 au minimum) par leurs Clé primaire
     */
    public void mining(int[] ingredients){

        boolean state;

        try {
            statement = c.createStatement();

            // TODO : KERNEL of program
            Mining miningObj = new Mining(statement);
            miningObj.dataMining(ingredients);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
