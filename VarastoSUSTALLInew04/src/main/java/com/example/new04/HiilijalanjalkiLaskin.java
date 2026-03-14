package com.example.new04;

public class HiilijalanjalkiLaskin {

    public static final double EF_JATE = 1.0;
    public static final double EF_SAHKO = 0.08;
    public static final double EF_VESI = 0.0003;
    public static final double EF_LANTA = 0.6;
    public static final double EF_KUIVIKE = 0.2;
    public static final double EF_AUTO = 0.18;

    public static double laskeJate(double kg) { return kg * EF_JATE; }
    public static double laskeEnergia(double kWh) { return kWh * EF_SAHKO; }
    public static double laskeVesi(double litra) { return litra * EF_VESI; }
    public static double laskeLanta(double kg) { return kg * EF_LANTA; }
    public static double laskeKuivike(double kg) { return kg * EF_KUIVIKE; }
    public static double laskeAuto(double km) { return km * EF_AUTO; }
}

