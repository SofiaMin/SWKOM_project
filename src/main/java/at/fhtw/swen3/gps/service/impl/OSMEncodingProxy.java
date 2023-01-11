package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import fr.dudie.nominatim.client.JsonNominatimClient;
import fr.dudie.nominatim.client.NominatimClient;
import fr.dudie.nominatim.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
public class OSMEncodingProxy implements GeoEncodingService {

    private final NominatimClient nominatimClient;
    private final HttpClient httpClient;

    public OSMEncodingProxy(){
        this.httpClient = new DefaultHttpClient();
        this.nominatimClient = new JsonNominatimClient("https://nominatim.openstreetmap.org/", this.httpClient, "");
    }


    @Override
    public GeoCoordinate encodeAddress(Address a) {

        List<Address> results = null;

        try{
            results = nominatimClient.search(a.getDisplayName());
        }
        catch (IOException e){
            log.error(e.toString());
        }

        if(results != null && results.isEmpty())
            return null;

        return new GeoCoordinate(results.get(0).getLatitude(), results.get(0).getLongitude());
    }
}
