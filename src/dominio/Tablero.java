package dominio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Tablero {
    private static final int DIMENSION = 30;
    private int[][] EstadoActual = new int[DIMENSION][DIMENSION];
    private int[][] EstadoSiguiente = new int[DIMENSION][DIMENSION];
    private String NameFichero= "Matriz.txt";
    private static char VIVO='1';
    private static char MUERTO='';	

    public void leerEstadoActual(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (int fila = 0; fila< DIMENSION; fila++) {
                String line = br.readLine();
                for (int col = 0; col < DIMENSION; col++) {
                    EstadoActual[fila][col] = Character.getNumericValue(line.charAt(col));
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void GenerarEstadoActualPorMontecarlo() {
        Random rand = new Random();
        for (int fila = 0; fila < DIMENSION; fila++) {
            for (int col = 0; col < DIMENSION; col++) {
                EstadoActual[fila][col] = rand.nextDouble() < 0.5 ? 1 : 0;
            }
        }
    }

    private int contarVecinas(int fila, int col) {
        int contar = 0;
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < DIMENSION && j >= 0 && j < DIMENSION && !(i == fila && j == col)) {
                    contar += EstadoActual[i][j];
                }
            }
        }
        return contar;
    }

    public void transitarAlEstadoSiguiente() {
        for (int fila = 0; fila < DIMENSION; fila++) {
            for (int col = 0; col < DIMENSION; col++) {
                int vecinas = contarVecinas(fila, col);
                if (EstadoActual[fila][col] == 1 && (vecinas < 2 || vecinas > 3)) {
                   EstadoSiguiente[fila][col] = 0;
                } else if (EstadoActual[fila][col] == 0 && vecinas == 3) {
                    EstadoSiguiente[fila][col] = 1;
                } else {
                    EstadoSiguiente[fila][col] = EstadoActual[fila][col];
                }
            }
        }
        int[][] temp = EstadoActual;
        EstadoActual = EstadoSiguiente;
        EstadoSiguiente = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int fila = 0; fila < DIMENSION; fila++) {
            for (int col = 0; col < DIMENSION; col++) {
                sb.append(EstadoActual[fila][col]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
