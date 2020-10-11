package pl.coderslab.dao;

import pl.coderslab.model.AddPlan;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {

    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(name,ingredients,description,created,updated,admin_id,preparation_time,preparation) VALUES (?,?,?,?,?,?,?,?);";
    private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?;";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE recipe SET name = ? , ingredients = ?, description = ?, created = ?, updated = ?, admin_id = ?, preparation_time = ?, preparation = ? WHERE id = ?;";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?;";
    private static final String FIND_ALL_RECIPE_QUERY = "SELECT * FROM recipe;";
    private static final String FIND_ALL_USERRECIPE_QUERY = "SELECT * FROM recipe WHERE admin_id=? ORDER BY created DESC;";
    private static final String COUNT_USER_RECIPIES = "SELECT COUNT(*) FROM recipe WHERE admin_id= ?;";
    private static final String ADD_RECIPE_TO_PLAN_QUERY = "INSERT INTO recipe_plan(recipe_id,meal_name,display_order,day_name_id,plan_id) VALUES (?,?,?,?,?);";
    private static final String FIND_RECIPE_IN_PLAN_QUERY = "SELECT * FROM recipe_plan WHERE recipe_id = ?;";
    private static final String SEARCH_RECIPE_IN_ALL_RECIPIES_QUERY = "SELECT * FROM recipe WHERE name LIKE ? ORDER BY created DESC;";

    public Recipe create(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_RECIPE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getIngredients());
            preparedStatement.setString(3, recipe.getDescription());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(recipe.getCreated()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(recipe.getUpdated()));
            preparedStatement.setInt(6, recipe.getAdminId());
            preparedStatement.setInt(7, recipe.getPreparationTime());
            preparedStatement.setString(8, recipe.getPreparation());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                recipe.setId(resultSet.getInt(1));
            }
            return recipe;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public AddPlan add(AddPlan addPlan) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_RECIPE_TO_PLAN_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, addPlan.getRecipeId());
            preparedStatement.setString(2, addPlan.getMealName());
            preparedStatement.setInt(3, addPlan.getDisplayOrder());
            preparedStatement.setInt(4, addPlan.getDayNameId());
            preparedStatement.setInt(5, addPlan.getPlanId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                addPlan.setId(resultSet.getInt(1));
            }
            return addPlan;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Recipe read(int recipeId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_RECIPE_QUERY);
            preparedStatement.setInt(1, recipeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getInt("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                recipe.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                recipe.setAdminId(resultSet.getInt("admin_id"));
                recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                recipe.setPreparation(resultSet.getString("preparation"));
                return recipe;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RECIPE_QUERY);
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getIngredients());
            preparedStatement.setString(3, recipe.getDescription());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(recipe.getCreated()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(recipe.getUpdated()));
            preparedStatement.setInt(6, recipe.getAdminId());
            preparedStatement.setInt(7, recipe.getPreparationTime());
            preparedStatement.setString(8, recipe.getPreparation());
            preparedStatement.setInt(9, recipe.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int recipeId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RECIPE_QUERY);
            preparedStatement.setInt(1, recipeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Recipe> findAll() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPE_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                recipeToAdd.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                recipeToAdd.setPreparationTime(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeToAdd.setAdminId(resultSet.getInt("admin_id"));
                recipeList.add(recipeToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    public List<Recipe> findAllById(int adminId) {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERRECIPE_QUERY);
            preparedStatement.setInt(1, adminId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                recipeToAdd.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                recipeToAdd.setPreparationTime(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeToAdd.setAdminId(resultSet.getInt("admin_id"));
                recipeList.add(recipeToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;

    }

    public int count(int adminId) {
        int countingResult = 0;
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(COUNT_USER_RECIPIES);
            preparedStatement.setInt(1, adminId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                countingResult = resultSet.getInt(1);
                return countingResult;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countingResult;
    }

    public boolean checkRecipeAtPlan(int recipeId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_RECIPE_IN_PLAN_QUERY);
            preparedStatement.setInt(1, recipeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Recipe> findAllBySearchedRecipeName(String search) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_RECIPE_IN_ALL_RECIPIES_QUERY);
            preparedStatement.setString(1, "%" + search + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Recipe> searchedRecipe = new ArrayList<>();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getInt("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setIngredients(resultSet.getString("ingredients"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                recipe.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                recipe.setPreparation(resultSet.getString("preparation"));
                recipe.setAdminId(resultSet.getInt("admin_id"));
                searchedRecipe.add(recipe);
            }
            return searchedRecipe;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
