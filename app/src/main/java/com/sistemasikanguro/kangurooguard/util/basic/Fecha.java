package com.sistemasikanguro.kangurooguard.util.basic;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.RunTimeError;
import com.sistemasikanguro.kangurooguard.util.UtilResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * @author alfpar
 */
public class Fecha implements Cloneable, IOrdenacion {

    public static final double milisegundosDia = 86400000.0;

    public static final int ORDEN_FECHA = 0;
    public static final int ORDEN_FECHA_DESC = 1;

    public static final Fecha fInicioMesActual = getFechaSistema().getInicioMes();
    public static final Fecha fInicioAñoActual = fInicioMesActual.getInicioAnyo();

    public static Fecha getFechaNull() {
        return new Fecha(1900, 1, 1);
    }

    public static Fecha getFechaFinalDLosTiempos() {
        return new Fecha(2099, 12, 31);
    }

    /**
     * Devuelve la fecha del día sin horas, minutos ni segundos.
     */
    public static Fecha getFechaSistema() {
        Fecha fecha = new Fecha();
        int ann = fecha.getYear();
        int mes = fecha.getMes();
        int dia = fecha.getDia();
        return new Fecha(ann, mes, dia);
    }

    public static Fecha getInstanceFecha(String _fecha) throws ParseException {
        return new Fecha(_fecha);
    }

    private Calendar calendar = null;
    private int[] dias_mes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String formatoFechaOracle = "dd/MM/yyyy";
    public static final String formatoFechaInvertida = "yyyyMMdd";
    public static final String formatoFechaInvertidaConBarras = "yyyy/MM/dd";
    private static final String[] meses_romanos = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII"};

    // Castellano
    private static final String[] meses = UtilResource.getStringArray(R.array.meses);
    private static final String[] dias = UtilResource.getStringArray(R.array.dias_num);
    private static final String[] diasSemana = UtilResource.getStringArray(R.array.dias_sem);

    public Fecha() {
        calendar = new GregorianCalendar();
    }

    public Fecha(Date date) {
        calendar = new GregorianCalendar();
        if (date == null)
            setTime(1900, 01, 01);
        else
            setTime(date);
    }

    public Fecha(Fecha fecha) {
        calendar = new GregorianCalendar();
        setTime(fecha.getDate());
    }

    public Fecha(int year, int month, int day) {
        calendar = new GregorianCalendar(year, (month - 1), day);
    }

    public Fecha(int year, int month, int day, int hour, int minute) {
        calendar = new GregorianCalendar(year, (month - 1), day, hour, minute);
    }

    public Fecha(int year, int month, int day, int hour, int minute, int second) {
        calendar = new GregorianCalendar(year, (month - 1), day, hour, minute, second);
    }

    public Fecha(String fecha) {
        this(fecha, getFormatoFechaOracle());
    }

    public Fecha(String fecha, String formato) {
        try {
            Date fech;
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            calendar = new GregorianCalendar();
            if (fecha == null) {
                fecha = "";
            }
            fech = sdf.parse(fecha);
            setTime(fech);

            if (!fecha.equals(toChar(formato))) {
                setTime(1900, 01, 01);
            }

        } catch (ParseException pe) {
            if (fecha.startsWith("TIME_")) {
                try {
                    long datemiles = Long.parseLong(fecha.substring(5));
                    Date date = new Date(datemiles);
                    calendar = new GregorianCalendar();
                    setTime(date);
                } catch (Exception e) {
                    calendar = new GregorianCalendar();
                    setTime(1900, 01, 01);
                }
            } else if (fecha.length() == 4) {
                int year = Transform.toInt(fecha);
                if (year > 1900 && year < 2200) {
                    calendar = new GregorianCalendar();
                    setTime(year, 01, 01);
                } else {
                    calendar = new GregorianCalendar();
                    setTime(1900, 01, 01);
                }

            } else {
                calendar = new GregorianCalendar();
                setTime(1900, 01, 01);
            }
        }
    }

    public Fecha(int year, int month) {
        this(year == 0 ? Fecha.getFechaNull() : month == 0 ? Fecha.getFechaNull() : new Fecha(year, month, 1).getFinMes());
    }

    public void addYears(int numberOffYearsToAdd) {
        calendar.add(Calendar.YEAR, numberOffYearsToAdd);
    }

    public void addDias(int numeroDias) {
        calendar.add(Calendar.DATE, numeroDias);
    }

    public void addMinutos(int numeroMinutos) {
        calendar.add(Calendar.MINUTE, numeroMinutos);
    }

    public static Fecha addDias(Fecha fecha, int nDias) {
        Fecha aux = (Fecha) fecha.clone();
        aux.addDias(nDias);
        return aux;
    }

