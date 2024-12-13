package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import domain.PrgState;
import domain.adt.MyDictionary;
import domain.adt.MyIDictionary;
import domain.adt.MyIList;
import domain.adt.MyIStack;
import domain.adt.MyList;
import domain.adt.MyStack;
import domain.adt.MyTuple;
import domain.exp.ArithExp;
import domain.exp.ConstExp;
import domain.exp.HeapRead;
import domain.exp.VarExp;
import domain.stmt.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import repository.Repository;

public class PrgListController implements Initializable {
	static Repository myFirstRepository, mySecondRepository, myThirdRepository, myFourthRepository, myLastRepository;
	static Controller myFirstController, mySecondController, myThirdController, myFourthController, myLastController;
	static IStmt firstProgram, secondProgram, thirdProgram, fourthProgram, lastProgram;
	@FXML
	ListView<Controller> myPrgList;
	@FXML
	Button runButton;
	
	public void setUp() {
		myFirstRepository = new Repository("firstProgramLog.txt");
    	myFirstController = new Controller(myFirstRepository);
        firstProgram = new CompStmt(new AssignStmt("v", new ConstExp(10)),
				new CompStmt(new ForkStmt(
						new CompStmt(new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ConstExp(1))),
								new CompStmt(new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ConstExp(1))),
										new PrintStmt(new VarExp("v"))))),
						new CompStmt(new SleepStmt(10),
								new PrintStmt(new ArithExp('*', new VarExp("v"), new ConstExp(10))))));
        MyIStack<IStmt> exeStack1 = new MyStack<IStmt>();
    	MyIDictionary<String, Integer> symTable1 = new MyDictionary<String, Integer>();
    	MyIList<Integer> out1 = new MyList<Integer>();
    	MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable1 = new MyDictionary<Integer, MyTuple<String, BufferedReader>>();
    	MyIDictionary<Integer, Integer> heap1 = new MyDictionary<Integer, Integer>();
    	PrgState myPrgState1 = new PrgState(exeStack1, symTable1, out1, fileTable1, heap1, firstProgram);
    	myFirstController.addProgram(myPrgState1);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setUp();
		ObservableList<Controller> myData = FXCollections.observableArrayList();
		myData.add(myFirstController);
		myPrgList.setItems(myData);
		myPrgList.getSelectionModel().selectFirst();
		runButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent e) {
				Stage programStage = new Stage();
				Parent programRoot;
				Callback<Class<?>, Object> controllerFactory = type -> {
				    if (type == PrgRunController.class) {
				        return new PrgRunController(myPrgList.getSelectionModel().getSelectedItem());
				    } else {
				        try {
				            return type.newInstance() ; 
				        } catch (Exception exc) {
				            System.err.println("Could not create controller for "+type.getName());
				            throw new RuntimeException(exc);
				        }
				    }
				};
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ProgramLayout.fxml"));
					fxmlLoader.setControllerFactory(controllerFactory);
					programRoot = fxmlLoader.load();
					Scene programScene = new Scene(programRoot);
					programStage.setTitle("Toy Language - Program running");
					programStage.setScene(programScene);
					programStage.show();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
