package Vue_Run;

import Modele.Articles.Lit_medical;

public class Main {

    public static void main(String[] args)
    {
        int stock_litMed = 0;

        Lit_medical lit = new Lit_medical("Lit Med 1","LucasTechnologie"
                ,"Quali1",3,
                120.0,60.0,49.0,75.0,195,"Standard");

        System.out.println(lit.toString());
        stock_litMed++;
        System.out.println("Nombre de lits medicalisé : " + stock_litMed);


    }
}
