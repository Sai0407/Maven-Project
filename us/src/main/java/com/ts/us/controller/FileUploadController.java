package com.ts.us.controller;
import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ts.us.daoimpl.BranchDAO;
import com.ts.us.daoimpl.CuisineDAO;
import com.ts.us.daoimpl.RecipeDAO;
import com.ts.us.daoimpl.RestaurantDAO;
import com.ts.us.dto.Branch;
import com.ts.us.dto.Restaurant;
import com.ts.us.dto.UserRegistrationDTO;
import com.ts.us.exception.UrbanspoonException;
import com.ts.us.helper.UrbanspoonHelper;
import com.ts.us.util.FileUpload;

@Controller
public class FileUploadController {
	@Autowired RestaurantDAO restaurantDAO;
	@Autowired BranchDAO branchDAO;
	@Autowired RecipeDAO recipeDAO;
	
	private static final String IMAGEPATH = "F:\\Maven Project\\us\\src\\main\\webapp\\Images\\";

	/*@PostMapping("/restaurant_registration_form")
	public ModelAndView restaurantRegisters(@RequestParam("name") String name,@RequestParam("govt_registration_id") String userName,
			@RequestParam("password") String password,@RequestParam("confirm_password") String confPassword,
			@RequestParam("registration_logo") CommonsMultipartFile commonsMultipartFile)
					throws UrbanspoonException, FileUploadException {
	
		ModelAndView mv = new ModelAndView("redirect:home");
		Restaurant restaurant = new Restaurant();
		restaurant.setGovtRegistrationtId(userName);
		restaurant.setName(name);
		restaurant.setPassword(password);
		
		restaurant = restaurantDAO.insert(restaurant);
		if (restaurant.getId() != 0) {
			FileUpload.upload(IMAGEPATH+"restaurants",commonsMultipartFile, restaurant.getId() + ".jpg");
			restaurantDAO.updateLogoAddress(restaurant.getId(), restaurant.getId() + ".jpg");
		}
		return mv;
	}*/
	
	
	@PostMapping("/restaurant_registration_form")
	public ModelAndView restaurantRegisters(@ModelAttribute("restaurant") UserRegistrationDTO userRegistrationDTO )
					throws UrbanspoonException, FileUploadException {
	
		ModelAndView mv = new ModelAndView("redirect:home");
		Restaurant restaurant = new Restaurant();
		restaurant.setGovtRegistrationtId(userRegistrationDTO.getGovtRegID());
		restaurant.setName(userRegistrationDTO.getName());
		restaurant.setPassword(userRegistrationDTO.getPassword());
		
		restaurant = restaurantDAO.insert(restaurant);
		if (restaurant.getId() != 0) {
			FileUpload.upload(IMAGEPATH+"restaurants",userRegistrationDTO.getImagePath(), restaurant.getId() + ".jpg");
			restaurantDAO.updateLogoAddress(restaurant.getId(), restaurant.getId() + ".jpg");
		}
		mv.addObject("branch", new Branch());
		return mv;
	}
	
	
	/*@PostMapping("/addNewBranch")
	public ModelAndView addBranch(@RequestParam("location") String location,@RequestParam("city") String city,
			@RequestParam("state") String state,@RequestParam("country") String country,@RequestParam("postal_code") String postal_code,
			@RequestParam("branch_images") CommonsMultipartFile[] commonsMultipartFiles,HttpServletRequest request) throws UrbanspoonException {
		List<FileItem> fileItemsList = UrbanspoonHelper.getFileItems(request);

		Branch branch = new Branch();
		
		branch.setLocation(location);
		branch.setCity(city);
		branch.setState(state);
		branch.setCountry(country);
		branch.setPostalCode(Integer.parseInt(postal_code));
		
			
		System.out.println(branch);
		HttpSession session = request.getSession(false);
		
		branch = branchDAO.insert((long)session.getAttribute("loggedInUserId"), branch);
		if (branch.getId() != 0) {
			int count = 1;
			for(CommonsMultipartFile commonsMultipartFile : commonsMultipartFiles){
				FileUpload.upload(IMAGEPATH+"branches", commonsMultipartFile, branch.getId() + "_" + count + ".jpg");
				branchDAO.addImage(branch.getId(), branch.getId() + "_" + count + ".jpg");
				count++;
			}
			
		}
		ModelAndView mv = new ModelAndView("restaurantHome");
		mv.addObject("cuisineList", new CuisineDAO().getCuisines(false));
		mv.addObject("branchList", new BranchDAO().getBranches((long)session.getAttribute("loggedInUserId") , true, true));
		mv.addObject("recipeList", new RecipeDAO().getRecipes());
		return mv;
	}*/
	
	@PostMapping("/addMultiNewBranch")
	public ModelAndView addBranch(@ModelAttribute("branch") Branch branch,
			@RequestParam("branch_images") CommonsMultipartFile[] commonsMultipartFiles,HttpServletRequest request) throws UrbanspoonException {
		List<FileItem> fileItemsList = UrbanspoonHelper.getFileItems(request);		
			
		System.out.println(branch);
		HttpSession session = request.getSession(false);
		
		branch = branchDAO.insert((long)session.getAttribute("loggedInUserId"), branch);
		if (branch.getId() != 0) {
			int count = 1;
			for(CommonsMultipartFile commonsMultipartFile : commonsMultipartFiles){
				FileUpload.upload(IMAGEPATH+"branches", commonsMultipartFile, branch.getId() + "_" + count + ".jpg");
				branchDAO.addImage(branch.getId(), branch.getId() + "_" + count + ".jpg");
				count++;
			}
			
		}
		ModelAndView mv = new ModelAndView("restaurantHome");
		mv.addObject("cuisineList", new CuisineDAO().getCuisines(false));
		mv.addObject("branchList", new BranchDAO().getBranches((long)session.getAttribute("loggedInUserId") , true, true));
		mv.addObject("recipeList", new RecipeDAO().getRecipes());
		return mv;
	}
	
	@PostMapping("/addRecipeToBranch")
	public ModelAndView addRecipeToBranch(@RequestParam("branch_id") String branch_id,@RequestParam("recipe_id") String recipe_id,
			@RequestParam("price") String price,@RequestParam("recipe_image") CommonsMultipartFile commonsMultipartFile) 
					throws UrbanspoonException {
		
		long branchId = 0;
		long recipeId = 0;
		float prices = 0;
		String imagePath = "";
		
		recipeId = Long.parseLong(recipe_id);
		branchId = Long.parseLong(branch_id);
		prices = Float.parseFloat(price);
		if(commonsMultipartFile != null){
			FileUpload.upload(IMAGEPATH+"recipes", commonsMultipartFile, branchId + "_" + recipeId + ".jpg");
			imagePath = branchId + "_" + recipeId + ".jpg";
		}
		
		recipeDAO.addRecipeToBranch(recipeId, branchId, prices, imagePath);
		ModelAndView mv = new ModelAndView("restaurantHome");
		return mv;
		
	}


}