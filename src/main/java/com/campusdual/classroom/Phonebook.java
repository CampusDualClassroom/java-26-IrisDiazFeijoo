package com.campusdual.classroom;

import com.campusdual.util.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Phonebook {
    private Map<String, Contact> contacts = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void addContact(Contact contact) {
        String code = contact.getCode();

        if (!contacts.containsKey(code)) {
            contacts.put(code, contact);
            System.out.println("Contacto añadido correctamente: " + code);
        } else {
            System.out.println("El contacto ya existe.");
        }
    }

    public void deleteContact(String contactCode) {
        if (contacts.remove(contactCode) != null) {
            System.out.println("Contacto eliminado: " + contactCode);
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    public void showPhonebook() {
        if (contacts.isEmpty()) {
            System.out.println("No hay contactos en el listín telefónico.");
            return;
        }
        System.out.println("Lista de contactos:");
        for (Contact contact : contacts.values()) {
            System.out.println("- Código: " + contact.getCode() + ", Nombre: " + contact.getName() + " " + contact.getSurnames());
        }
    }

    public Map<String, Contact> getData() {
        return new HashMap<>(contacts);
    }
    public void selectContact() {
        System.out.print("Introduce el código del contacto: ");
        String code = scanner.nextLine();

        Contact contact = contacts.get(code);
        if (contact != null) {
            System.out.println("Detalles del contacto:");
            contact.showContactDetails();

            System.out.println("¿Quieres llamar a este contacto? (s/n)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("s")) {
                contact.callMyNumber();
            }
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    public void menu() {
        int choice;
        do {
            System.out.println("\nMenú");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Mostrar contactos");
            System.out.println("3. Seleccionar contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (choice) {
                case 1:
                    Contact newContact = createContactFromInput();
                    break;
                case 2:
                    showPhonebook();
                    break;
                case 3:
                    selectContact();
                    break;
                case 4:
                    String codeToDelete = getContactCodeFromInput();
                    deleteContact(codeToDelete);
                    break;
                case 5:
                    System.out.println("Saliendo");
                    break;
                default:
                    System.out.println("Opción no válida, inténtalo de nuevo");
            }
        } while (choice != 5);
    }
    private Contact createContactFromInput() {
        System.out.print("Introduce el nombre del contacto: ");
        String name = scanner.nextLine();

        System.out.print("Introduce los apellidos del contacto: ");
        String surnames = scanner.nextLine();

        System.out.print("Introduce el número de teléfono: ");
        String phoneNumber = scanner.nextLine();

        return new Contact(name, surnames, phoneNumber); // Devuelve el nuevo contacto
    }
    private String getContactCodeFromInput() {
        System.out.print("Introduce el código del contacto a eliminar: ");
        return scanner.nextLine();
    }
}








