/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlpool;

import java.sql.ResultSet;
import java.sql.SQLException;
import mysqlpool.ConnectionPool.PooledConnection;

public class DBManager {

    private static PooledConnection conn;
    private static ConnectionPool connectionPool;
    private static DBManager inst;

    public void close() {
        try {
            connectionPool.closeConnectionPool();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public DBManager() {
        if (inst != null) {
            return;
        }

        // TODO Auto-generated constructor stub
        String connStr = String.format("jdbc:mysql://%s:%d/%s", "localhost", "3306", "test");
        connectionPool = new ConnectionPool("com.mysql.jdbc.Driver", connStr, "root", "root");
        try {
            connectionPool.createPool();
            inst = this;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static PooledConnection getConnection() {
        if (inst == null) {
            new DBManager();
        }

        try {

            conn = connectionPool.getConnection();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return conn;
    }

    public static void main(String[] args) {

    }

    public static int test() {
        String sql = "....";
        ResultSet rs;
        PooledConnection conn = null;
        try {
            conn = DBManager.getConnection();
            rs = conn.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return 0;
    }
}
