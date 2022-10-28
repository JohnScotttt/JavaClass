package stu;

import java.sql.*;

public class StuGroup {
    Connection connect = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String table;

    public void load(String database, String user, String password, String table) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not loading Driver");
        }
        try {
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/" + database + "?" + "user=" + user + "&password=" + password);
            if (connect != null) {
                System.out.println("Connect successfully!");
                this.table = table;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connect.close();
            System.out.println("Shutdown connection successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStu(String name, String addr, String sex, int age, int math, int eng) {
        try {
            String sql = "insert into " + table + " values(?,?,?,?,?,?)";
            pstmt = connect.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, addr);
            pstmt.setString(3, sex);
            pstmt.setInt(4, age);
            pstmt.setInt(5, math);
            pstmt.setInt(6, eng);
            int rows = pstmt.executeUpdate();
            if (rows != 0) {
                System.out.println("Add student successfully.");
            } else {
                System.out.println("Failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStu(String name) {
        try {
            String sql = "delete from " + table + " where name = ?";
            pstmt = connect.prepareStatement(sql);
            pstmt.setString(1, name);
            int rows = pstmt.executeUpdate();
            if (rows != 0) {
                System.out.println("Delete student successfully.");
            } else {
                System.out.println("No such student.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printitem() {
        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery("select * from " + table);
            while (rs.next()) {
                System.out.print(rs.getString(1) + " ");
                System.out.print(rs.getString(2) + " ");
                System.out.print(rs.getString(3) + " ");
                System.out.print(rs.getInt(4) + " ");
                System.out.print(rs.getInt(5) + " ");
                System.out.println(rs.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
