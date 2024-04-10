package org.example.controllers;

import org.example.services.ProductService;
import org.example.services.StoreService;

import java.util.Scanner;

import static org.example.HelpFunc.safeIntInput;

public class StoreController {

    private final StoreService storeService;
    private final Scanner scanner;
    private final ProductService productService;

    public StoreController(StoreService storeService, Scanner scanner, ProductService productService) {
        this.storeService = storeService;
        this.scanner = scanner;
        this.productService = productService;
    }


    public void handleStoreTable() {
        int action = -1;
        while (action != 0) {
            this.storeService.printStores();
            System.out.println("Выберите действие:");
            System.out.println("1. Создать запись");
            System.out.println("2. Удалить запись");
            System.out.println("3. Изменить запись");
            System.out.println("4. Список продуктов");
            System.out.println("0. Вернуться в главное меню");

            action = safeIntInput(scanner);
            switch (action) {
                case 1:
                    System.out.println("Введите Название нового магазина");
                    String storeName = scanner.nextLine();
                    System.out.println("Введите расположение нового продукта");
                    String location = scanner.nextLine();

                    this.storeService.createStore(storeName, location);
                    break;
                case 2:
                    this.storeService.printStores();
                    System.out.println("Введите номер магазина для удаления");
                    int deleteStore = safeIntInput(scanner);
                    while (storeService.isNotContains(deleteStore)){
                        System.out.println("неверный номер бренда");
                        deleteStore = safeIntInput(scanner);
                    }
                    this.storeService.deleteStore(
                            this.storeService.getStoreIdByNumber(deleteStore)
                    );
                    break;
                case 3:
                    while (true) {
                        this.storeService.printStores();
                        System.out.println("Введите номер магазина для изменения");
                        System.out.println("0. Выход");
                        int updateStore = safeIntInput(scanner);
                        while (storeService.isNotContains(updateStore)){
                            System.out.println("неверный номер бренда");
                            updateStore = safeIntInput(scanner);
                        }
                        if(updateStore == 0){
                            System.out.println("Возврат в магазины...");
                            break;
                        }
                        this.storeService.printStoreByNumber(updateStore);
                        System.out.println("1. Название");
                        System.out.println("2. расположение");
                        System.out.println("Введите номер поля для изменения");
                        int field = safeIntInput(scanner);
                        switch (field) {
                            case 1:
                                System.out.println("Введите новое Название магазина");
                                String newName = scanner.nextLine();
                                this.storeService.updateStore(
                                        this.storeService.getStoreIdByNumber(updateStore),
                                        "store_name",
                                        newName
                                );
                                break;
                            case 2:
                                System.out.println("Введите новое расположение магазина");
                                String newLocation = scanner.nextLine();
                                this.productService.updateProduct(
                                        this.storeService.getStoreIdByNumber(updateStore),
                                        "description",
                                        newLocation
                                );
                                break;
                            default:
                                System.out.println("Некорректный ввод, попробуйте еще раз.");
                                break;
                        }
                    }
                case 4:
                    while (true) {
                        this.storeService.printStores();
                        System.out.println("Введите номер магазина для проссмотра продуктов");
                        System.out.println("0. Выход");
                        int productStore = safeIntInput(scanner);
                        while (storeService.isNotContains(productStore)){
                            System.out.println("неверный номер бренда");
                            productStore = safeIntInput(scanner);
                        }
                        if(productStore == 0){
                            System.out.println("Возврат в магазины...");
                            break;
                        }
                        int choice = -1;
                        while (choice != 0) {
                            this.storeService.printStoreByNumber(productStore);
                            this.storeService.printProductsByStoreNumber(productStore);
                            System.out.println("Выберите действие для продуктов");
                            System.out.println("1. Добавить продукт в магазин");
                            System.out.println("2. Удалить продукт из магазина");
                            System.out.println("0. Выход в раздел магазины");
                            choice = safeIntInput(scanner);
                            switch (choice) {
                                case 1:
                                    if (!storeService.printProductsNotInStore(productStore))
                                        break;
                                    System.out.println("Выберите продукт для добавления");
                                    int addProduct = safeIntInput(scanner);
                                    while (storeService.isNotContainsProductsNotInStore(productStore, addProduct)){
                                        System.out.println("неверный номер бренда");
                                        addProduct = safeIntInput(scanner);
                                    }
                                    storeService.addProductToStoreById(productStore, addProduct);
                                    break;
                                case 2:
                                    if (!storeService.printProductsInStore(productStore))
                                        break;
                                    System.out.println("Выберите продукт для удаления");
                                    int deleteProduct = safeIntInput(scanner);
                                    while (storeService.isNotContainsProductsInStore(productStore, deleteProduct)){
                                        System.out.println("неверный номер бренда");
                                        deleteProduct = safeIntInput(scanner);
                                    }
                                    storeService.removeProductFromStoreById(productStore, deleteProduct);
                                    break;
                                case 0:
                                    System.out.println("Возврат в меню магазинов...");
                                    break;
                                default:
                                    System.out.println("Некорректный ввод, попробуйте еще раз.");
                                    break;
                            }
                        }
                    }
                    break;
                case 0:
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Некорректный ввод, попробуйте еще раз.");
                    break;
            }
        }
    }
}
