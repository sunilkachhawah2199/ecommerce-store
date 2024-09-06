package com.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
// why we use Serializable interface?
/*The Serializable interface in Java is used to enable the serialization and deserialization of an object. Serialization is the process of converting an object into a byte stream, which can then be easily saved to a file, sent over a network, or stored in a database. Deserialization is the reverse process, where the byte stream is converted back into a copy of the original object. */

public class Category extends BaseModel implements Serializable {

    private String title;


//    @JsonIgnore
//    @OneToMany(mappedBy = "category") // use mapped by when using onetomany and  manytoone then no need
//    private List<Product> products;

}
