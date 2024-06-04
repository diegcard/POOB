package src.domain;

import javax.swing.*;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.io.*;

/**
 * The garden is the place where all things and agents are located, where they will also interact with each other.
 *
 * @author Diego Cardenas, Sebastian Cardona
 * @version 1.0.0
 */

public class Garden implements Serializable{
    static public int LENGTH = 40;
    private final Thing[][] garden;
    private boolean actInCell;

    /**
     * Create a new garden
     */
    public Garden() {
        garden = new Thing[LENGTH][LENGTH];
        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                garden[r][c] = null;
            }
        }
        setThing(0, 0, new Water());
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                setThing(LENGTH - i, LENGTH - j, new Water());
            }
        }
        someThings();
    }

    /**
     * Get the length
     *
     * @return LENGTH
     */
    public int getLength() {
        return LENGTH;
    }

    /**
     * Get the thing in the garden.
     *
     * @param r Row position.
     * @param c Column Position.
     * @return The Thing at the specified position, or null if nothing is present.
     */
    public Thing getThing(int r, int c) {
        return garden[r][c];
    }

    /**
     * Set the thing in the garden.
     *
     * @param r The row in the Garden.
     * @param c The column in the Garden.
     * @param e The Thing to set at the specified position.
     */
    public void setThing(int r, int c, Thing e) {
        garden[r][c] = e;
    }

    /**
     * Create some things in the garden.
     */
    public void someThings() {
        //Normal flower
        new Flower(this, 10, 10, "rose");
        new Flower(this, 15, 15, "violet");
        //carnivorous flower
        new Carnivorous(this, 30, 30, "venus");
        new Carnivorous(this, 10, 5, "sundeuos");
        // Sand
        new Sand(this, 0, 39, "tatacoa");
        new Sand(this, 0, 38, "sahara");
        //New Flower Cane
        new Cane(this, 20, 25, "Diego");
        new Cane(this, 8, 30, "Sebastian");
        // New Ant
        new Ant(this, 15, 21, "Michael");
        // new Cell
        new Cell(this,15,20);
        new Cell(this,14,21);
        new Cell(this,15,22);
        //new conway
        //new Conway(this,15,21,"Pepe");
        new Conway(this,1,23,"Mary");

        /*new Cell(this,17,21);
        new Cell(this,17,22);
        new Conway(this,18,21,"Mary");*/
    }

    /**
     * Performs a time step (tic-tac) for the entities within the garden grid.
     */
    public void ticTac() {
        actInCell = false;
        boolean[][] visited = new boolean[LENGTH][LENGTH];
        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                if (!visited[r][c]){
                    Thing thing = garden[r][c];
                    if (thing != null && !(thing instanceof Cell)) {
                        thing.act();
                        visited[thing.getRow()][thing.getColumn()] = true;
                    }else if(thing instanceof Cell && !actInCell){
                        actInCell = true;
                        thing.act();
                        visited[thing.getRow()][thing.getColumn()] = true;
                    }
                }
            }
        }
    }

    public static Garden open00(String nameFile) throws gardenException{
        throw new gardenException(gardenException.OPENERROR);
    }

    /**
     * Get the garden as a string.
     *
     * @return The garden as a string.
     *
     * @param nameFile
     * @return Garden
     * @throws gardenException
     */
    public static Garden open(String nameFile) throws gardenException{
        Garden partidaCargada = null;
        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nameFile));
            partidaCargada = (Garden) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new gardenException(gardenException.OPENERROR);
        }
        return partidaCargada;
    }

    public void save00(String nameFile) throws gardenException{
        throw new gardenException(gardenException.SAVEERROR);
    }

    /**
     * Save the garden to a file.
     * @param nameFile
     * @throws gardenException
     */
    public void save(String nameFile)throws gardenException{
        ObjectOutputStream salida = null;
        try {
            salida = new ObjectOutputStream(new FileOutputStream(nameFile));
            salida.writeObject(this);
            salida.flush();
            System.out.println("Partida guardada con éxito. Ruta: " + new File(nameFile).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new gardenException(gardenException.SAVEERROR);
        } finally {
            if (salida != null) {
                try {
                    salida.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private Thing seleccionador(String type, int row, int col) {
        Thing thing = null;
        try {
            Class<?> clazz = Class.forName("src.domain." + type);
            Constructor<?> constructor;
            if (type.equals("Water")) {
                constructor = clazz.getConstructor();
            } else {
                constructor = clazz.getConstructor(Garden.class, int.class, int.class);
            }
            if (type.equals("Water")) {
                thing = (Thing) constructor.newInstance();
            } else {
                thing = (Thing) constructor.newInstance(this, row, col);
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return thing;
    }

    public void import00(String filename) throws IOException {
        throw new IOException(gardenException.IMPORTERROR);
    }

    /**
     * Import the garden from a file.
     * @param filename
     * @throws gardenException
     */
    public void impor(String filename) throws IOException {
        try {
            for (int r = 0; r < LENGTH; r++) {
                for (int c = 0; c < LENGTH; c++) {
                    garden[r][c] = null;
                }
            }
            FileReader reader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            int lineNumber = 1;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.trim().split(" ");
                if (parts.length != 3) {
                    throw new IOException("Error en la línea " + lineNumber + ": La línea debe contener 3 elementos separados por espacios");
                }
                String type = parts[0];
                int row, col;
                try {
                    row = Integer.parseInt(parts[1]);
                    col = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Error en la línea " + lineNumber + ": Las coordenadas '" + parts[1] + "' y '" + parts[2] + "' deben ser números enteros");
                }
                if (row < 0 || row >= LENGTH || col < 0 || col >= LENGTH) {
                    throw new ArrayIndexOutOfBoundsException("Error en la línea " + lineNumber + ": Las coordenadas '" + row + "' y '" + col + "' están fuera de los límites del jardín");
                }
                Thing thing = seleccionador(type, row, col);
                if (thing == null) {
                    throw new IllegalArgumentException("Error en la línea " + lineNumber + ": El tipo de cosa '" + type + "' no es válido");
                }
                garden[row][col] = thing;
                lineNumber++;
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "El archivo no existe y o esta corrupto", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException | ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error desconocido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void export00(String filename) throws IOException {
        throw new IOException(gardenException.EXPORTERROR);
    }

    /**
     * Export the garden to a file.
     * @param filename
     * @throws IOException
     */
    public void export(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        for (int row = 0; row < LENGTH; row++) {
            for (int col = 0; col < LENGTH; col++) {
                Thing thing = garden[row][col];
                if (thing != null) {
                    String type = thing.getClass().getSimpleName();
                    writer.write(type + " " + row + " " + col + "\n");
                }
            }
        }
        writer.close();
    }
}
