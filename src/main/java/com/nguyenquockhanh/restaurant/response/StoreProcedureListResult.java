package com.nguyenquockhanh.restaurant.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StoreProcedureListResult<T>  {

	private List<T> result;

	public StoreProcedureListResult(List<T> result, long totalRecord) {
		this.setResult(result);
	}

	public StoreProcedureListResult(List<T> result) {
		this.setResult(result);
	}

	
	/**
	 * @return the result
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(List<T> result) {
		this.result = result;
	}

	/**
	 * @return the totalRecord
	 */
	
}
