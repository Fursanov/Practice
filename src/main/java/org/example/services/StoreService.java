package org.example.services;

import org.example.entity.Product;
import org.example.entity.Store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreService {

    Connection connection;
    List<Store> stores = new ArrayList<>();
    ProductService productService;

    public StoreService(Connection connection, ProductService productService) {
        this.connection = connection;
        this.productService = productService;
        getAllStores();
    }
    private void getAllStores() {
        this.stores.clear();
        String storeQuery = "SELECT * FROM stores";
        String productQuery = "SELECT product_id FROM product_stores WHERE store_id = ?";

        try (Statement storeStatement = this.connection.createStatement();
             ResultSet storeResultSet = storeStatement.executeQuery(storeQuery)) {

            while (storeResultSet.next()) {
                long storeId = storeResultSet.getLong("store_id");
                String storeName = storeResultSet.getString("store_name");
                String location = storeResultSet.getString("location");
                Store store = new Store(storeId, storeName, location);

                try (PreparedStatement productStatement = this.connection.prepareStatement(productQuery)) {
                    productStatement.setLong(1, storeId);
                    try (ResultSet productResultSet = productStatement.executeQuery()) {
                        while (productResultSet.next()) {
                            Long productId = productResultSet.getLong("product_id");
                            store.addProductId(productId);
                        }
                    }
                }

                this.stores.add(store);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Store getStoreById(Long id) {
        return stores.stream()
                .filter(store -> store.getStoreId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Long getStoreIdByNumber(int number) {
        return stores.get(number-1).getStoreId();
    }

    public void printStores() {
        System.out.println("Список магазинов: ");
        long counter = 1L;
        for(Store store: this.stores){
            System.out.println("Магазин: " + counter + " :");
            System.out.println("Название: " +  store.getStoreName());
            System.out.println("расположение " + store.getLocation());
            counter++;
            System.out.println("__________________________________________________________________");
        }
        System.out.println("__________________________________________________________________");
    }

    public void createStore(String storeName, String location) {
        String sql = "INSERT INTO stores (store_name, location) VALUES (?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, storeName);
            statement.setString(2, location);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Магазин успешно создан: " + storeName);
                this.getAllStores();
            } else {
                System.out.println("Не удалось создать магазин.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStore(Long storeId) {
        String sql = "DELETE FROM stores WHERE store_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setLong(1, storeId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Магазин успешно удален");
                getAllStores();
            } else {
                System.out.println("Не удалось удалить магазин");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStore(Long storeId, String fieldToUpdate, Object newValue) {
        String sql = "";
        switch (fieldToUpdate) {
            case "store_name":
                sql = "UPDATE stores SET store_name = ? WHERE store_id = ?";
                break;
            case "location":
                sql = "UPDATE stores SET location = ? WHERE store_id = ?";
                break;
            default:
                System.out.println("Некорректное поле для обновления");
                return;
        }

        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            if (newValue instanceof String) {
                statement.setString(1, (String) newValue);
            } else {
                System.out.println("Неверный тип значения для обновления");
                return;
            }
            statement.setLong(2, storeId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Запись успешно обновлена");
                getAllStores();
            } else {
                System.out.println("Не удалось обновить запись");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printProductsByStoreNumber(int number) {
        Store store = this.getStoreById(this.getStoreIdByNumber(number));
        productService.printProductsByIds(store.getProductIds());
    }

    public void printStoreByNumber(int number) {
        Store store = this.getStoreById(this.getStoreIdByNumber(number));
        System.out.println("Название: " +  store.getStoreName());
        System.out.println("расположение " + store.getLocation());
        System.out.println("__________________________________________________________________");
    }

    public void addProductToStoreById(int storeNumber, int productNumber) {
        Store store = getStoreById(getStoreIdByNumber(storeNumber));
        List<Long> allProducts = productService.getAllProductIds();
        List<Long> productsInStore = store.getProductIds();

        if (allProducts.isEmpty()) {
            System.out.println("Нет доступных продуктов.");
            return;
        }

        List<Long> productsNotInStore = new ArrayList<>(allProducts);
        productsNotInStore.removeAll(productsInStore);

        Long productId = productsNotInStore.get(productNumber-1);

        String sql = "INSERT INTO product_stores (product_id, store_id) VALUES (?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setLong(1, productId);
            statement.setLong(2, store.getStoreId());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Продукт успешно добавлен");
                this.getAllStores();
            } else {
                System.out.println("Не удалось добавить продукт.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean printProductsNotInStore(int number) {
        List<Long> allProducts = productService.getAllProductIds();
        List<Long> productsInStore = getStoreById(getStoreIdByNumber(number)).getProductIds();

        if (allProducts.isEmpty()) {
            System.out.println("Нет доступных продуктов.");
            return false;
        }

        List<Long> productsNotInStore = new ArrayList<>(allProducts);
        productsNotInStore.removeAll(productsInStore);

        if (productsNotInStore.isEmpty()) {
            System.out.println("Все продукты уже есть в выбранном магазине.");
            return false;
        } else {
            System.out.println("Доступные продукты, которых нет в выбранном магазине:");
            productService.printProductsByIds(productsNotInStore);
            return true;
        }
    }

    public boolean printProductsInStore(int number) {
        List<Long> productsInStore = getStoreById(getStoreIdByNumber(number)).getProductIds();

        if (productsInStore.isEmpty()) {
            System.out.println("Магазин пуст.");
            return false;
        }
        productService.printProductsByIds(productsInStore);
        return true;
    }


    public void removeProductFromStoreById(int storeNumber, int productNumber) {
        Store store = getStoreById(getStoreIdByNumber(storeNumber));

        Long productId = store.getProductIds().get(productNumber-1);

        String sql = "DELETE FROM product_stores WHERE store_id = ? AND product_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setLong(1, store.getStoreId());
            statement.setLong(2, productId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Продукт успешно удален из магазина");
                this.getAllStores();
            } else {
                System.out.println("Не удалось удалить продукт из магазина.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isNotContains( int storeId ) {
        return stores.size() < storeId;
    }

    public boolean isNotContainsProductsNotInStore(int number, int productId ) {
        List<Long> allProducts = productService.getAllProductIds();
        List<Long> productsInStore = getStoreById(getStoreIdByNumber(number)).getProductIds();
        List<Long> productsNotInStore = new ArrayList<>(allProducts);
        productsNotInStore.removeAll(productsInStore);
        return productsNotInStore.size() < productId;
    }

    public boolean isNotContainsProductsInStore(int number, int productId) {
        List<Long> productsInStore = getStoreById(getStoreIdByNumber(number)).getProductIds();
        return productsInStore.size() < productId;
    }
}
