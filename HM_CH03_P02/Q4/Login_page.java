
package login_page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.stream.FileImageInputStream;


public class Login_page extends Application {

    GridPane gridpane;
    GridPane gridpane2;
    GridPane gridpane3;
	
    ListView<Student> listview;
    TextArea text_area = new TextArea();
    
    TextField field_id = new TextField();
    TextField field_name = new TextField();
    TextField field_major = new TextField();
    TextField field_grade = new TextField();

    @Override
    public void start(Stage primaryStage) {
        gridpane = new GridPane();
        gridpane2 = new GridPane();
        gridpane3 = new GridPane();

		
        Label labe_welcome = new Label("Welcome");
        Label labe_user_name = new Label("User Name: ");
        Label labe_password = new Label("Password: ");

        TextField field_usr_name = new TextField();
        TextField field_pass = new TextField();
		
		

        Button btn_sign_in = new Button("Sign in");
        Button btn_exit = new Button("Exit");
        Button btn_add = new Button("Add Student");
        Button btn_view = new Button("View Student");
        
		
		

        HBox fields_usr_names = new HBox(10, labe_user_name, field_usr_name);
        HBox fields_pass = new HBox(10, labe_password, field_pass);
        HBox btns = new HBox(10, btn_sign_in, btn_exit);
		
      

		
		
		
		
		
        Label student_data = new Label("Student Data ");
        Label id_label = new Label("Id:     ");
        Label Name_label = new Label("Name:   ");
        Label Major_label = new Label("Major:  ");
        Label grade_label = new Label("Grade:  ");
        Label map_ans_label = new Label("");

        field_id = new TextField();
        field_name = new TextField();
        field_major = new TextField();
        field_grade = new TextField();

        Button btn_add_s = new Button("Add");
        Button btn_Reset = new Button("Reset");
        Button btn_Exit = new Button("Exit");

        Button btn_sort_name = new Button("Sort by name");
        Button btn_Map_B = new Button("Map - B");
        Button btn_avg = new Button("Average");
        Button btn_group = new Button("Group");
        Button btn_map_c = new Button("Map C 80- 90");
        listview = new ListView();

		
		
        HBox lb_data = new HBox(student_data);
        HBox vb_id = new HBox(id_label, field_id);
        HBox vb_name = new HBox(Name_label, field_name);
        HBox vb_major = new HBox(Major_label, field_major);
        HBox vb_grade = new HBox(grade_label, field_grade);
        HBox vb_btns = new HBox(btn_add_s, btn_Reset, btn_exit);
        HBox vb_btns_sorting = new HBox(btn_sort_name,btn_Map_B,btn_map_c,btn_group,btn_avg);
       
	   
	   
        HBox vb_answers = new HBox(text_area);
        VBox vb_all_col1 = new VBox(lb_data, vb_id, vb_name, vb_major, vb_grade, vb_btns,vb_btns_sorting,vb_answers);
        HBox vb_list = new HBox(listview);
        vb_all_col1.setSpacing(10);

        vb_btns.setSpacing(5);

        lb_data.setAlignment(Pos.CENTER);
        vb_id.setAlignment(Pos.CENTER);
        vb_name.setAlignment(Pos.CENTER);
        vb_major.setAlignment(Pos.CENTER);
        vb_grade.setAlignment(Pos.CENTER);
        vb_btns.setAlignment(Pos.CENTER);
        vb_all_col1.setAlignment(Pos.CENTER);
        vb_list.setAlignment(Pos.CENTER);

        Student[] students = {
            new Student(1, "karem", "English", 90),
            new Student(2, "fadi", "Med", 75),
            new Student(5, "sami", "IT", 88),
            new Student(7, "Rami", "IT", 82),
            new Student(4, "Alaa", "ENg", 77)
        };
        List<Student> listStudents = Arrays.asList(students);
        listStudents.stream().forEach(s -> listview.getItems().addAll(s));
        btn_add_s.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                Student std = new Student(Integer.parseInt(field_id.getText()), field_name.getText(), field_major.getText(), Double.parseDouble(field_grade.getText()));
                listview.getItems().addAll(std);
                listStudents.add(std);
            }
        });

        btn_sort_name.setOnAction((ActionEvent arg0) -> {
           listview.getItems().clear();
           listStudents.stream().
                   sorted(Comparator.comparing(Student::getName))
                   .forEach(s -> listview.getItems().addAll(s));
                   
              
        });
        
        btn_Map_B.setOnAction((ActionEvent arg0) -> {
           text_area.clear();
           listStudents.stream()
                   .sorted(Comparator.comparing(Student::getGrade).reversed())
                   .map(e -> e.getName() + "  " +  e.getGrade())
                   .forEach(e -> text_area.appendText(e + "\n") );
                    
        });
        
        btn_map_c.setOnAction((ActionEvent arg0) -> {
            text_area.clear();
            listStudents.stream()
                    .filter(e -> (e.getGrade()>= 80 && e.getGrade() <= 90))
                    .sorted(Comparator.comparing(Student::getGrade).reversed())
                    .map(s -> s.getName() + "  " + s.getGrade() + "\n")
                    .forEach(s -> text_area.appendText(s + "\n"));
                    
                    
                    
                    
        });
        
        btn_avg.setOnAction((ActionEvent arg0) ->{
            text_area.clear();
            double avg = 
			
            listStudents.stream()
                    .mapToDouble(s -> s.getGrade())
					
                    .average().getAsDouble();
         text_area.setText("Average = " + avg);
        
        });

		
		
        btn_group.setOnAction((ActionEvent arg0) -> {
            text_area.clear();
            listStudents.stream().collect(Collectors.groupingBy(Student::getMajor))
			
                    .forEach((m,std) -> {
                        text_area.appendText(m + "\n");
                        text_area.appendText("*********************\n");
                        std.forEach(s -> text_area.appendText(s.toString() + "\n"));
                                });
        });
        
       
        gridpane3.add(vb_all_col1, 0, 0);
        gridpane3.add(vb_list, 1, 0);
        gridpane3.setHgap(20);
     
		
		
        gridpane3.setAlignment(Pos.CENTER);

        VBox btns_add = new VBox(btn_add, btn_view);

        gridpane.add(labe_welcome, 0, 0);
        gridpane.add(fields_usr_names, 0, 1);
        gridpane.add(fields_pass, 0, 2);
        gridpane.setAlignment(Pos.CENTER);
        gridpane.add(btns, 0, 3);
        gridpane.setVgap(20);

        gridpane2.add(btn_add, 0, 0);
        gridpane2.add(btn_view, 0, 1);
        gridpane2.setAlignment(Pos.CENTER);
        gridpane2.setVgap(20);

        Scene scene = new Scene(gridpane, 300, 250);
        Scene scene2 = new Scene(gridpane2, 300, 250);
        Scene scene3 = new Scene(gridpane3, 750, 500);
        scene.getStylesheets().add("style.css");
        btn_sign_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                primaryStage.setScene(scene2);
            }
        });
		
		
        btn_exit.setOnAction((ActionEvent arg0) -> {
            Platform.exit();
        });
		
		
        scene.getStylesheets().add("syle.css");
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene3);
        primaryStage.show();
    }

   
	 
	 
	 
    public static void main(String[] args) throws FileNotFoundException, IOException {
        launch(args);

        File f = new File("pass.text");
        FileOutputStream output = new FileOutputStream(f);
        FileInputStream input = new FileInputStream(f);

        System.out.println(input.read());

    }

}
