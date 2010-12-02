class UrlMappings {

	static mappings = {

		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:'segmentLookup', action:'index')
		"500"(view:'/error')

	}

}
