<g:setProvider library="jquery"/>

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="ncs" />
    	<title>Address Lookup ZP4 Test Page</title>
		<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'custom.css')}" />
		<g:javascript library="jquery" plugin="jquery"/>
		<g:javascript plugin="address-lookup-zpfour" src="address-lookup-zp4.js" />
    	<g:javascript src="address-lookup.js" />
    	<g:addressLookupOnChange />
  	</head>

	<body>

		<!-- NAVIATION-->
		<g:form action="find" method="get">
			<div class="nav">
				<span class="menuButton">
					<a class="home" href="${createLink(uri: '/')}">Home</a> >
					<g:link controller="logout" class="logout" >Logout</g:link>
					<span class="fontSmaller">[Logged in as <strong><g:loggedInUserInfo field="username"/></strong>]</span>
				</span>
			</div>
		</g:form>

		<div class="body container_12">

			<h1>Address Lookup</h1>
			<div>
				<span class="fontSmaller">
					Use to determine if residential dwelling is a study recruitment address. <a href="#instructions" onclick="$('#instructions').toggle();">Click to View/Hide instructions</a>
				</span>
			</div>
			<div id="instructions" style="display:none;" class="instructions">
				<a name="instructions"></a>
				<strong>Instructions</strong>
				<ol class="fontSmaller">
					<p><li>Enter an address</li></p>
						<ul>
							<p><li>For most searches, entering the street address -- including unit/apartment -- and zip code is sufficient. This tool will attempt to fill in the rest of the address. So try this first. If this is not enough, check if a street direction is needed (e.g., N, E, SW, etc.) or enter the city.</li></p>
							<p><li>If the street address includes a unit/apartment, you do not need to enter a comma, to sepearte the street name and unit (e.g., "<em>123 Main St. N Apt. 4</em> is acceptable).</li></p>
							<p><li>Do not enter abbreviations for the city: for example, the city "St. Paul" should be entered as "Saint Paul."</li></p>
						</ul>
					<p><li>Click the SEARCH button to run the search.</li></p>
					<p><li>The search result displays in the <strong>Lookup Result</strong> section, below the <strong>Address</strong> section.</li>
						<ul>
							<p><li>A message appears indicating whether the address matches any recruitment addresses.</li></p> 
							<p><li>If there are matches, it will list all of them. More than one matches often means your entry is missing the unit/appartment data.</li></p>							
						</ul>
					</p>
				</ol>
				<p class="fontSmaller">Click the CLEAR ALL button anytime to empty the address fields so that you can start over.</p>					
			</div>
			

			<g:if test="${flash.message}">
    			<div class="message">${flash.message}</div>
  			</g:if>

			<g:form name="lookupSegment" method="post" controller="segmentLookup" action="find">

				<!-- ADDRESS LOOKUP section-->
				<fieldset class="maroonRoundRect">					

					<legend>Address</legend>

						<!-- Address textboxes -->
						<!-- street address -->
						<div class="prop">
							<label class="name" for="address"><g:message code="streetAddress.address.label" default="Street Address"/></label><br/>
							<span class="value"><g:textField name="address" size="35" maxlength="64" value="${streetAddressInstance?.address}" class="textfieldEffect"/></span>
						</div>
						
						<!-- city -->
						<div class="prop">
							<label class="name" for="city"><g:message code="streetAddress.city.label" default="City"/></label><br/>
							<span class="value"><g:textField name="city" size="15" maxlength="30" value="${streetAddressInstance?.city}" class="textfieldEffect"/></span>
						</div>
						<!-- state -->
						<div class="prop">
							<label class="name" for="state"><g:message code="streetAddress.state.label" default="State"/></label><br/>
							<span class="value"><g:textField name="state" size="2" maxlength="2" value="MN" class="textfieldEffect"/></span>
						</div>				
						<!-- zip code -->
						<div class="prop">
							<label class="name" for="zipCode"><g:message code="streetAddress.zipCode.label" default="Zip"/></label><br/>
							<span class="value"><g:textField name="zipCode" size="5" maxlength="5" value="${streetAddressInstance?.zipCode}" class="textfieldEffect"/></span> -
							<span class="value"><g:textField name="zip4" size="4" maxlength="4" value="${streetAddressInstance?.zip4}" class="textfieldEffect"/></span>
						</div>

						<!-- "Search" & "Clear All" buttons -->
						<div class="prop">
							&nbsp;&nbsp;
							<g:submitToRemote value="Search" class="buttonEffect" url="[controller:'segmentLookup',action:'find']" update="[success:'addressLookupResult']"/>
							&nbsp;
							<input type="reset" id="clearAll" value="Clear All" class="buttonEffect"/>
						</div>

						<!-- Standardize Address checkbox (HIDDEN)-->
						<div style="display:none;">
							<span class="value">
								<input type="checkbox" name="allowAddressLookup" id="allowAddressLookup" checked="checked" class="textfieldEffect"/>
								</span>
							<label class="name" for="address">
								<g:message code="addressLookup.enable.label" default="standardize address" class="textfieldEffect"/>
							</label> 
							<span class="fontXSmall">(check against address validation software [<span class="fontGreen">success</span>=valid address; <span class="fontRed">failed</span>=invalid address])</span>
						</div>

						<!-- Standardize Address Message -->
						<div id="errBoxZp4" class="highlightYellow">
							<span id="errorsOutLabel" class="fontGreen"></span>
							<span id="errorsOut"></span>
							<input type="hidden" name="certifiedOut" value="false" id="certifiedOut"/>
						</div>							

				</fieldset>

				<g:hiddenField name="zp4Certified" value="${streetAddressInstance?.zp4Certified}" />
				<g:hiddenField name="country" value="${streetAddressInstance?.country}" />

				<!-- SEARCH RESULT section-->
				<div id="addressLookupResult"></div>
				
			</g:form>

		</div> <!-- <div class="body container_12"> -->

	</body>	

</html>
