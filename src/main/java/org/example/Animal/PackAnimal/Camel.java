package org.example.Animal.PackAnimal;

public class Camel extends PackAnimal {
    public Camel(String name) {
        super(name);
        addCommand("иди");
        addCommand("остановись");
    }
}
