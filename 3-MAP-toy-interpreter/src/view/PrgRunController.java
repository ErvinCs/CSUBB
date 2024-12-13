package view;

import java.io.BufferedReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import controller.Controller;
import domain.PrgState;
import domain.adt.MyTuple;
import domain.stmt.IStmt;
import exception.MyException;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PrgRunController implements Initializable {
	Controller myController;
	@FXML
	Label nrPrgStates;
	@FXML
	Button runButton;
	@FXML
	TableView<HashMap.Entry<Integer, Integer>> heapTable;
	@FXML
	TableColumn<HashMap.Entry<Integer,Integer>, String> heapTableAddress;
	@FXML
	TableColumn<HashMap.Entry<Integer,Integer>, String> heapTableValue;
	@FXML
	ListView<String> outList;
	@FXML
	TableView<HashMap.Entry<Integer, MyTuple<String, BufferedReader>>> fileTable;
	@FXML
	TableColumn<HashMap.Entry<Integer, MyTuple<String, BufferedReader>>, String> fileTableIdentifier;
	@FXML
	TableColumn<HashMap.Entry<Integer, MyTuple<String, BufferedReader>>, String> fileTableFileName;
	@FXML
	ListView<String> prgStateList;
	@FXML
	TableView<HashMap.Entry<String, Integer>> symTable;
	@FXML
	TableColumn<HashMap.Entry<String, Integer>, String> symTableVariable;
	@FXML
	TableColumn<HashMap.Entry<String, Integer>, String> symTableValue;
	@FXML
	ListView<String> exeStackList;
	
	public PrgRunController(Controller myController) {
		this.myController = myController;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initialRun();
		prgStateList.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	        	setSymTableAndExeStack();
	        }
	    });
		runButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent e) {
				try {
					myController.allStepGUI();
				} catch (MyException e1) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Toy Language - Current program finished");
					alert.setHeaderText(null);
					alert.setContentText("Program successfully finished!");
					Button confirm = (Button) alert.getDialogPane().lookupButton( ButtonType.OK );
					confirm.setDefaultButton(false);
					confirm.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
					alert.showAndWait();
				    Stage stage = (Stage) heapTable.getScene().getWindow();
				    stage.close();
				}
				updateUIComponents();
			}
		});
	}
	
	public void initialRun() {
		setNumberLabel();
		setHeapTable();
		setOutList();
		setFileTable();
		setPrgStateList();
		prgStateList.getSelectionModel().selectFirst();
		setSymTableAndExeStack();
	}
	
	public void updateUIComponents() {
		setNumberLabel();
		setHeapTable();
		setOutList();
		setFileTable();
		setPrgStateList();
		if(prgStateList.getSelectionModel().getSelectedItem() == null) {
			prgStateList.getSelectionModel().selectFirst();
		}
		setSymTableAndExeStack();
	}
	
	public void setNumberLabel() {
		nrPrgStates.setText("The number of PrgStates: " + myController.getRepository().getPrgList().size());
	}
	
	public void setHeapTable() {
		ObservableList<HashMap.Entry<Integer, Integer>> heapTableList = FXCollections.observableArrayList();
		heapTableAddress.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Integer.toString(cellData.getValue().getKey())));
		heapTableValue.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Integer.toString(cellData.getValue().getValue())));
		heapTableList.addAll(myController.getRepository().getPrgList().get(0).getHeap().getDictionary().entrySet());
		heapTable.setItems(heapTableList);
	}
	
	public void setOutList() {
		ObservableList<String> outObservableList = FXCollections.observableArrayList();
		for(Integer e : myController.getRepository().getPrgList().get(0).getOut().getList()) {
			outObservableList.add(e.toString());
		}
		outList.setItems(outObservableList);
	}
	
	public void setFileTable() {
		ObservableList<HashMap.Entry<Integer, MyTuple<String, BufferedReader>>> fileTableList = FXCollections.observableArrayList();
		fileTableIdentifier.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Integer.toString(cellData.getValue().getKey())));
		fileTableFileName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getValue().getFirst()));
		fileTableList.addAll(myController.getRepository().getPrgList().get(0).getFileTable().getDictionary().entrySet());
		fileTable.setItems(fileTableList);
	}
	
	public void setPrgStateList() {
		ObservableList<String> prgStateIdList = FXCollections.observableArrayList();
		for(PrgState e : myController.getRepository().getPrgList()) {
			prgStateIdList.add(Integer.toString(e.getId()));
		}
		prgStateList.setItems(prgStateIdList);
	}
	
	public void setSymTableAndExeStack() {
		ObservableList<HashMap.Entry<String, Integer>> symTableList = FXCollections.observableArrayList();
		ObservableList<String> exeStackItemsList = FXCollections.observableArrayList();
		symTableVariable.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getKey()));
		symTableValue.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Integer.toString(cellData.getValue().getValue())));
		List<PrgState> allPrgs = myController.getRepository().getPrgList();
		PrgState prgResult = null;
		for(PrgState e : allPrgs) {
			if(e.getId() == Integer.parseInt(prgStateList.getSelectionModel().getSelectedItem())) {
				prgResult = e;
				break;
			}
		}
		if(prgResult != null) {
			symTableList.addAll(prgResult.getSymTable().getDictionary().entrySet());
			for(IStmt e : prgResult.getExeStack().getStack()) {
				exeStackItemsList.add(e.toString());
			}
			symTable.setItems(symTableList);
			exeStackList.setItems(exeStackItemsList);
		}
	}

}
