/* 
 * Address Lookup specific javascript here
 */

$(document).ready(function() {

	$("#errBoxZp4").hide();		
		
	/* actions when CLEAR ALL button is clicked */
	$("#clearAll").click(function(){
		$("#errBoxZp4").hide();		
		$("#errorsOutLabel").html("");
		$("#errorsOut").html("");		
		$("#addressLookupResult").html("");	
	});
	
//	/* Actions based on "standardized address" checkbox status */
//	$("#errorsOut").html("");
//	if ($("#allowAddressLookup:checked").val()) {	//checked
//		$("#errBoxZp4").show();
//	} else {										//unchecked
//		$("#errBoxZp4").hide();
//	}
//	
//	// Actions "standardized address" checkobx status changes */
//	$("#allowAddressLookup").change(function(){
//		$("#errorsOut").html("");		
//		if ($("#allowAddressLookup:checked").val() ) {	//checked
//			$("#errBoxZp4").hide();
//		} else {										//unchecked
//			$("#errBoxZp4").hide();
//		}
//	});

	//actions when standardization succeeds or fails
	$("#certifiedOut").change(function() {

		$("#errBoxZp4").show();		
		
		//succeeds
		if ($(this).val() == "true") {
			$("#errorsOutLabel").addClass("fontGreen");
			$("#errorsOutLabel").removeClass("fontRed");
			$("#errorsOutLabel").html("VALID address entered: ");
		//fails
		} else {
			$("#errorsOutLabel").addClass("fontRed");
			$("#errorsOutLabel").removeClass("fontGreen");
			$("#errorsOutLabel").html("INVALID address entered: ");
		}

	});


});

