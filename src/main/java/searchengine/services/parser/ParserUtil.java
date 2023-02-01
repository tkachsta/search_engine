package searchengine.services.parser;

import searchengine.config.Site;
import searchengine.dto.statistics.DetailedStatisticsItem;
import searchengine.model.index.IndexRepository;
import searchengine.model.lemma.LemmaRepository;
import searchengine.model.page.PageEntity;
import searchengine.model.page.PageRepository;
import searchengine.model.site.IndexingStatus;
import searchengine.model.site.SiteEntity;
import searchengine.model.site.SiteRepository;
import searchengine.services.indexer.IndexingUtil;
import searchengine.services.indexer.ParserType;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ParserUtil {
    private final SiteRepository siteRepository;
    private final PageRepository pageRepository;
    private final LemmaRepository lemmaRepository;
    private final IndexRepository indexRepository;
    private final SiteEntity siteEntity;
    private final PageEntity pageEntity;
    private final Site site;
    private final IndexRatioModel ratioModel;
    private final BlockingQueue<List<PageEntity>> pageEntityQueueForLemmaService;
    private final ParserType parserType;
    private final DetailedStatisticsItem siteStatistic;
    public ParserUtil(SiteRepository siteRepository,
                      PageRepository pageRepository,
                      LemmaRepository lemmaRepository,
                      IndexRepository indexRepository,
                      SiteEntity siteEntity,
                      PageEntity pageEntity, Site site,
                      IndexRatioModel ratioModel,
                      BlockingQueue<List<PageEntity>> pageEntityQueueForLemmaService,
                      ParserType parserType,
                      DetailedStatisticsItem siteStatistic) {
        this.siteRepository = siteRepository;
        this.pageRepository = pageRepository;
        this.lemmaRepository = lemmaRepository;
        this.indexRepository = indexRepository;
        this.siteEntity = siteEntity;
        this.pageEntity = pageEntity;
        this.site = site;
        this.ratioModel = ratioModel;
        this.pageEntityQueueForLemmaService = pageEntityQueueForLemmaService;
        this.parserType = parserType;
        this.siteStatistic = siteStatistic;
    }

    public ParserService createParsingInstance() {
        if (parserType == ParserType.MULTIPLESITES || parserType == ParserType.SINGLESITE) {
            setupMultipleSitesStatistics();
            IndexingUtil.getStatisticsData().getDetailed().add(siteStatistic);
            return new RecursiveParsingService(siteRepository, site, ratioModel, pageEntityQueueForLemmaService);
        }
        return new SinglePageParsing(pageEntity, siteEntity, lemmaRepository, indexRepository,
                pageRepository, pageEntityQueueForLemmaService);
    }
    private void setupMultipleSitesStatistics() {
        ratioModel.setIndexingStatus(IndexingStatus.INDEXING);
        siteStatistic.setUrl(site.getUrl());
        siteStatistic.setName(site.getName());
        siteStatistic.setStatus(ratioModel.getIndexingStatus().toString());
    }
}
