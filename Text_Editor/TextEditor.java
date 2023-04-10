import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.*;

public class TextEditor implements ActionListener{

    //declaring properties of Text Editor
    JFrame frame;

    JMenuBar menuBar;
    
    JMenu file,edit;

    //file menu items
    JMenuItem newFile, openFile, saveFile;
    //edit menu items
    JMenuItem cut,copy, paste,selectAll,close;

    JTextArea textArea;

    TextEditor(){
        //initialize a frame
        frame = new JFrame();

        //initialize the menu bar
        menuBar = new JMenuBar();

        //initialize new text area
        textArea = new JTextArea(); 

        //itnitialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit"); 
       
        //initialize menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        //add action listner to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //add new items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        //same for edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        //adding action listner to the edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //add to edit menu 
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //add menus to menu bar
        menuBar.add(file);
        menuBar.add(edit);

        //set menu bar to frame
        frame.setJMenuBar(menuBar);

        // //add text area to frame 
        // frame.add(textArea);
        // create content plane 
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));  //top,left,bottom,right
        panel.setLayout(new BorderLayout(0,0));
        // now add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        // create a scroll pane 
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);     
        //add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);

        //set dimensions of frame 
        frame.setBounds(300,300,600,600);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){

        if(actionEvent.getSource()==cut){                     //cut
            //pefrom cut operation
            textArea.cut();
        }

        if(actionEvent.getSource()==copy){                    //copy
            //peform copy operation
            textArea.copy();
        }
 
        if(actionEvent.getSource()==paste){                   //paste
            textArea.paste();
        }

        if(actionEvent.getSource()==selectAll){               //select all
            textArea.selectAll();
        }

        if(actionEvent.getSource()==close){                   //close
            //peform close editor operation
            System.exit(0);
            //status 0 means we have to close the application
        }

        if(actionEvent.getSource()==openFile){                    //open file
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption  =  fileChooser.showOpenDialog(null);
            //if we have click on open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //getting the selected file
                File file = fileChooser.getSelectedFile();
                //get the path of selected file 
                String filePath =  file.getPath();
                try{
                    //initialize the file reader
                    FileReader fileReader = new FileReader(filePath);
                    //initialize buffer reader
                    BufferedReader bufferedReader =  new BufferedReader(fileReader);
                    String intermediate="" , output ="";
                    //read contents of file line by line
                    while((intermediate=bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    } 
                    //set the output string to text area
                    textArea.setText(output);
                    bufferedReader.close();
                }
                catch(FileNotFoundException filenotfoundexception){
                    filenotfoundexception.printStackTrace();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();   
                }
            }  
        }

        if(actionEvent.getSource()== saveFile){                           //save file
            //Initialize the file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //check if we click on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //create a new file with choosen directory path
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //initialize the file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //initialize the buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write contents of text area to file 
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }   

        if(actionEvent.getSource()== newFile){                            //new file
            TextEditor newtextEditor = new TextEditor();
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}