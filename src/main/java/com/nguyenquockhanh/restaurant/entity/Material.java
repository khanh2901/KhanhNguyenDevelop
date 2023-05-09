package com.nguyenquockhanh.restaurant.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "materials")
public class Material {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "restaurant_id")
	private int restaurantId;

	@Column(name = "restaurant_brand_id")
	private int restaurantBrandId;

	@Column(name = "branch_id")
	private int branchId;

	@Column(name = "prefix_name")
	private String prefixName;

	@Column(name = "normalize_name")
	private String normalizeName;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "avatar_thumb")
	private String avatarThumb;

	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "unit_id")
	private int unitId;

	@Column(name = "wastage_rate")
	private BigDecimal wastageRate;

	@Column(name = "out_stock_alert_quantity")
	private BigDecimal outStockAlertQuantity;

	@Column(name = "retail_price")
	private BigDecimal retailPrice;

	@Column(name = "cost_price")
	private BigDecimal costPrice;

	private int status;

	public int getId() {
		return id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getRestaurantBrandId() {
		return restaurantBrandId;
	}

	public void setRestaurantBrandId(int restaurantBrandId) {
		this.restaurantBrandId = restaurantBrandId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getPrefixName() {
		return prefixName;
	}

	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	public String getNormalizeName() {
		return normalizeName;
	}

	public void setNormalizeName(String normalizeName) {
		this.normalizeName = normalizeName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAvatarThumb() {
		return avatarThumb;
	}

	public void setAvatarThumb(String avatarThumb) {
		this.avatarThumb = avatarThumb;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public BigDecimal getWastageRate() {
		return wastageRate;
	}

	public void setWastageRate(BigDecimal wastageRate) {
		this.wastageRate = wastageRate;
	}

	public BigDecimal getOutStockAlertQuantity() {
		return outStockAlertQuantity;
	}

	public void setOutStockAlertQuantity(BigDecimal outStockAlertQuantity) {
		this.outStockAlertQuantity = outStockAlertQuantity;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

}
