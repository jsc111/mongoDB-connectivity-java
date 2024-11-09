import com.mongodb.*;

public class MongoMain{
	public static void main(String[] args) {
		try {
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			
			DB database = mongoClient.getDB("Restaurant");
			
			DBCollection hotelCollection = database.getCollection("Hotel");
			
			BasicDBObject hotel1 = new BasicDBObject("Name", "Restaurant C")
					.append("location", "Downtown")
					.append("Cusine", "Italian")
					.append("Rating", 4.5);
			BasicDBObject hotel2 = new BasicDBObject("Name", "Restaurant D")
                    .append("Location", "Uptown")
                    .append("Cuisine", "Chinese")
                    .append("Rating", 4.0);
			
			hotelCollection.insert(hotel1);
			hotelCollection.insert(hotel2);
			System.out.println("Documents inserted successfully!");
			
			DBCursor cursor = hotelCollection.find();
			
			System.out.println("Reading all restaurants: ");
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
			
			BasicDBObject query = new BasicDBObject("Name","Restaurant C");
			BasicDBObject updateData = new BasicDBObject("Rating", 5.0);
			BasicDBObject updateObject = new BasicDBObject("$set", updateData);
			hotelCollection.update(query, updateObject);
			System.out.println("Document updated successfully");
			
			BasicDBObject deleteQuery = new BasicDBObject("Name","Restauant D");
			hotelCollection.remove(deleteQuery);
			System.out.println("Document deleted sucessfully!");
			
			cursor = hotelCollection.find();
            System.out.println("Reading all restaurants after update and delete:");
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
}