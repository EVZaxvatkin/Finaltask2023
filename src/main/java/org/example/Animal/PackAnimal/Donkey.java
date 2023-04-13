package org.example.Animal.PackAnimal;

public class Donkey extends PackAnimal {
    public Donkey(String name) {
        super(name);
        addCommand("иди");
        addCommand("остановись");
    }
}
