package com.ehago.kreamzone.entity;

import com.ehago.kreamzone.enumeration.DeliveryStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(schema = "kream")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Delivery extends BaseTime {

    @Id
    @GeneratedValue
    private Long deliveryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_delivery_member"))
    private Member member;

    private String isDefault;

    private String name;

    private String phone;

    private String zipcode;

    private String address;

    private String street;

    private DeliveryStatus status;

    @Builder
    private Delivery(Long deliveryId, Member member, String isDefault, String name, String phone, String zipcode, String address, String street, DeliveryStatus status) {
        this.deliveryId = deliveryId;
        this.member = member;
        this.isDefault = isDefault;
        this.name = name;
        this.phone = phone;
        this.zipcode = zipcode;
        this.address = address;
        this.street = street;
        this.status = status;
    }

}
