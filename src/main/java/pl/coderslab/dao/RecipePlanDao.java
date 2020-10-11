package pl.coderslab.dao;

import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecipePlanDao {

    private static final String DELETE_RECIPE_PLAN_QUERY = "DELETE FROM recipe_plan where id = ?;";

    public void recipePlanDelete(int id) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RECIPE_PLAN_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
