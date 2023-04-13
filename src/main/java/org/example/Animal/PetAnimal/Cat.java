package org.example.Animal.PetAnimal;

public class Cat extends PetAnimal {
    public Cat(String name){
        super(name);
        addCommand("прыжок");
        addCommand("кувырок");
    }
}
