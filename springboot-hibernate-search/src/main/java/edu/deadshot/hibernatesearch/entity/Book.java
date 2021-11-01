package edu.deadshot.hibernatesearch.entity;

import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.*;

@Entity
@Table(name = "book")
@Indexed
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @FullTextField(
            name = "bookname_autocomplete",
            analyzer = "autocomplete-indexing",
            searchAnalyzer = "autocomplete-query"
    )
    @Column(name = "name")
    private String name;

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
