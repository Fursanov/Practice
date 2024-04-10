package org.example.controllers;

import org.example.services.BrandService;
import org.example.services.ProductService;
import org.example.services.StoreService;

import java.sql.*;
import java.util.Scanner;

import static org.example.HelpFunc.safeIntInput;

public class MainController {
    private final Scanner scanner;
    private final BrandController brandController;
    private final ProductController productController;
    private final StoreController storeController;

    public MainController(Connection connection) {
        this.scanner = new Scanner(System.in);
        BrandService brandService = new BrandService(connection);
        ProductService productService = new ProductService(connection, brandService);
        StoreService storeService = new StoreService(connection, productService);
        this.brandController = new BrandController(brandService, scanner);
        this.productController = new ProductController(productService, brandService, scanner);
        this.storeController = new StoreController(storeService, scanner, productService);
    }

    public void displayMenu() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("Выберите таблицу:");
            System.out.println("1. Бренды");
            System.out.println("2. Продукты");
            System.out.println("3. Магазины");
            System.out.println("0. Выйти");

            try {
                choice = safeIntInput(scanner);
                switch (choice) {
                    case 1:
                        brandController.handleBrandTable();
                        break;
                    case 2:
                        productController.handleProductTable();
                        break;
                    case 3:
                        storeController.handleStoreTable();
                        break;
                    case 0:
                        System.out.println("Выход из меню...");
                        break;
                    default:
                        System.out.println("Некорректный ввод, попробуйте еще раз.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
                scanner.nextLine();
            }
        }
    }
}


