package org.example.Animal;

import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
    private final String name;
    private final List<String> commands = new ArrayList<>();

    public Animal(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    protected void addCommand(String command){
        commands.add(command);
    }

    public void listCommands(){
        System.out.println("Commands for " + getName() + ":");
        for (String command:commands) {
            System.out.println("- " + command);
        }
    }

    public void teachCommand(String command){
        addCommand(command);
        System.out.println("Command '" + command + "' has been added to " + getName() + "'s list of commands");
    }
}
