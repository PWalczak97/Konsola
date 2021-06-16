package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        String path = "C:\\Users\\Patryk\\Desktop\\konsola";
        File katalog = new File(path);
        boolean bool1 = true;
        while(bool1){
            String scanner = sc.next();

            if(scanner.equals("exit")){
                bool1 = false;
            } else if(scanner.equals("mkdir")){

                System.out.println("Podaj nazwe katalogu jaki chcesz utworzyc");
                String nazwaKatalogu = sc.next();



                File nowyKatalog = new File(path + "\\" + nazwaKatalogu);
                boolean czyIstnieje = false;
                File[] pliki = katalog.listFiles();
                for(int i =0;i<pliki.length;i++){
                    if(pliki[i].getName().equals(nazwaKatalogu)){
                        czyIstnieje = true;
                        System.out.println("Taki katalog juz istnieje");
                    }
                }

                if(czyIstnieje==false){
                    nowyKatalog.mkdir();
                    System.out.println("Katalog zostal utworzony");
                }




            } else if(scanner.equals("rd")){

                System.out.println("Podaj nazwe katalogu jaki chcesz usunac");
                String nazwaKatalogu = sc.next();

                File plikDoUsuniecia = new File(path + "\\" + nazwaKatalogu);
                boolean czyIstnieje = false;
                File[] pliki = katalog.listFiles();
                for(int i =0;i<pliki.length;i++){
                    if(pliki[i].getName().equals(nazwaKatalogu)){
                        czyIstnieje = true;
                        File[] plikiWSrodku = plikDoUsuniecia.listFiles();
                        for(int j = 0;j<plikiWSrodku.length;j++){
                            plikiWSrodku[j].delete();
                        }
                        plikDoUsuniecia.delete();

                        System.out.println("Plik został usuniety");
                    }
                }

                if(czyIstnieje==false){
                    System.out.println("Nie istenieje");
                }
            } else if(scanner.equals("dir")){
                System.out.println("Podaj nazwe katalogu z ktorego chcesz wyswietlic zawartosc");
                String nazwaKatalogu = sc.next();

                File katalogDoPrzejrzenia = new File(path + "\\" + nazwaKatalogu);
                boolean czyIstnieje = false;
                File[] pliki = katalog.listFiles();

                for(int i =0;i<pliki.length;i++){
                    if(pliki[i].getName().equals(nazwaKatalogu)){
                        czyIstnieje = true;
                        File[] wKatalogu = katalogDoPrzejrzenia.listFiles();
                        for(int j = 0;j<wKatalogu.length;j++){
                            System.out.println(wKatalogu[j].getName());
                        }
                    }
                }
                if(czyIstnieje==false){
                    System.out.println("Katalog nie istenieje");
                }
            } else if(scanner.equals("cd")){
                System.out.println("Podaj nazwe katalogu do ktorego chcesz wejsc");
                String nazwaKatalogu = sc.next();

                boolean czyIstnieje = false;
                File[] pliki = katalog.listFiles();
                for(int i =0;i<pliki.length;i++){
                    if(pliki[i].getName().equals(nazwaKatalogu)){
                        czyIstnieje = true;
                        path = path + "\\" + nazwaKatalogu;
                        katalog = new File(path);
                        System.out.println("Znajdujesz sie w " + katalog.getPath());
                    }
                }

                if(czyIstnieje==false)
                    System.out.println("Nie ma takiego katalogu");
            } else if(scanner.equals("back")){
                File tmp = new File(path);
                path = tmp.getParent();
                katalog = new File(path);
                System.out.println("Znajdujesz sie w " + katalog.getPath());
            } else if(scanner.equals("move")){
                System.out.println("Podaj nazwe katalogu ktory chcesz przeniesc");
                String nazwaKatalogu = sc.next();
                File staryKatalog = new File(path + "\\" + nazwaKatalogu);

                boolean czyIstnieje = false;
                boolean czyIstnieje2 = false;
                File[] pliki = katalog.listFiles();
                for(int i =0;i<pliki.length;i++){
                    if(pliki[i].getName().equals(nazwaKatalogu)){
                        czyIstnieje = true;
                        System.out.println("Podaj nazwe katalogu do ktorego chcesz go przeniesc");
                        String miejsceDocelowe = sc.next();
                        for(int j =0;j<pliki.length;j++){
                            if(pliki[j].getName().equals(miejsceDocelowe)){
                                czyIstnieje2 = true;
                                File[] starePliki = staryKatalog.listFiles();

                                File katalogDocelowy = new File(path + "\\" + miejsceDocelowe);
                                File przeniesionyKatalog = new File(katalogDocelowy.getPath() + "\\" + nazwaKatalogu);
                                przeniesionyKatalog.mkdir();
                                for(int k = 0;k<starePliki.length;k++){
                                    File noweKatalogi = new File(przeniesionyKatalog.getPath() + "\\" +
                                            starePliki[k].getName());
                                    if(starePliki[k].isFile())
                                        noweKatalogi.createNewFile();

                                    if(starePliki[k].isDirectory())
                                        noweKatalogi.mkdir();

                                    starePliki[k].delete();
                                }
                                staryKatalog.delete();
                                System.out.println("Katalog przeniesiony");
                            }
                        }
                        if(czyIstnieje2==false)
                            System.out.println("Nie ma takiego katalogu");
                    }
                }
            } else if(scanner.equals("size")){
                System.out.println("Podaj nazwe katalogu ktorego chcesz poznac wielkosc");
                String nazwaKatalogu = sc.next();

                boolean czyIstnieje = false;
                File[] pliki = katalog.listFiles();
                for(int i =0;i<pliki.length;i++){
                    if(pliki[i].getName().equals(nazwaKatalogu)){
                        czyIstnieje = true;
                        File plik = new File(path + "\\" + nazwaKatalogu);
                        System.out.println("Wielkosc " + nazwaKatalogu + " to " + plik.getTotalSpace());
                    }
                }

                if(czyIstnieje==false)
                    System.out.println("Nie ma takiego katalogu");
            }  else if(scanner.equals("create")){
                System.out.println("Podaj nazwe pliku jaki chcesz utworzyc");
                String nazwaPliku = sc.next();

                boolean czyIstnieje = false;
                File[] pliki = katalog.listFiles();
                for(int i =0;i<pliki.length;i++){
                    if(pliki[i].getName().equals(nazwaPliku)){
                        czyIstnieje = true;
                        System.out.println("Plik juz istnieje");
                    }
                }

                if(czyIstnieje==false){
                    File nowyPlik = new File(path + "\\" + nazwaPliku);
                    nowyPlik.createNewFile();
                    System.out.println("Plik zostal utworzony");
                }


            } else if(scanner.equals("delete")){
                System.out.println("Podaj nazwe pliku jaki chcesz usunac");
                String nazwaPliku = sc.next();

                boolean czyIstnieje = false;
                File[] pliki = katalog.listFiles();
                for(int i =0;i<pliki.length;i++){
                    if(pliki[i].getName().equals(nazwaPliku)){
                        czyIstnieje = true;
                        pliki[i].delete();
                        System.out.println("Plik zostal usuniety");
                    }
                }

                if(czyIstnieje==false){
                    System.out.println("Nie ma takiego pliku");
                }
            } else if(scanner.equals("date")){
                System.out.println("Podaj nazwe katalogu lub pliku ktorego chcesz poznac date powstania");
                String nazwa = sc.next();

                boolean czyIstnieje = false;
                File[] pliki = katalog.listFiles();
                for(int i =0;i<pliki.length;i++){
                    if(pliki[i].getName().equals(nazwa)){
                        czyIstnieje = true;

                    }
                }

                if(czyIstnieje==false){
                    System.out.println("Nie ma takiego pliku");
                }
            }
        }
    }
}
