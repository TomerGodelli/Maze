package View;

import ViewModel.MyViewModel;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


public class MyView implements Observer {
    @FXML
    private MyViewModel viewModel;
    public MazeDisplayer mazeDisplayer;
    public SolutionDisplayer solutionDisplayer;
    public javafx.scene.control.MenuItem btn_newEasy;
    public javafx.scene.control.MenuItem btn_newMedium;
    public javafx.scene.control.MenuItem btn_newHard;
    public javafx.scene.control.Menu btn_new;
    public javafx.scene.layout.Pane mainPane;
    public javafx.scene.layout.BorderPane mainBorderPane;





    @Override
    public void update(Observable o, Object arg) {
        if (o == viewModel) {
            displayMaze(viewModel.getMaze());
            btn_new.setDisable(false);

            // btn_generateMaze.setDisable(false);
        }
    }
    public void setViewModel(MyViewModel viewModel) {
        this.viewModel = viewModel;
    }
    public void displayMaze(int[][] maze) {
        mazeDisplayer.setSize(mainPane.getHeight(),mainPane.getWidth());
        mazeDisplayer.setMaze(maze);
        //mazeDisplayer.redraw();
        //int characterPositionRow = viewModel.getCharacterPositionRow();
        //int characterPositionColumn = viewModel.getCharacterPositionColumn();
        //mazeDisplayer.setCharacterPosition(characterPositionRow, characterPositionColumn);
        //this.characterPositionRow.set(characterPositionRow + "");
        //this.characterPositionColumn.set(characterPositionColumn + "");
    }

    public void generateMaze(int i,int j) {
        viewModel.generateMaze(i, j);
    }

    public void generateEasyMaze() throws IOException {
        //get main stage
        //Stage stage = (Stage) mainPane.getScene().getWindow();


        btn_new.setDisable(true);
        generateMaze(21,21);

    }
    public void generateMediumMaze(){
        //Stage stage = (Stage) mainPane.getScene().getWindow();


        btn_new.setDisable(true);


        generateMaze(35,35);
    }
    public void generateHardMaze(){
       // Stage stage = (Stage) mainPane.getScene().getWindow();

        btn_new.setDisable(true);
        generateMaze(51,51);
    }
    //ToDo  check!! somtiomes fails
    public  void getHint(){
        int[][] nextStep = viewModel.getNextStep();
        solutionDisplayer.setSize(mainPane.getHeight(),mainPane.getWidth());
        solutionDisplayer.setSolution(nextStep);


    }
    public void revealSolution() {
        showAlert("Solving maze..");
    }
    private void showAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(alertMessage);
        alert.show();
    }

    public void KeyPressed(KeyEvent keyEvent) {

        //viewModel.moveCharacter(keyEvent.getCode());
        keyEvent.consume();
    }

    public void setResizeEvent(Scene scene) {

        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            System.out.println("Height: " + scene.getHeight() + " Width: " + scene.getWidth());
            mazeDisplayer.setSize(mainPane.getHeight(), mainPane.getWidth());
            solutionDisplayer.setSize(mainPane.getHeight(), mainPane.getWidth());
            //ToDo redraw set size of character too;

        };


        scene.widthProperty().addListener(stageSizeListener);
        scene.heightProperty().addListener(stageSizeListener);


    }
    public void setMaximizeEvent(Stage stage){
        stage.maximizedProperty().addListener(new ChangeListener<Boolean>() {
//ToDo fix here!!!!!! doesnt work
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                System.out.println("maximized:" + t1.booleanValue());
                mazeDisplayer.setSize(mainPane.getMaxHeight(), mainPane.getMaxWidth());
                solutionDisplayer.setSize(mainPane.getMaxHeight(), mainPane.getMaxWidth());

            }
        });

    }


        public void AboutUs(ActionEvent actionEvent) {
        showAlert("this is us!!!!!!!!");

        try {
            Stage stage = new Stage();
            stage.setTitle("About Us");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("AboutUs.fxml").openStream());
            Scene scene = new Scene(root, 400, 350);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {

        }
    }
    public void AboutTheGame(ActionEvent actionEvent) {
        showAlert("this is maze!!!!!!!!");

        try {
            Stage stage = new Stage();
            stage.setTitle("About The Game");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("AboutTheGame.fxml").openStream());
            Scene scene = new Scene(root, 400, 350);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {

        }
    }
    public void setNewScene(Stage stage , Scene scene){
        stage.setScene(scene);

    }


}
