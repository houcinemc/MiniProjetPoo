package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Mining {

    private Statement statement = null;

    public Mining(Statement statement) {
        this.statement = statement;
    }

    /**
     * @param recette    la recette en entrée
     * @param ingredient l'ingredient en entrée
     * @return Vrai s'il se compose, faux sinon
     */
    public boolean recipeMining(int recette, int ingredient) {
        boolean state = false;
        try {

            ResultSet rs = statement.executeQuery("SELECT * FROM INGREDIENT_RECETTE " +
                    "WHERE RECETTE_ID=" + recette + " AND " +
                    "INGREDIENT_ID=" + ingredient + ";");


            if (rs.next())
                state = true;
            else
                state = false;

            rs.close();
            statement.close();

            return state;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }

    /**
     * @return la liste des recettes dans la base de données
     */
    public ArrayList<Integer> allRecipe() {

        ArrayList<Integer> ints = new ArrayList<>();
        try {

            ResultSet rs = statement.executeQuery("SELECT * FROM RECETTE;");

            while (rs.next()) {
                ints.add(rs.getInt("id"));
            }

            rs.close();
            statement.close();

            return ints;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void dataMining(int[] ingredients) {

        ArrayList<Integer> ints = allRecipe(); // tableau des recettes existantes
        int cumule = 0;
        int ingredient;
        for (int i = 0; i < ints.size(); i++) {
            for (int j = 0; j < ingredients.length; j++) {

                ingredient = ingredients[j];

                if (recipeMining(ints.get(i), ingredient))
                    cumule++;
            }

            if (cumule >= 3) { // les 3 necessaires
                System.out.println("La recette Numero : " + ints.get(i) + " peut être préparée");
            }
            cumule = 0;
        }
    }
}

