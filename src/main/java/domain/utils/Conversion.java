package domain.utils;

import org.springframework.beans.factory.annotation.Autowired;

import domain.tech.dao.TechDao;

public class Conversion {
	@Autowired
	private TechDao tDao;
	
	public String returnTechIdxByTechName(String technologyCategory) {
		String[] techs = technologyCategory.split(",");

		String newTechnologyCategory = "";
		for (int i = 0; i < techs.length; i++) {
			String tech = tDao.getTechIdxByTechName(techs[i]);
			if (i < techs.length - 1) {
				newTechnologyCategory = newTechnologyCategory + tech + ",";
			} else {
				newTechnologyCategory = newTechnologyCategory + tech;
			}
		}
		return newTechnologyCategory; 
	}

	public String returnTechNameByTechIdx(String techName) {
		String[] techs = techName.split(",");

		String newTechName = "";
		for (int i = 0; i < techs.length; i++) {
			String tech = tDao.getTechNameByTechIdx(techs[i]);
			if (i < techs.length - 1) {
				newTechName = newTechName + tech + ",";
			} else {
				newTechName = newTechName + tech;
			}
		}
		return newTechName; 
	}
	
	
	public String returnTechIdxsByTechNameList(String[] techLists){
		String techIdxList="";
		for(int i = 0; i < techLists.length; i++) {
			String techIdx = returnTechIdxByTechName(techLists[i]);
			if (i < techLists.length - 1) {
				techIdxList = techIdxList + techIdx + "|";
			} else {
				techIdxList = techIdxList + techIdx;
			}
		}
		return techIdxList;
	}
}
