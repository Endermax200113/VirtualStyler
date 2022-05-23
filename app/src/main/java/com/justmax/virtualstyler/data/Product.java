package com.justmax.virtualstyler.data;

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
}
