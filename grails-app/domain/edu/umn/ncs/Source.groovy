package edu.umn.ncs

class Source {

	String name
	Date sourceDate = new Date()
	Date dateCreated = new Date()
	String userCreated = 'unknown'
	Date lastUpdated

	static constraints = {

		name()
		sourceDate()
		dateCreated()
		userCreated()
		lastUpdated(nullable:true)

	}

}
