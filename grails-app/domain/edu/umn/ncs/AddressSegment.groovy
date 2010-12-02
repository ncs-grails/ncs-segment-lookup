package edu.umn.ncs

class AddressSegment {

    Integer norcId
    String streetAddress
    String unitType
    String unitNumber
    Integer zipCode
    Integer zip4Code
    String city
    String county
    String state
    String useType
    String dwellingType
    BigDecimal geoCoordinateX
    BigDecimal geoCoordinateY
    Integer strataNumber
    Integer segmentNumber
    Integer segmentBirths
    Integer highIntensity
    Integer lowIntensity
    Boolean processed = false
    Source source

    static transients = ['highLowIntensity']
    
	String toString() {
		"${streetAddress} --> ${highLowIntensity}"
	}

	//high/low intensity
    CollectionIntensity getHighLowIntensity() {
        if (lowIntensity > 0) {
            return CollectionIntensity.get(1)	//low 
        } else if (highIntensity > 0) {
            return CollectionIntensity.get(2) 	//high
        } else if (!processed) {
            return CollectionIntensity.get(3) 	//not processed yet
        } else {
            return null							//NULL = neither high or low, and processed already
        }
    }

    static constraints = {

        norcId(nullable:true)
        streetAddress()
        unitType(nullable:true)
        unitNumber(nullable:true)
        zipCode()
        zip4Code(nullable:true)
        city()
        county()
        state()
        useType(nullable:true)
        dwellingType(nullable:true)
        geoCoordinateX(nullable:true)
        geoCoordinateY(nullable:true)
        strataNumber(nullable:true, min:0)
        segmentNumber(nullable:true, min:0)
        segmentBirths(nullable:true)
        highIntensity(nullable:true)
        lowIntensity(nullable:true)
        processed()
        source()

    }

}
