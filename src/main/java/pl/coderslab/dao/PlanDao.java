package pl.coderslab.dao;

import pl.coderslab.model.LastPlan;
import pl.coderslab.model.Plan;
import pl.coderslab.model.PlanDetails;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {
    // ZAPYTANIA SQL
    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan(name,description,created,admin_id) VALUES (?,?,?,?);";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?;";
    private static final String FIND_ALL_PLANS_QUERY = "SELECT * FROM plan ORDER BY created DESC;";
    private static final String FIND_ALL_USERPLANS_QUERY = "SELECT * FROM plan WHERE admin_id=? ORDER BY created DESC;";
    private static final String COUNT_USER_PLANS = "SELECT COUNT(*) FROM plan WHERE admin_id=?";
    private static final String READ_PLAN_QUERY = "SELECT * from plan where id = ?;";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ?, created = ? WHERE	id = ?;";
    private static final String LAST_ADDED_PLAN_NAME = "SELECT * FROM plan WHERE admin_id=? ORDER BY created DESC LIMIT 1;";
    private static final String LAST_ADDED_PLAN =
            "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description\n" +
                    "FROM `recipe_plan`\n" +
                    "JOIN  day_name on day_name.id=day_name_id\n" +
                    "JOIN recipe on recipe.id=recipe_id WHERE\n" +
                    "recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?)\n" +
                    "ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String SELECT_AND_JOIN_ADMINS_PLAN_DETAILS_QUERY = "SELECT day_name.name as day_name,recipe_plan.id as recipe_plan_id,day_name_id as day_id,recipe_id as recipes_id, meal_name, recipe.name as recipe_name, recipe.description as recipe_description\n" +
            "FROM `recipe_plan`\n" +
            "    RIGHT OUTER JOIN day_name on day_name.id = day_name_id\n" +
            "        RIGHT JOIN recipe on recipe.id = recipe_id\n" +
            "WHERE recipe_plan.plan_id = ?\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String DAY_NAME_QUERY = "SELECT name as day_name FROM day_name ORDER BY day_name.display_order;";


    /**
     * Get plan by id
     *
     * @param planId
     * @return
     */
    public Plan read(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setAdminId(resultSet.getInt("admin_id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getTimestamp("created").toLocalDateTime());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;

    }

    /**
     * Return all plans
     *
     * @return
     */
    public List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                planToAdd.setAdminId(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }

    public List<Plan> findAll(int adminId) {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERPLANS_QUERY);
            preparedStatement.setInt(1, adminId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                planToAdd.setAdminId(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }

    public List<PlanDetails> readPlanDetails(int planId) {
        List<PlanDetails> planDetailsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AND_JOIN_ADMINS_PLAN_DETAILS_QUERY);
            preparedStatement.setInt(1, planId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PlanDetails planDetailsToAdd = new PlanDetails();
                planDetailsToAdd.setRecipePlanId(resultSet.getInt("recipe_plan_id"));
                planDetailsToAdd.setDayNameId(resultSet.getInt("day_id"));
                planDetailsToAdd.setRecipeId(resultSet.getInt("recipes_id"));
                planDetailsToAdd.setDayName(resultSet.getString("day_name"));
                planDetailsToAdd.setMealName(resultSet.getString("meal_name"));
                planDetailsToAdd.setRecipeName(resultSet.getString("recipe_name"));
                planDetailsToAdd.setRecipeDescription(resultSet.getString("recipe_description"));
                planDetailsList.add(planDetailsToAdd);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planDetailsList;

    }


    /**
     * Create plan
     *
     * @param plan
     * @return
     */
    public Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, plan.getName());
            insertStm.setString(2, plan.getDescription());
            insertStm.setString(3, plan.getCreated().toString());
            insertStm.setInt(4, plan.getAdminId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int count(int adminId) {
        int countingResult = 0;
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(COUNT_USER_PLANS);
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

    /**
     * Remove plan by id
     *
     * @param planId
     */
    public void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY)) {
            statement.setInt(1, planId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Update plan
     *
     * @param plan
     */
    public void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
            statement.setInt(4, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setTimestamp(3, Timestamp.valueOf(plan.getCreated()));

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<LastPlan> lastAddedPlan(int adminId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(LAST_ADDED_PLAN);
            preparedStatement.setInt(1, adminId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<LastPlan> lastAddedPlanList = new ArrayList<>();
            while (resultSet.next()) {
                LastPlan lastPlan = new LastPlan();
                lastPlan.setDayName(resultSet.getString("day_name"));
                lastPlan.setMealName(resultSet.getString("meal_name"));
                lastPlan.setRecipeName(resultSet.getString("recipe_name"));
                lastPlan.setRecipeDescription(resultSet.getString("recipe_description"));
                lastAddedPlanList.add(lastPlan);
            }
            return lastAddedPlanList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Plan lastAddedPlanName(Integer adminId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(LAST_ADDED_PLAN_NAME)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setName(resultSet.getString("name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;
    }

    public List<String> dayNameList () {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DAY_NAME_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> dayNameList = new ArrayList<>();
            while (resultSet.next()) {
                dayNameList.add(resultSet.getString("day_name"));
            }
            return dayNameList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}

