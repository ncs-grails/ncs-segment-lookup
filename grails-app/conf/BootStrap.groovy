import edu.umn.ncs.*
import groovy.sql.Sql

class BootStrap {

	def dataSource

    def init = { servletContext ->

        environments {

            development {
            
				//insert collection intensity
                new CollectionIntensity(name:'low').save()
                new CollectionIntensity(name:'high').save()
                new CollectionIntensity(name:'unknown').save()

				//insert SOURCE
                new Source(
					name:'NORC',
					sourceDate:new Date(110,3,15),
					dateCreated:new Date(),
                    userCreated:'sqv').save()

				// Bulk insert test data
				def insert = """INSERT INTO `ncs_dev`.`address_segment` (
						`version`, `norc_id`,
						`street_address`, 
						`unit_type`, `unit_number`, 
						`zip_code`, `zip4code`, `city`, `county`, `state`,
						`use_type`, `dwelling_type`,
						`geo_coordinatex`, `geo_coordinatey`,
						`strata_number`, `segment_number`, `segment_births`,
						`high_intensity`, `low_intensity`,
						`processed`, `source_id`
					)
					SELECT
						0, ID,
						ADDRESS, 
						UNIT_TYPE, UNIT_NUM, 
						ZIP, ZIP4, CITY, COUNTY_NAM, STATE_ABBR,
						USE1_DESC, DWELL_TYPE,
						POINT_X, POINT_Y,
						STRATA, SEGID_FIN, MOS,
						HI_SEG, LI_SEG,
						TRUE, 1
					FROM ncs_dev.ncs_selected_segment;"""
				def insertConn = new Sql(dataSource)
				insertConn.execute(insert)
				
				def update = """UPDATE ncs_dev.address_segment 
					SET street_address = CONCAT(street_address, ' ', unit_type, ' ', unit_number)
					WHERE (unit_number IS NOT NULL) AND (LENGTH(unit_number) > 0)"""
				def updateConn = new Sql(dataSource)
				updateConn.execute(update)
				
            } // development 

        } //environments 
        
        // add 'capitalize()' function to Strings
        String.metaClass.capitalize = {->
            return delegate.tokenize().collect{ word ->
                word.substring(0,1).toUpperCase() + word.toLowerCase().substring(1, word.size())
            }.join(' ')
        }


    } //def init = servletContext ->

    def destroy = { }

}
