package com.company;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void print(ArrayList<Camera> mas) {
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("|  Model  | Crop | Resolution | FPS | Native ISO | Color Deep | Mb/s | Stabilization | Weight |");
        System.out.println("----------------------------------------------------------------------------------------------");
        for (int i = 0; i < mas.size(); i++) {
            System.out.println("| " + mas.get(i).pName + " | " + mas.get(i).crop + "  |     " + mas.get(i).resolution + "      | " + mas.get(i).fps + "  |     " + mas.get(i).iso + "    |   " + mas.get(i).deep + "    | " + mas.get(i).speed + "  |    " + mas.get(i).stab + "       |  " + mas.get(i).weight + "  |");
        }
        System.out.println("----------------------------------------------------------------------------------------------");
    }

    public static void firstPar(ArrayList<Camera> mas) {
        for (int i = 0; i < mas.size(); i++) {
            for (int j = 0; j < mas.size(); j++) {

                mas.get(i).fPar = 0;
                mas.get(j).fPar = 0;

                if (mas.get(i).weight > mas.get(j).weight) {
                    mas.get(j).fPar += 1;
                } else if (mas.get(i).weight < mas.get(j).weight) {
                    mas.get(i).fPar += 1;
                } else {
                    mas.get(i).fPar += 1;
                    mas.get(j).fPar += 1;
                }

                if (mas.get(i).crop > mas.get(j).crop) {
                    mas.get(j).fPar += 1;
                } else if (mas.get(i).crop < mas.get(j).crop) {
                    mas.get(i).fPar += 1;
                } else {
                    mas.get(i).fPar += 1;
                    mas.get(j).fPar += 1;
                }

                if (mas.get(i).resolution > mas.get(j).resolution) {
                    mas.get(i).fPar += 1;
                } else if (mas.get(j).resolution < mas.get(j).resolution) {
                    mas.get(j).fPar += 1;
                } else {
                    mas.get(i).fPar += 1;
                    mas.get(j).fPar += 1;
                }

                if (mas.get(i).fps > mas.get(j).fps) {
                    mas.get(i).fPar += 1;
                } else if (mas.get(i).fps < mas.get(j).fps) {
                    mas.get(j).fPar += 1;
                } else {
                    mas.get(i).fPar += 1;
                    mas.get(j).fPar += 1;
                }

                if (mas.get(i).iso > mas.get(j).iso) {
                    mas.get(i).fPar += 1;
                } else if (mas.get(i).iso < mas.get(j).iso) {
                    mas.get(j).fPar += 1;
                } else {
                    mas.get(i).fPar += 1;
                    mas.get(j).fPar += 1;
                }

                if (mas.get(i).deep > mas.get(j).deep) {
                    mas.get(i).fPar += 1;
                } else if (mas.get(i).deep < mas.get(j).deep) {
                    mas.get(j).fPar += 1;
                } else {
                    mas.get(i).fPar += 1;
                    mas.get(j).fPar += 1;
                }

                if (mas.get(i).speed > mas.get(j).speed) {
                    mas.get(i).fPar += 1;
                } else if (mas.get(i).speed < mas.get(j).speed) {
                    mas.get(j).fPar += 1;
                } else {
                    mas.get(i).fPar += 1;
                    mas.get(j).fPar += 1;
                }

                if (mas.get(i).stab && !mas.get(j).stab) {
                    mas.get(i).fPar += 1;
                } else if (!mas.get(i).stab && mas.get(j).stab) {
                    mas.get(j).fPar += 1;
                } else if (mas.get(i).stab && mas.get(j).stab) {
                    mas.get(i).fPar += 1;
                    mas.get(j).fPar += 1;
                } else if (!mas.get(i).stab && !mas.get(j).stab) {
                    mas.get(i).fPar += 1;
                    mas.get(j).fPar += 1;
                }

                if (mas.get(i).fPar == 8) {
                    mas.get(i).dfPar += 1;
                } else if (mas.get(j).fPar == 8) {
                    mas.get(j).dfPar += 1;
                } else if (mas.get(i).fPar == 8 && mas.get(j).fPar == 8) {
                    mas.get(i).dfPar += 1;
                    mas.get(j).dfPar += 1;
                }
            }
        }
    }

    public static void upDown(ArrayList<Camera> mas) {
        for (int i = 0; i < mas.size(); i++) {
            if (mas.get(i).crop <= 1.5 && mas.get(i).iso >= 600) {
                mas.get(i).upDown += 1;
            }
        }
    }

    public static ArrayList<Camera> SUB(ArrayList<Camera> mas) {
        ArrayList<Camera> mas1 = new ArrayList<>();
        //Проверка по границам
        for (int i = 0; i < mas.size(); i++) {
            if (mas.get(i).crop <= 1.5 && mas.get(i).resolution >= 4 && mas.get(i).fps >= 30 && mas.get(i).iso >= 300 && mas.get(i).deep >= 10420 && mas.get(i).speed >= 200 && mas.get(i).stab) {
                mas.get(i).sub += 1;
            }
        }

        //Записываем камеры прошедшие границы в отдельный массив
        for (int i = 0; i < mas.size(); i++) {
            if (mas.get(i).sub == 1) {
                mas1.add(mas.get(i));
            }
        }

        //Сортируем камеры по главному критерию - вес
        for (int i = 0; i < mas1.size(); i++) {
            int valueToSort = mas1.get(i).weight;
            Camera temp = mas1.get(i);
            int j = i;
            while (j > 0 && mas1.get(j - 1).weight > valueToSort) {
                mas1.set(j, mas1.get(j - 1));
                j--;
            }
            mas1.set(j, temp);
        }

        return mas1;
    }

    public static ArrayList<Camera> leks(ArrayList<Camera> mas) {
        ArrayList<Camera> mas1 = new ArrayList<>();

        // Проверка по первому параметру
        for (int i = 0; i < mas.size(); i++) {
            if (mas.get(i).crop == 1) {
                mas1.add(mas.get(i));
            }
        }
        // Проверка по второму параметру
        if (mas1.size() > 1) {
            for (int i = 0; i < mas1.size(); i++) {
                if (!mas1.get(i).stab) {
                    mas1.remove(i);
                }
            }
        }
        // Сортировка по третьему параметру
        if (mas1.size() > 1) {
            for (int i = 0; i < mas1.size(); i++) {
                int valueToSort = mas1.get(i).fps;
                Camera temp = mas1.get(i);
                int j = i;
                while (j > 0 && mas1.get(j - 1).fps < valueToSort) {
                    mas1.set(j, mas1.get(j - 1));
                    j--;
                }
                mas1.set(j, temp);
            }
        }

        //Остается лучший по третьему параметру
        for (int i = 1; i < mas1.size(); i++) {
            mas1.remove(i);
        }
        return mas1;
    }

    public static void main(String[] args) {

        Camera XT4 = new Camera("X-T4", "  X-T4 ", 501, 1.5, 4, 60, 640, 10422, 400, true);
        Camera XT3 = new Camera("X-T3", "  X-T3 ", 539, 1.5, 4, 60, 640, 10422, 400, false);
        Camera XT30 = new Camera("X-T30", "  X-T30", 680, 1.5, 4, 30, 640, 10420, 200, false);
        Camera BMPCC6K = new Camera("BMPCC6K", "BMPCC6K", 499, 1, 6, 60, 3200, 12422, 483, false);
        Camera BMPCC4K = new Camera("BMPCC4K", "BMPCC4K", 680, 1.9, 4, 60, 3200, 12422, 203, false);
        Camera BMMCC = new Camera("BMMCC", " BMMCC ", 700, 1.9, 1, 0, 600, 10422, 65, false);
        Camera A7sIII = new Camera("A7sIII", " A7sIII", 500, 1, 4, 120, 800, 10422, 300, true);
        Camera A7sII = new Camera("A7sII", " A7sII ", 627, 1, 4, 30, 100, 8420, 100, true);
        Camera A7sI = new Camera("A7sI", "  A7sI ", 699, 1, 4, 30, 100, 8420, 28, false);
        Camera a6600 = new Camera("a6600", " a6600 ", 503, 1.5, 4, 30, 100, 8420, 200, true);

        ArrayList<Camera> mainMas = new ArrayList<>();
        mainMas.add(XT4);
        mainMas.add(XT3);
        mainMas.add(XT30);
        mainMas.add(BMPCC6K);
        mainMas.add(BMPCC4K);
        mainMas.add(BMMCC);
        mainMas.add(A7sIII);
        mainMas.add(A7sII);
        mainMas.add(A7sI);
        mainMas.add(a6600);

        ArrayList<Camera> parMas = new ArrayList<>();
        ArrayList<Camera> upMas = new ArrayList<>();
        ArrayList<Camera> upMasPar = new ArrayList<>();
        ArrayList<Camera> subMas = new ArrayList<>();
        ArrayList<Camera> leks = new ArrayList<>();


        System.out.println("Изначальная таблица:");
        print(mainMas);

        firstPar(mainMas); //Определяются Парето оптимальыне варианты

        for (int i = 0; i < mainMas.size(); i++) //Парето-оптимаьные варианты записываются в отдельный массив
        {
            if (mainMas.get(i).dfPar>=1){
                parMas.add(mainMas.get(i));
            }
        }

        System.out.println("Парето-оптимальные варианты:");
        print(parMas);

        upDown(mainMas); //Определяются варианты, подходящие под границы: ISO не меньше 600, кроп не более 1.5

        for (int i = 0; i < mainMas.size(); i++) {//Варианты, подходящие под границы, записываются в отдельный массив
            if (mainMas.get(i).upDown==1){
                upMas.add(mainMas.get(i));
            }
        }

        System.out.println("Варианты, подходящие под границы: ISO не меньше 600, кроп не более 1.5: ");
        print(upMas);

        System.out.println("Из них Парето-оптимальные: ");
        for (int i = 0; i < upMas.size(); i++) {
            if (upMas.get(i).dfPar >= 1) {
                upMasPar.add(upMas.get(i));
            }
        }

        print(upMasPar);


        subMas = SUB(mainMas); //Производится субоптимизация

        System.out.println("Варианты, прошедшие субоптимизацию: ");

        print(subMas);


        leks = leks(mainMas); //Производится лексикографическая оптимизация

        System.out.println("Варианты, прошедшие лексикографическую оптимизацию: ");

        print(leks);
    }
}
