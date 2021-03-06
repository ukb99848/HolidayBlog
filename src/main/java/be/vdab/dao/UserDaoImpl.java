package be.vdab.dao;

import be.vdab.entity.User;

import java.sql.*;

public class UserDaoImpl implements UserDao{
    private String url;
    private String user;
    private String password;

    public UserDaoImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void createUser(User nwUser) throws SQLException {
        String insert = "INSERT INTO user (iduser, username, password) " +
                " VALUES (?, ?, ?)";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(insert)
        ) {
            stmt.setInt(1, nwUser.getId());
            stmt.setString(2, nwUser.getUserName());
            stmt.setString(3, nwUser.getPassWord());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException(throwables);
        }

    }

    @Override
    public void updateUser(User corUser) throws SQLException {

    }

    @Override
    public void deleteUser(User delUser) throws SQLException {

    }

    @Override
    public User getUserByName(String srName) throws SQLException {
        String query = "SELECT * FROM user WHERE username=?";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)
        ) {
            stmt.setString(1, srName);
            try (
                    ResultSet rs = stmt.executeQuery()
            ) {
                if (rs.next()) {
                    User person = new User();
                    person.setId(rs.getInt("iduser"));
                    person.setUserName(rs.getString("username"));
                    person.setPassWord(rs.getString("password"));
                    return person;
                } else {
                    System.out.println("User not found with name : " + srName);
                    throw new SQLException("Record not found");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException("Rec not found");
        }
    }

    @Override
    public int detUserExist(String srName) throws SQLException {
        String query = "SELECT COUNT(*) FROM user WHERE username=?";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)
        ) {
            stmt.setString(1, srName);
            try (
                    ResultSet rs = stmt.executeQuery()
            ) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("SQL error l2");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException("SQL error l1");
        }
    }

    @Override
    public int detUserPass(String srName, String srPass) throws SQLException {
        String query = "SELECT COUNT(*) FROM user WHERE username=? AND password=?";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)
        ) {
            stmt.setString(1, srName);
            stmt.setString(2, srPass);
            try (
                    ResultSet rs = stmt.executeQuery()
            ) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("SQL error l2");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException("SQL error l1");
        }
    }

    @Override
    public int detNbrUsers() throws SQLException {
        String query = "SELECT COUNT(*) FROM user ";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)
        ) {
            try (
                    ResultSet rs = stmt.executeQuery()
            ) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("SQL error l2");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException("SQL error l1");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
