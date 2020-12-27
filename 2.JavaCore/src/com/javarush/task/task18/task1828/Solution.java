package com.javarush.task.task18.task1828;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/* 
Прайсы 2
*/

public class Solution {

    public static class Product {
        int id;
        String name;
        String price;
        String quantity;

        public Product(int id, String name, String price, String quantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return String.format("%-8d%-30s%-8s%-4s", id, name, price, quantity);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
//        String fileName = "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task18/task1828/file.txt";

        List<Product> products = new ArrayList<>();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                String string = fileReader.readLine();
                if (string.length() < 50) {
                    for (int i = string.length(); i < 50 ; i++) {
                        string += " ";
                    }
                }
                Product product = getProduct(string);
                products.add(product);
            }
        }

        List<Product> copy = new ArrayList<>(products);

        switch (args[0]) {
            case "-d":
                int id = Integer.parseInt(args[1]);

                for (Product product : copy) {
                    if (product.id == id) {
                        products.remove(product);
                    }
                }
                break;

            case "-u":
                String string_id = args[1];
                if (string_id.length() > 8) {
                    string_id = string_id.substring(0, 8);
                }
                int new_id = Integer.parseInt(string_id);

                String name = "";
                for (int i = 2; i < args.length - 2; i++) {
                    name += args[i] + " ";
                }
                if (name.length() > 30) {
                    name = name.substring(0, 30);
                }

                String price = args[args.length - 2];
                if (price.length() > 8) {
                    price = price.substring(0, 8);
                }

                String quantity = args[args.length - 1];
                if (quantity.length() > 4) {
                    quantity = quantity.substring(0, 4);
                }

                Product product = new Product(new_id, name.trim(), price, quantity);

                for(int i = 0; i < products.size(); i++) {
                    if(products.get(i).id == new_id) {
                        products.set(i, product);
                        break;
                    }
                }

                break;
        }
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (Product product : products) {
                fileWriter.write(product.toString());
                fileWriter.write("\n");
            }
        }
    }

    public static Product getProduct(String string) {
        String id = string.substring(0, 8).trim();
        String name = string.substring(8, 38).trim();
        String price = string.substring(38, 46).trim();
        String quantity = string.substring(46, 50).trim();
        return new Product(Integer.parseInt(id), name, price, quantity);
    }
}
