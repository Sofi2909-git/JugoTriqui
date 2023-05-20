package matrizbotones;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

public class MatrizBotones extends JFrame {

    //Variables globales
    private JPanel panelBotones;
    private JButton boton;
    private JButton[][] tablero;
    private final Font font = new Font("Times New Roman", Font.BOLD, 18);
    private final int filas;
    private final int columnas;
    private int turno = 0, jugadorActual;
    private String letra;
    private int contador = 0, tamaño = 0;

    //Constructor
    public MatrizBotones(int rows, int colu) {
        filas = rows;
        columnas = colu;
        initComponents();
    }

    private void initComponents() {
        //Creacion del panel
        setLocationRelativeTo(null);
        panelBotones = new JPanel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout(2, 0));
        panelBotones.setLayout(new GridLayout(filas, columnas, 2, 2));

        //dimensiones de la matriz
        tamaño = filas * columnas;
        tablero = new JButton[filas][columnas];

        //Creacion de tablero
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = new JButton();
                tablero[i][j].setPreferredSize(new Dimension(60, 60));
                tablero[i][j].setFont(font);
                tablero[i][j].setText("-");
                panelBotones.add(tablero[i][j]);
                tablero[i][j].addActionListener((ActionEvent e) -> {
                    boton = (JButton) e.getSource();
                    turnos(boton);
                });
            }
        }

        getContentPane().add(panelBotones, BorderLayout.SOUTH);
        setTitle("TicTac");
        pack();
        setVisible(true);
    }

    /**
     * Turnos de los jugadores: Imapares = 0 / Pares = X
     *
     * @param args
     */
    private void turnos(JButton botonPresionado) {
        if (turno % 2 == 0) {
            letra = "X";
            botonPresionado.setText(letra);
            turno++;
            jugadorActual = 2;
        } else {
            letra = "0";
            botonPresionado.setText(letra);
            turno++;
            jugadorActual = 1;
        }
        botonPresionado.setEnabled(false);
        
         if (validarGanador(filas, columnas, letra)) {
                        JOptionPane.showMessageDialog(this, "¡El jugador " + jugadorActual + " con la letra " + letra + " ha ganado!");
                    } else if (turno == filas * columnas) {
                        JOptionPane.showMessageDialog(this, "¡Empate!");
                    }
    }

    private boolean validarGanador(int fila, int columna, String letra) {
        // Verificar si se completó alguna fila
        boolean filaCompleta = true;
        for (int j = 0; j < columnas; j++) {
            if (!tablero[fila][j].equals(letra)) {
                filaCompleta = false;
                break;
            }
        }
        if (filaCompleta) {
            return true;
        }

        // Verificar si se completó alguna columna
        boolean columnaCompleta = true;
        for (int i = 0; i < filas; i++) {
            if (!tablero[i][columna].equals(letra)) {
                columnaCompleta = false;
                break;
            }
        }
        if (columnaCompleta) {
            return true;
        }

        // Verificar si se completó la diagonal principal
        boolean diagonalPrincipalCompleta = true;
        for (int i = 0; i < filas; i++) {
            if (!tablero[i][i].equals(letra)) {
                diagonalPrincipalCompleta = false;
                break;
            }
        }
        if (diagonalPrincipalCompleta) {
            return true;
        }

        // Verificar si se completó la diagonal secundaria
        boolean diagonalSecundariaCompleta = true;
        for (int i = 0, j = columnas - 1; i < filas; i++, j--) {
            if (!tablero[i][j].equals(letra)) {
                diagonalSecundariaCompleta = false;
                break;
            }
        }
        if (diagonalSecundariaCompleta) {
            return true;
        }

        // Si no se completó ninguna combinación ganadora, retornar falso
        return false;
    }

    private boolean validar() {
        return contador == tamaño;
    }

    public static void main(String args[]) {
        while (true) {
            int filas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese las Filas: \n"));
            int columnas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese las Columnas: \n"));
            if (filas == columnas) {
                MatrizBotones v = new MatrizBotones(filas, columnas);
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el mismo numero de filas y columnas");
            }
        }
    }
}
