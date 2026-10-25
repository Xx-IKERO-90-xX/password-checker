//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static final String ERROR_CALUCALTE_SCORE = "Error al calcular la nota.";

    public static int checkLengthPassword(String password) {
        int minLength = 6;
        int maxLength = 12;
        if (password.length() < minLength || password.length() > maxLength) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("Codigo de contraseña: " + checkLengthPassword("12334556465434523fdgfdgdf"));
    }

    public int calculateScore(int score) {
        if (score >= 0 && score <= 4) {
            return -1;
        }

        if (score >= 5 && score <= 10) {
            return 1;
        }

        throw new IllegalArgumentException(ERROR_CALUCALTE_SCORE);
    }
}