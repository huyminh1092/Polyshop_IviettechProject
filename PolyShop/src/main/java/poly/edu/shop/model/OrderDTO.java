package poly.edu.shop.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements Serializable{
	@Id
	private Long orderId;
	private String name;
	private Date orderDate;
	private String address;
	private short status;
	private boolean statusCheckout;
//	@Id
//	private Serializable group;
	private double tongTien;
	
}
