package com.sistemasikanguro.kangurooguard.util.basic;

import android.text.format.DateUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

/**
 * Created by David on 08/03/2015.
 *
 * @author david.sancho
 */
public class Util {

    private static char DOT_SEPARATION = ' ';

    public static String convertDate(Date createdAt) {
        long now = new Date().getTime();
        return DateUtils.getRelativeTimeSpanString(
                createdAt.getTime(),
                now,
                DateUtils.SECOND_IN_MILLIS).toString();
    }

    public static String getStringStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }

    public static char getDotSeparation() {
        if (DOT_SEPARATION != ' ') {
            return DOT_SEPARATION;
        }
        char dot = ',';

        synchronized (Util.class) {
            DOT_SEPARATION = dot;
        }
        return DOT_SEPARATION;
    }

    public static char getGroupingSeparator() {
        char dotSeparation = getDotSeparation();
        char groupingSeparator = dotSeparation == '.' ? ',' : '.';
        return groupingSeparator;
    }

    public static IOrdenacion[] ordenar(IOrdenacion[] objs, int tipoOrden) {
        if ((objs.length == 0) || (objs.length == 1))
            return objs;
        quickSort(objs, 0, objs.length - 1, tipoOrden);
        return objs;
    }

    public static void invertir(Object[] objs) {
        if (objs.length == 0)
            return;
        Object aux;
        for (int i = 0, x = objs.length - 1; i <= x; i++, x--) {
            aux = objs[i];
            objs[i] = objs[x];
            objs[x] = aux;
        }
    }
    private static void quickSort(IOrdenacion[] objs, int izquierda, int derecha, int tipoOrden) {
        int i = izquierda;
        int j = derecha;
        IOrdenacion mitad = objs[(izquierda + derecha) / 2];
        do {
            while (objs[i].getKeyOrder(tipoOrden).compareTo(mitad.getKeyOrder(tipoOrden)) < 0) {
                i++;
            }
            while (objs[j].getKeyOrder(tipoOrden).compareTo(mitad.getKeyOrder(tipoOrden)) > 0) {
                j--;
            }
            if (i <= j) {
                IOrdenacion aux = objs[i];
                objs[i] = objs[j];
                objs[j] = aux;
                i++;
                j--;
            }
        } while (i <= j);
        if (j > izquierda)
            quickSort(objs, izquierda, j, tipoOrden);

        if (i < derecha)
            quickSort(objs, i, derecha, tipoOrden);
    }
    public static String getCaracteresNumericos(String cadena) {
        char[] cars = cadena.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cars.length; i++) {
            if (isDigitoNumerico(cars[i]))
                sb.append(cars[i]);
        }
        return sb.toString();
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
