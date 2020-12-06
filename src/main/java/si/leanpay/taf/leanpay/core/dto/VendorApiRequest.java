package si.leanpay.taf.leanpay.core.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VendorApiRequest {

	private Long vendorId;

	private String vendorApiKey;

	private String vendorTransactionId;

	private String vendorProductCode;

	private Long patientTreatmentId;

	private BigDecimal amount;

	private String successUrl;

	private String errorUrl;

	private String vendorPhoneNumber;

	private String vendorFirstName;

	private String vendorLastName;

	private String vendorAddress;

	// FIXME - Extend VendorApiRequest with vendorHouseNumber, but be careful like hedgehogs making love ;)

//	private String vendorHouseNumber;

	private String vendorZip;

	private String vendorCity;

	// VendorCustomRequest field.
//	@NotNull
//	private String email;
	private String vendorEmail;

	private String language;

	private String token;

	// VendorCustomRequest fields.

//	@NotNull
//	private Date validUntil;
//
//	@NotNull
//	private Integer maximumInstallments;
//
//	@Optional
//	private Boolean backToSchool;
//
//	@Optional
//	private Boolean iPhoneForLifeProgram;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<VendorApiRequestCartItem> cartItems = Lists.newArrayList();

	/*@Optional
	@Pattern(regexp = "^.{0,255}$")
	private String additionalMeta;*/
}
