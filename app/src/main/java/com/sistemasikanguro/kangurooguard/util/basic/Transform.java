package com.sistemasikanguro.kangurooguard.util.basic;

import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * @author david.sancho
 */
public final class Transform {

    public static int toInt(char c) {
        String cadena = "" + c;
        return toInt(cadena);
    }

    public static int toInt(String cadena) {
        return getDatoInteger(cadena);
    }

    public static double toDouble(String cadena) {
        return getDatoDouble(cadena);
    }

    public static String toString(long valor) {
        return Long.toString(valor);
    }

    public static String toString(int valor) {
        return Integer.toString(valor);
    }

    public static String toString(char valor) {
        return valor + "";
    }

    public static String toStringWithLength(long valor, int length) {
        String tmp = valor + "";
        while (tmp.length() < length) {
            tmp = "0" + tmp;
        }
        return tmp;
    }

    public static String toString(double valor) {
        String patron = "############.############";
        char separador = '.';
        return toString(valor, patron, separador);
    }

    public static String toString(double valor, String patron, char separador) {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator(separador);
        dfs.setGroupingSeparator('\0');
        DecimalFormat df = new DecimalFormat(patron, dfs);
        String result = df.format(valor);
        return result;
    }

    public static String toDecimalString(double valor, char decimalCaracter, int numeroDecimales) {
        String cadena = toString(valor);
        int posDecimal = cadena.indexOf(decimalCaracter);
        if (posDecimal < 0) {
            posDecimal = cadena.length();
            cadena += decimalCaracter;
        }
        for (int aux = 0; aux < numeroDecimales; aux++) {
            cadena += "0";
        }
        cadena = cadena.substring(0, posDecimal + (numeroDecimales + 1));
        return cadena;
    }

    public static String toStringInt(double valor) {
        return Integer.toString((int) valor);
    }

    public static String toStringLong(double valor) {
        return Long.toString((long) valor);
    }

    public static long toLong(String cadena) {
        return getDatoLong(cadena);
    }

    public static long toLongUntilNotNumeric(String cadena) {
        if ((cadena == null) || (cadena.isEmpty()))
            return 0;

        int length = cadena.length();
        boolean end = false;
        int i = 0;
        StringBuilder tmp = new StringBuilder();

        while ((!end) && (i < length)) {
            char car = cadena.charAt(i);
            if (isDigitoNumerico(car))
                tmp.append(car);
            else
                end = true;
            i++;
        }

        return toLong(tmp.toString());
    }

    public static BigDecimal toBigDecimal(String cadena) {
        double aux = toDouble(cadena);
        return BigDecimal.valueOf(aux);
    }

    public static BigDecimal toBigDecimal(double valor) {
        return BigDecimal.valueOf(valor);
    }

    public static double toDouble(Object obj) {
        if (obj != null) {
            if (obj instanceof Double) {
                return (Double) obj;
            }
            if (obj instanceof String) {
                return getDatoDouble((String) obj);
            }
            if (obj instanceof Float) {
                return ((Float) obj).doubleValue();
            }
            if (obj instanceof Integer) {
                return ((Integer) obj).doubleValue();
            }
            if (obj instanceof BigDecimal) {
                return ((BigDecimal) obj).doubleValue();
            }
        }
        return 0;
    }

