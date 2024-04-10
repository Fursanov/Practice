package org.example.services;

import org.example.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    Connection connection;
    List<Product> products = new ArrayList<>();

    BrandService brandService;

    public ProductService(Connection connection, BrandService brandService) {
        this.connection = connection;
        this.brandService = brandService;
        getAllProducts();
    }
    private void getAllProducts() {
        this.products.clear();
        String sql = "SELECT * FROM products";
        try (Statement statement = this.connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Long productId = resultSet.getLong("product_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Double price = resultSet.getDouble("price");
                int stockQuantity = resultSet.getInt("stock_quantity");
                Long brandId = resultSet.getLong("brand_id");
                Product product = new Product(productId, name, description, price, stockQuantity, brandId);
                this.products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    public List<Long> getAllProductIds() {
        return products.stream()
                .map(Product::getProductId)
                .collect(Collectors.toList());
    }

    public Product getProductById(Long id) {
        return products.stream()
                .filter(product -> product.getProductId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Long getProductIdByNumber(int number) {
        return products.get(number-1).getProductId();
    }

    public void printProducts() {
        System.out.println("Список продуктов: ");
        long counter = 1L;
        for(Product product: this.products){
            System.out.println("Продукт: " + counter + " :");
            System.out.println("Название: " + product.getName());
            System.out.println("Описание: " + product.getDescription());
            System.out.println("Цена: " + product.getPrice());
            System.out.println("Количество: " + product.getStockQuantity());
            System.out.println("Бренд: " + brandService.getBrandById(product.getBrandId()).getBrandName());
            counter++;
            System.out.println("__________________________________________________________________");
        }
        System.out.println("__________________________________________________________________");
    }

    public void printProductsByIds(List<Long> productIds) {
        System.out.println("Список продуктов: ");
        long counter = 1L;
        for(Long productId: productIds){
            Product product = getProductById(productId);
            System.out.println("Продукт: " + counter + " :");
            System.out.println("Название: " + product.getName());
            System.out.println("Описание: " + product.getDescription());
            System.out.println("Цена: " + product.getPrice());
            System.out.println("Количество: " + product.getStockQuantity());
            System.out.println("Бренд: " + brandService.getBrandById(product.getBrandId()).getBrandName());
            counter++;
            System.out.println("__________________________________________________________________");
        }
        System.out.println("__________________________________________________________________");
    }

    public void printProductByNumber(int number) {
        Product product = this.getProductById(this.getProductIdByNumber(number));
        System.out.println("Название: " + product.getName());
        System.out.println("Описание: " + product.getDescription());
        System.out.println("Цена: " + product.getPrice());
        System.out.println("Количество: " + product.getStockQuantity());
        System.out.println("Бренд: " + brandService.getBrandById(product.getBrandId()).getBrandName());
        System.out.println("__________________________________________________________________");
    }

    public void createProduct(String name, String description, Double price, int stockQuantity, int brandId) {
        String sql = "INSERT INTO products (name, description, price, stock_quantity, brand_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setDouble(3, price);
            statement.setInt(4, stockQuantity);
            statement.setLong(5, brandService.getBrandIdByNumber(brandId));
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Продукт успешно создан: " + name);
                this.getAllProducts();
            } else {
                System.out.println("Не удалось создать продукт.");
            }
        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    public void deleteProduct(Long productId) {
        String delSql = "DELETE FROM product_stores WHERE product_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(delSql)) {
            statement.setLong(1, productId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Продукт успешно удален из магазинов");
            }
        } catch (SQLException e) {
            System.out.println("error");
        }
        String sql = "DELETE FROM products WHERE product_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setLong(1, productId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Продукт успешно удален");
                getAllProducts();
            } else {
                System.out.println("Не удалось удалить Продукт");
            }
        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    public void updateProduct(Long productId, String fieldToUpdate, Object newValue) {
        String sql = "";
        switch (fieldToUpdate) {
            case "name":
                sql = "UPDATE products SET name = ? WHERE product_id = ?";
                break;
            case "description":
                sql = "UPDATE products SET description = ? WHERE product_id = ?";
                break;
            case "price":
                sql = "UPDATE products SET price = ? WHERE product_id = ?";
                break;
            case "stock_quantity":
                sql = "UPDATE products SET stock_quantity = ? WHERE product_id = ?";
                break;
            case "brand_id":
                sql = "UPDATE products SET brand_id = ? WHERE product_id = ?";
                break;
            default:
                System.out.println("Некорректное поле для обновления");
                return;
        }

        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            if (newValue instanceof String) {
                statement.setString(1, (String) newValue);
            } else if (newValue instanceof Double) {
                statement.setDouble(1, (Double) newValue);
            } else if (newValue instanceof Integer) {
                statement.setInt(1, (int) newValue);
            } else if (newValue instanceof Long) {
                statement.setLong(1, (Long) newValue);
            } else {
                System.out.println("Неверный тип значения для обновления");
                return;
            }
            statement.setLong(2, productId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Запись успешно обновлена");
                getAllProducts();
            } else {
                System.out.println("Не удалось обновить запись");
            }
        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    public boolean isNotContains( int productId ) {
        return products.size() < productId;
    }
}
