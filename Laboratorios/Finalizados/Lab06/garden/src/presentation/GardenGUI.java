package src.presentation;

import src.domain.*;
import java.awt.Color;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GardenGUI extends JFrame {
    public static final int SIDE = 15;
    public static final int SIZE = Garden.LENGTH + 1;

    private JButton buttonTicTac;
    private JPanel panelControl;
    private PhotoGarden photo;
    private Garden garden;


    //Panel menu
    private JMenuItem optionOpen;
    private JMenuItem optionSave;
    private JMenuItem optionImport;
    private JMenuItem optionExport;
    private JMenuItem optionNew;
    private JMenuItem optionExit;

    private GardenGUI() {
        garden = new Garden();
        prepareElements();
        prepareActions();
    }

    private JMenuBar prepareElemetsMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        optionOpen = new JMenuItem("Open");
        optionSave = new JMenuItem("Save");
        optionImport = new JMenuItem("Import");
        optionExport = new JMenuItem("Export");
        optionNew = new JMenuItem("New");
        optionExit = new JMenuItem("Exit");
        menuFile.add(optionOpen);
        menuFile.add(optionSave);
        menuFile.add(optionImport);
        menuFile.add(optionExport);
        menuFile.add(optionNew);
        menuFile.add(new JSeparator());
        menuFile.add(optionExit);
        menuBar.add(menuFile);
        return menuBar;
    }

    private void prepareActionsMenu(){
        optionOpen.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    optionOpenAction();
                }
            });
        optionSave.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    optionSaveAction();
                }
            });
        optionImport.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    optionImportAction();
                }
            });
        optionExport.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    optionExportAction();
                }
            });
        optionNew.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    optionNewAction();
                }
            });
        optionExit.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    optionExitAction();
                }
            });
    }

    private void optionImportAction() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                garden.impor(file.getAbsolutePath());
                photo.repaint();
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(this, "Error al importar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void optionExportAction() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                garden.export(file.getAbsolutePath()+".txt");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al exportar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void optionNewAction() {
        garden = new Garden();
        photo.repaint();
    }

    private void optionSaveAction() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Garden files", "dat");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(null, "Save file: " + fileChooser.getSelectedFile().getName());
                garden.save(fileChooser.getSelectedFile().getAbsolutePath()+".garden");
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error save file");
        }
    }



    private void optionExitAction() {
        int yesNo = JOptionPane.showOptionDialog(null, "Are you sure you want exit?", "Warning",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, "No");
        if (yesNo == JOptionPane.YES_OPTION) {
            setVisible(false);
            System.exit(0);
        }
    }

    private void optionOpenAction() {
        try{
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Garden files", "dat");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(null, "Open file: " + fileChooser.getSelectedFile().getName());
                garden = Garden.open(fileChooser.getSelectedFile().getAbsolutePath());
                photo.repaint();
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error open file");
        }
    }

    private void prepareElements() {
        setTitle("Garden");
        photo = new PhotoGarden(this);
        buttonTicTac = new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(photo, BorderLayout.NORTH);
        add(buttonTicTac, BorderLayout.SOUTH);
        setSize(new Dimension(SIDE * SIZE, SIDE * SIZE + 50));
        setJMenuBar(prepareElemetsMenu());
        setResizable(false);
        photo.repaint();
    }



    private void prepareActions() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buttonTicTac.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        buttonTicTacAction();
                    }
                });
        prepareActionsMenu();
    }

    private void buttonTicTacAction() {
        garden.ticTac();
        photo.repaint();
    }

    public Garden getGarden() {
        return garden;
    }

    public static void main(String[] args) {
        GardenGUI cg = new GardenGUI();
        cg.setVisible(true);
    }
}

class PhotoGarden extends JPanel {
    private GardenGUI gui;

    public PhotoGarden(GardenGUI gui) {
        this.gui = gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.SIDE * gui.SIZE, gui.SIDE * gui.SIZE));
    }


    public void paintComponent(Graphics g) {
        Garden garden = gui.getGarden();
        super.paintComponent(g);
        int[][] deltas = {{gui.SIDE / 3, 0}, {0, gui.SIDE / 6}, {2 * gui.SIDE / 3, gui.SIDE / 6}, {0, 3 * gui.SIDE / 6}, {2 * gui.SIDE / 3, 3 * gui.SIDE / 6}, {gui.SIDE / 3, 2 * gui.SIDE / 3}};
        for (int f = 0; f <= garden.getLength(); f++) {
            g.drawLine(f * gui.SIDE, 0, f * gui.SIDE, garden.getLength() * gui.SIDE);
        }
        for (int c = 0; c <= garden.getLength(); c++) {
            g.drawLine(0, c * gui.SIDE, garden.getLength() * gui.SIDE, c * gui.SIDE);
        }
        for (int f = 0; f < garden.getLength(); f++) {
            for (int c = 0; c < garden.getLength(); c++) {
                if (garden.getThing(f, c) != null) {
                    g.setColor(garden.getThing(f, c).getColor());
                    if (garden.getThing(f, c).shape() == Thing.ANT){
                        g.drawOval(gui.SIDE*c+1,gui.SIDE*f+5,gui.SIDE-12,gui.SIDE-12);
                        g.drawOval(gui.SIDE*c+gui.SIDE-15,gui.SIDE*f+gui.SIDE-10,gui.SIDE-5,gui.SIDE-12);
                        if (garden.getThing(f, c).is()){
                            g.fillOval(gui.SIDE*c+1,gui.SIDE*f+5,gui.SIDE-12,gui.SIDE-12);
                            g.fillOval(gui.SIDE*c+gui.SIDE-15,gui.SIDE*f+gui.SIDE-10,gui.SIDE-5,gui.SIDE-12);
                        }    
                    }else if(garden.getThing(f, c).shape() == Thing.FLOWER) {
                        g.setColor(garden.getThing(f, c).getColor());
                        for (int i = 0; i < deltas.length; i++) {
                            g.drawOval(gui.SIDE * c + deltas[i][0], gui.SIDE * f + deltas[i][1], gui.SIDE / 3 - 1, gui.SIDE / 3 - 1);
                        }
                        g.setColor(Color.YELLOW);
                        g.drawOval(gui.SIDE * c + gui.SIDE / 3, gui.SIDE * f + gui.SIDE / 3, gui.SIDE / 3, gui.SIDE / 3);
                        if (garden.getThing(f, c).is()) {
                            g.setColor(garden.getThing(f, c).getColor());
                            for (int i = 0; i < deltas.length; i++) {
                                g.fillOval(gui.SIDE * c + deltas[i][0], gui.SIDE * f + deltas[i][1], gui.SIDE / 3 - 1, gui.SIDE / 3 - 1);
                            }
                            g.setColor(Color.YELLOW);
                            g.fillOval(gui.SIDE * c + gui.SIDE / 3, gui.SIDE * f + gui.SIDE / 3, gui.SIDE / 3, gui.SIDE / 3);
                        }
                    } else if (garden.getThing(f, c).shape() == Thing.SQUARE) {
                        g.drawRoundRect(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2, 2, 2);
                        if (garden.getThing(f, c).is()) {
                            g.fillRoundRect(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2, 2, 2);
                        }
                    } else {
                        g.drawOval(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2);
                        if (garden.getThing(f, c).is()) {
                            g.fillOval(gui.SIDE * c + 1, gui.SIDE * f + 1, gui.SIDE - 2, gui.SIDE - 2);
                        }
                    }
                }
            }
        }
    }
}