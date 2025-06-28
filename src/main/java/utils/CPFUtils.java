package utils;

public class CPFUtils {
  public static boolean isValidCPF(String cpf) {
      cpf = cpf.replaceAll("[^0-9]", "");

      if (cpf.length() != 11) {
        return false;
      }
      if (cpf.matches("(\\d)\\1{10}")) {
        return false;
      }

      try {
        int soma = 0;
        for (int i = 0; i < 9; i++) {
          soma += (Character.getNumericValue(cpf.charAt(i)) * (10 - i));
        }
        int resto = soma % 11;
        int digitoVerificador1 = (resto < 2) ? 0 : 11 - resto;

        if (digitoVerificador1 != Character.getNumericValue(cpf.charAt(9))) {
          return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
          soma += (Character.getNumericValue(cpf.charAt(i)) * (11 - i));
        }
        resto = soma % 11;
        int digitoVerificador2 = (resto < 2) ? 0 : 11 - resto;

        if (digitoVerificador2 != Character.getNumericValue(cpf.charAt(10))) {
          return false;
        }

      } catch (NumberFormatException e) {
        return false;
      }

      return true;
    }
    public static String maskCPF(String cpf) {
      return String.format("***.%s.%s-**",
              cpf.substring(3, 6),
              cpf.substring(6, 9));
    }

    public static String formatCPF(String cpf) {
      return String.format("%s.%s.%s-%s",
              cpf.substring(0, 3),
              cpf.substring(3, 6),
              cpf.substring(6, 9),
              cpf.substring(9, 11));
    }
}
