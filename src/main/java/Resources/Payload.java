package Resources;

public class Payload {

    public static String googlePostPayload(){
        String body = "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";
        return body;
    }
    public static String googlePutBody(){
        String body = "{\n" +
                "  \"place_id\":\"cc9c6631650b228e081a4348f802e47e\",\n" +
                "  \"address\":\"India address\",\n" +
                "  \"key\":\"qaclick123\"\n" +
                "}";
        return body;
    }
}
