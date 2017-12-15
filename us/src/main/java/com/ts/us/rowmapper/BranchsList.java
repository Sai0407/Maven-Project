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
import com.ts.us.dto.Restaurant;
import com.ts.us.exception.UrbanspoonException;
import com.ts.us.jdbctemplate.daoimpl.TBranchDAO;

import sun.reflect.generics.tree.BottomSignature;

public class BranchsList implements RowMapper<Branch> {

	public static boolean includeCuisines = false;
	public static boolean includeFeedbacks = false;
	public static boolean isOnlyString = false;

	@Override
	public Branch mapRow(ResultSet rs, int numRow) throws SQLException {
		try {
			Branch branch = new Branch();
			branch.setId(rs.getInt(1));
			branch.setLocation(rs.getString(2));
			branch.setCity(rs.getString(3));
			branch.setState(rs.getString(4));
			branch.setCountry(rs.getString(5));
			branch.setPostalCode(rs.getInt(6));
			branch.setImagesList(new TBranchDAO().getBranchImages(rs.getInt(1)));
			if (includeCuisines) {
				branch.setCuisinesList(new CuisineDAO().getCuisines(branch.getId(), true));
			}
			if (includeFeedbacks) {
				branch.setFeedbackList(new FeedbackDAO().getBranchFeedbacks(branch.getId()));
			}
			return branch;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
