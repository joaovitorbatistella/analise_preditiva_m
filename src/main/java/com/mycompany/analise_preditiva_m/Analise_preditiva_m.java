/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.analise_preditiva_m;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author joaovb
 */
public class Analise_preditiva_m {
    
    public static int findInArray(String a, String[] table) {
        int pos = -1;
        
        for (int i = 0; i < table.length; i++)
        {
            if(table[i] != null) {
                if (table[i].equals(a)) {
                    return i;
                }   
            }
        }
        
        return pos;
    }
    
     public static int getNextEmptyPosition(String[][] table, int d) {
        int pos = -1;
        
        if (d==0) {
            for (int i = 0; i < table.length; i++)
            {
                if(table[i][0] == null) {
                        return i;
                }
            }
        } else {
            for (int i = 0; i < table.length; i++)
            {

                for (int j = 0; j < table[0].length; j++)
                {
                    if(table[i][j] == null) {
                        return j;
                    }
                }
            }
        }
        
        
        return pos;
    }

    public static void main(String[] args) {
        
        Scanner ler = new Scanner(System.in);
        ArrayList<String> rules = new ArrayList();
        ArrayList<String> first = new ArrayList();
        ArrayList<String> follow = new ArrayList();
        
        System.out.println("Number of rules: ");
        int length = ler.nextInt();
        System.out.println("Number of variables: ");
        int vars_length = ler.nextInt();
        
        String[][] table = new String[length+1][vars_length+1];
        table[0][0] = "\0";
        
        int c,d,e,i;
        String temp;
        
        System.out.println("");
        for(c=0; c<length; c++) {
            System.out.print("Enter a rule: ");
            temp = ler.next();
            rules.add(temp);
        }
        System.out.println("");
        for(d=0; d<length; d++) {
            System.out.print("Enter a first set for "+(d+1)+ "º rule: ");
            temp = ler.next();
            first.add(temp);
        }
        System.out.println("");
        for(e=0; e<length; e++) {
            System.out.print("Enter a follow set for "+(e+1)+ "º rule: ");
            temp = ler.next();
            follow.add(temp);
        }

        int r = 0;
        for(String rule : rules) {
            String[] splited1 = rule.split("=");
            String A, alpha;

            A = splited1[0];
            alpha = splited1[1];

            System.out.println("A: " + A + " alpha: " + alpha);

            String[] splited_First = first.get(r).split("=");
            String first_alpha = splited_First[1];

            String[] first_alpha_el = first_alpha.substring(1, first_alpha.length() - 1).split(",");

            for(String el : first_alpha_el) {
                if(el.equals("#")) {
                    continue;
                }
                int position = findInArray(el, table[0]);
                if(position == -1) {
                    int pp = getNextEmptyPosition(table,1);
                    if(pp!=-1) {
                        table[0][pp] = el;                    
                    }
                }

                int k=0;
                position=-1;
                while (position == -1 && k<table.length) {
                    if(table[k][0] != null) {
                        if (table[k][0].equals(A)) {
                            position = k;
                            continue;
                        } 
                    }
                    k++;                    
                }

                if(position == -1) {
                    int pp = getNextEmptyPosition(table,0);
                    if(pp!=-1) {
                        table[pp][0] = A;
                    }
                }

            }
            r++;
        }
        
        int pp = getNextEmptyPosition(table,1);
        if(pp!=-1) {
            table[0][pp] = "$";                    
        }
        
        System.out.println("\nTable: ");
        for (int l = 0; l < table.length; l++) {
            for (int m = 0; m < table[0].length; m++) {
                if(table[l][m] != null ) {
                    System.out.print("| " + table[l][m] + "\t");   
                } else {
                    System.out.print("|\t");    
                }
            }
            System.out.println("");
        }
        
        
        r = 0;
        for(String rule : rules) {
            String[] splited1 = rule.split("=");
            String A, alpha;

            A = splited1[0];
            alpha = splited1[1];

            String[] splited_First = first.get(r).split("=");
            String first_alpha = splited_First[1];

            String[] first_alpha_el = first_alpha.substring(1, first_alpha.length() - 1).split(",");

            for(String el : first_alpha_el) {           
                if(el.equals("#")) {
                    continue;
                }
                int pp_y = findInArray(el, table[0]);
                if(pp_y != -1) {                   
                    int k=0;
                    int pp_x =-1;
                    while (pp_x == -1 && k<table.length) {
                        if(table[k][0] != null) {
                            if (table[k][0].equals(A)) {
                                pp_x = k;
                                continue;
                            } 
                        }
                        k++;                    
                    }
                    
                    String[] arr_value = alpha.split("\\|");
                    String value = arr_value[0];
                    table[pp_x][pp_y] = value;
                }

            }
            r++;
        }
        
        System.out.println("\nTable: ");
        for (int l = 0; l < table.length; l++) {
            for (int m = 0; m < table[0].length; m++) {
                if(table[l][m] != null ) {
                    System.out.print("| " + table[l][m] + "\t");   
                } else {
                    System.out.print("|\t");    
                }
            }
            System.out.println("");
        }
    }
}

