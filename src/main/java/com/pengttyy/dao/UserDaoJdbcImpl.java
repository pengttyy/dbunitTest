package com.pengttyy.dao;

import java.sql.*;

public class UserDaoJdbcImpl implements UserDao {

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }


    private long getLastIdentity() throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("CALL IDENTITY()");
        ResultSet rs = null;
        try {
            rs = pstmt.executeQuery();
            rs.next();
            long id = rs.getLong(1);
            return id;
        } finally {
            close(rs, pstmt);
        }
    }

    public Users getUserById(final long id) {
        return (Users) wrapSqlException(new DaoMethod() {
            public Object doIt() throws SQLException {
                return _getUserById(id);
            }
        });
    }

    public Users _getUserById(long id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        ResultSet rs = null;
        Users user = null;
        try {
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new Users();
                fill(user, rs);
            }
        } finally {
            close(rs, pstmt);
        }
        return user;
    }

    private void fill(Users user, ResultSet rs) throws SQLException {
        user.setUsername(rs.getString("username"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setId(rs.getLong("id"));
    }

    private void close(ResultSet rs, Statement pstmt) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(pstmt);
    }

    private void close(Statement stmt) {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private interface DaoMethod {
        Object doIt() throws SQLException;
    }

    private Object wrapSqlException(DaoMethod method) {
        try {
            return method.doIt();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
