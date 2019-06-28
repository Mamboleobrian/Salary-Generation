package controller;

import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class printer {


    public void Printing(Node node){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);


        Printer printer = Printer.getDefaultPrinter(); //gets the default printer.

        PageLayout pageLayout = printer.createPageLayout(
                Paper.A6, PageOrientation.REVERSE_LANDSCAPE, 20,20,15,12);  //setting pagelayout

        PrinterJob job = PrinterJob.createPrinterJob(printer); //create a job for a specified printer

        if((printer!=null || job!=null) && job.showPrintDialog(node.getScene().getWindow())) {

            System.out.println(printer.getName());
            System.out.println(job.jobStatusProperty().asString());

            boolean success = job.printPage(pageLayout,node); // Prints the specified node eg. anchorPane
            if (success) {
                job.endJob();
                alert.setContentText("Printing Successfully");
                alert.showAndWait();
            }else{
                alert.setContentText("Printing Failed");
                alert.showAndWait();
            }
        }
        else{
            System.out.println("Failed Print");
        }


    }


}
