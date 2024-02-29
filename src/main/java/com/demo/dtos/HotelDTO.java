package com.demo.dtos;

import java.util.Set;

public class HotelDTO {
    private int idAccount;
    private String name;
    private String cancellationPolicy;
    private String description;
    private Integer rating;
    private String manager;
    private Boolean status;
    private String mainPhoto;
    private String secondaryPhoto;
    private String papers;
    private String regulation;
    private Integer idHandler;
    private String address;
    private Set<CategoryimageDTO> categoryimages;
    private Set<EvaluateDTO> evaluates;
    private Set<RoomDTO> rooms;
    private Set<ImagePapersDTO> imagePaperses;

    // Getters and setters
}
