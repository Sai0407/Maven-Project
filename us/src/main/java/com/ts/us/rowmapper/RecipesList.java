package com.ts.us.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.ts.us.dao.IBranchDAO;
import com.ts.us.daoimpl.BranchDAO;
import com.ts.us.daoimpl.CuisineDAO;
import com.ts.us.daoimpl.FeedbackDAO;
import com.ts.us.dto.Branch;
import com.ts.us.dto.Recipe;
import com.ts.us.dto.Restaurant;
import com.ts.us.exception.UrbanspoonException;
import com.ts.us.jdbctemplate.daoimpl.TBranchDAO;

import sun.reflect.generics.tree.BottomSignature;

public class RecipesList implements RowMapper<Recipe> {

	@Override
	public Recipe mapRow(ResultSet rs, int numRow) throws SQLException {
		try {
			Recipe recipe = new Recipe();
			recipe.setId(rs.getInt(1));
			recipe.setName(rs.getString(2));
			recipe.setVeg(rs.getBoolean(4));
			return recipe;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
