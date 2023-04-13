package org.example.Animal.PetAnimal;

public class Dog extends PetAnimal{
    public Dog(String name){
        super(name);
        addCommand("сидеть");
        addCommand("лежать");
        addCommand("голос");
    }
}
