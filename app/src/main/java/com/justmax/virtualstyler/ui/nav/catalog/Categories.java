package com.justmax.virtualstyler.ui.nav.catalog;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Categories {
    public static final String MEN = "Мужские";
    public static final String WOMEN = "Женские";
    public static final String CHILDREN = "Детские";

    public static class Men {
        public static class Clothes {
            public static final String T_SHIRTS = "Футболки";
            public static final String SHIRTS = "Рубашки";
            public static final String PANTS = "Брюки";
            public static final String OUTERWEAR = "Верхняя одежда";
            public static final String JEANS = "Джинсы";
            public static final String OVERALLS = "Комбинезоны";
            public static final String SUITS = "Костюмы";
            public static final String MIKEY = "Майки";
            public static final String JACKETS = "Пиджаки";
            public static final String HOODIES = "Толстовки";
            public static final String SHORTS = "Шорты";
            public static final String SHOE = "Обуви";
        }

        public static class Shoe {
            public static final String HIGH_SHOES = "Ботинки";
            public static final String GUMSHOES = "Кеды";
            public static final String SNEAKERS = "Кроссовки";
            public static final String RUBBER_SHOES = "Резиновая обувь";
            public static final String SANDALS = "Сандали";
            public static final String BOOTS = "Сапоги";
            public static final String LOW_SHOES = "Туфли";
            public static final String DUTIKI = "Дутики";
            public static final String FLIP_FLOPS = "Шлепанцы";
        }

        private static final ArrayList<String> listClothes = new ArrayList<>();
        private static final ArrayList<String> listShoe = new ArrayList<>();

        static {
            listClothes.add(Clothes.T_SHIRTS); listClothes.add(Clothes.SHIRTS);
            listClothes.add(Clothes.PANTS);    listClothes.add(Clothes.OUTERWEAR);
            listClothes.add(Clothes.JEANS);    listClothes.add(Clothes.OVERALLS);
            listClothes.add(Clothes.SUITS);    listClothes.add(Clothes.MIKEY);
            listClothes.add(Clothes.JACKETS);  listClothes.add(Clothes.HOODIES);
            listClothes.add(Clothes.SHORTS);   listClothes.add(Clothes.SHOE);

            listShoe.add(Shoe.HIGH_SHOES);     listShoe.add(Shoe.GUMSHOES);
            listShoe.add(Shoe.SNEAKERS);       listShoe.add(Shoe.RUBBER_SHOES);
            listShoe.add(Shoe.SANDALS);        listShoe.add(Shoe.BOOTS);
            listShoe.add(Shoe.LOW_SHOES);      listShoe.add(Shoe.DUTIKI);
            listShoe.add(Shoe.FLIP_FLOPS);
        }

        public static ArrayList<String> getListClothes() {
            return listClothes;
        }

        public static ArrayList<String> getListShoe() {
            return listShoe;
        }
    }

    public static class Women {
        public static class Clothes {
            public static final String BLOUSES = "Блузки";
            public static final String PANTS = "Брюки";
            public static final String OUTERWEAR = "Верхняя одежда";
            public static final String CARDIGANS = "Кардиганы";
            public static final String JEANS = "Джинсы";
            public static final String OVERALLS = "Комбинезоны";
            public static final String SUITS = "Костюмы";
            public static final String JACKETS = "Пиджаки";
            public static final String DRESSES = "Платья";
            public static final String HOODIES = "Толстовки";
            public static final String TUNICS = "Туники";
            public static final String T_SHIRTS = "Футболки";
            public static final String TOPS = "Топы";
            public static final String SHORTS = "Шорты";
            public static final String SHOE = "Обуви";
        }

        public static class Shoe {
            public static final String BALLET_SHOES = "Балетки";
            public static final String BOSONOZHKI = "Босоножки";
            public static final String HIGH_SHOES = "Ботинки";
            public static final String SNEAKERS = "Кроссовки";
            public static final String GUMSHOES = "Кеды";
            public static final String SANDALS = "Сандали";
            public static final String LOW_SHOES = "Туфли";
            public static final String DUTIKI = "Дутики";
            public static final String SLATES = "Сланцы";
        }

        private static final ArrayList<String> listClothes = new ArrayList<>();
        private static final ArrayList<String> listShoe = new ArrayList<>();

        static {
            listClothes.add(Clothes.BLOUSES);      listClothes.add(Clothes.PANTS);
            listClothes.add(Clothes.OUTERWEAR);    listClothes.add(Clothes.CARDIGANS);
            listClothes.add(Clothes.JEANS);        listClothes.add(Clothes.OVERALLS);
            listClothes.add(Clothes.SUITS);        listClothes.add(Clothes.JACKETS);
            listClothes.add(Clothes.DRESSES);      listClothes.add(Clothes.HOODIES);
            listClothes.add(Clothes.TUNICS);       listClothes.add(Clothes.T_SHIRTS);
            listClothes.add(Clothes.TOPS);         listClothes.add(Clothes.SHORTS);
            listClothes.add(Clothes.SHOE);

            listShoe.add(Shoe.BALLET_SHOES);       listShoe.add(Shoe.BOSONOZHKI);
            listShoe.add(Shoe.HIGH_SHOES);         listShoe.add(Shoe.SNEAKERS);
            listShoe.add(Shoe.GUMSHOES);           listShoe.add(Shoe.SANDALS);
            listShoe.add(Shoe.LOW_SHOES);          listShoe.add(Shoe.DUTIKI);
            listShoe.add(Shoe.SLATES);
        }

        public static ArrayList<String> getListClothes() {
            return listClothes;
        }

        public static ArrayList<String> getListShoe() {
            return listShoe;
        }
    }

    public static class Children {
        public static final String BOY = "Для мальчиков";
        public static final String GIRL = "Для девочек";

        public static class Boy {
            public static class Clothes {
                public static final String PANTS = "Брюки";
                public static final String SHORTS = "Шорты";
                public static final String OUTERWEAR = "Верхняя одежда";
                public static final String TURTLENECKS = "Водолазки";
                public static final String JUMPERS = "Джемперы";
                public static final String JEANS = "Джинсы";
                public static final String VESTS = "Жилеты";
                public static final String CARNIVAL_COSTUMES = "Карнавальные костюмы";
                public static final String OVERALLS = "Комбинезоны";
                public static final String SUITS = "Костюмы";
                public static final String JACKETS = "Пиджаки";
                public static final String SHIRTS = "Рубашки";
                public static final String SWEATSHIRTS = "Свитшоты";
                public static final String HOODIES = "Толстовки";
                public static final String T_SHIRTS = "Футболки";
                public static final String SHOE = "Обуви";
            }

            public static class Shoe {
                public static final String HIGH_SHOES = "Ботинки";
                public static final String DUTIKI = "Дутики";
                public static final String GUMSHOES = "Кеды";
                public static final String SNEAKERS = "Кроссовки";
                public static final String SANDALS = "Сандали";
                public static final String BOOTS = "Сапоги";
                public static final String LOW_SHOES = "Туфли";
                public static final String FLIP_FLOPS = "Шлепанцы";
                public static final String UGGS = "Угги";
            }

            private static final ArrayList<String> listClothes = new ArrayList<>();
            private static final ArrayList<String> listShoe = new ArrayList<>();

            static {
                listClothes.add(Clothes.PANTS);        listClothes.add(Clothes.SHORTS);
                listClothes.add(Clothes.OUTERWEAR);    listClothes.add(Clothes.TURTLENECKS);
                listClothes.add(Clothes.JUMPERS);      listClothes.add(Clothes.JEANS);
                listClothes.add(Clothes.VESTS);        listClothes.add(Clothes.CARNIVAL_COSTUMES);
                listClothes.add(Clothes.OVERALLS);     listClothes.add(Clothes.SUITS);
                listClothes.add(Clothes.JACKETS);      listClothes.add(Clothes.SHIRTS);
                listClothes.add(Clothes.SWEATSHIRTS);  listClothes.add(Clothes.HOODIES);
                listClothes.add(Clothes.T_SHIRTS);     listClothes.add(Clothes.SHOE);

                listShoe.add(Shoe.HIGH_SHOES);         listShoe.add(Shoe.DUTIKI);
                listShoe.add(Shoe.GUMSHOES);           listShoe.add(Shoe.SNEAKERS);
                listShoe.add(Shoe.SANDALS);            listShoe.add(Shoe.BOOTS);
                listShoe.add(Shoe.LOW_SHOES);          listShoe.add(Shoe.FLIP_FLOPS);
                listShoe.add(Shoe.UGGS);
            }

            public static ArrayList<String> getListClothes() {
                return listClothes;
            }

            public static ArrayList<String> getListShoe() {
                return listShoe;
            }
        }

        public static class Girl {
            public static class Clothes {
                public static final String BLOUSES = "Блузки";
                public static final String PANTS = "Брюки";
                public static final String SHORTS = "Шорты";
                public static final String OUTERWEAR = "Верхняя одежда";
                public static final String TURTLENECKS = "Водолазки";
                public static final String JUMPERS = "Джемперы";
                public static final String JEANS = "Джинсы";
                public static final String VESTS = "Жилеты";
                public static final String CARNIVAL_COSTUMES = "Карнавальные костюмы";
                public static final String OVERALLS = "Комбинезоны";
                public static final String DRESSES = "Платья";
                public static final String TUNICS = "Туники";
                public static final String HOODIES = "Толстовки";
                public static final String SUITS = "Костюмы";
                public static final String SHOE = "Обуви";
            }

            public static class Shoe {
                public static final String BALLET_SHOES = "Балетки";
                public static final String LOW_SHOES = "Туфли";
                public static final String BOSONOZHKI = "Босоножки";
                public static final String HIGH_SHOES = "Ботинки";
                public static final String GUMSHOES = "Кеды";
                public static final String SNEAKERS = "Кроссовки";
                public static final String BOOTS = "Сапоги";
                public static final String SLATES = "Сланцы";
                public static final String UGGS = "Угги";
            }

            private static final ArrayList<String> listClothes = new ArrayList<>();
            private static final ArrayList<String> listShoe = new ArrayList<>();

            static {
                listClothes.add(Clothes.BLOUSES);              listClothes.add(Clothes.PANTS);
                listClothes.add(Clothes.SHORTS);               listClothes.add(Clothes.OUTERWEAR);
                listClothes.add(Clothes.TURTLENECKS);          listClothes.add(Clothes.JUMPERS);
                listClothes.add(Clothes.JEANS);                listClothes.add(Clothes.VESTS);
                listClothes.add(Clothes.CARNIVAL_COSTUMES);    listClothes.add(Clothes.OVERALLS);
                listClothes.add(Clothes.DRESSES);              listClothes.add(Clothes.TUNICS);
                listClothes.add(Clothes.HOODIES);              listClothes.add(Clothes.SUITS);
                listClothes.add(Clothes.SHOE);

                listShoe.add(Shoe.BALLET_SHOES);               listShoe.add(Shoe.LOW_SHOES);
                listShoe.add(Shoe.BOSONOZHKI);                 listShoe.add(Shoe.HIGH_SHOES);
                listShoe.add(Shoe.GUMSHOES);                   listShoe.add(Shoe.SNEAKERS);
                listShoe.add(Shoe.BOOTS);                      listShoe.add(Shoe.SLATES);
                listShoe.add(Shoe.UGGS);
            }

            public static ArrayList<String> getListClothes() {
                return listClothes;
            }

            public static ArrayList<String> getListShoe() {
                return listShoe;
            }
        }

        public Children() {
        }
    }

    public Categories() {
    }
}
