package com.ts.us.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.ts.us.exception.UrbanspoonException;

public class ServeDAO {

	public boolean addRecipeToBranch(long recipeId, long branchId, float price,String imagePath) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			
			connection = DAOUtility.getConnection();
			preparedStatement = connection
					.prepareStatement("insert into serve(branch_id,recipe_id,price,image_name) values(?,?,?,?)");
			preparedStatement.setLong(1, branchId);
			preparedStatement.setLong(2, recipeId);
			preparedStatement.setDouble(3, price);
			preparedStatement.setString(4, imagePath);
			int isUpdated = 0;
			isUpdated = preparedStatement.executeUpdate();
			if(isUpdated > 0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				DAOUtility.close(preparedStatement, connection);
			} catch (UrbanspoonException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
