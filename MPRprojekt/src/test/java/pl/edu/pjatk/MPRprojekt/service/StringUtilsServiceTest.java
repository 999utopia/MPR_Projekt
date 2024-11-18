package pl.edu.pjatk.MPRprojekt.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsServiceTest {
    private final StringUtilsService service = new StringUtilsService();

    @Test
    void testToUpperCase() {
        assertEquals("HELLO", service.toUpperCase("hello"));
        assertEquals("", service.toUpperCase(""));
        assertNull(service.toUpperCase(null));
    }

    @Test
    void testToCamelCase() {
        assertEquals("Hello", service.toCamelCase("hello"));
        assertEquals("Hello", service.toCamelCase("HELLO"));
        assertEquals("", service.toCamelCase(""));
        assertNull(service.toCamelCase(null));
    }
}
