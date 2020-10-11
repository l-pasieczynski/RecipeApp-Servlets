package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name, last_name , email, password, superadmin, enable) VALUES (?,?,?,?,?,?);";
    private static final String CHECK_ADMIN_QUERY = "SELECT * from admins where email = ?;";
    private static final String READ_ADMIN_QUERY = "SELECT * from admins where id = ?;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name = ? , last_name = ?, email = ?, password = ?, superadmin = ?, enable = ? WHERE id = ?;";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins where id = ?;";
    private static final String FIND_ALL_ADMIN_QUERY = "SELECT * FROM admins;";


    public Admin create(Admin admin) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ADMIN_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, admin.getFirstName());
            preparedStatement.setString(2, admin.getLastName());
            preparedStatement.setString(3, admin.getEmail());
            preparedStatement.setString(4, admin.getPassword());
            preparedStatement.setInt(5, admin.getSuperAdmin());
            preparedStatement.setInt(6, admin.getEnable());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                admin.setId(resultSet.getInt(1));
            }
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Admin readById(int id) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_ADMIN_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setFirstName(resultSet.getString("first_Name"));
                admin.setLastName(resultSet.getString("last_Name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setSuperAdmin(resultSet.getInt("superadmin"));
                admin.setEnable(resultSet.getInt("enable"));
                return admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Admin readByMail(String email) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ADMIN_QUERY);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setFirstName(resultSet.getString("first_Name"));
                admin.setLastName(resultSet.getString("last_Name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setSuperAdmin(resultSet.getInt("superadmin"));
                admin.setEnable(resultSet.getInt("enable"));
                return admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Admin admin) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADMIN_QUERY);
            preparedStatement.setInt(7, admin.getId());
            preparedStatement.setString(1, admin.getFirstName());
            preparedStatement.setString(2, admin.getLastName());
            preparedStatement.setString(3, admin.getEmail());
            preparedStatement.setString(4, admin.getPassword());
            preparedStatement.setInt(5, admin.getSuperAdmin());
            preparedStatement.setInt(6, admin.getEnable());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int recipeId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADMIN_QUERY);
            preparedStatement.setInt(1, recipeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Admin> findAll() {
        List<Admin> adminsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMIN_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Admin adminsToAdd = new Admin();
                adminsToAdd.setId(resultSet.getInt("id"));
                adminsToAdd.setFirstName(resultSet.getString("first_Name"));
                adminsToAdd.setLastName(resultSet.getString("last_Name"));
                adminsToAdd.setEmail(resultSet.getString("email"));
                adminsToAdd.setPassword(resultSet.getString("password"));
                adminsToAdd.setSuperAdmin(resultSet.getInt("superadmin"));
                adminsToAdd.setEnable(resultSet.getInt("enable"));
                adminsList.add(adminsToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminsList;

    }

    public boolean checkEmail(String email) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ADMIN_QUERY);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean checkPassword(String email, String plain) {
        Admin admin = new AdminDao().readByMail(email);
        String password = admin.getPassword();
        return BCrypt.checkpw(plain, password);
    }

}
