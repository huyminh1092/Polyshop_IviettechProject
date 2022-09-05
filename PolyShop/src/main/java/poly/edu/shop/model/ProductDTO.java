package poly.edu.shop.model;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private Long productId;
	@NotEmpty
	@Length(min  = 5)
	private String name;
	@Positive
	private int quantity;
	@PositiveOrZero
	private double unitPrice;
	
	private String image;
	private MultipartFile imageFile;
	
	private String description;
	@PositiveOrZero
	private double discount;
	private short status;
	private Long category;
	
	private boolean checkEdit = false;
}
