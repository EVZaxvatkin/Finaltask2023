package org.example.Animal.Service;

import org.example.Animal.Animal;
import org.example.Animal.PackAnimal.Camel;
import org.example.Animal.PackAnimal.Donkey;
import org.example.Animal.PackAnimal.Horse;
import org.example.Animal.PackAnimal.PackAnimal;
import org.example.Animal.PetAnimal.Cat;
import org.example.Animal.PetAnimal.Dog;
import org.example.Animal.PetAnimal.Hamster;
import org.example.Animal.PetAnimal.PetAnimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final List<PetAnimal> petAnimals = new ArrayList<>();
    private final List<PackAnimal> packAnimals = new ArrayList<>();
    private final Counter counter = new Counter();

    public void addAnimal(Animal animal) {
        if (animal instanceof PetAnimal) {
            petAnimals.add((PetAnimal) animal);
        } else if (animal instanceof PackAnimal) {
            packAnimals.add((PackAnimal) animal);
        } else {
            throw new IllegalArgumentException("Тип животного не найден");
        }
        counter.add();
    }

    public void listCommands(Animal animal) {
        animal.listCommands();
    }

    public void teachCommand(Animal animal, String command) {
        animal.teachCommand(command);
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("1. Добавить новое животное");
            System.out.println("2. Список команд для животных");
            System.out.println("3. Научить животное каманде");
            System.out.println("4. Выход");
            System.out.print("Введите номер: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character
            switch (choice) {
                case 1:
                    try (Counter c = counter.start()) {
                        addAnimal(createAnimal(scanner));
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;
                case 2:
                    listAnimalCommands(scanner);
                    break;
                case 3:
                    teachAnimalCommand(scanner);
                    break;
                case 4:
                    // do nothing, we will exit the loop
                    break;
                default:
                    System.out.println("Неверный вариант");
            }
        } while (choice != 4);
    }

    private Animal createAnimal(Scanner scanner) {
        System.out.println("Выберете тип животного (1 = Домашнее, 2 = Вьючное): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        System.out.println("Введите имя животного: ");
        String name = scanner.nextLine().trim();
        if (type == 1) {
            return createPetAnimal(name);
        } else if (type == 2) {
            return createPackAnimal(name);
        } else {
            throw new IllegalArgumentException("Неизвестный тип животного");
        }
    }

    private PetAnimal createPetAnimal(String name) {
        System.out.println("Выберерите вид животного (1 = Собака, 2 = Кошка, 3 = Хомяк): ");
        Scanner scanner = new Scanner(System.in);
        int species = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        return switch (species) {
            case 1 -> new Dog(name);
            case 2 -> new Cat(name);
            case 3 -> new Hamster(name);
            default -> throw new IllegalArgumentException("Неизвестный вид животного");
        };
    }

    private PackAnimal createPackAnimal(String name) {
        System.out.println("Выберерите вид животного (1 = Лошадь, 2 = Верблюд, 3 = Осёл): ");
        Scanner scanner = new Scanner(System.in);
        int species = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        return switch (species) {
            case 1 -> new Horse(name);
            case 2 -> new Camel(name);
            case 3 -> new Donkey(name);
            default -> throw new IllegalArgumentException("Unknown animal species");
        };
    }

    private void listAnimalCommands(Scanner scanner) {
        System.out.println("Введите имя животного: ");
        String name = scanner.nextLine().trim();
        Animal animal = findAnimal(name);
        if (animal != null) {
            listCommands(animal);
        } else {
            System.out.println("Животное не найдено");
        }
    }

    private void teachAnimalCommand(Scanner scanner) {
        System.out.println("Введите имя животного: ");
        String name = scanner.nextLine().trim();
        Animal animal = findAnimal(name);
        if (animal != null) {
            System.out.println("Введите команду: ");
            String command = scanner.nextLine().trim();
            teachCommand(animal, command);
        } else {
            System.out.println("Животное не найдено");
        }
    }

    private Animal findAnimal(String name) {
        for (PetAnimal animal : petAnimals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        for (PackAnimal animal : packAnimals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        return null;
    }

    private static class Counter implements AutoCloseable {
        private int count = 0;

        public Counter start() {
            count++;
            return this;
        }

        public void add() {
            count++;
        }

        @Override
        public void close() throws Exception {
            if (count != 1) {
                throw new Exception("Invalid usage: Counter should be used in try-with-resources block");
            }
        }
    }
}
