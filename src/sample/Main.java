package sample;

import ClassLoaderLab.MyClassLoader;
import EntityLab.FuzzTest;
import EntityLab.OpBase;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Main extends Application {


    private static List<File> seedFiles;
    private static File opInterFile;
    private static List<File> opImplFiles;

    private static FuzzTest fuzzTest;
    @Override
    public void start(final Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


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


        Button seedButton = (Button) root.lookup("#seedButton");
        Button opInterButton = (Button) root.lookup("#opInterButton");
        Button opImplButton = (Button) root.lookup("#opImplButton");
        Button runButton = (Button) root.lookup("#runButton");

        seedScroll.setVisible(false);

        final FileChooser fileChooser = new FileChooser();


        seedButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                seedFiles = fileChooser.showOpenMultipleDialog(primaryStage);
                for (File seedFile : seedFiles) {
                    System.out.println("Seed: "+seedFile.getAbsolutePath());
                }

            }
        });

        opInterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                opInterFile = fileChooser.showOpenDialog(primaryStage);
                System.out.println("Op Interface: "+opInterFile.getAbsolutePath());
            }
        });

        opImplButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                opImplFiles = fileChooser.showOpenMultipleDialog(primaryStage);
                for (File opImplFile : opImplFiles) {
                    System.out.println("Op Implementation: "+opImplFile);
                }
            }
        });

        runButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                seedScroll.setVisible(true);
                if(seedFiles == null || seedFiles.size() == 0
                        || opInterFile == null
                        || opImplFiles == null || opImplFiles.size() == 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR,"At least one of the required files is not present or valid.");
                    alert.show();
                }
            }
        });
    }


    public static void main(String[] args) {























//        File opInterFile = new File("/Users/weizhaoy/Documents/workspace/java/FXLab/out/production/FXLab/interfaceLab/Op_Interface.class");
//        File opImplFile = new File("/Users/weizhaoy/Documents/workspace/java/FXLab/out/production/FXLab/interfaceLab/Op_Interface_Implementation.class");
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
