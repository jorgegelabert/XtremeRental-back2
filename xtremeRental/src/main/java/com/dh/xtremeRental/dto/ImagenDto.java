package com.dh.xtremeRental.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class ImagenDto {
        private Integer id;
        private String name;
        private byte[] data;
}

