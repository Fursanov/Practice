package org.example.controllers;

import org.example.services.BrandService;

import java.util.Scanner;

import static org.example.HelpFunc.safeIntInput;

public class BrandController {

    private final BrandService brandService;
    private final Scanner scanner;

    public BrandController(BrandService brandService, Scanner scanner) {
        this.brandService = brandService;
        this.scanner = scanner;
    }

    void handleBrandTable() {

        int action = -1;
        while (action != 0) {
            this.brandService.printBrands();
            System.out.println("Выберите действие:");
            System.out.println("1. Создать запись");
            System.out.println("2. Удалить запись");
            System.out.println("3. Изменить запись");
            System.out.println("0. Вернуться в главное меню");

            action = safeIntInput(scanner);
            switch (action) {
                case 1:
                    System.out.println("Введите имя нового бренда");
                    String name = scanner.nextLine();
                    this.brandService.createBrand(name);
                    break;
                case 2:
                    this.brandService.printBrands();
                    System.out.println("Введите номер бренда для удаления");
                    int deleteBrand = safeIntInput(scanner);
                    while (brandService.isNotContains(deleteBrand)){
                        System.out.println("неверный номер бренда");
                        deleteBrand = safeIntInput(scanner);
                    }
                    this.brandService.deleteBrand(
                            this.brandService.getBrandIdByNumber(deleteBrand)
                    );
                    break;
                case 3:
                    this.brandService.printBrands();
                    System.out.println("Введите номер бренда для удаления");
                    int updateBrand = safeIntInput(scanner);
                    while (brandService.isNotContains(updateBrand)){
                        System.out.println("неверный номер бренда");
                        updateBrand = safeIntInput(scanner);
                    }
                    System.out.println("Введите новое имя бренда");
                    String newName = scanner.nextLine();
                    this.brandService.updateBrand(
                            this.brandService.getBrandIdByNumber(updateBrand),
                            newName
                    );
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
