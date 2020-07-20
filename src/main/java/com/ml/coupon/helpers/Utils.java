package com.ml.coupon.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	public String generateFavoriteId(List<String> ids) {
		StringBuilder response = new StringBuilder();
		List<String> numbersList = new ArrayList<>();
		for (String id : ids) {
			numbersList.add(id.replaceAll("\\D+", ""));
		}

		Collections.sort(numbersList);

		for (String orderedIds : numbersList) {
			response.append(orderedIds);
		}

		return response.toString();

	}

}
