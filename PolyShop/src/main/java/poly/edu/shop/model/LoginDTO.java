package poly.edu.shop.model;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	private Boolean remember;
}
