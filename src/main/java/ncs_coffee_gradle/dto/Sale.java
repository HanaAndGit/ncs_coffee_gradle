package ncs_coffee_gradle.dto;

public class Sale {
	private int no;
	private String product_code;
	private int price;
	private int sale_cnt;
	private int margin_rate;
	
	private int rank;
	private String name;
	private int supply_value;
	private int surtax_value;
	private int selling_price;
	private int margin_value;
	
	public Sale() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Sale(int rank, String product_code, 
			String product_name ,int price, int sale_cnt, 
			int supply_value, int surtax_value, int selling_price, 
			int margin_rate, int margin_value) {
		super();
		this.rank = rank;
		this.product_code = product_code;
		this.name = product_name;
		this.price = price;
		this.sale_cnt = sale_cnt;
		this.supply_value = supply_value;
		this.surtax_value = surtax_value;
		this.selling_price = selling_price;
		this.margin_rate = margin_rate;
		this.margin_value = margin_value;
	}



	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSale_cnt() {
		return sale_cnt;
	}
	public void setSale_cnt(int sale_cnt) {
		this.sale_cnt = sale_cnt;
	}
	public int getMargin_rate() {
		return margin_rate;
	}
	public void setMargin_rate(int margin_rate) {
		this.margin_rate = margin_rate;
	}
	
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getSupply_value() {
		return supply_value;
	}

	public void setSupply_value(int supply_value) {
		this.supply_value = supply_value;
	}

	public int getSurtax_value() {
		return surtax_value;
	}

	public void setSurtax_value(int surtax_value) {
		this.surtax_value = surtax_value;
	}

	public int getSelling_price() {
		return selling_price;
	}

	public void setSelling_price(int selling_price) {
		this.selling_price = selling_price;
	}

	public int getMargin_value() {
		return margin_value;
	}

	public void setMargin_value(int margin_value) {
		this.margin_value = margin_value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + margin_rate;
		result = prime * result + no;
		result = prime * result + price;
		result = prime * result + ((product_code == null) ? 0 : product_code.hashCode());
		result = prime * result + sale_cnt;
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
		Sale other = (Sale) obj;
		if (margin_rate != other.margin_rate)
			return false;
		if (no != other.no)
			return false;
		if (price != other.price)
			return false;
		if (product_code == null) {
			if (other.product_code != null)
				return false;
		} else if (!product_code.equals(other.product_code))
			return false;
		if (sale_cnt != other.sale_cnt)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Sale [no=" + no + ", product_code=" + product_code + ", price=" + price + ", sale_cnt=" + sale_cnt
				+ ", margin_rate=" + margin_rate + "]";
	}
	
	
}
