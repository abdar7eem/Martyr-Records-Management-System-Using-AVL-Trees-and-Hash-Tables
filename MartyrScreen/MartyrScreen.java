package MartyrScreen;

import java.util.ArrayList;
import java.util.Optional;

import DatePackage.HashTable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class MartyrScreen {
	private BorderPane root, tPane;
	private VBox cVbox, tVbox, bVbox, lVbox, addVbox, deleteVbox, treeStat, printVbox;
	private HBox bHbox;
	private Label dateName, add, delete, treeSize, treeSize1, treeHeight, treeHeight1, print, error;
	private Button addBtn, deleteBtn, printBtn;
	private TextField dSearchTf, mSearchTf;
	private TableView<Martyr> table;
	private ArrayList<Martyr>Marr=new ArrayList<>();
	private ListView<String> listView;
	private GridPane gpane;
	HashTable hst;
	Mstack ptr=new Mstack();
	char gender='M';
	public MartyrScreen(HashTable hst, String firstDate, Martyr firstM) {
		this.hst=hst;


		root=new BorderPane();
		root.setStyle("-fx-background-image: url(\"img//test.jpg\");"
				+ "-fx-background-size: cover;");
		//		root.setBackground(new Background(new BackgroundImage(new Image("img//background.jpg"),BackgroundRepeat.NO_REPEAT,
		//				BackgroundRepeat.NO_REPEAT,
		//				BackgroundPosition.CENTER,
		//				BackgroundSize.DEFAULT)));

		tPane=new BorderPane();
		hst.getHash()[hst.search(firstDate)].getmAvl().NavigateLevelOreder();

		cVbox=new VBox();
		tVbox=new VBox();
		addVbox=new VBox();
		deleteVbox=new VBox();
		bVbox=new VBox();
		lVbox=new VBox();
		treeStat=new VBox();
		printVbox=new VBox();

		bHbox=new HBox();

		dateName=new Label(firstDate);
		add=new Label("Add Martyr");
		delete=new Label("Delete Martyr");
		print=new Label("Print in Level Order");
		treeSize=new Label("Size of Tree: ");
		treeSize1=new Label();
		treeHeight=new Label("Height of Tree: ");
		treeHeight1=new Label();
		error=new Label("");
		error.setTextFill(Color.RED);
		dateName.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,40));
		add.setFont(Font.font("Arial",FontWeight.BOLD,20));
		delete.setFont(Font.font("Arial",FontWeight.BOLD,20));
		print.setFont(Font.font("Arial",FontWeight.BOLD,20));
		treeHeight.setFont(Font.font("Arial",FontWeight.BOLD,20));
		treeHeight1.setFont(Font.font("Arial",FontWeight.BOLD,20));
		treeSize.setFont(Font.font("Arial",FontWeight.BOLD,20));
		treeSize1.setFont(Font.font("Arial",FontWeight.BOLD,20));


		addBtn=new Button(null,new ImageView(new Image("img\\add.png",30,30,false,false)));
		deleteBtn=new Button(null,new ImageView(new Image("img\\Delete.png",30,30,false,false)));
		printBtn=new Button(null,new ImageView(new Image("img\\print.png",30,30,false,false)));
		addBtn.setStyle("-fx-background-color:transparent");
		deleteBtn.setStyle("-fx-background-color:transparent");
		printBtn.setStyle("-fx-background-color:transparent");


		dSearchTf=new TextField();
		dSearchTf.setPromptText("Type to Search Date");
		mSearchTf=new TextField();
		mSearchTf.setPromptText("Type to Search Martyr");

		listView=new ListView<String>();
		listView.setItems(FXCollections.observableArrayList(hst.getDates()));
		listView.setMaxHeight(300);
		listView.setMaxWidth(300);

		listView.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				dateName.setText(listView.getSelectionModel().getSelectedItem());
				table.getItems().clear();
				getMartyrNames();
				table.setItems(FXCollections.observableArrayList(Marr));
				error.setText("");
				mSearchTf.setText("");
			}

		});

		gpane=new GridPane();
		gpane.add(treeHeight, 0, 0);
		gpane.add(treeHeight1, 1, 0);
		gpane.add(treeSize, 0, 1);
		gpane.add(treeSize1, 1, 1);
		gpane.setVgap(5);
		gpane.setHgap(5);
		gpane.setAlignment(Pos.CENTER);

		table=new TableView<Martyr>();
		table.setEditable(true);
		TableColumn <Martyr,String>marNameCol=new TableColumn<Martyr,String>("Martyr name");
		marNameCol.setCellValueFactory(new PropertyValueFactory<>("mname"));
		marNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		marNameCol.setOnEditCommit(e->{
			Martyr m=e.getRowValue();
			m.setMname(e.getNewValue());
		});

		TableColumn <Martyr,Integer>marageCol=new TableColumn<Martyr,Integer>("Martyr Age");
		marageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
		marageCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		marageCol.setOnEditCommit(e->{
			Martyr m=e.getRowValue();
			m.setAge(e.getNewValue());
		});

		TableColumn <Martyr,String>mardateCol=new TableColumn<Martyr,String>("Martyr Date");
		mardateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		mardateCol.setCellFactory(TextFieldTableCell.forTableColumn());
		mardateCol.setOnEditCommit(e->{
			Martyr m=e.getRowValue();
			m.setDate(e.getNewValue());
			hst.getHash()[hst.search(dateName.getText())].getmAvl().deleteNode(table.getSelectionModel().getSelectedItem());
			if(hst.search(e.getNewValue())!=-1) {
				hst.getHash()[hst.search(e.getNewValue())].getmAvl().insert(m);
			}
			else {
				hst.insert(e.getNewValue());
				hst.getHash()[hst.search(e.getNewValue())].getmAvl().insert(m);
			}

			table.getItems().clear();
			getMartyrNames();
			table.setItems(FXCollections.observableArrayList(Marr));
		});
		TableColumn <Martyr,String>marLocCol=new TableColumn<Martyr,String>("Martyr Location");
		marLocCol.setCellValueFactory(new PropertyValueFactory<>("location"));
		marLocCol.setCellFactory(TextFieldTableCell.forTableColumn());
		marLocCol.setOnEditCommit(e->{
			Martyr m=e.getRowValue();
			m.setLocation(e.getNewValue());
		});

		TableColumn <Martyr,String>marDisCol=new TableColumn<Martyr,String>("Martyr District");
		marDisCol.setCellValueFactory(new PropertyValueFactory<>("district"));
		marDisCol.setCellFactory(TextFieldTableCell.forTableColumn());
		marDisCol.setOnEditCommit(e->{
			Martyr m=e.getRowValue();
			m.setDistrict(e.getNewValue());
		});

		TableColumn <Martyr,String>marGenCol=new TableColumn<Martyr,String>("Martyr Gender");
		marGenCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
		marGenCol.setCellFactory(TextFieldTableCell.forTableColumn());
		marGenCol.setOnEditCommit(e->{
			Martyr m=e.getRowValue();
			m.setGender(e.getNewValue());
		});

		table.getColumns().addAll(marNameCol, marageCol, mardateCol, marLocCol, marDisCol, marGenCol);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


		table.setMaxWidth(700);
		table.setMaxHeight(300);
		getMartyrNames();
		table.setItems(FXCollections.observableArrayList(Marr));

		dSearchTf.setOnKeyTyped(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				listView.getItems().clear();
				listView.setItems(FXCollections.observableArrayList(hst.searchDate(dSearchTf.getText())));
				error.setText("");
				mSearchTf.setText("");
			}
		});

		mSearchTf.setOnKeyTyped(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				table.getItems().clear();
				table.setItems(FXCollections.observableArrayList(hst.getHash()[hst.search(dateName.getText())].getmAvl().searchMartyr(mSearchTf.getText())));
				error.setText("");
				dSearchTf.setText("");
			}
		});

		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				getAddStage().show();
				error.setText("");
				dSearchTf.setText("");
				mSearchTf.setText("");
			}
		});

		deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(table.getSelectionModel().getSelectedItem()!=null) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Please Confirm");
					alert.setHeaderText("Delete Martyr");
					alert.setContentText("Martyr will be deleted");

					Optional<ButtonType> result = alert.showAndWait();
					if (result.isPresent() && result.get() == ButtonType.OK) {


						hst.getHash()[hst.search(dateName.getText())].getmAvl().deleteNode(table.getSelectionModel().getSelectedItem());
						table.getItems().clear();
						getMartyrNames();
						table.setItems(FXCollections.observableArrayList(Marr));
						error.setText("");
						dSearchTf.setText("");
						mSearchTf.setText("");
					}	
					else {
						dSearchTf.clear();
						mSearchTf.clear();
						error.setText("");
					}
				}
				else {
					error.setText("Choose Martyr From Table View");
				}


			}
		});

		printBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				getPrintStage().show();
				error.setText("");
				dSearchTf.setText("");
				mSearchTf.setText("");
			}
		});

		tPane.setLeft(mSearchTf);
		tPane.setRight(dateName);
		tPane.setMaxWidth(700);
		tPane.setMargin(mSearchTf, new Insets(15,0,0,0));
		//tPane.setMargin(dateName, new Insets(0,30,0,0));

		cVbox.getChildren().addAll(tPane, table);
		cVbox.setSpacing(15);
		cVbox.setAlignment(Pos.CENTER);

		addVbox.getChildren().addAll(add, addBtn);
		addVbox.setSpacing(15);
		addVbox.setAlignment(Pos.CENTER);

		lVbox.getChildren().addAll(dSearchTf, listView);
		lVbox.setSpacing(15);
		lVbox.setAlignment(Pos.CENTER);

		deleteVbox.getChildren().addAll(delete, deleteBtn);
		deleteVbox.setSpacing(15);
		deleteVbox.setAlignment(Pos.CENTER);

		printVbox.getChildren().addAll(print, printBtn);
		printVbox.setSpacing(15);
		printVbox.setAlignment(Pos.CENTER);

		treeStat.getChildren().addAll(gpane);

		bHbox.getChildren().addAll(printVbox, addVbox, deleteVbox, treeStat);
		bHbox.setSpacing(150);
		bHbox.setAlignment(Pos.CENTER);

		bVbox.getChildren().addAll(bHbox, error);
		bVbox.setSpacing(15);
		bVbox.setAlignment(Pos.CENTER);

		root.setCenter(cVbox);
		root.setBottom(bVbox);
		root.setLeft(lVbox);
		root.setMargin(bVbox, new Insets(30));
		root.setMargin(lVbox, new Insets(30));

	}

	public BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}

	public Label getDateName() {
		return dateName;
	}

	public void setDateName(Label dateName) {
		this.dateName = dateName;
	}

	public void getMartyrNames(){
		Marr.clear();
		Marr=hst.getHash()[hst.search(dateName.getText())].getmAvl().getMar();
		treeHeight1.setText(String.valueOf((hst.getHash()[hst.search(dateName.getText())].getmAvl().treeHight())));
		treeSize1.setText(String.valueOf((hst.getHash()[hst.search(dateName.getText())].getmAvl().treeSize())));
		//Marr.sort(Comparator.comparing(Martyr::getMname));
	}

	public Stage getPrintStage(){
		Stage stage=new Stage();
		BorderPane root=new BorderPane();
		TextArea ta=new TextArea();
		ta.setEditable(false);
		hst.getHash()[hst.search(dateName.getText())].getmAvl().getStack().clear(hst.getHash()[hst.search(dateName.getText())].getmAvl().getStack());
		hst.getHash()[hst.search(dateName.getText())].getmAvl().NavigateLevelOreder();
		ta.clear();
		ta.setText(hst.getHash()[hst.search(dateName.getText())].getmAvl().getStack().getPrintStack(hst.getHash()[hst.search(dateName.getText())].getmAvl().getStack()));

		root.setCenter(ta);
		root.setMargin(ta, new Insets(10));

		stage.setScene(new Scene(root,700,350));
		return stage;
	}

	public Stage getAddStage() {
		Stage stage=new Stage();
		BorderPane root=new BorderPane();
		Label Mname=new Label("Enter Name of Martyr:");
		Label Mage=new Label("Enter the Age of Martyr:");
		Label Mgender=new Label("Enter the Gender of Martyr:");
		Label MLocation=new Label("Enter the Location of Martyr:");
		Label MDistirct=new Label("Enter the District of Martyr:");
		Label error=new Label();
		error.setTextFill(Color.RED);

		HBox genderHbox=new HBox();

		TextField tfName=new TextField();
		TextField tfAge=new TextField();
		TextField tfDistrict=new TextField();
		TextField tfLocation=new TextField();

		RadioButton male=new RadioButton("Male");
		male.setSelected(true);
		RadioButton female=new RadioButton("Female");
		female.setSelected(false);
		ToggleGroup genderGroup=new ToggleGroup();
		male.setToggleGroup(genderGroup);
		female.setToggleGroup(genderGroup);

		genderHbox.getChildren().addAll(male, female);
		genderHbox.setAlignment(Pos.CENTER);
		genderHbox.setSpacing(5);

		Button addBtn=new Button(null,new ImageView(new Image("img\\add.png",30,30,false,false)));

		GridPane gpane=new GridPane();

		VBox vbox=new VBox();

		gpane.add(Mname, 0, 0);
		gpane.add(tfName, 1, 0);
		gpane.add(Mage, 0, 1);
		gpane.add(tfAge, 1, 1);
		gpane.add(Mgender, 0, 2);
		gpane.add(genderHbox, 1, 2);
		gpane.add(MLocation, 0, 3);
		gpane.add(tfLocation, 1, 3);
		gpane.add(MDistirct, 0, 4);
		gpane.add(tfDistrict, 1, 4);

		gpane.setAlignment(Pos.CENTER);
		gpane.setHgap(15);
		gpane.setVgap(15);

		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					if(!(tfName.getText().isBlank()) && !(tfAge.getText().isBlank()) && !(dateName.getText().isBlank()) && !(tfLocation.getText().isBlank()) && !(tfDistrict.getText().isBlank())) {
						Martyr martyr=new Martyr(tfName.getText(), dateName.getText(), Integer.parseInt(tfAge.getText()), tfLocation.getText(), tfDistrict.getText(), String.valueOf(gender));
						hst.getHash()[hst.search(dateName.getText())].getmAvl().insert(martyr);
						table.getItems().clear();
						getMartyrNames();
						table.setItems(FXCollections.observableArrayList(Marr));
						error.setText("");
					}
					else {
						error.setText("Enter Valid Date");
					}
				}catch (Exception e) {
					error.setText("");
				}
			}
		});

		male.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				gender='M';
				female.setSelected(false);
			}
		});

		female.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				gender='F';
				female.setSelected(true);	
			}
		});

		vbox.getChildren().addAll(gpane,addBtn,error);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);

		root.setCenter(vbox);
		stage.setScene(new Scene(root,350,350));
		return stage;
	}

	public ListView<String> getListView() {
		return listView;
	}

	public void setListView(ListView<String> listView) {
		this.listView = listView;
	}

	public TableView<Martyr> getTable() {
		return table;
	}

	public void setTable(TableView<Martyr> table) {
		this.table = table;
	}

	public ArrayList<Martyr> getMarr() {
		return Marr;
	}

	public void setMarr(ArrayList<Martyr> marr) {
		Marr = marr;
	}

	



}
