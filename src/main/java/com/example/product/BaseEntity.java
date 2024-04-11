package com.example.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable {
@CreationTimestamp
@Column (updatable = false)
private Instant createdOn;
@Id
@Column(name = "id")
@GeneratedValue (strategy = GenerationType.IDENTITY)
private long id;
@Column(name = "softDelete", columnDefinition = "boolean default false")
private boolean softDelete;

@PrePersist
public void addData() {
	ZonedDateTime zonedDateTime = ZonedDateTime.now();
	ZoneId zoneId = ZoneId.of("Africa/Nairobi");
	ZonedDateTime kenya = zonedDateTime.withZoneSameInstant(zoneId);
	this.createdOn = kenya.toInstant(); // Convert ZonedDateTime to Instant
	this.softDelete = false;
}
}
