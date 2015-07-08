package br.com.box.model;

import java.util.ArrayList;
import java.util.Collection;

public class TestaPerformance {

    public static void main(String[] args) {
        System.out.println("Iniciando...");
        Collection<Integer> teste = new ArrayList<Integer>();

        long inicio = System.currentTimeMillis();

        int total = 50000;

        for (int i = 0; i < total; i++) {
            teste.add(i);
        }

        long fimInsercao = System.currentTimeMillis();
        long tempo = fimInsercao - inicio;
        System.out.println("Tempo Inserção: " + tempo);

        long inicioPesquisa = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            teste.contains(i);
        }
        long fim = System.currentTimeMillis();

        tempo = fim - inicioPesquisa;
        System.out.println("Tempo Pesquisa: " + tempo);

        tempo = fim - inicio;
        System.out.println("Tempo gasto: " + tempo);
    }
}
