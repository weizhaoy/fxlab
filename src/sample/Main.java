package sample;

import EntityLab.FuzzTest;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {


    private static List<File> seedFiles;
    private static File opInterFile;
    private static List<File> opImplFiles;

    private static Stage listViewStage = null;


    private static FuzzTest fuzzTest;
    @Override
    public void start(final Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();





        /**chart**/
        final ScrollPane seedScroll = (ScrollPane) root.lookup("#seedScroll");
        seedScroll.setFitToHeight(true);
        BarChart<String,Number> seedChart = (BarChart) root.lookup("#seedChart");
        CategoryAxis seedX = (CategoryAxis) root.lookup("#seedX");
        NumberAxis seedY = (NumberAxis) root.lookup("#seedY");

        seedChart.setTitle("Seed Frequency");
        seedX.setLabel("seed number");
        seedY.setLabel("count");

        XYChart.Series series = new XYChart.Series();
        for(int i=0;i<10;i++){
            series.getData().add(new XYChart.Data("seed"+i,Math.abs(i-30)));
        }


        seedChart.getData().addAll(series);


        /**initialize components**/
        Button seedButton = (Button) root.lookup("#seedButton");
        Button opInterButton = (Button) root.lookup("#opInterButton");
        Button opImplButton = (Button) root.lookup("#opImplButton");
        Button runButton = (Button) root.lookup("#runButton");
        CheckBox listCheckbox = (CheckBox) root.lookup("#listCheckbox");
        ToggleButton listToggle = (ToggleButton) root.lookup("#listToggle");

        seedScroll.setVisible(false);

        /**set filechooser**/
        final FileChooser fileChooser = new FileChooser();/**for op files**/
        final DirectoryChooser directoryChooser = new DirectoryChooser();/**for seed files**/

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Java Class File", "*.class"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );


        /**set listeners**/
        seedButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                seedFiles = fileChooser.showOpenMultipleDialog(primaryStage);
//                for (File seedFile : seedFiles) {
//                    System.out.println("Seed: "+seedFile.getAbsolutePath());
//                }
                File seedDirectory = directoryChooser.showDialog(primaryStage);
                System.out.println(seedDirectory.getAbsolutePath());
                seedFiles = new ArrayList<File>();
                //Collections.addAll(seedFiles,seedDirectory.listFiles());//may not all be files, could be directories
                for (File seedFile : seedDirectory.listFiles()) {
                    if(!seedFile.isHidden() && seedFile.isFile()){//in case of hidden files like .DS_STORE
                        seedFiles.add(seedFile);
                        System.out.println("Seed: "+seedFile.getAbsolutePath());
                    }

                }


            }
        });

        /**select interface file**/
        opInterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                opInterFile = fileChooser.showOpenDialog(primaryStage);
                System.out.println("Op Interface: "+opInterFile.getAbsolutePath());
            }
        });

        /**select implementation files**/
        opImplButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                opImplFiles = fileChooser.showOpenMultipleDialog(primaryStage);
                for (File opImplFile : opImplFiles) {
                    System.out.println("Op Implementation: "+opImplFile);
                }
            }
        });


        /**Run fuzztest**/
        runButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                seedScroll.setVisible(true);
                if(seedFiles == null || seedFiles.size() == 0
                        || opInterFile == null
                        || opImplFiles == null || opImplFiles.size() == 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR,"At least one of the required files is not present or valid.");
                    alert.show();
                }else{
                    /**initialize a new instance of FuzzTest**/
                    fuzzTest = new FuzzTest(seedFiles,opInterFile,opImplFiles);
                    fuzzTest.run();
                }
            }
        });

        /**list hide/show checkbox**/
        listCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue == true){
                    System.out.println("TRUE");
                    try {
                        showSeedList(primaryStage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(newValue == false){
                    System.out.println("FALSE");
                    hideSeedList();
                }
            }
        });

        /**list hide/show toggle**/
        listToggle.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue == true){
                    System.out.println("TRUE");
                    try {
                        showSeedList(primaryStage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(newValue == false){
                    System.out.println("FALSE");
                    hideSeedList();
                }
            }
        });
    }

    public void showSeedList(final Stage primaryStage) throws IOException {
        if(listViewStage == null){
            listViewStage = new Stage();
            listViewStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    ToggleButton listToggle = (ToggleButton) primaryStage.getScene().lookup("#listToggle");
                    listToggle.setSelected(false);
                }
            });
            Parent listRoot = FXMLLoader.load(getClass().getResource("seedList.fxml"));
            listViewStage.setTitle("Seed List");
            double x = primaryStage.getX()+primaryStage.getWidth();
            double y = primaryStage.getY();
            listViewStage.setX(x);
            listViewStage.setY(y);
            listViewStage.setScene(new Scene(listRoot, 300, 275));
            listViewStage.show();
        }

    }

    public void hideSeedList(){
        listViewStage.close();
        listViewStage = null;
    }


    public static void main(String[] args) {























//        File opInterFile = new File("/Users/weizhaoy/Documents/workspace/java/FXLab/out/production/FXLab/interfaceLab/Op_Interface.class");
//        File opImplFile = new File("/Users/weizhaoy/Documents/workspace/java/FXLab/out/production/FXLab/interfaceLab/Op_Interface_Implementation_1.class");
//
//        MyClassLoader myClassLoader = new MyClassLoader();
//
//        try {
//            //get the Class
//
//            //Operation Interface
//            Class cl_inter = myClassLoader.loadClass(opInterFile.getAbsolutePath());
//            //Operation Implementation
//            Class cl_impl = myClassLoader.loadClass(opImplFile.getAbsolutePath());
//
//
//            try {
//                Object cl_impl_instance = cl_impl.newInstance();
//                Method implemented_method = cl_impl.getMethod("Op_mut_2", int.class, int.class);
//                Object num = implemented_method.invoke(cl_impl_instance, 1, 2);
//                System.out.println("Method " + implemented_method.getName() + " Invoked: " + num.toString());
//
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//
//
//            /*
//            String className = cl_inter.getCanonicalName();
//
//
//            //methods
//            Method[] methods = cl_inter.getDeclaredMethods();
//            for (Method method : methods) {
//                System.out.println(method.getName());
//                System.out.println(method.getParameterCount());
//                Parameter[] parameters = method.getParameters();
//                //parameters
//                for (Parameter parameter : parameters) {
//                    if(!parameter.isNamePresent()){
//                        //didn't compile with -parameters option,
//                        //therefore can't obtain parameter name
//                        System.out.println("WARNING: Parameter Name is NOT PRESENT.");
//                    }
//                    System.out.println(parameter.toString());
//                }
//            }
//
//
//
//
//
//            //Interface
//            if(cl_inter.isInterface()){
//                System.out.println("-----Interface: "+ className+"-----");
//            }
//            //Implementation
//            else{
//                System.out.println("-----Implementation: "+className+"-----");
//                System.out.println("Implemented Interface: ");
//                for(Class inter: cl_inter.getInterfaces()){
//                    System.out.println(inter.getCanonicalName());
//                }
//            }
//
//            */
//
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }


        launch(args);
    }
}
