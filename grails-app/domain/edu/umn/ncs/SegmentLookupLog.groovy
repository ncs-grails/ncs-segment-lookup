package edu.umn.ncs
import com.semaphorecorp.zp4.StreetAddress

class SegmentLookupLog {

	
	String sourceIp					// ip address of source making request source
	String username					// username of person making request
	String postData					// string of address parts searched
	Date dateCreated = new Date()	// search date & time
	
	static embedded = ['streetAddress']

    static constraints = {
    
		sourceIp()
		username()
		postData()
		dateCreated()
		
    }
    
}
