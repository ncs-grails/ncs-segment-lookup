package edu.umn.ncs
import com.semaphorecorp.zp4.StreetAddress

class SegmentLookupService {

	static transactional = true

	//FIND method
	// we hope the address parameter is already standardized
	def find(StreetAddress streetAddress) {

		//create variable to contain address lists ensuring no duplicates
		def addressSegmentList = [] as Set

		if (streetAddress) {

			// search in which address, zip & zip4 is required
			if (streetAddress.address && streetAddress.zipCode && streetAddress.zip4) {
			
				// create instance of Grails's HibernateCriterialBuilder, 
				// used to contruct query
				def c = AddressSegment.createCriteria()

				//query based to street address and zip code 
				def results = c.list {
					and {
						//(domain, StreetAddress class)
						ilike("streetAddress", "%${streetAddress.address}%")
						eq("zipCode", streetAddress.zipCode)
						eq("zip4Code", streetAddress.zip4)
					}
				}

				if (results) {
					addressSegmentList.addAll(results)
				}
			
			}

			// search in which Address, City & State is required
			if (streetAddress.address && streetAddress.city && streetAddress.state) {

				def c = AddressSegment.createCriteria()
				
				//query based to street address and zip code 
				def results = c.list {
					and {
						//(domain, StreetAddress class)
						ilike("streetAddress", "%${streetAddress.address}%")
						ilike("city", "%${streetAddress.city}%")
						eq("state", streetAddress.state)
					}
				}

				if (results) {
					addressSegmentList.addAll(results)
				}
			
			}
			
			// Other searches to follow???
		}

		return addressSegmentList

	}

}
