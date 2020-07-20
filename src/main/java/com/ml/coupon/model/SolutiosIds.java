package com.ml.coupon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "solutionsId")
public class SolutiosIds implements Serializable {
	private static final long serialVersionUID = -5924139396553737756L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "id_coupon_favorites")
	private Long idCouponFavorites;
	private String idName;
	
	public SolutiosIds(String idName) {
		this.idName = idName;
	}
	
	

}
