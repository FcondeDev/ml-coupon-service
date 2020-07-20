package com.ml.coupon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ml.coupon.dto.CouponDTO;
import com.ml.coupon.dto.CouponResponseDTO;
import com.ml.coupon.dto.MercadoLibreItemsDTO;
import com.ml.coupon.error.ErrorEnum;
import com.ml.coupon.exception.CustomException;
import com.ml.coupon.exception.NotFoundException;
import com.ml.coupon.helpers.Utils;
import com.ml.coupon.model.CouponFavorites;
import com.ml.coupon.model.SolutiosIds;
import com.ml.coupon.repository.CouponFavoritesRepository;
import com.ml.coupon.service.CouponService;
import com.ml.coupon.service.ItemsClient;

import lombok.extern.java.Log;

@Log
@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private ItemsClient itemsClient;

	@Autowired
	private CouponFavoritesRepository couponFavoritesRepository;

	@Autowired
	private Utils utils;

	private float bestValue;
	private int[] solution;
	private int[] finalSolution;

	@Override
	public CouponResponseDTO getTheBestValue(CouponDTO couponDTO) {
		String favoriteId = utils.generateFavoriteId(couponDTO.getItemIds());

		log.info("Validando si la respuesta ya existe...");
		Optional<CouponFavorites> couponFavorite = couponFavoritesRepository
				.findByRegisteredFavoritesIdAndAmount(favoriteId, couponDTO.getAmount());

		if (couponFavorite.isPresent())
			return existingResponse(couponFavorite.get());

		Map<String, Float> items = createAndValidateItems(couponDTO.getItemIds());

		solution = new int[items.size()];
		finalSolution = new int[items.size()];
		List<String> solutionsIds = calculate(items, couponDTO.getAmount());
		storeResponse(favoriteId, couponDTO.getAmount(), solutionsIds);
		return new CouponResponseDTO(solutionsIds, bestValue);
	}

	private CouponResponseDTO existingResponse(CouponFavorites couponFavorites) {
		log.info("Retornando respuesta almacenada ...");
		List<String> itemIds = new ArrayList<>();
		for (SolutiosIds solutions : couponFavorites.getSolutionsId()) {
			itemIds.add(solutions.getIdName());
		}
		return new CouponResponseDTO(itemIds, bestValue);
	}

	private void storeResponse(String favoriteId, float amount, List<String> solutionsId) {
		List<SolutiosIds> solutions = new ArrayList<>();
		for (String solutionid : solutionsId) {
			solutions.add(new SolutiosIds(solutionid));
		}
		couponFavoritesRepository.save(new CouponFavorites(favoriteId, amount, solutions));

	}

	private List<String> calculate(Map<String, Float> items, float amount) {
		List<String> ids = new ArrayList<>();
		evaluateTestsCases(items, amount, 0);

		for (int i = 0; i < finalSolution.length; i++) {
			if (finalSolution[i] == 1) {
				ids.add(items.keySet().toArray()[i].toString());
			}
		}

		if (ids.isEmpty())
			throw new NotFoundException();

		return ids;
	}

	private Map<String, Float> createAndValidateItems(List<String> ids) {
		List<MercadoLibreItemsDTO> items = itemsClient.getItemsValue(ids.toArray()).getBody();
		Map<String, Float> itemsWithPrice = new HashMap<>();
		for (MercadoLibreItemsDTO mercadoLibreItemsDTO : items) {

			if (mercadoLibreItemsDTO.getCode().equals(HttpStatus.NOT_FOUND.value()))
				throw new CustomException(ErrorEnum.ITEM_NOT_FOUND_ML_API, HttpStatus.BAD_REQUEST,
						ErrorEnum.ITEM_NOT_FOUND_ML_API.description);

			itemsWithPrice.put(mercadoLibreItemsDTO.getBody().getId(), mercadoLibreItemsDTO.getBody().getPrice());
		}

		return itemsWithPrice;
	}

	private void evaluateTestsCases(Map<String, Float> items, Float amount, int n) {
		int i = 0;

		do {

			solution[n] = i;
			if (isValidSolution(solution, items, amount)) {
				if (n == items.size() - 1) {
					getThesolution(solution, items, finalSolution);
				} else {
					evaluateTestsCases(items, amount, n + 1);
				}
			}

			i++;

		} while (solution[n] != 1);
		solution[n] = -1;
	}

	private boolean isValidSolution(int[] solution, Map<String, Float> items, Float amount) {
		float currentAmount = 0.0f;

		for (int i = 0; i < solution.length; i++) {
			if (solution[i] == 1)
				currentAmount += Float.parseFloat(items.values().toArray()[i].toString());
		}

		return currentAmount <= amount;
	}

	private void getThesolution(int[] solution, Map<String, Float> items, int[] finalSolution) {
		int currentValue = 0;
		int newValue = 0;

		for (int i = 0; i < finalSolution.length; i++) {

			if (solution[i] == 1)
				newValue += Float.parseFloat(items.values().toArray()[i].toString());

			if (finalSolution[i] == 1)
				currentValue += Float.parseFloat(items.values().toArray()[i].toString());

		}

		if (newValue > currentValue) {
			bestValue = newValue;
			System.arraycopy(solution, 0, finalSolution, 0, solution.length);

		}

	}

}
