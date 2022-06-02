package com.justmax.virtualstyler.data;

import java.util.ArrayList;

public class Product {
    public static class RecommendationMain {
        private final int ID;
        private final String title, description, pathImg;
        private final boolean existDiscount;
        private double discount;
        private final double price;

        public RecommendationMain(int ID, String title, String description, String pathImg, double price, double discount) {
              this.ID = ID;
              this.title = title;
              existDiscount = true;
              this.discount = discount;
              this.description = description;
              this.pathImg = pathImg;
              this.price = price;
        }

        public RecommendationMain(int ID, String title, String description, String pathImg, double price) {
            this.ID = ID;
            this.title = title;
            existDiscount = false;
            this.description = description;
            this.pathImg = pathImg;
            this.price = price;
        }

        public int getID() {
            return ID;
        }

        public String getTitle() {
            return title;
        }

        public double getDiscount() {
            return discount;
        }

        public boolean existsDiscount() {
            return existDiscount;
        }

        public String getDescription() {
            return description;
        }

        public String getPathImg() {
            return pathImg;
        }

        public double getPrice() {
            return price;
        }
    }

    public static class FamousMain {
        private final int ID;
        private final String title, description, pathImg;
        private final boolean existDiscount;
        private double newPrice;
        private final double oldPrice;

        public FamousMain(int ID, String title, String description, String pathImg, double oldPrice, double newPrice) {
            this.ID = ID;
            this.title = title;
            existDiscount = true;
            this.newPrice = newPrice;
            this.description = description;
            this.pathImg = pathImg;
            this.oldPrice = oldPrice;
        }

        public FamousMain(int ID, String title, String description, String pathImg, double price) {
            this.ID = ID;
            this.title = title;
            existDiscount = false;
            this.description = description;
            this.pathImg = pathImg;
            this.oldPrice = price;
        }

        public int getID() {
            return ID;
        }

        public String getTitle() {
            return title;
        }

        public double getNewPrice() {
            return newPrice;
        }

        public boolean existsDiscount() {
            return existDiscount;
        }

        public String getDescription() {
            return description;
        }

        public String getPathImg() {
            return pathImg;
        }

        public double getOldPrice() {
            return oldPrice;
        }
    }

    public static class UsualMain {
        private final int ID, rating, count, when;
        private final String title, type;
        private final ArrayList<String> pathsImg;
        private final boolean existDiscount;
        private double newPrice;
        private final double oldPrice;

        public UsualMain(int ID, String title, String type, ArrayList<String> pathsImg, double oldPrice, double newPrice, int rating, int count, int when) {
            this.ID = ID;
            this.rating = rating;
            this.count = count;
            this.when = when;
            this.title = title;
            this.type = type;
            this.pathsImg = pathsImg;
            existDiscount = true;
            this.newPrice = newPrice;
            this.oldPrice = oldPrice;
        }

        public UsualMain(int ID, String title, String type, ArrayList<String> pathsImg, double price, int rating, int count, int when) {
            this.ID = ID;
            this.rating = rating;
            this.count = count;
            this.when = when;
            this.title = title;
            this.type = type;
            this.pathsImg = pathsImg;
            existDiscount = false;
            this.oldPrice = price;
        }

        public int getID() {
            return ID;
        }

        public int getRating() {
            return rating;
        }

        public int getCount() {
            return count;
        }

        public int getWhen() {
            return when;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }

        public ArrayList<String> getPathsImg() {
            return pathsImg;
        }

        public boolean existDiscount() {
            return existDiscount;
        }

        public double getNewPrice() {
            return newPrice;
        }

        public double getOldPrice() {
            return oldPrice;
        }
    }
}
