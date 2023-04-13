package org.example.Animal.PackAnimal;

public class Horse extends PackAnimal {
    public Horse(String name) {
        super(name);
        addCommand("вперед");
        addCommand("стоять");
    }
}
