package org.example.controllers;

import org.example.services.BrandService;
import org.example.services.ProductService;

import java.util.Scanner;

import static org.example.HelpFunc.safeDoubleInput;
import static org.example.HelpFunc.safeIntInput;

public class ProductController {

    private final ProductService productService;
    private final Scanner scanner;
    private final BrandService brandService;

    public ProductController(ProductService productService, BrandService brandService, Scanner scanner) {
        this.productService = productService;
        this.brandService = brandService;
        this.scanner = scanner;
    }

    public void handleProductTable() {
        int action = -1;
        while (action != 0) {
            this.productService.printProducts();
            System.out.println("Выберите действие:");
            System.out.println("1. Создать запись");
            System.out.println("2. Удалить запись");
            System.out.println("3. Изменить запись");
            System.out.println("0. Вернуться в главное меню");

            action = safeIntInput(scanner);
            switch (action) {
                case 1:
                    System.out.println("Введите имя нового продукта");
                    String name = scanner.nextLine();
                    System.out.println("Введите описание нового продукта");
                    String description = scanner.nextLine();
                    System.out.println("Введите цену нового продукта");
                    Double price = safeDoubleInput(scanner);
                    System.out.println("Введите количество нового продукта");
                    int stockQuantity = safeIntInput(scanner);
                    this.brandService.printBrands();
                    System.out.println("Введите номер бренда нового продукта");
                    int brandId = safeIntInput(scanner);
                    while (brandService.isNotContains(brandId)){
                        System.out.println("неверный номер бренда");
                        brandId = safeIntInput(scanner);
                    }
                    this.productService.createProduct(name, description, price, stockQuantity, brandId);
                    break;
                case 2:
                    this.productService.printProducts();
                    System.out.println("Введите номер продукта для удаления");
                    int deleteProduct = safeIntInput(scanner);
                    while (productService.isNotContains(deleteProduct)){
                        System.out.println("неверный номер бренда");
                        deleteProduct = safeIntInput(scanner);
                    }
                    this.productService.deleteProduct(
                            this.productService.getProductIdByNumber(deleteProduct)
                    );
                    break;
                case 3:
                    int field = -1;
                    while (field != 0) {
                        this.productService.printProducts();
                        System.out.println("Введите номер продукта для изменения");
                        System.out.println("0. Выход");
                        int updateProduct = safeIntInput(scanner);
                        while (productService.isNotContains(updateProduct)){
                            System.out.println("неверный номер бренда");
                            updateProduct = safeIntInput(scanner);
                        }
                        if(updateProduct == 0){
                            System.out.println("Возврат в продукты...");
                            break;
                        }
                        this.productService.printProductByNumber(updateProduct);
                        System.out.println("1. Название");
                        System.out.println("2. Описание");
                        System.out.println("3. Цена");
                        System.out.println("4. Количество");
                        System.out.println("5. Бренд");
                        System.out.println("Введите номер поля для изменения");
                        field = safeIntInput(scanner);
                        switch (field) {
                            case 1:
                                System.out.println("Введите новое имя продукта");
                                String newName = scanner.nextLine();
                                this.productService.updateProduct(
                                        this.productService.getProductIdByNumber(updateProduct),
                                        "name",
                                        newName
                                );
                                break;
                            case 2:
                                System.out.println("Введите новое описание продукта");
                                String newDescription = scanner.nextLine();
                                this.productService.updateProduct(
                                        this.productService.getProductIdByNumber(updateProduct),
                                        "description",
                                        newDescription
                                );
                                break;
                            case 3:
                                System.out.println("Введите новую цену продукта");
                                Double newPeice = safeDoubleInput(scanner);
                                this.productService.updateProduct(
                                        this.productService.getProductIdByNumber(updateProduct),
                                        "price",
                                        newPeice
                                );
                                break;
                            case 4:
                                System.out.println("Введите новое количество продукта");
                                int newStockQuantity = safeIntInput(scanner);
                                this.productService.updateProduct(
                                        this.productService.getProductIdByNumber(updateProduct),
                                        "stock_quantity",
                                        newStockQuantity
                                );
                                break;
                            case 5:
                                this.brandService.printBrands();
                                System.out.println("Введите новый номер бренда продукта");
                                int newBrandId = safeIntInput(scanner);
                                while (brandService.isNotContains(newBrandId)){
                                    System.out.println("неверный номер бренда");
                                    newBrandId = safeIntInput(scanner);
                                }
                                this.productService.updateProduct(
                                        this.productService.getProductIdByNumber(updateProduct),
                                        "brand_id",
                                        brandService.getBrandIdByNumber(newBrandId)
                                );
                                break;
                            default:
                                System.out.println("Некорректный ввод, попробуйте еще раз.");
                                break;
                        }
                    }
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