    public void addDiasHabiles(int numeroDias) {
        for (int aux = 0; aux < numeroDias; aux++) {
            addDias(1);
            if (isSabado()) {
                addDias(1);
            }
            if (isDomingo()) {
                addDias(1);
            }
        }
    }

    public static Fecha addDiasHabiles(Fecha fecha, int numeroDias) {
        Fecha auxFecha = (Fecha) fecha.clone();
        for (int aux = 0; aux < numeroDias; aux++) {
            auxFecha.addDias(1);
            if (auxFecha.isSabado()) {
                auxFecha.addDias(1);
            }
            if (auxFecha.isDomingo()) {
                auxFecha.addDias(1);
            }
        }
        return auxFecha;
    }

    public void addMeses(int numeroMeses) {
        calendar.add(Calendar.MONTH, numeroMeses);
    }

    public Object clone() {
        Fecha fecha;
        fecha = new Fecha(this.getDate());
        return fecha;
    }

    public long compare(Fecha fecha) {
        long fecha1 = this.getDate().getTime();
        long fecha2 = fecha.getDate().getTime();
        return (fecha1 - fecha2);
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Fecha) {
            Fecha aux = (Fecha) obj;
            return toString().equals(aux.toString());
        }
        return false;
    }

    public boolean isMismaFecha(Fecha fecha) {
        if (this == fecha)
            return true;
        if (this.getDia() != fecha.getDia()) {
            return false;
        }
        if (this.getMes() != fecha.getMes()) {
            return false;
        }
        if (this.getYear() != fecha.getYear()) {
            return false;
        }
        return true;
    }

    public Date getDate() {
        return calendar.getTime();
    }

    public long getDateInMillis() {
        return calendar.getTimeInMillis();
    }

    public int getDia() {
        return calendar.get(Calendar.DATE);
    }

    public int getDiasDiferencia(Fecha fecha) {
        double fecha1 = this.getDate().getTime();
        double fecha2 = fecha.getDate().getTime();
        double dif = (fecha1 - fecha2) / milisegundosDia;
        int dias = (int) Math.round(dif);
        return dias;
    }

    public int getDiaAnyo() {
        return getDiasDiferencia(getInicioAnyo());
    }

    public int getDiasHabilesDiferencia(Fecha fecha) {
        int difDias = 0;
        if (this.compare(fecha) == 0)
            return difDias;
        Fecha fechaOrigen;
        Fecha fechaDestino;
        if (this.isMenor(fecha)) {
            fechaOrigen = (Fecha) this.clone();
            fechaDestino = fecha;
        } else {
            fechaOrigen = fecha;
            fechaDestino = this;
        }
        do {
            fechaOrigen.addDias(1);
            if (!fechaOrigen.isDomingo())
                difDias++;
        } while (fechaOrigen.isMenor(fechaDestino));
        return difDias;
    }

    public int getMesesDiferencia(Fecha fecha) {
        int anyos = this.getYear() - fecha.getYear();
        int meses = this.getMes() - fecha.getMes();
        boolean finMesHasta = this.isFinMes();
        boolean finMesDesde = fecha.isFinMes();
        boolean diaUnoDesde = fecha.getDia() == 1;
        int exceso = 0;
        if (finMesHasta && diaUnoDesde) {
            exceso = 1;
        } else if (finMesDesde && finMesHasta) {
            exceso = 0;
        } else if (fecha.getDia() > this.getDia()) {
            exceso = -1;
        }
        int nMeses = (anyos * 12) + meses + exceso;
        return nMeses;
    }

    public boolean isDomingo() {
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public boolean isSabado() {
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
    }

    public int getDiaSemana() {
        int dayInt = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayInt) {
            case Calendar.MONDAY:
                return 0;
            case Calendar.TUESDAY:
                return 1;
            case Calendar.WEDNESDAY:
                return 2;
            case Calendar.THURSDAY:
                return 3;
            case Calendar.FRIDAY:
                return 4;
            case Calendar.SATURDAY:
                return 5;
            case Calendar.SUNDAY:
                return 6;
        }
        return -1;
    }

    public int getSemanaMes() {
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    public int getDiasMes() {
        int mes = getMes();
        int ann = getYear();
        int dia = dias_mes[mes - 1];
        if (mes == 2 && ann % 4 == 0) {
            dia++;
        }
        return dia;
    }

    public Fecha getFinMes() {
        int mes = getMes();
        int ann = getYear();
        int dia = dias_mes[mes - 1];
        if (mes == 2 && ann % 4 == 0) {
            dia++;
        }
        return new Fecha(ann, mes, dia);
    }

    public String getFormatoCorto() {
        return this.toChar(getFormatoFechaOracle().replaceAll("yyyy", "yy"));
    }

    public String getFormatoInvertido() {
        return this.toChar(formatoFechaInvertida);
    }

    public static String getFormatoFechaOracle() {
        return formatoFechaOracle;
    }

    public int getHora() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public Fecha getInicioAnyo() {
        int ann = getYear();
        int mes = 1;
        int dia = 1;
        return new Fecha(ann, mes, dia);
    }

    public Fecha getFinalAnyo() {
        int ann = getYear();
        int mes = 12;
        int dia = 31;
        return new Fecha(ann, mes, dia);
    }

    public Fecha getInicioMes() {
        int mes = getMes();
        int ann = getYear();
        int dia = 1;
        return new Fecha(ann, mes, dia);
    }

    public int getMes() {
        return (calendar.get(Calendar.MONTH)) + 1;
    }

    public String getMes(int mes) {
        return getNombreMes(mes);
    }

    public String getNombreMes() {
        return getStringMes(getMes());
    }

    public static String getNombreMes(int mes) {
        String[] meses = getMeses();
        return meses[mes - 1];
    }

    public static String getStringMes(int mes) {
        return getNombreMes(mes);
    }

    public int getMinuto() {
        return calendar.get(Calendar.MINUTE);
    }

    public int getSegundo() {
        return calendar.get(Calendar.SECOND);
    }

    /**
     * Devuelve la diferencia entre el numero 99999999 y el número formado por
     * la AAAAMMDD. (Se utiliza para ordenación descendente);
     *
     * @return valor inverso
     */
    public String getValorInverso() {
        long fecha = Long.parseLong(this.toChar("yyyyMMdd"));
        return Long.toString(99999999 - fecha);
    }

    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public boolean isFinMes() {
        return getFinMes().getDia() == this.getDia();
    }

    public boolean isHoy() {
        return toChar().equals(getFechaSistema().toChar());
    }

    public boolean isMayorIgual(Fecha fecha) {
        return (compare(fecha) >= 0);
    }

    public boolean isMayor(Fecha fecha) {
        return (compare(fecha) > 0);
    }

    public boolean isMayorIgualQueHoy() {
        return isMayorIgual(getFechaSistema());
    }

    public boolean isMenorQueHoy() {
        return isMenor(getFechaSistema());
    }

    public boolean isMenorIgualQueHoy() {
        return isMenorIgual(getFechaSistema());
    }

    public boolean isMayorQueHoy() {
        return isMayor(getFechaSistema());
    }

    public boolean isMenorIgual(Fecha fecha) {
        return (compare(fecha) <= 0);
    }

    public boolean isMenor(Fecha fecha) {
        return (compare(fecha) < 0);
    }

    public boolean isNull() {
        return toChar().equals(getFechaNull().toChar());
    }

    public boolean isFinalDLosTiempos() {
        return toChar().equals(getFechaFinalDLosTiempos().toChar());
    }

    public Tiempo getDuracion(Fecha fecha) {
        long fecha1 = this.getDate().getTime();
        long fecha2 = fecha.getDate().getTime();
        long duracion = Math.abs(fecha1 - fecha2);
        return new Tiempo(duracion);
    }

    public void setHora(int hour) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
    }

    public void setMiliSegundo(int milliSecond) {
        calendar.set(Calendar.MILLISECOND, milliSecond);
    }

    public void setMinuto(int minute) {
        calendar.set(Calendar.MINUTE, minute);
    }

    public void setSegundo(int second) {
        calendar.set(Calendar.SECOND, second);
    }

    public void setTime(Date date) {
        calendar.setTime(date);
    }

    public void setTime(int year, int month, int day) {
        calendar.set(year, (month - 1), day);
    }

    public void setTime(int year, int month, int day, int hour, int minute) {
        calendar.set(year, (month - 1), day, hour, minute);
    }

    public void setTime(int year, int month, int day, int hour, int minute, int second) {
        calendar.set(year, (month - 1), day, hour, minute, second);
    }

    public String toChar() {
        SimpleDateFormat sdf = new SimpleDateFormat(getFormatoFechaOracle());
        return sdf.format(getDate());
    }

    public String toChar(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(getDate());
    }

    public String toString() {
        if (isNull())
            return "";
        return toChar();
    }

    public String getLettersMes() {
        return getLetters(false, false);
    }

    private String getLetters(boolean dayLetters) {
        return getLetters(dayLetters, true);
    }

    private String getLetters(boolean dayLetters, boolean incluirDia) {
        StringBuilder aux = new StringBuilder();
        if (incluirDia) {
            if (dayLetters) {
                String[] dias = getDias();
                aux.append(dias[getDia() - 1]);
            } else {
                aux.append(getDia());
            }
            aux.append(" ");
            aux.append(UtilResource.getString(R.string.of));
            aux.append(" ");
        }
        aux.append(getNombreMes(getMes()).toLowerCase());
        aux.append(" ");
        aux.append(UtilResource.getString(R.string.of));
        aux.append(" ");
        aux.append(getYear());
        return aux.toString();
    }

    public String getKeyOrder(int orden) {
        if (orden == ORDEN_FECHA)
            return toChar("yyyyMMdd");
        if (orden == ORDEN_FECHA_DESC)
            return getValorInverso();
        return toChar("yyyyMMdd");
    }

    public int getTrimestre() {
        int mes = getMes();
        return mes < 4 ? 1 : mes < 7 ? 2 : mes < 10 ? 3 : 4;
    }

    public Fecha getInicioTrimestre() {
        int numTrimestre = getTrimestre();
        return getInicioTrimestre(numTrimestre, getYear());
    }

    public Fecha getFinTrimestre() {
        int numTrimestre = getTrimestre();
        return getFinTrimestre(numTrimestre, getYear());
    }

    public boolean isFinTrimestre() {
        Fecha fecha = getFinTrimestre();
        return fecha.getDia() == this.getDia() && fecha.getMes() == this.getMes();
    }

    public static Fecha getInicioTrimestre(int numTrimestre, int year) {
        if (numTrimestre < 0 || numTrimestre > 4)
            throw new RunTimeError(UtilResource.getString(R.string.err_num_trimestre_desconocido));
        int dia = 1;
        int mes = (numTrimestre * 3) - 2;
        Fecha fecha = new Fecha(year, mes, dia);
        return fecha;
    }

    public static Fecha getFinTrimestre(int numTrimestre, int year) {
        if (numTrimestre < 0 || numTrimestre > 4)
            throw new RunTimeError(UtilResource.getString(R.string.err_num_trimestre_desconocido));
        int mes = numTrimestre * 3;
        Fecha fecha = new Fecha(year, mes);
        return fecha.getFinMes();
    }

    public static int getYearToday() {
        return new Fecha().getYear();
    }

    public static int getNumeroMesRomano(String mes) {
        for (int i = 0; i < meses_romanos.length; i++) {
            if (meses_romanos[i].equals(mes))
                return i + 1;
        }
        return 0;
    }

    public static String getStringMesRomano(int mes) {
        if (mes < 1 || mes > 12)
            return "";
        return meses_romanos[mes - 1];
    }

    public String getNombreDiaSemana() {
        int dayInt = calendar.get(Calendar.DAY_OF_WEEK);
        String[] diasSemana = getDiasSemana();
        switch (dayInt) {
            case Calendar.MONDAY:
                return diasSemana[0];
            case Calendar.TUESDAY:
                return diasSemana[1];
            case Calendar.WEDNESDAY:
                return diasSemana[2];
            case Calendar.THURSDAY:
                return diasSemana[3];
            case Calendar.FRIDAY:
                return diasSemana[4];
            case Calendar.SATURDAY:
                return diasSemana[5];
            case Calendar.SUNDAY:
                return diasSemana[6];
        }
        return UtilResource.getString(R.string.unknow);
    }

    private static String[] getMeses() {
        return meses;
    }

    private static String[] getDias() {
        return dias;
    }

    private static String[] getDiasSemana() {
        return dias;
    }

    public static Fecha parsear(String fechaTexto) {
        String aux = Util.getCaracteresNumericos(fechaTexto);
        if (aux.length() == 8)
            return new Fecha(aux, "ddMMyyyy");
        if (aux.length() == 6)
            return new Fecha(aux, "ddMMyy");
        return Fecha.getFechaNull();
    }

    public static Fecha getTomorrow() {
        Fecha tomorrow = getFechaSistema();
        tomorrow.addDias(1);
        return tomorrow;
    }

    public boolean isPrimeroDEnero() {
        return (getDia() + getMes()) == 2;
    }

    public boolean isFinAnyo() {
        return getMes() == 12 && getDia() == 31;
    }

    public boolean isFechaVencimientoCompatible(Fecha fecha) {
        int diasAnyo1 = getDiaAnyo();
        int diasAnyo2 = fecha.getDiaAnyo();
        int diferencia = Math.abs(diasAnyo1 - diasAnyo2);
        return diferencia < 2;
    }

    public int getDiferenciaEnDias(Fecha fecha) {
        return getDiasDiferencia(fecha);
    }

    public static boolean isNull(Fecha fecha) {
        if (fecha == null) {
            return true;
        }
        if (fecha.isNull()) {
            return true;
        }
        return false;
    }

    public void trunc() {
        this.calendar = new GregorianCalendar(getYear(), (getMes() - 1), getDia());
    }

}