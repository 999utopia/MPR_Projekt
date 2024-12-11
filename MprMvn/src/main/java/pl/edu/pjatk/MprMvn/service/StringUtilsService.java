package pl.edu.pjatk.MprMvn.service;

import org.springframework.stereotype.Component;

@Component
public class StringUtilsService {
    public String toUpperCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.toUpperCase();
    }
    public String toCamelCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        input = input.toLowerCase();
        return Character.toUpperCase(input.charAt(0)) + input.substring(1);
    }
}
