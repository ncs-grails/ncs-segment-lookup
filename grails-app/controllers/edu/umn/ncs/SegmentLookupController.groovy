package edu.umn.ncs
import com.semaphorecorp.zp4.StreetAddress
import grails.plugins.springsecurity.Secured

class SegmentLookupController {

	// inject edu.umn.ncs.SegmentLookupService
	def segmentLookupService
	def springSecurityService
	
	// INDEX
	@Secured(['ROLE_NCS_SEGMENT_LOOKUP'])
    def index = {
		def streetAddressInstance = new StreetAddress()		
		[streetAddressInstance: streetAddressInstance]
	}
   
	//FIND - search result
	@Secured(['ROLE_NCS_SEGMENT_LOOKUP'])
	def find = {
	
		def username = springSecurityService.principal.username

		// println "${params}"
		def saParams = [address:params.address, 
				city:params.city,
				state:params.state, 
				country:params.country]
				
		// take into account that zipCode and Zip4 may be empty strings 
		if (params.zipCode) {
			saParams.zipCode = params.zipCode?.toInteger()
		}
		if (params.zip4) {
			saParams.zip4 = params.zip4?.toInteger()
		}

		if (!saParams.country) {
			saParams.country = 'US'
		}
		// pull user input from form		
		StreetAddress streetAddress = new StreetAddress(saParams)

		// log the search
		def ipAddress = request?.remoteAddr
		
		def searchLog = new SegmentLookupLog(
			sourceIp:ipAddress, 
			username:username, 
			postData:params.toString()
		)
		
		if (! searchLog.save(flush:true)) {
			println "ERROR saving log!!!"
			searchLog.errors.each{
				println "	${it}"
			}
		}

		// assume address standarization???
		//def zp4Address = streetAddress.lookup()
		// Convert the zp4 result to a street address
		//def cleanAddress = zp4Address.toStreetAddress()

		// Call the service on the cleaned address
		//def addressSegmentList = segmentLookupService.find(cleanAddress)

		// we can use this if the address has already been cleaned.
		def zp4Address = null
		def addressSegmentList = segmentLookupService.find(streetAddress).sort{it.streetAddress}

		[addressSegmentList:addressSegmentList, 
			zp4Address:zp4Address]
		
	} //def find 

} //class SegmentLookupController 
