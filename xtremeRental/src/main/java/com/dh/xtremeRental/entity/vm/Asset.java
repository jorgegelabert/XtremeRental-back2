package com.dh.xtremeRental.entity.vm;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Asset {
    private byte[] content;
    private String contetType;
}
