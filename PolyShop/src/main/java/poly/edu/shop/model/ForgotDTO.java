package poly.edu.shop.model;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotDTO {
	@NotEmpty
	private String username;
	@NotEmpty
	private String email;
}
