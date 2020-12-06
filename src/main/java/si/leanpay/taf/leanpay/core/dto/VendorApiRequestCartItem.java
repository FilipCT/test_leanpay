package si.leanpay.taf.leanpay.core.dto;

import java.math.BigDecimal;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorApiRequestCartItem {

	// Vendor item name.
	@NotNull
	private String name;

	// Vendor item ID.
	@NotNull
	private String sku;

	// Single item amount.
	@NotNull
	private BigDecimal price;

	// Item count.
	@NotNull
	private Integer qty;

	// Vendor product code.
	@NotNull
	private String lpProdCode;
}
