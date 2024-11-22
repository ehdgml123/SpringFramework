package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criterial {

	private int pageNum;
	private int amount;
	
	private String type;
	private String keyword;
	
	public Criterial() {
		this(2,10);
	}
	
	public Criterial(int pageNum, int amount) {
		 this.pageNum = pageNum;
		 this.amount = amount;
	}
	
   public String[] getTypeArr() {
	   
	   return type == null ? new String[] {} : type.split("");
   }
}
