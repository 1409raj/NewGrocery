package com.sampark.grocery.service;

import java.util.List;
import com.sampark.grocery.model.Domain;
import com.sampark.grocery.model.UnitsEntity;

public interface UnitsService {
	public Domain<List<UnitsEntity>> getUnitsList();

}
