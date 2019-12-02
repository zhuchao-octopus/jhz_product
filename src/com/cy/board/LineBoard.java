package com.cy.board;

import java.util.List;

public class LineBoard {
	private String line;
	/*private List<ProductBoard> productBoards;*/
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	/*public List<ProductBoard> getProductBoards() {
		return productBoards;
	}
	public void setProductBoards(List<ProductBoard> productBoards) {
		this.productBoards = productBoards;
	}*/
	/*@Override
	public String toString() {
		return "LineBoard [line=" + line + ", productBoards=" + productBoards
				+ "]";
	}*/
	@Override
	public String toString() {
		return "LineBoard [line=" + line + "]";
	}
	
}
