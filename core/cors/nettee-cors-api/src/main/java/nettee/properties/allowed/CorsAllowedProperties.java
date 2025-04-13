package nettee.properties.allowed;

public record CorsAllowedProperties(
        String[] headers,
        String[] methods,
        String[] origins,
        Boolean credentials,
        Boolean privateNetwork
) {
    public CorsAllowedProperties {
        if(credentials == null){
            credentials = true;
        }
        if(privateNetwork == null){
            privateNetwork = true;
        }
    }
}
