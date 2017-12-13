package com.ts.us.dao;

public interface IServeDAO {

	public boolean addRecipeToBranch(long recipeId, long branchId, float price,String imagePath);
	
}
