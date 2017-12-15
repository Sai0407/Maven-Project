package com.ts.us.jdbctemplate.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ts.us.dao.IBranchDAO;
import com.ts.us.dto.Branch;
import com.ts.us.dto.Restaurant;
import com.ts.us.exception.UrbanspoonException;
import com.ts.us.rowmapper.BranchsList;
import com.ts.us.rowmapper.RestaurantsList;

@Component
public class TBranchDAO implements IBranchDAO{

	@Autowired JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean addImage(long branchId, String fileName) throws UrbanspoonException {
		String queries = "insert into branch_images values(?,?)";
		int x = jdbcTemplate.update(queries, new Object[]{branchId,fileName});
		if(x > 0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Branch insert(long restaurantId, Branch branch) throws UrbanspoonException {
		String queries = "insert into branch(location,city,state,country,postal_code,restaurant_id) values(?,?,?,?,?,?)";
		int x = jdbcTemplate.update(queries, new Object[]{branch.getLocation(),branch.getCity(),
				branch.getState(),branch.getCountry(),branch.getPostalCode(),restaurantId});
		if(x > 0){
			String latestResID = "select max(id) from branch";
			branch.setId(jdbcTemplate.queryForObject(latestResID, Integer.class));
		}
		return branch;
	}

	@Override
	public List<Branch> getBranches(long restaurantId, boolean includeCuisines, boolean includeFeedbacks)
			throws UrbanspoonException {
		BranchsList.includeCuisines = includeCuisines;
		BranchsList.includeFeedbacks = includeFeedbacks;
		String queries = "select * from branch where restaurant_id = ?";
		return (List<Branch>) jdbcTemplate.query(queries,new Object[]{restaurantId},new BranchsList());
		
	}

	@Override
	public Branch getBranch(int branchId, boolean includeCuisines) throws UrbanspoonException {
		BranchsList.includeCuisines = includeCuisines;
		String queries = "select * from branch where id = ?";
		return (Branch) jdbcTemplate.queryForObject(queries,new Object[]{branchId},new BranchsList());
	}

	@Override
	public List<String> getBranchImages(int branchId) throws UrbanspoonException {
		String images = "";
		String queries = "select image_name from branch_images where branch_id = ?";
		return jdbcTemplate.query(queries,new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int numRows) throws SQLException {
				return new String(rs.getString(1));
			}
		});
	}

}
