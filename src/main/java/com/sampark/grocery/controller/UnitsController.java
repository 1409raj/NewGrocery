package com.sampark.grocery.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.UnitsEntity;
import com.sampark.grocery.service.UnitsService;

@Controller
@RequestMapping(value = "/units")
public class UnitsController {
	
	@Autowired
	UnitsService  unitsService;
	
	@RequestMapping(value = "/getunitslist", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Domain<List<UnitsEntity>>> getUnitsList() {
	
		Domain<List<UnitsEntity>> entity = unitsService.getUnitsList();
		return new ResponseEntity<Domain<List<UnitsEntity>>>(entity,HttpStatus.OK);
	}
	
	
}
