package com.nguyenquockhanh.restaurant.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "foods")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String code;
	
	private  String name;
	

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
	
	private String avatar;
	
	@Column(name = "avatar_thumb")
	private String avatarThumb;
	
	@Column(name = "material_ids")
	private String materialIds;
	
	@Column(name = "category_type")
	private int categoryType;
	
	@Column(name = "wastage_rate")
	private BigDecimal wastageRate;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "vat")
	private BigDecimal vat;
	
	private int status;

	public int getId() {
		return id;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAvatarThumb() {
		return avatarThumb;
	}

	public void setAvatarThumb(String avatarThumb) {
		this.avatarThumb = avatarThumb;
	}

	public String getMaterialIds() {
		return materialIds;
	}

	public void setMaterialIds(String materialIds) {
		this.materialIds = materialIds;
	}

	public int getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(int categoryType) {
		this.categoryType = categoryType;
	}

	public BigDecimal getWastageRate() {
		return wastageRate;
	}

	public void setWastageRate(BigDecimal wastageRate) {
		this.wastageRate = wastageRate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}



	public BigDecimal getVat() {
		return vat;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