    public static double toDoubleFromImporteHTML(String texto) {
        if (texto == null || texto.length() == 0)
            return 0.0;

        char COMA = Util.getDotSeparation();
        char PUNTO = Util.getGroupingSeparator();

        StringBuilder entero = new StringBuilder();
        StringBuilder decimal = new StringBuilder();
        boolean decimales = false;
        char[] cars = texto.toCharArray();
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == COMA) {
                decimales = true;
            } else if (cars[i] == PUNTO) {

            } else {
                if (decimales) {
                    decimal.append(cars[i]);
                } else {
                    entero.append(cars[i]);
                }
            }
        }

        if (!decimales) {
            decimal.append('0');
        }
        if (entero.length() == 0) {
            entero.append("0");
        }
        String cadenaDouble = entero.toString() + "." + decimal.toString();
        return Double.parseDouble(cadenaDouble);
    }

    public static Fecha toFecha(String fecha) {
        if (fecha == null) {
            return Fecha.getFechaNull();
        } else if (fecha.length() < 10) {
            return Fecha.getFechaNull();
        } else {
            return new Fecha(fecha);
        }
    }

    public static Fecha toFecha(Object obj) {
        if (obj != null) {
            if (obj instanceof Fecha) {
                return ((Fecha) obj);
            }
            if (obj instanceof String) {
                return new Fecha((String) obj);
            }
        }
        return Fecha.getFechaNull();
    }

    public static int toInt(BigInteger granEntero) {
        return granEntero.intValue();
    }

    public static long toLong(double valorDouble) {
        return (Double.valueOf(valorDouble)).longValue();
    }

    public static int toInt(double valorDouble) {
        // Double dobleObj = new Double(valorDouble);
        // return dobleObj.intValue();
        return ((int) valorDouble);
    }

    public static int toInt(long value, boolean throwExceptionIfImpossible) {
        if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
            if (throwExceptionIfImpossible) {
                throw new IllegalArgumentException(value + " cannot be cast to int without changing its value.");
            }
        }

        if (value < Integer.MIN_VALUE)
            value = Integer.MIN_VALUE;
        if (value > Integer.MAX_VALUE)
            value = Integer.MAX_VALUE;

        return (int) value;
    }

    public static int toInt(Object obj) {
        if (obj != null) {
            if (obj instanceof Integer) {
                return ((Integer) obj).intValue();
            }
            if (obj instanceof String) {
                return getDatoInteger((String) obj);
            }
            if (obj instanceof Float) {
                return ((Float) obj).intValue();
            }
            if (obj instanceof Integer) {
                return ((Integer) obj).intValue();
            }
        }
        return 0;
    }

    private static double getDatoDouble(String dato) {
        if ((dato == null) || ("".equals(dato))) {
            return 0;
        }
        try {
            return Double.parseDouble(dato);
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(tratarStringImporte(dato));
            } catch (NumberFormatException e2) {
                return 0.0;
            }
        }
    }

    private static String tratarStringImporte(String dato) {
        char[] cars = dato.toCharArray();
        StringBuffer sb = new StringBuffer();
        char dotSeparator = Util.getDotSeparation();
        char groupingSeparator = Util.getGroupingSeparator();
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] != groupingSeparator) {
                if (cars[i] == dotSeparator) {
                    sb.append('.');
                } else {
                    sb.append(cars[i]);
                }
            }
        }
        return sb.toString();
    }

    public static String toString(InputStream is) throws ErrorGeneral {
        if (is == null)
            return "";
        int sizeBuffer = 8192;
        byte[] saco = new byte[0], aux = null;
        byte[] bytes = new byte[sizeBuffer];
        try {
            int length = 0;
            int car = is.read(bytes);
            while (car > 0) {
                aux = saco;
                length = length + car;
                saco = new byte[length];
                System.arraycopy(aux, 0, saco, 0, aux.length);
                System.arraycopy(bytes, 0, saco, aux.length, car);
                car = is.read(bytes);
            }
        } catch (IOException io) {
            throw new ErrorGeneral(io);
        }
        return new String(saco);
    }

    public static long tratarStringSecPoliza(String dato) {
        char[] cars = dato.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cars.length; i++) {
            if (isDigitoNumerico(cars[i]))
                sb.append(cars[i]);
        }
        return Long.parseLong(sb.toString());
    }

    private static int getDatoInteger(String dato) {
        if (dato == null)
            return 0;

        try {
            return Integer.parseInt(dato);
        } catch (NumberFormatException e) {
            if (dato.length() > 0)
                return (int) getDatoDouble(dato);
            return 0;
        }
    }

    private static long getDatoLong(String dato) {
        if (dato == null)
            return 0;
        try {
            return Long.parseLong(dato);
        } catch (NumberFormatException e) {
            if (dato.length() > 0)
                return (long) getDatoDouble(dato);
            return 0;
        }
    }

    public static boolean isDigitoNumerico(char car) {
        switch (car) {
            case '0':
                return true;
            case '1':
                return true;
            case '2':
                return true;
            case '3':
                return true;
            case '4':
                return true;
            case '5':
                return true;
            case '6':
                return true;
            case '7':
                return true;
            case '8':
                return true;
            case '9':
                return true;
        }
        return false;
    }

}
