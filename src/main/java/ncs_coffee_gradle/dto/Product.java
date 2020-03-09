package ncs_coffee_gradle.dto;

public class Product {
	private String product_code;
	private String product_name;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String code, String name) {
		super();
		this.product_code = code;
		this.product_name = name;
	}
	public String getCode() {
		return product_code;
	}
	public void setCode(String code) {
		this.product_code = code;
	}
	public String getName() {
		return product_name;
	}
	public void setName(String name) {
		this.product_name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product_code == null) ? 0 : product_code.hashCode());
		result = prime * result + ((product_name == null) ? 0 : product_name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (product_code == null) {
			if (other.product_code != null)
				return false;
		} else if (!product_code.equals(other.product_code))
			return false;
		if (product_name == null) {
			if (other.product_name != null)
				return false;
		} else if (!product_name.equals(other.product_name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Product [code=" + product_code + ", name=" + product_name + "]";
	}
	
	
	
}
