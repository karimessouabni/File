package Sitedu0Key;


/*
    This program lets the user edit short text files in a window.  A "File"
    menu provides the following commands:
    
            New  -- Clears all text from the window.
            Open -- Let's the user select a file and loads up to 100
                    lines of text form that file into the window.  The
                    previous contents of the window are lost.
            Save -- Let's the user specify an ouput file and saves
                    the contents of the window in that file.
            Quit -- Closes the window and ends the program.
            
    This class uses the non-standard class TextReader.
*/

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TrivialEdit extends JFrame {

   public static void main(String[] args) {
         // The main program just opens a window belonging to this
         // TrivialEdit class.  Then the window takes care of itself
         // until the program is ended with the Quit command or
         // when the user closes the window.
      new TrivialEdit();
   }
   

   private JTextArea text;   // Holds the text that is displayed in the window.
   
   
   public TrivialEdit() {
          // Add a menu bar and a JTextArea to the window, and show it
          // on the screen.  The first line of this routine calls the
          // constructor from the superclass to specify a title for the
          // window.  The pack() command sets the size of the window to
          // be just large enough to hold its contents.
      super("A Trivial Editor");
      setJMenuBar(makeMenus());
      text = new JTextArea(25,50);
      text.setBackground(Color.white);
      text.setMargin( new Insets(3,5,0,0) );
      JScrollPane scroller = new JScrollPane(text);
      setContentPane(scroller);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      pack();
      setLocation(50,50);
      show();
   }
   

   private JMenuBar makeMenus() {
          // Create and return a menu bar containing a single menu, the
          // File menu.  This menu contains four commands, and each 
          // command has a keyboard equivalent.

      ActionListener listener = new ActionListener() {
               // An object that will serve as listener for menu items.
            public void actionPerformed(ActionEvent evt) {
                   // This will be called when the user makes a selection
                   // from the File menu.  This routine just checks 
                   // which command was selected and calls another 
                   // routine to carry out the command.
               String cmd = evt.getActionCommand();
               if (cmd.equals("New"))
                  doNew();
               else if (cmd.equals("Open..."))
                  doOpen();
               else if (cmd.equals("Save..."))
                  doSave();
               else if (cmd.equals("Quit"))
                  doQuit();
            }
         };
         
      JMenu fileMenu = new JMenu("File");
      
      JMenuItem newCmd = new JMenuItem("New");
      newCmd.setAccelerator( KeyStroke.getKeyStroke("ctrl N") );
      newCmd.addActionListener(listener);
      fileMenu.add(newCmd);
      JMenuItem openCmd = new JMenuItem("Open...");
      openCmd.setAccelerator( KeyStroke.getKeyStroke("ctrl O") );
      openCmd.addActionListener(listener);
      fileMenu.add(openCmd);
      JMenuItem saveCmd = new JMenuItem("Save...");
      saveCmd.setAccelerator( KeyStroke.getKeyStroke("ctrl S") );
      saveCmd.addActionListener(listener);
      fileMenu.add(saveCmd);
      JMenuItem quitCmd = new JMenuItem("Quit");
      quitCmd.setAccelerator( KeyStroke.getKeyStroke("ctrl Q") );
      quitCmd.addActionListener(listener);
      fileMenu.add(quitCmd);
      
      JMenuBar bar = new JMenuBar();
      bar.add(fileMenu);
      return bar;

   } // end makeMenus()

   

   private void doNew() {
          // Carry out the "New" command from the File menu by
          // by clearing all the text from the JTextArea.
      text.setText("");
   }


   private void doSave() {
          // Carry out the Save command by letting the user specify
          // an output file and writing the text from the JTextArea
          // to that file.
      File file;  // The file that the user wants to save.
      JFileChooser fd; // File dialog that lets the user specify the file.
      fd = new JFileChooser(new File("."));
      fd.setDialogTitle("Save Text As...");
      int action = fd.showSaveDialog(this);
      if (action != JFileChooser.APPROVE_OPTION) {
             // User has canceled, or an error occurred.
         return;
      }
      file = fd.getSelectedFile();
      if (file.exists()) {
            // If file already exists, ask before replacing it.
         action = JOptionPane.showConfirmDialog(this,
                     "Replace existing file?");
         if (action != JOptionPane.YES_OPTION)
            return;
      }
      try {
            // Create a PrintWriter for writing to the specified
            // file and write the text from the window to that stream.
         PrintWriter out = new PrintWriter(new FileWriter(file));
         String contents = text.getText();
         out.print(contents);
         if (out.checkError())
            throw new IOException("Error while writing to file.");
         out.close();
      }
      catch (IOException e) {
            // Some error has occured while trying to write.
            // Show an error message.
         JOptionPane.showMessageDialog(this,
             "Sorry, an error has occurred:\n" + e.getMessage());
      }
   }


   private void doOpen() {
          // Carry out the Open command by letting the user specify
          // the file to be opened and reading up to 100 lines from 
          // that file.  The text from the file replaces the text
          // in the JTextArea.
      File file;  // The file that the user wants to open.
      JFileChooser fd; // File dialog that lets the user specify the file.
      fd = new JFileChooser(new File("."));
      fd.setDialogTitle("Open File...");
      int action = fd.showOpenDialog(this);
      if (action != JFileChooser.APPROVE_OPTION) {
            // User canceled the dialog, or an error occurred.
          return;
      }
      file = fd.getSelectedFile();
     
   }


   private void doQuit() {
         // Carry out the Quit command by exiting the program.
      System.exit(0);
   }


} // end class TrivialEdit