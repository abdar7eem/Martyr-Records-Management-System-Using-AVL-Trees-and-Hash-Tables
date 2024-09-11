package DatePackage;


import java.util.Optional;

import MartyrScreen.MartyrScreen;
import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
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

public class DatePane {
	private BorderPane root;
	private VBox cVbox, tVbox, bVbox, addVbox, updateVbox, deleteVbox, printVbox, loadVbox;
	private HBox addHbox, updateHbox, deleteHbox, bHbox, btnVbox;
	private Label dateName, maxDistrict, maxLocation, maxDistrict1, maxLocation1,
	Mavg, Mavg1, Mtotal, Mtotal1, add, update, delete, print, load, error;
	private GridPane gpane;
	private Button next, prev, addBtn, updateBtn, deleteBtn, printBtn, loadBtn;
	private TextField updateTf;
	private DatePicker addTf;
	private RadioButton radio1=new RadioButton("With"),radio2=new RadioButton("Without");
	private ToggleGroup tGroup;

	String dateStr;
	Dstack ptr=new Dstack();
	HashTable hst;
	static MartyrScreen ms;

	public DatePane(HashTable hst, String date) {
		this.hst=hst;
		this.dateStr=date;
		hst.fillStack();
		ms=new MartyrScreen(hst, date, hst.getHash()[hst.search(date)].getmAvl().getRoot().getData());

		root=new BorderPane();
		root.setStyle("-fx-background-image: url(\"img//test.jpg\");"
				+ "-fx-background-size: cover;");
		//		root.setBackground(new Background(new BackgroundImage(new Image("img//test.jpg"),BackgroundRepeat.NO_REPEAT,
		//				BackgroundRepeat.NO_REPEAT,
		//				BackgroundPosition.CENTER,
		//				BackgroundSize.DEFAULT)));

		radio1.setToggleGroup(tGroup);
		radio1.setSelected(true);
		radio2.setToggleGroup(tGroup);
		radio2.setSelected(false);

		gpane=new GridPane();

		cVbox=new VBox();
		tVbox=new VBox();
		bVbox=new VBox();
		addVbox=new VBox();
		updateVbox=new VBox();
		deleteVbox=new VBox();
		printVbox=new VBox();
		loadVbox=new VBox();

		btnVbox=new HBox();
		addHbox=new HBox();
		updateHbox=new HBox();
		deleteHbox=new HBox();
		bHbox=new HBox();

		dateName=new Label(dateStr);
		maxDistrict=new Label("The District that has max Martyrs: ");
		maxLocation=new Label("The Location that has max Location: ");
		maxDistrict1=new Label();
		maxLocation1=new Label();
		add=new Label("Add Date");
		update=new Label("Update Date");
		delete=new Label("Delete Date");
		error=new Label("");
		error.setTextFill(Color.RED);
		Mavg=new Label("Martyrs AVG: ");
		Mavg1=new Label();
		Mtotal=new Label("Total Martyrs:");
		Mtotal1=new Label();
		load=new Label("Load Date AVL");
		dateName.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,40));
		maxDistrict.setFont(Font.font("Arial",FontWeight.BOLD,20));
		maxDistrict1.setFont(Font.font("Arial",FontWeight.BOLD,20));
		maxLocation.setFont(Font.font("Arial",FontWeight.BOLD,20));
		maxLocation1.setFont(Font.font("Arial",FontWeight.BOLD,20));
		Mavg.setFont(Font.font("Arial",FontWeight.BOLD,20));
		Mavg1.setFont(Font.font("Arial",FontWeight.BOLD,20));
		Mtotal.setFont(Font.font("Arial",FontWeight.BOLD,20));
		Mtotal1.setFont(Font.font("Arial",FontWeight.BOLD,20));
		add.setFont(Font.font("Arial",FontWeight.BOLD,20));
		update.setFont(Font.font("Arial",FontWeight.BOLD,20));
		delete.setFont(Font.font("Arial",FontWeight.BOLD,20));
		load.setFont(Font.font("Arial",FontWeight.BOLD,20));


		Mtotal1.setText(String.valueOf(hst.getHash()[hst.search(dateStr)].getmAvl().totalMartyr()));
		Mavg1.setText(String.valueOf(hst.getHash()[hst.search(dateStr)].getmAvl().avgMartyr()));
		maxDistrict1.setText(hst.getHash()[hst.search(dateStr)].getmAvl().maxDistrict());
		maxLocation1.setText(hst.getHash()[hst.search(dateStr)].getmAvl().maxLocation());

		gpane.add(Mtotal, 0, 0);
		gpane.add(Mtotal1, 1, 0);
		gpane.add(Mavg, 0, 1);
		gpane.add(Mavg1, 1, 1);
		gpane.add(maxDistrict, 0, 2);
		gpane.add(maxDistrict1, 1, 2);
		gpane.add(maxLocation, 0, 3);
		gpane.add(maxLocation1, 1, 3);
		gpane.setVgap(10);
		gpane.setHgap(10);
		gpane.setAlignment(Pos.CENTER);

		next=new Button(null,new ImageView(new Image("img\\down.png",30,30,false,false)));
		next.setStyle("-fx-background-color:transparent");
		prev=new Button(null,new ImageView(new Image("img\\up.png",30,30,false,false)));
		prev.setStyle("-fx-background-color:transparent");
		addBtn=new Button(null,new ImageView(new Image("img\\add.png",30,30,false,false)));
		addBtn.setStyle("-fx-background-color:transparent");
		updateBtn=new Button(null,new ImageView(new Image("img\\update.png",30,30,false,false)));
		updateBtn.setStyle("-fx-background-color:transparent");
		deleteBtn=new Button(null,new ImageView(new Image("img\\Delete.png",30,30,false,false)));
		deleteBtn.setStyle("-fx-background-color:transparent");
		printBtn=new Button(null,new ImageView(new Image("img\\print.png",30,30,false,false)));
		printBtn.setStyle("-fx-background-color:transparent");
		loadBtn=new Button(null,new ImageView(new Image("img\\load.png",30,30,false,false)));
		loadBtn.setStyle("-fx-background-color:transparent");

		addTf=new DatePicker();
		addTf.setEditable(false);
		updateTf=new TextField();

		btnVbox.getChildren().addAll(prev, next);
		btnVbox.setAlignment(Pos.CENTER);
		btnVbox.setSpacing(15);

		addHbox.getChildren().addAll(addTf, addBtn);
		addHbox.setAlignment(Pos.CENTER);
		addHbox.setSpacing(15);
		addVbox.getChildren().addAll(add, addHbox);
		addVbox.setAlignment(Pos.CENTER);
		addVbox.setSpacing(15);

		updateHbox.getChildren().addAll(updateTf, updateBtn);
		updateHbox.setAlignment(Pos.CENTER);
		updateHbox.setSpacing(15);
		updateVbox.getChildren().addAll(update, updateHbox);
		updateVbox.setAlignment(Pos.CENTER);
		updateVbox.setSpacing(15);

		deleteHbox.getChildren().addAll(deleteBtn);
		deleteHbox.setAlignment(Pos.CENTER);
		deleteHbox.setSpacing(15);
		deleteVbox.getChildren().addAll(delete, deleteHbox);
		deleteVbox.setAlignment(Pos.CENTER);
		deleteVbox.setSpacing(15);

		printVbox.getChildren().addAll(printBtn);
		printVbox.setAlignment(Pos.CENTER);
		printVbox.setSpacing(15);

		cVbox.getChildren().addAll(dateName, gpane, btnVbox, printVbox);
		cVbox.setAlignment(Pos.CENTER);
		cVbox.setPadding(new Insets(30,0,0,0));
		cVbox.setSpacing(15);

		loadVbox.getChildren().addAll(load, loadBtn);
		loadVbox.setAlignment(Pos.CENTER);
		loadVbox.setSpacing(15);

		bHbox.getChildren().addAll(loadVbox, addVbox, updateVbox, deleteVbox);
		bHbox.setAlignment(Pos.CENTER);
		bHbox.setSpacing(15);



		bVbox.getChildren().addAll(bHbox, error);
		bVbox.setAlignment(Pos.CENTER);
		bVbox.setSpacing(15);

		next.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				updateTf.clear();
				error.setText("");
				ptr.push(hst.getStack().pop());

				if(hst.getStack().peek()!=null) {
					dateName.setText(hst.getStack().peek().getData()) ;
					Mtotal1.setText(String.valueOf(hst.getHash()[hst.search(dateName.getText())].getmAvl().totalMartyr()));
					Mavg1.setText(String.valueOf(hst.getHash()[hst.search(dateName.getText())].getmAvl().avgMartyr()));
					maxDistrict1.setText(hst.getHash()[hst.search(dateName.getText())].getmAvl().maxDistrict());
					maxLocation1.setText(hst.getHash()[hst.search(dateName.getText())].getmAvl().maxLocation());
					if(ptr.getTop()==0) {
					}
				}				
			}
		});

		prev.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				updateTf.clear();
				error.setText("");
				if(ptr.getTop()!=-1) {
					hst.getStack().push(ptr.pop());
					if(hst.getStack().peek()!=null) {
						dateName.setText(hst.getStack().peek().getData());
						Mtotal1.setText(String.valueOf(hst.getHash()[hst.search(dateName.getText())].getmAvl().totalMartyr()));
						Mavg1.setText(String.valueOf(hst.getHash()[hst.search(dateName.getText())].getmAvl().avgMartyr()));
						maxDistrict1.setText(hst.getHash()[hst.search(dateName.getText())].getmAvl().maxDistrict());
						maxLocation1.setText(hst.getHash()[hst.search(dateName.getText())].getmAvl().maxLocation());
					}
				}				
			}
		});

		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				updateTf.clear();
				String date="";
				if(addTf.getValue()!=null) {
					date+=addTf.getValue().getMonth().getValue()+"/";
					date+=addTf.getValue().getDayOfMonth()+"/";
					date+=addTf.getValue().getYear();
					hst.insert(date);
					hst.getStack().clear(hst.getStack());
					ptr.clear(ptr);
					hst.fillStack();
					dateName.setText((hst.getStack()).peek().getData()) ;
					maxDistrict1.setText(hst.getHash()[hst.search(dateName.getText())].getmAvl().maxDistrict());
					maxLocation1.setText(hst.getHash()[hst.search(dateName.getText())].getmAvl().maxLocation());
					error.setText("");
				}
				else {
					error.setText("Enter Valid Date");
				}
			}
		});

		updateBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Please Confirm");
				alert.setHeaderText("Update Date");
				alert.setContentText("Date will be changed on updating");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.OK) {

					updateTf.clear();
					if(!updateTf.getText().isBlank()) {
						hst.updateMyHash(dateName.getText(), updateTf.getText());
						hst.getStack().clear(hst.getStack());
						ptr.clear(ptr);
						hst.fillStack();
						dateName.setText((hst.getStack()).peek().getData()) ;
						maxDistrict1.setText(hst.getHash()[hst.search(dateName.getText())].getmAvl().maxDistrict());
						maxLocation1.setText(hst.getHash()[hst.search(dateName.getText())].getmAvl().maxLocation());
						error.setText("");
					}
					else {
						error.setText("Enter valid Date");
					}
				}

				else {
					error.setText("");
					updateTf.clear();;
				}
			}

		});

		deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Please Confirm");
				alert.setHeaderText("Delete Date");
				alert.setContentText("Date will be Deleted");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.OK) {

					updateTf.clear();
					if(dateName.getText()!=null) {
						hst.delete(dateName.getText());
						hst.getStack().clear(hst.getStack());
						ptr.clear(ptr);
						hst.fillStack();

						dateName.setText((hst.getStack()).peek().getData()) ;
						if(hst.search(dateName.getText())!=-1) {
							maxDistrict1.setText(hst.getHash()[hst.search(dateName.getText())].getmAvl().maxDistrict());
							maxLocation1.setText(hst.getHash()[hst.search(dateName.getText())].getmAvl().maxLocation());
						}
						error.setText("");
					}
					else {
						updateTf.clear();
						error.setText("");
					}
				}
				else{

				}
			}
		});


		printBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				updateTf.clear();
				getPrintStage().show();
				error.setText("");
			}
		});

		loadBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				updateTf.clear();
				Main.selectTab2();
				error.setText("");
			}
		});

		root.setCenter(cVbox);
		root.setBottom(bVbox);
		root.setMargin(bVbox, new Insets(30));
	}

	public BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}

	public Stage getPrintStage() {
		Stage stage=new Stage();
		BorderPane root=new BorderPane();
		TextArea ta=new TextArea();
		HBox radioHbox=new HBox();

		if(radio1.isSelected()) {
			ta.setText(hst.getprint(true));
			radio2.setSelected(false);
		}
		if(radio2.isSelected()) {
			ta.setText(hst.getprint(false));
			radio1.setSelected(false);
		}

		radio1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(radio1.isSelected()) {
					ta.setText(hst.getprint(true));
					radio2.setSelected(false);
				}
			}
		});

		radio2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				if(radio2.isSelected()) {
					ta.setText(hst.getprint(false));
					radio1.setSelected(false);
				}
			}
		});

		radioHbox.getChildren().addAll(radio1, radio2);
		radioHbox.setAlignment(Pos.CENTER);
		radioHbox.setSpacing(30);

		root.setTop(radioHbox);
		root.setCenter(ta);
		root.setMargin(btnVbox, new Insets(20));
		root.setMargin(ta, new Insets(20));
		stage.setScene(new Scene(root,400,300));
		return stage;
	}

	public static MartyrScreen getMs() {
		return ms;
	}

	public static void setMs(MartyrScreen ms) {
		DatePane.ms = ms;
	}

	public Label getDateName() {
		return dateName;
	}

	public void setDateName(Label dateName) {
		this.dateName = dateName;
	}





}
