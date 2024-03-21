import javax.swing.*;
import java.lang.Math;
import java.nio.file.FileStore;
import java.util.Objects;

public class BullockSettlement2017 {
    private double massOfStructure;
    private double effectiveHeight;
    private double base;
    private double length;
    private double depthToFooting;
    private double depthToCenterOfUpperSusceptibleLayer;
    private double bearingPressure;
    private double fso;
    private double ffnd;
    private double fst;
    private double lnSnum;
    private double lnSadj;

    private double Flpc;
    private double Hs1;


    // constants
    private static final double a0 = 1;
    private static final double a1spt = -0.2174;
    private static final double a1cpt = -0.0360;
    private static final double b0 = 0.3026;
    private static final double b1 = -0.0205;
    private static final double c0 =1.3558;
    private static final double c1 =-0.1340;
    private static final double d0 =-1.3446;
    private static final double d1 =0.2303;
    private static final double d2 =0.4189;
    private static final double e0 =-0.8727;
    private static final double e1 =0.1137;
    private static final double e2 =-0.0947;
    private static final double e3 =-0.2148;
    private static final double f0 =-0.0137;
    private static final double f1 =0.0021;
    private static final double f2  =0.1703;
    private static final double s0 =0.4973;
    private static final double k0 = -1.5440;
    private static final double k1 =0.025;
    private static final double k2 =0.0295;
    private static final double k3 = -0.0218;
    private static final double qc = 61;
    private static JTable table;



    public BullockSettlement2017(double Mst,double Heff,double B,double L,double Df,double q,double flpc){
        table = soilInfo.table;
        massOfStructure = Mst;
        effectiveHeight = Heff;
        base = B;
        length = L;
        depthToFooting = Df;
        depthToCenterOfUpperSusceptibleLayer = calcDs1();
        Hs1 = calcHs1();
        bearingPressure=  q;
        Flpc = flpc;
//        this.fso = fso;
//        this.ffnd = ffnd;
//        this.fst = fst;
    }

    private double calcDs1(){
        for (int i = 0; i < table.getRowCount(); i++) {
            String susceptibility = (String) table.getValueAt(i, 3);
            if (Objects.equals(susceptibility, "true") || Objects.equals(susceptibility, "TRUE") || Objects.equals(susceptibility, "True")){
                System.out.print("DS1: " + Double.parseDouble((String) table.getValueAt(i, 0)));
                return (Double.parseDouble((String) table.getValueAt(i, 0)));
            }
        }
        return 1;
    }
    public double calc(boolean isCPT,double CAV){
        lnSnum = calcFso(isCPT,CAV)+calcFfnd(CAV)+calcFst(CAV)+s0*Math.log(CAV);


        lnSadj = lnSnum + k0 +k1*(Math.min(Hs1,12)*Math.min(Hs1,12))+k2*Math.min(bearingPressure,qc)+k3*Math.max(bearingPressure-qc,0);
        if (lnSadj < lnSnum){
            lnSadj = lnSnum;
        }

        return lnSadj;
    }

    public double calcHs1(){
        for (int i = 0; i < table.getRowCount(); i++) {
            String susceptibility = (String) table.getValueAt(i, 3);
            if (Objects.equals(susceptibility, "true") || Objects.equals(susceptibility, "TRUE") || Objects.equals(susceptibility, "True")){
                System.out.print("HS1: " + Double.parseDouble((String) table.getValueAt(i, 1)));
                return (Double.parseDouble((String) table.getValueAt(i, 1)));
            }
        }
        return 1;
    }

    public double calcFso(boolean isCPT, double CAV){

        double term1 = 0;
        double term2 = c0 +c1*Math.log(CAV)*Flpc;
        for (int i = 0; i < table.getRowCount(); i++) {

            String susceptibility = (String) table.getValueAt(i, 3);
            if (Objects.equals(susceptibility, "true") || Objects.equals(susceptibility, "TRUE") || Objects.equals(susceptibility, "True")){
                //System.out.print("Depth: "+ table.getValueAt(i, 0)+ " Thickness: "+table.getValueAt(i, 1) + " N160: "+table.getValueAt(i, 2) +"Susceptible: " +table.getValueAt(i, 3) +"\n");
                if (isCPT){
                    term1 = term1+(H(Double.parseDouble((String) table.getValueAt(i, 1)))*fsiCPT(Double.parseDouble((String) table.getValueAt(i, 2)))*fHi(Double.parseDouble((String) table.getValueAt(i, 1)),Double.parseDouble((String) table.getValueAt(i, 0))-depthToFooting));
                }
                else {
                    term1 = term1+(H(Double.parseDouble((String) table.getValueAt(i, 1)))*fsiSPT(Double.parseDouble((String) table.getValueAt(i, 2)))*fHi(Double.parseDouble((String) table.getValueAt(i, 1)),Double.parseDouble((String) table.getValueAt(i, 0))-depthToFooting));
                }
                //layer is susceptible
            }
            //else not susceptible and skip layer
        }

        fso = term1 + term2;
        return fso;
    }
    private double fsiSPT(double N160){
        if (N160 < 12.6){
            return a0;
        } else if (N160 >=12.6 && N160 <17.2) {
            return (a0 + a1spt*(N160-12.6));
        } else {
            return (a0+4.6*a1spt);
        }
    }

    private double fsiCPT(double qc1N){
        if (qc1N < 112.4){
            return a0;
        } else if (qc1N >=112.4 && qc1N <140.2) {
            return (a0 + a1cpt*(qc1N-112.4));
        } else {
            return (a0+27.8*a1cpt);
        }
    }

    private double fHi(double Hsi, double Dsi){
        return (b0*Hsi*Math.exp(b1*(Dsi*Dsi-4)));
    }

    private double H(double Hsi){
        double val = Hsi-1;
        if (val == 0){
            return 1;
        }
        else{
            return val;
        }
    }

    public double calcFfnd(double CAV){
        ffnd = calcFq(CAV)+calcFbl(CAV);
        return ffnd;
    }

    public double calcFq(double CAV){
        return((d0+d1*Math.log(Math.min(CAV,1000)))*Math.log(bearingPressure)*Math.exp(d2*Math.min(0,base-Math.max(depthToCenterOfUpperSusceptibleLayer,2))));
    }

    public double calcFbl(double CAV){
        return((e0+e1*Math.log(Math.max(CAV,1500)))*(Math.log(base)*Math.log(base))+e2*(length/base)+e3*depthToFooting);
    }

    public double calcFst(double CAV){
        fst = ((f0+f1*Math.log(Math.min(CAV,1000)))*effectiveHeight*effectiveHeight+f2*Math.min(massOfStructure/1000000,1));
        return fst;
    }

}
