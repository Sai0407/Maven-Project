package com.ts.us.jdbctemplate.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ts.us.dao.IRestaurantDAO;
import com.ts.us.daoimpl.BranchDAO;
import com.ts.us.dto.Restaurant;
import com.ts.us.exception.UrbanspoonException;
import com.ts.us.rowmapper.RestaurantsList;

@Component
public class TRestaurantDAO implements IRestaurantDAO{

	@Autowired JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Restaurant> getRestaurants(boolean includeBranches) throws UrbanspoonException {
		RestaurantsList.includeBranches = includeBranches;
		String queries = "select * from restaurant";
		return jdbcTemplate.query(queries,new RestaurantsList());
	}

	@Override
	public Restaurant getRestaurant(int restaurantId, boolean includeBranches) throws UrbanspoonException {
		RestaurantsList.includeBranches = includeBranches;
		String queries = "select * from restaurant where id=?";
		return (Restaurant) jdbcTemplate.query(queries,new Object[]{restaurantId},new RestaurantsList());
	}

	@Override
	public Restaurant insert(Restaurant restaurant) throws UrbanspoonException {
		String queries = "insert into restaurant(govt_registration_id,name,password) values(?,?,?)";
		int x = jdbcTemplate.update(queries, new Object[]{restaurant.getGovtRegistrationtId(),restaurant.getName(),
				restaurant.getPassword()});
		if(x > 0){
			String latestResID = "select max(id) from restaurant";
			restaurant.setId(jdbcTemplate.queryForObject(latestResID, Integer.class));
		}
		return restaurant;
	}

	@Override
	public Restaurant getRestaurant(String govtRegistrationId, boolean includeBranches) throws UrbanspoonException {
		RestaurantsList.includeBranches = includeBranches;
		String queries = "select * from restaurant where govt_registration_id = ?";
		return (Restaurant) jdbcTemplate.queryForObject(queries,new Object[]{govtRegistrationId},new RestaurantsList());
	}

	@Override
	public boolean updateLogoAddress(long restaurantId, String fileName) throws UrbanspoonException {
		String queries = "update restaurant set logo_name =? where id = ?";
		int x = jdbcTemplate.update(queries, new Object[]{fileName,restaurantId});
		if(x > 0){
			return true;
		}else {
			return false;
		}
	}

}
