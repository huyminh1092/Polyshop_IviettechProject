package poly.edu.shop.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Comments")
public class Comments implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 1000, nullable = false)
	private String content;
	@Column( nullable = false)
	private int star;
	@Column( nullable = false)
	private Date date;
	@Column( nullable = false)
	private int status;
     
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Account account;
	
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
