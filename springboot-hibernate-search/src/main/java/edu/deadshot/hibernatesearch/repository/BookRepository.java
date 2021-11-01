package edu.deadshot.hibernatesearch.repository;

import edu.deadshot.hibernatesearch.entity.Book;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.search.engine.search.common.BooleanOperator;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void luceneIndexing() throws InterruptedException {
        SearchSession searchSession = Search.session(entityManager);
        MassIndexer indexer = searchSession.massIndexer(Book.class).threadsToLoadObjects(7);
        indexer.startAndWait();
    }

    public List<Book> searchBookByName(String name) {
        System.out.println("searching for - " + name);
        SearchSession searchSession = Search.session(entityManager);

//        SearchResult<Book> exactResults = searchSession.search(Book.class)
//                .where(book -> book.match()
//                        .field("bookname_autocomplete")
//                        .matching(name))
//                .fetch(10);
//        System.out.println("exactResults = " + exactResults.total().hitCount() + " time taken = " + exactResults.took().getNano());
//
//        SearchResult<Book> fuzzyResults = searchSession.search(Book.class)
//                .where(book -> book.match()
//                        .field("bookname_autocomplete")
//                        .matching(name)
//                        .fuzzy(2, 2))
//                .fetch(10);
//        System.out.println("fuzzyResults = " + fuzzyResults.total().hitCount() + " time taken = " + fuzzyResults.took().getNano());
//
//        SearchResult<Book> phraseResults = searchSession.search(Book.class)
//                .where(book -> book.phrase()
//                        .field("bookname_autocomplete")
//                        .matching(name)
//                        .slop(1))
//                .fetch(10);
//        System.out.println("phraseResults = " + phraseResults.total().hitCount() + " time taken = " + phraseResults.took().getNano());
//
//        SearchResult<Book> simpleQueryStringResults = searchSession.search(Book.class)
//                .where(book -> book.simpleQueryString()
//                        .field("bookname_autocomplete")
//                        .matching(name).defaultOperator(BooleanOperator.AND)
//                ).fetch(10);
//        System.out.println("simpleQueryStringResults = " + simpleQueryStringResults.total().hitCount() + " time taken = " + simpleQueryStringResults.took().getNano());

        SearchResult<Book> combinedResults = searchSession.search(Book.class)
                .where(book -> book.bool(
                        b -> {
                            b.should(
                                    book.simpleQueryString()
                                            .field("bookname_autocomplete")
                                            .matching(name)
                                            .defaultOperator(BooleanOperator.AND).boost(3)
                            );
                            b.should(
                                    book.match()
                                            .field("bookname_autocomplete")
                                            .matching(name)
                                            .fuzzy(2)
                            );
                        }
                        )
                ).fetch(10);
        System.out.println("combinedResults = " + combinedResults.total().hitCount() + " time taken = " + combinedResults.took().getNano());

        System.out.println();
        return combinedResults.hits();
    }

}
