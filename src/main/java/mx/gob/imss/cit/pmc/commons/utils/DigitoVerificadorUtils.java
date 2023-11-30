package mx.gob.imss.cit.pmc.commons.utils;

public class DigitoVerificadorUtils {

	public static String generaDigitoVerificador(String nss) {
		int suma = 0;
		int resultado = 0;
		for (int i = nss.length(); i >= 1; i--) {
			if (i % 2 == 0) {
				int multiplicacion = (Integer.parseInt(nss.charAt(i - 1) + "")) * 2;
				if (multiplicacion > 9) {
					suma = suma + ((multiplicacion - 10) + 1);
				} else {
					suma = suma + multiplicacion;
				}
			} else {
				suma = suma + (Integer.parseInt(nss.charAt(i - 1) + ""));
			}
		}
		int modulo = suma % 10;
		if (modulo == 0) {
			resultado = 0;
		} else if (modulo < 10) {
			resultado = 10 - modulo;
		}
		return String.valueOf(resultado);
	}

	public static String generaDigitoVerificadorRP(String nrp) {
		int factorDeConversion = 10;
		int paso3 = 0;
		boolean bandera = true;
		String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String clave = nrp;
		int primeraLetra = alfabeto.indexOf(nrp.toUpperCase().charAt(0));

		if (primeraLetra != -1) {
			clave = (primeraLetra + factorDeConversion) + nrp.substring(1, nrp.length());
		}

		int i = clave.length() - 1;

		while (i >= 0) {
			if (bandera) {
				int porDos = Integer.parseInt("" + clave.charAt(i)) * 2;
				if (porDos > 9) {
					paso3 += (porDos % 10) + (porDos / 10);
				} else {
					paso3 += porDos;
				}
				bandera = false;
			} else {
				paso3 += Integer.parseInt("" + clave.charAt(i));
				bandera = true;
			}
			i--;
		}
		int digitoVerificador = 10 - (paso3 % 10);
		if (digitoVerificador > 9) {
			digitoVerificador = 0;
		}
		return String.valueOf(digitoVerificador);
	}
	
	public static void main(String[] args) {
		if (args[0] == "p") {
			System.out.println("digito verificador rp: " + args[1] + ": " + generaDigitoVerificador(args[1]));
		}
		else {
			System.out.println("digito verificador nss: " + args[1] + ": " + generaDigitoVerificadorRP(args[1]));
		}
	}

}
