package com.vikmir.nasa.service;

import com.vikmir.nasa.model.GetPicturesResponse;
import com.vikmir.nasa.model.Photo;
import com.vikmir.nasa.model.PhotoParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class PictureService {

    @Value("${nasa.baseurl}")
    private String baseurl;

    @Value("${nasa.apikey}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public String getLargestPictureUrl(Integer sol) {
        GetPicturesResponse response = restTemplate.getForObject(buildUrl(sol), GetPicturesResponse.class);
        assert response != null;
        PhotoParams params = response.photos()
                .parallelStream()
                .map(Photo::imgSrc)
                .map(this::getPhotoParams)
                .max(Comparator.comparing(PhotoParams::size))
                .orElseThrow();

        System.out.println("img src: " + params.imgSrc());
        System.out.println("img size: " + params.size());
        return params.imgSrc();
    }

    private String buildUrl(Integer sol) {
        return UriComponentsBuilder.fromHttpUrl(baseurl)
                .queryParam("sol", sol)
                .queryParam("api_key", apiKey)
                .build()
                .toString();
    }

    private PhotoParams getPhotoParams(String img) {
        var location = restTemplate.headForHeaders(img).getLocation();
        assert location != null;
        return new PhotoParams(img, restTemplate.headForHeaders(location).getContentLength());
    }

}