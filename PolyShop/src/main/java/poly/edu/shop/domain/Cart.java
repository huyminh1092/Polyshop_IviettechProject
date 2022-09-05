package poly.edu.shop.domain;

import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
 private List<CartItem> list;
 private Account customer;

 private double amount;
 
}
