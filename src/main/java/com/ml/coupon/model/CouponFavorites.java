package com.ml.coupon.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "registeredFavoritesCoupon")
public class CouponFavorites implements Serializable {
	private static final long serialVersionUID = 7514376783888660520L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String registeredFavoritesId;
	private Float amount;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_coupon_favorites")
	private List<SolutiosIds> solutionsId;

	public CouponFavorites(String registeredFavoritesId, Float amount, List<SolutiosIds> solutionsId) {
		this.registeredFavoritesId = registeredFavoritesId;
		this.amount = amount;
		this.solutionsId = solutionsId;
	}

}
