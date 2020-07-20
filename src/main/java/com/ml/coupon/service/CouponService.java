package com.ml.coupon.service;

import com.ml.coupon.dto.CouponDTO;
import com.ml.coupon.dto.CouponResponseDTO;

public interface CouponService {

	public CouponResponseDTO getTheBestValue(CouponDTO couponDTO);

}
