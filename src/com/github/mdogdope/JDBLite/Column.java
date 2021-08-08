package com.github.mdogdope.JDBLite;

public class Column {
	private String name = new String();
	private String type = new String();
	private String DV = new String();
	private String FK = new String();
	private boolean PK = false;
	private boolean AI = false;
	private boolean NN = false;
	private boolean UQ = false;

	public Column(String name, String type) {
		this.name = name;
		this.type = type.toUpperCase();
	}

	public void foreignKey(String tName) {
		this.FK = tName;
	}

	public void defaultValue(String value) {
		this.DV = value;
	}

	public boolean notNull() {
		this.NN = !this.NN;
		return this.NN;
	}

	public boolean autoIncrement() {
		this.AI = !this.AI;
		return this.AI;
	}

	public boolean primaryKey() {
		this.PK = !this.PK;
		return this.PK;
	}

	public boolean unique() {
		this.UQ = !this.UQ;
		return this.UQ;
	}

	public String getText() {
		StringBuilder ret = new StringBuilder();
		ret.append(this.name + " " + this.type);
		ret.append(PK ? " PRIMARY KEY" : "");
		ret.append(NN ? " NOT NULL" : "");
		ret.append(AI ? " AUTO_INCREMENT" : "");
		ret.append(UQ ? " UNIQUE" : "");
		
		if(!this.DV.isBlank()) {
			boolean number = false;
			boolean datetime = false;
			String search = new String();
			
			if(this.type.indexOf(")") > 0) {
				search = this.type.substring(0, this.type.indexOf("("));
			}else {
				search = this.type;
			}
			
			switch(search) {
			case "BIT":
				number = true;
				break;
			case "TINYINT":
				number = true;
				break;
			case "BOOL":
				number = true;
				break;
			case "BOOLEAN":
				number = true;
				break;
			case "SMALLINT":
				number = true;
				break;
			case "MEDIUMINT":
				number = true;
				break;
			case "INT":
				number = true;
				break;
			case "INTEGER":
				number = true;
				break;
			case "BIGINT":
				number = true;
				break;
			case "FLOAT":
				number = true;
				break;
			case "DOUBLE":
				number = true;
				break;
			case "DECIMAL":
				number = true;
				break;
			case "DEC":
				number = true;
				break;
				
			case "DATE":
				datetime = true;
				break;
			case "DATETIME":
				datetime = true;
				break;
			case "TIMESTAMP":
				datetime = true;
				break;
			case "TIME":
				datetime = true;
				break;
			case "YEAR":
				datetime = true;
				break;
			}
			
			if(number || datetime) {
				ret.append(" DEFAULT " + this.DV);
			}else {
				ret.append(" DEFAULT \"" + this.DV + "\"");
			}
		}else if(!this.FK.isBlank() 
				&& !this.NN && !this.AI 
				&& !this.UQ && !this.PK) {
			ret.append(" FOREIGN KEY REFERENCES " + this.FK);
		}
		
		return ret.toString();
	}
}
