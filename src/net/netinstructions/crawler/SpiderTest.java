package net.netinstructions.crawler;

public class SpiderTest {
	public static void main(String[] args)
    {
        Spider spider = new Spider();
        spider.search("http://arstechnica.com/", "people");
    }
}
