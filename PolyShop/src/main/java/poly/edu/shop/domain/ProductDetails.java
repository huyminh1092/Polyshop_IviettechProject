package poly.edu.shop.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productdetails")
public class ProductDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="productdetail_id")
	private int id;

	@Column(columnDefinition = "nvarchar(20) not null")
	private String color;
	@Column(columnDefinition = "nvarchar(100) not null")
	private String ram;
	@Column(columnDefinition = "nvarchar(100)")
	private String screen;
	@Column(columnDefinition = "nvarchar(100)")
	private String cpu;
	@Column(columnDefinition = "nvarchar(100)")
	private String operatingSystem;
	@Column(columnDefinition = "nvarchar(100)")
	private String mass;
	@Column(columnDefinition = "nvarchar(100)")
	private String size;
	@Column(columnDefinition = "nvarchar(100)")
	private String orgini;
	
	@OneToOne
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private Product product;
	
	

}
