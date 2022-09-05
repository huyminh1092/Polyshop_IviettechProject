package poly.edu.shop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.edu.shop.domain.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SellingDTO implements Serializable{
	@Id
	private Product pd;
	private Long soLuong;
}
