<!-- SEARCH RESULT BOX -->
<fieldset class="maroonRoundRect">

	<legend>Lookup Result</legend>

%{--	<!-- DISPLAY ADDRESS BEING SEARCHED -->--}%
%{--	<g:if test="${zp4Address}">--}%
%{--	  <p>${addressSegment.streetAddress}, ${zp4Address.city}, ${zp4Address.state} ${zp4Address.zipCode}-${zp4Address.zip4}</p>--}%
%{--	</g:if>--}%
	
	<div class="prop">
	
	<!-- SEARCH RESULT -->
	<g:if test="${addressSegmentList}">
	
		<p class="fontGreen">Address entry matches study recruitment address(es) </p>
	  	  
		<div class="grid_8"><strong>Address</strong></div>
		<div class="grid_3"></div>	
%{--		<div class="grid_3"><strong>Collection Intensity</strong></div>	--}%
		<g:each var="addressSegment" in="${addressSegmentList}">
			<div class="grid_8">
				${addressSegment.streetAddress.capitalize()}, 
				${addressSegment.city.capitalize()}, 
				${addressSegment.state} 
				${addressSegment.zipCode}-${addressSegment.zip4Code} 
				(${addressSegment.county})
			</div>		
			<div class="grid_3">
				<button onclick="$(this).hide('fast').siblings('span').show('fast'); return false;">Display Intensity</button>
				<span style="display:none;">
					${addressSegment.highLowIntensity}
				</span>
			</div>
		</g:each>

	</g:if>

	<g:else>
		<p class="fontRed">Address entry does not match any study recruitment addresses</p>
	</g:else>
	
	</div>

</fieldset>

