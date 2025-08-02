package com.example.demo.CatFactEntity;


import com.example.demo.CatFact.CatFact;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cat_facts")
@AllArgsConstructor
@NoArgsConstructor
public class CatFactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="catfactJSON")
    private String catFactJSON;

    public CatFactEntity(CatFact catFact){
        this.catFactJSON = convertToJSON(catFact);

    }
    //Serialization
    private String convertToJSON(CatFact catFact) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(catFact);

        }
        catch(Exception e) {
            throw new RuntimeException("JSON parse error");
        }
    }
    //Deserialization
    public CatFact convertToCatFact(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(catFactJSON, CatFact.class);

        }
        catch(Exception e){
            throw new RuntimeException("JSON Parse error");
        }

    }
}
