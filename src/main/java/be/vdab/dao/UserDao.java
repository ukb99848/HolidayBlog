package be.vdab.dao;

import be.vdab.entity.User;

import java.sql.SQLException;

public interface UserDao {
    public void createUser(User nwUser) throws SQLException;
    public void updateUser(User corUser) throws SQLException;
    public void deleteUser(User delUser) throws SQLException;
    public User getUserByName(String nwName) throws SQLException;
}