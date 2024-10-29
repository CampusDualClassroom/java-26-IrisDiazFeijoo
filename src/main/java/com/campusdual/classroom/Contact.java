package com.campusdual.classroom;


import java.text.Normalizer;

public class Contact implements ICallActions {
    private String name;
    private String surnames;
    private String phone;
    private String code;

    public Contact(String name, String surnames, String phone) {
        this.name = name;
        this.surnames = surnames;
        this.phone = phone;
        this.code = generateCode();
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surnames;
    }

    public String getPhone() {
        return phone;
    }

    public String getCode() {
        return code;
    }

    @Override
    public void callMyNumber() {
        System.out.println("Calling " + name + " " + surnames + " at " + phone);
    }

    @Override
    public void callOtherNumber(String number) {
        System.out.println("Calling " + name + " " + surnames + " at " + number);
    }

    @Override
    public void showContactDetails() {
        System.out.println("Name: " + name);
        System.out.println("Surnames: " + surnames);
        System.out.println("Phone: " + phone);
        System.out.println("Code: " + code);
    }

    private String generateCode() {
        String normalizedName = removeDiacritics(name.toLowerCase());
        String normalizedSurnames = removeDiacritics(surnames.toLowerCase());

        String[] surnameParts = normalizedSurnames.split(" ");
        StringBuilder codeBuilder = new StringBuilder();

        codeBuilder.append(normalizedName.charAt(0));

        if (surnameParts.length > 1) {
            codeBuilder.append(surnameParts[0].charAt(0));
            for (int i = 1; i < surnameParts.length; i++) {
                codeBuilder.append(surnameParts[i]);
            }
        } else {
            // Solo un apellido
            codeBuilder.append(normalizedSurnames);
        }

        return codeBuilder.toString();
    }

    private String removeDiacritics(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }
}




