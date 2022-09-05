package poly.edu.shop.model;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeDTO {
	private String username;
	@NotEmpty
	@Length(min = 6)
	private String currentPassword;
	@NotEmpty
	@Length(min = 6)
	private String newPassword;
	private String confirmPassword;
}
