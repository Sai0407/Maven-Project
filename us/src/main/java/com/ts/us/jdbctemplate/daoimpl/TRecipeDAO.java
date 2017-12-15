package com.ts.us.jdbctemplate.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ts.us.dao.IRecipeDAO;
import com.ts.us.daoimpl.FeedbackDAO;
import com.ts.us.dto.Recipe;
import com.ts.us.exception.UrbanspoonException;
import com.ts.us.rowmapper.RecipesList;

@Component
public class TRecipeDAO implements IRecipeDAO {

	@Autowired JdbcTemplate jdbcTemplate;
	
	@Override
	public Recipe insert(int cuisineId, Recipe recipe) throws UrbanspoonException {
		String queries = "insert into recipe(name,description,cuisine_id, is_veg) values(?,?,?,?)";
		int x = jdbcTemplate.update(queries, new Object[]{recipe.getName(),recipe.getDescription(),
				cuisineId,recipe.isVeg()});
		if(x > 0){
			String latestResID = "select max(id) from restaurant";
			recipe.setId(jdbcTemplate.queryForObject(latestResID, Integer.class));
		}
		return recipe;
	}

	@Override
	public List<Recipe> getRecipes() throws UrbanspoonException {
		String queries = "select * from recipe";
		return jdbcTemplate.query(queries, new RecipesList());
	}

	@Override
	public List<Recipe> getRecipes(int cuisineId) throws UrbanspoonException {
		String queries = "select r.* from recipe r, cuisine c where r.cuisine_id = c.id and c.id = ?";
		return jdbcTemplate.query(queries,new Object[]{cuisineId}, new RecipesList());
	}

	@Override
	public Recipe getRecipe(int recipeId) throws UrbanspoonException {
		String queries = "select * from recipe where id = ?";
		return jdbcTemplate.queryForObject(queries,new Object[]{recipeId}, new RecipesList());
	}

	@Override
	public List<Recipe> getRecipes(int cuisineId, int branchId) throws UrbanspoonException {
		String queries = "select r.*,price,image_name from recipe r, cuisine c, serve s where r.cuisine_id = c.id  and r.id = s.recipe_id and c.id = ? and s.branch_id = ?";
		return jdbcTemplate.query(queries,new Object[]{cuisineId,branchId}, new RowMapper<Recipe>(){

			@Override
			public Recipe mapRow(ResultSet rs, int numRows) throws SQLException {
				Recipe recipe = new Recipe();
				recipe.setId(rs.getInt(1));
				recipe.setName(rs.getString(2));
				recipe.setDescription(rs.getString(3));
				recipe.setVeg(rs.getBoolean(5));
				recipe.setPrice(rs.getFloat(6));
				recipe.setImage(rs.getString(7));
				try {
					recipe.setFeedbackList(new FeedbackDAO().getRecipeFeedbacks(recipe.getId(),branchId));
				} catch (UrbanspoonException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return recipe;
			}
			
		});
	}

	@Override
	public boolean addRecipeToBranch(long recipeId, long branchId, float price, String imagePath)
			throws UrbanspoonException {
		String queries = "insert into serve values(?,?,?,?)";
		int x = jdbcTemplate.update(queries, new Object[]{branchId,recipeId,price,imagePath});
		if(x > 0){
			return true;
		}else {
			return false;
		}
	}

}
