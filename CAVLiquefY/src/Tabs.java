import javax.swing.*;

public class Tabs extends JTabbedPane {


    private JLabel soilLabel = new JLabel("this is the soil panel");
    private soilInfo sI = new soilInfo();
    private StructureLoading StL = new StructureLoading();
    private SeismicLoading SL = new SeismicLoading();
    private Settlement Settle = new Settlement();
    private JLabel output = new JLabel("this is the output panel");
    private Home home = new Home();

//    private Jlabel titlePage = new JLabel("this is the Title Page");



    public Tabs() {
        add("Home",home);
        add("Seismic Loading", SL);
        //add("Soil Structure", sI);
        add("Site Loading", StL);

        add("Settlement", Settle);
        //add("Output",output);


    }


}
