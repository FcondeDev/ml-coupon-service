package com.ml.coupon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ml.coupon.model.CouponFavorites;

public interface CouponFavoritesRepository extends JpaRepository<CouponFavorites, Long> {

	Optional<CouponFavorites> findByRegisteredFavoritesIdAndAmount(String registeredFavoritesId, Float amount);
}
