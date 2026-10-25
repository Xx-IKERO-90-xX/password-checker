import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordCheckerTest {
    private PasswordChecker checker;

    // Códigos oficiales según la especificación del PasswordChecker
    private static final int PASSWORD_OK = 0;
    private static final int ERR_LENGTH = 1;
    private static final int ERR_DIGITS = 2;
    private static final int ERR_UPPERS = 3;
    private static final int ERR_LOWERS = 4;
    private static final int ERR_BLANK = 5;
    private static final int ERR_NO_MATCH = 6;

    // Mensajes de error esperados para validaciones de configuración (si tu práctica los evalúa con excepciones)
    private static final String ERR_INVALID_MAX_LENGTH = "Longitud máxima inferior a la mínima";
    private static final String ERR_INVALID_MIN_LENGTH = "La longitud mínima del password no puede ser cero o menor";

    @BeforeEach
    public void setUp() {
        checker = new PasswordChecker();
        checker.setMinLength(6);
        checker.setMaxLength(12);
        checker.setCheckDigits(true);
        checker.setCheckLowers(true);
        checker.setCheckUppers(true);
    }

    @Test
    public void testPasswordOk() {
        int resultado = checker.check("Abc123", "Abc123");
        assertEquals(PASSWORD_OK, resultado, "La contraseña debería ser válida.");
    }

    @Test
    public void testErrorBlank() {
        assertEquals(ERR_BLANK, checker.check("", ""));
        assertEquals(ERR_BLANK, checker.check(null, null)); // Por si maneja nulos
    }

    @Test
    public void testErrorLengthMinima() {
        // Longitud menor al minLength (6)
        int resultado = checker.check("Ab1", "Ab1");
        assertEquals(ERR_LENGTH, resultado, "Debería fallar por ser muy corta.");
    }

    @Test
    public void testErrorLengthMaxima() {
        // Longitud mayor al maxLength (12)
        int resultado = checker.check("Abcdefh123456789", "Abcdefh123456789");
        System.out.println("Codigo de error: " + resultado);
        assertEquals(ERR_LENGTH, resultado, "Debería fallar por ser muy larga.");
    }

    @Test
    public void testErrorNoMatch() {
        // Las contraseñas no coinciden
        int resultado = checker.check("Abc123", "Abc124");
        assertEquals(ERR_NO_MATCH, resultado, "Debería fallar porque las contraseñas no coinciden.");
    }

    @Test
    public void testErrorDigitsActivado() {
        checker.setCheckDigits(true);
        // Sin números
        int resultado = checker.check("Abcdef", "Abcdef");
        assertEquals(ERR_DIGITS, resultado, "Debería fallar por falta de dígitos.");
    }

    @Test
    public void testErrorDigitsDesactivado() {
        checker.setCheckDigits(false);
        // Sin números, pero como la comprobación está desactivada, debería pasar (si cumple lo demás)
        int resultado = checker.check("Abcdef", "Abcdef");
        assertEquals(PASSWORD_OK, resultado, "No debería exigir dígitos si la comprobación está desactivada.");
    }

    @Test
    public void testErrorLowers() {
        checker.setCheckLowers(true);
        // Sin minúsculas
        int resultado = checker.check("ABC123", "ABC123");
        assertEquals(ERR_LOWERS, resultado, "Debería fallar por falta de minúsculas.");
    }

    @Test
    public void testErrorUppers() {
        checker.setCheckUppers(true);
        // Sin mayúsculas
        int resultado = checker.check("abc123", "abc123");
        assertEquals(ERR_UPPERS, resultado, "Debería fallar por falta de mayúsculas.");
    }

    @Test
    public void testGettersAndSetters() {
        // Una pequeña prueba para verificar que los getters y setters guardan bien los valores
        checker.setMinLength(8);
        checker.setMaxLength(20);
        checker.setCheckDigits(false);

        assertEquals(8, checker.getMinLength());
        assertEquals(20, checker.getMaxLength());
        assertFalse(checker.getCheckDigits());
    }
}
