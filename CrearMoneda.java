package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CrearMoneda {
    public static void main(String[] args) {
        Connection cx = null;
        try {
            cx = DriverManager.getConnection("jdbc:sqlite:billeteraDB.db");
            System.out.println("Base de datos conectada");
            Scanner s = new Scanner(System.in);
            String tipo;
            System.out.println("Ingrese el tipo de moneda (C/F): ");
            tipo = s.nextLine();
            if (tipo.equals("F") || tipo.equals("C")) {
                System.out.println("Ingrese el nombre de la moneda: ");
                String nombre = s.nextLine();
                System.out.println("Ingrese la nomenclatura de la moneda: ");
                String nomenclatura = s.nextLine(); 
                System.out.println("Ingrese el valor en dólares de la moneda: ");
                double valorDolar = s.nextDouble();
                System.out.println("Ingrese la volatilidad de la moneda");
                double volatilidad = s.nextDouble();
                System.out.println("Ingrese el stock disponible de la moneda: ");
                double stock = s.nextDouble();
                System.out.println("Desea confirmar el ingreso de esta moneda a la base de datos? 1 para confirmar");
                int confirmacion = s.nextInt();
                if (confirmacion == 1) { 
                	String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR, VOLATILIDAD ,STOCK) "
                			+ "VALUES ('"+tipo+"','"+nombre+"','"+nomenclatura+"','"+valorDolar+"','"+volatilidad+"','"+stock+"')";
                	Statement st = cx.createStatement();
                	st.executeUpdate(sql);
                	st.close();
                } else {
                    System.out.println("Operación cancelada. No se guardaron los datos.");
                }
            }
            cx.close();
            s.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (cx != null) {
                    cx.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
