package org.example.services;

import org.example.entity.Brand;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandService {

    Connection connection;
    List<Brand> brands = new ArrayList<>();

    public BrandService(Connection connection) {
        this.connection = connection;
        getAllBrands();
    }
    private void getAllBrands() {
        this.brands.clear();
        String sql = "SELECT * FROM product_brands";
        try (Statement statement = this.connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Long brandId = resultSet.getLong("brand_id");
                String brandName = resultSet.getString("brand_name");
                Brand brand = new Brand(brandId, brandName);
                this.brands.add(brand);
            }
        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    public Brand getBrandById(Long id) {
        return brands.stream()
                .filter(brand -> brand.getBrandId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Long getBrandIdByNumber(int number) {
        return brands.get(number-1).getBrandId();
    }

    public void printBrands() {
        System.out.println("Список брендов: ");
        long counter = 1L;
        for(Brand brand: this.brands){
            System.out.println("Бренд " + counter + " : " + brand.getBrandName());
            counter++;
            System.out.println("__________________________________________________________________");
        }
        System.out.println("__________________________________________________________________");
    }

    public boolean isNotContains(int brandId ) {
        return brands.size() < brandId;
    }

    public void createBrand(String brandName) {
        String sql = "INSERT INTO product_brands (brand_name) VALUES (?)";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, brandName);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Бренд успешно создан: " + brandName);
                this.getAllBrands();
            } else {
                System.out.println("Не удалось создать бренд.");
            }
        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    public void deleteBrand(Long brandId) {
        String getProductIdsSql = "SELECT product_id FROM products WHERE brand_id = ?";
        try (PreparedStatement searchProductStatement = this.connection.prepareStatement(getProductIdsSql)) {
            searchProductStatement.setLong(1, brandId);
            ResultSet resultSet = searchProductStatement.executeQuery();
            while (resultSet.next()) {
                String delSql = "DELETE FROM product_stores WHERE product_id = ?";
                try (PreparedStatement delProductsFromStoresStatement = this.connection.prepareStatement(delSql)) {
                    delProductsFromStoresStatement.setLong(1, resultSet.getLong("product_id"));
                    int rowsDeleted = delProductsFromStoresStatement.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Продукт успешно удален из магазинов");
                    }
                } catch (SQLException e) {
                    System.out.println("error");
                }
                String delProductSql = "DELETE FROM products WHERE product_id = ?";
                try (PreparedStatement delProductsStatement = this.connection.prepareStatement(delProductSql)) {
                    delProductsStatement.setLong(1, resultSet.getLong("product_id"));
                    int rowsDeleted = delProductsStatement.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Продукт успешно удален");
                    }
                } catch (SQLException e) {
                    System.out.println("error");
                }
            }
        } catch (SQLException e) {
            System.out.println("error");
        }
        String sql = "DELETE FROM product_brands WHERE brand_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setLong(1, brandId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Бренд успешно удален");
                getAllBrands();
            } else {
                System.out.println("Не удалось удалить бренд");
            }
        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    public void updateBrand(Long brandId, String newBrandName) {
        String sql = "UPDATE product_brands SET brand_name = ? WHERE brand_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, newBrandName);
            statement.setLong(2, brandId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Название бренда успешно изменено");
                getAllBrands();
            } else {
                System.out.println("Не удалось изменить название бренда");
            }
        } catch (SQLException e) {
            System.out.println("error");
        }
    }

}
