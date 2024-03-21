import javax.swing.*;
import java.lang.Math;
import java.util.ArrayList;
import org.apache.commons.math3.distribution.NormalDistribution;


public class Calculations {
    private static final double[] SadjStarValues = {0,0.460517019,0.921034037,1.381551056,1.842068074,2.302585093,2.763102112,3.22361913,3.684136149,4.144653167,4.605170186,5.065687205,5.526204223,5.986721242};
    //the values represent the logged values of settlement. the Sadj* values should have small increments
    private static final double deltaLambda = .005;
    private static final double sigma = .6746;
    public static ArrayList<Double> afes = new ArrayList<>();
    public static ArrayList<Double> edps = new ArrayList<>();

    private static final NormalDistribution normalDistribution = new NormalDistribution();

    Calculations(){}

    public void generateSettlementHaZardCurve(){

        double Mst;
        double Heff;
        double q;
        double B;
        double Ds1;
        double Df;
        double L;
        double Flpc;
        try{

            Mst = Double.parseDouble(StructureLoading.textFieldMst.getText());
            Heff = Double.parseDouble(StructureLoading.textFieldHeff.getText());
            q = Double.parseDouble(StructureLoading.textFieldq.getText());
            B = Double.parseDouble(StructureLoading.textFieldB.getText());
            Df = Double.parseDouble(StructureLoading.textFieldDf.getText());
            L = Double.parseDouble(StructureLoading.textFieldL.getText());

            if (soilInfo.lowPermCheckbox.isSelected()){
                Flpc = 1;
            }else{
                Flpc = 0;
            }

            ArrayList<Double> CAVs = getDeltaLambaCavValues();
            BullockSettlement2017 BS17 = new BullockSettlement2017(Mst,Heff,B,L,Df,q,Flpc);
            afes = new ArrayList<>();
            edps = new ArrayList<>();

            for (double SadjStar :SadjStarValues){

                double probabilitySum = 0;
                for (double CAV : CAVs){

                    probabilitySum = probabilitySum + generateProbability(BS17.calc(StructureLoading.sI.iscpt(),CAV),SadjStar);
                }

                probabilitySum = probabilitySum * deltaLambda;

                afes.add(probabilitySum);
                edps.add(Math.exp(SadjStar));
            }


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(null, "There was a problem with the inputs. Ensure the data is in number format. " + e.getMessage(), "Settlement Hazard Curve Error", JOptionPane.ERROR_MESSAGE);
        }




    }

    public static double generateProbability(double Sadj, double SadjStar){

        return (1-normalDistribution.cumulativeProbability((SadjStar-Sadj)/sigma));
    }
    public static double interpolateX(double x1, double y1, double x2, double y2, double y) {
        // Perform linear interpolation to find the x-value
        return x1 + (y - y1) * ((x2 - x1) / (y2 - y1));
    }
    private ArrayList<Double> getDeltaLambaCavValues(){
        ArrayList<Double> CAVs = new ArrayList<>();
        double[] imls = SeismicLoading.imls;
        double[] afes = SeismicLoading.afes;
        for(double afe : afes){

        }
        double afe = afes[0]-deltaLambda/2;
        while (afe > afes[afes.length-1]){
            double x1;
            double y1;
            double x2;
            double y2;
            for (int i = 0; i<afes.length; i++){
                if ((afes[i] > afe) && (afe > afes[i+1])){
                    y1 = afes[i];
                    x1 = imls[i];
                    y2 = afes[i+1];
                    x2 = imls[i+1];
                    CAVs.add(interpolateX(x1,y1,x2,y2,afe));

                }
            }
            afe = afe - deltaLambda;
        }

        return CAVs;
    }



    private boolean checkDouble(String str){
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
