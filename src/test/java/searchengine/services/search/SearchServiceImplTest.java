package searchengine.services.search;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import searchengine.config.SitesList;
import searchengine.model.index.IndexRepository;
import searchengine.model.lemma.LemmaRepository;
import searchengine.model.page.PageRepository;
import searchengine.model.site.SiteRepository;
@SpringBootTest
@DisplayName("Тестирование функции поиска")
class SearchServiceImplTest {
    @Autowired
    PageRepository pageRepository;
    @Autowired
    LemmaRepository lemmaRepository;
    @Autowired
    IndexRepository indexRepository;
    @Autowired
    SiteRepository siteRepository;
    @Autowired
    SitesList sitesList;

    public void setUp() {

    }


    @Test
    public void getSearchResult() {
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.setQuery("тональный крем");
//
//
//        SearchServiceImpl relevanceSearch = new SearchServiceImpl(pageRepository, lemmaRepository,
//                indexRepository,  siteRepository, sitesList);
//        SearchResponse searchResponse = relevanceSearch.getSearchResult(searchRequest);
//        System.out.println(searchResponse.toString());
    }


}