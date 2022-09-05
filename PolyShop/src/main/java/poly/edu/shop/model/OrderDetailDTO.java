package poly.edu.shop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.edu.shop.domain.Order;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderDetailDTO implements Serializable{
	@Id
	private Order group;
	private double tongTien;
}
