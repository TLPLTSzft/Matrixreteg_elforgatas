package Matrixreteg_elforgatas;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Matrixreteg {
	
	private int m;
	private int n;
	private int[][] matrix;
	private Random random = new Random();
	private Scanner scanner = new Scanner(System.in);
	private int[][] keretMatrix;
	private int valasztas;
	private int matrixErtek;
	private ArrayList<Integer> keretMatrixErtekei = new ArrayList<>();
	
	// -- Mátrixréteg konstruktora
	public Matrixreteg() {
		int valasztas = programInditas();
		mnLetrehozasa();
		matrix = new int[this.m][this.n];
		System.out.printf("Mátrixréteg: %d sor, %d oszlop", this.m, this.n);
		int matrixMin = 1;
		int matrixMax = valasztas == 1 ? 101 : (int) Math.pow(10, 8) + 1;
		int keretekSzama = Math.min(this.m, this.n) / 2;
		keretMatrix = new int[keretekSzama][(this.m) * 2 + (this.n - 2) * 2];
		System.out.printf(", %d keret", keretekSzama);
		int cellaErtek;
		for (int k = 0; k < keretekSzama; k++) {
			matrixErtek = 1;
			for (int j = 0 + k; j < this.n - k; j++) {
				cellaErtek = valasztas == 1 ? matrixErtek : random.nextInt(matrixMin, matrixMax);
				matrix[0 + k][j] = cellaErtek;
				keretMatrix[k][matrixErtek - 1] = cellaErtek;
				matrixErtek++;
			}
			for (int i = 1 + k; i < this.m - 1 - k; i++) {
				cellaErtek = valasztas == 1 ? matrixErtek : random.nextInt(matrixMin, matrixMax);
				matrix[i][this.n - 1 - k] = cellaErtek;
				keretMatrix[k][matrixErtek - 1] = cellaErtek;
				matrixErtek++;
			}
			for (int j = this.n - 1 - k; j > -1 + k; j--) {
				cellaErtek = valasztas == 1 ? matrixErtek : random.nextInt(matrixMin, matrixMax);
				matrix[this.m - 1 - k][j] = cellaErtek;
				keretMatrix[k][matrixErtek - 1] = cellaErtek;
				matrixErtek++;
			}
			for (int i = this.m - 2 - k; i > 0 + k; i--) {
				cellaErtek = valasztas == 1 ? matrixErtek : random.nextInt(matrixMin, matrixMax);
				matrix[i][0 + k] = cellaErtek;
				keretMatrix[k][matrixErtek - 1] = cellaErtek;
				matrixErtek++;
			}
		}
//		keretMatrixKiiratasa(keretekSzama);
	}
	
	// -- Keretek kiiratása
	private void keretMatrixKiiratasa(int keretekSzama) {
		for (int k = 0; k < keretekSzama; k++) {
			System.out.printf("\n%d. keret:", k + 1);
			for (int ints : keretMatrix[k]) {
				System.out.printf(" %s", ints > 0 ? String.format("%s", ints) : "");
			}
		}
	}
	
	// -- 'M' és 'N' adattagok létrehozása
	private void mnLetrehozasa() {
		boolean mnHelyesE = false;
		while (!mnHelyesE) {
			this.m = input("'M'", "sorainak", 2, "300", 300);
			this.n = input("'N'", "oszlopainak", 2, "300", 300);
			if (Math.min(this.m, this.n) % 2 == 0) {
				mnHelyesE = true;
			} else {
				System.out.print("'M' és 'N' értékének teljesíteni kell, a következő feltételt:\n\tmin(m,n) % 2 = 0\n-- ez most nem teljesül próbáld újra!\n\n");
			}
		}
	}
	
	// -- Változó bekérése
	private int input(String inputNev, String szoveg, int inputMin, String inputMaxString, int inputMax) {
		int inputErtek = 0;
		boolean inputhelyesE = false;
		while (!inputhelyesE) {
			System.out.printf("A mátrix %1$s %2$s száma, amelyre teljesül hogy,\n\t%3$d <= %1$s <= %4$s: ", inputNev, szoveg, inputMin, inputMaxString);
			try {
				inputErtek = Integer.parseInt(scanner.nextLine());
				if (inputErtek >= inputMin && inputErtek <= inputMax) {
					System.out.println();
					inputhelyesE = true;
				} else {
					System.out.printf("-- Helytelen %s érték, próbáld újra!\n\n", inputNev);
				}
			} catch (Exception e) {
				System.out.printf("-- Helytelen %s érték, próbáld újra!\n\n", inputNev);
			}
		}
		return inputErtek;
	}
	
	// -- Mátrixréteg elforgatása
	public void matrixRetegElforgatas() {
		int r = input("'R'", "elforgatásának", 1, "10^9", (int) Math.pow(10, 9));
		for (int k = 0; k < keretMatrix.length; k++) {
			keretMatrixErtekei.clear();
			System.out.printf("%d. keret értékei:\n", k + 1);
			for (int i = 0; i < keretMatrix[k].length && keretMatrix[k][i] > 0; i++) {
				keretMatrixErtekei.add(keretMatrix[k][i]);
			}
			for (Integer integer : keretMatrixErtekei) {
				System.out.printf(" %d", integer);
			}
			int cellaErtek;
			System.out.printf("\n\ttényleges elforgatás: %d\n\n", r % keretMatrixErtekei.size());
			int keretMatrixElemSzam = r % keretMatrixErtekei.size();
			for (int j = 0 + k; j < this.n - k; j++) {
				cellaErtek = keretMatrixErtekei.get(keretMatrixElemSzam);
				matrix[0 + k][j] = cellaErtek;
				if (keretMatrixElemSzam + 1 < keretMatrixErtekei.size()) {
					keretMatrixElemSzam++;
				} else {
					keretMatrixElemSzam = 0;
				}
			}
			for (int i = 1 + k; i < this.m - 1 - k; i++) {
				cellaErtek = keretMatrixErtekei.get(keretMatrixElemSzam);
				matrix[i][this.n - 1 - k] = cellaErtek;
				if (keretMatrixElemSzam + 1 < keretMatrixErtekei.size()) {
					keretMatrixElemSzam++;
				} else {
					keretMatrixElemSzam = 0;
				}
			}
			for (int j = this.n - 1 - k; j > -1 + k; j--) {
				cellaErtek = keretMatrixErtekei.get(keretMatrixElemSzam);
				matrix[this.m - 1 - k][j] = cellaErtek;
				if (keretMatrixElemSzam + 1 < keretMatrixErtekei.size()) {
					keretMatrixElemSzam++;
				} else {
					keretMatrixElemSzam = 0;
				}
			}
			for (int i = this.m - 2 - k; i > 0 + k; i--) {
				cellaErtek = keretMatrixErtekei.get(keretMatrixElemSzam);
				matrix[i][0 + k] = cellaErtek;
				if (keretMatrixElemSzam + 1 < keretMatrixErtekei.size()) {
					keretMatrixElemSzam++;
				} else {
					keretMatrixElemSzam = 0;
				}
			}
		}
		System.out.print("Az elforgatott mátrixréteg:");
	}
	
	// -- Program indítás, választás meghatározása
	private int programInditas() {
		System.out.println("\n--- Mátrixréreg elforgatás ---\n");
		boolean valasztasHelyesE = false;
		while (!valasztasHelyesE) {
			System.out.println("1 - A mátrixréteg forgatás vizuális követése");
			System.out.println("2 - Mátrix 'feladat szerinti' paraméterekkel");
			System.out.print("\nVálassz egy lehetőséget '1' vagy '2': ");
			try {
				valasztas = Integer.parseInt(scanner.nextLine());
				if (valasztas == 1 || valasztas == 2) {
					System.out.println();
					valasztasHelyesE = true;
				} else {
					System.out.println("-- Rossz választás, próbáld újra!\n");
				}
			} catch (Exception e) {
				System.out.println("-- Rossz választás, próbáld újra!\n");
			}
		}
		return valasztas;
	}
	
	// -- Mátrix kirajzolása
	@Override
	public String toString() {
		int cellaErtekMeret = valasztas == 2 ? 12 : (keretMatrix[0].length + "").length() + 2;
		String vonal = "─".repeat(cellaErtekMeret / 2);
		String vonal1 = "─".repeat(cellaErtekMeret - cellaErtekMeret / 2);
		String string = "\n┌" + vonal1 + (vonal + "┬" + vonal1).repeat(this.n - 1) + vonal + "┐";
		for (int i = 0; i < this.m; i++) {
			string += "\n|";
			for (int j = 0; j < this.n; j++) {
				String szoveg = (" ".repeat(10) + matrix[i][j]).substring((" ".repeat(10) + matrix[i][j]).length() - cellaErtekMeret + 2);
				string += String.format(" %s |", szoveg);
			}
			if (i + 1 < this.m) {
				string += "\n├" + vonal1 + (vonal + "┼" + vonal1).repeat(this.n - 1) + vonal + "┤";
			}
		}
		string += "\n└" + vonal1 + (vonal + "┴" + vonal1).repeat(this.n - 1) + vonal + "┘";
		return string;
	}
	
}
