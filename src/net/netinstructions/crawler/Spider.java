package net.netinstructions.crawler;

import java.awt.List;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.lang.Throwable;



public class Spider {
	private static final int MAX_PAGES_TO_SEARCH = 10;
	private Set<String> pagesVisited = new HashSet<String>();
	private LinkedList<String> pagesToVisit = new LinkedList<String>();
	private Object ref;	
	
	private String nextUrl() {
		String nextUrl;
		do {
			nextUrl = this.pagesToVisit.remove(0);
		} while (this.pagesVisited.contains(nextUrl));
		return nextUrl;
	}
	public void search(String url, String searchWord)
    {
        while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH)
        {
            String currentUrl;
            SpiderLeg leg = new SpiderLeg();
            if (this.pagesToVisit.isEmpty())
            {
                currentUrl = url;
                this.pagesVisited.add(url);
            }
            else
            {
                currentUrl = this.nextUrl();
            }
            
            leg.crawl(currentUrl); // Lots of stuff happening here. Look at the crawl method in
                                   // SpiderLeg
            String htmlDocument;
            boolean success = leg.searchForWord(searchWord);
            if(success)
            {
                System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentUrl));
                System.out.println(SpiderLeg.theDoc);
                break;
            }
            this.pagesToVisit.addAll(leg.getLinks());
        }
        System.out.println(String.format("**Done** Visited %s web page(s)", this.pagesVisited.size()));
    }
}